package be.ecornely.gpx;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.LoggerFactory;

import be.ecornely.gpx.configuration.Configuration;
import be.ecornely.gpx.configuration.DefaultConfiguration;

/**
 * <p>This class can download HTML pages from www.geocaching.com</p>
 * <p>On creation, it is using the {@link DefaultConfiguration} class to fill the cookies information of its' HTTPClient</p>
 */
public class GCDownloader {

	private CloseableHttpClient client;
	private CookieStore cookieStore;
	private Configuration gcConfiguration;

	/**
	 * Constructor initilializing a GCDownloader with the HTTPClient default Configuration
	 * */
	public GCDownloader(Configuration gcConfiguration) {
		this.gcConfiguration = gcConfiguration;
		cookieStore = getDefaultCookieStore();
		client = HttpClients.custom().setDefaultCookieStore(cookieStore)
				.setUserAgent("Mozilla/5.0 (Windows NT 6.2; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0")
				.setRedirectStrategy(new LaxRedirectStrategy()).build();
	}

	private CookieStore getDefaultCookieStore() {
		BasicCookieStore cookieStore = new BasicCookieStore();
		List<Configuration.Cookie> cookies = gcConfiguration.getCookies();
		if(cookies != null && cookies.size()>0){
			Iterator<Configuration.Cookie> iterator = cookies.iterator();
			while (iterator.hasNext()) {
				Configuration.Cookie cookie = (Configuration.Cookie) iterator.next();
				cookieStore.addCookie(createGCCookie(cookie.getKey(), cookie.getValue()));
			}
		}
		return cookieStore;
	}

	private BasicClientCookie createGCCookie(String key, String value) {
		BasicClientCookie cookie = new BasicClientCookie(key, value);
		cookie.setDomain("www.geocaching.com");
		cookie.setPath("/");
		cookie.setExpiryDate(Date.from(
				LocalDate.now().plus(15, ChronoUnit.DAYS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		return cookie;
	}

	/**
	 * <p>Read the HTML content of a geocahe page based on it's GC code</p>
	 * <p>This will make a GET request to http://www.geocaching.com/geocache/{gcCode} and return the HTML content</p>
	 * */
	public String getCachePage(String gcCode) throws URISyntaxException, IOException {
		String pageContent = null;
		HttpGet get = new HttpGet("http://www.geocaching.com/geocache/" + gcCode);
		pageContent = executeMethod(get);

		return pageContent;
	}

	private String executeMethod(HttpUriRequest request) throws IOException {
		String pageContent = null;
		try (CloseableHttpResponse response = client.execute(request)) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				pageContent = EntityUtils.toString(entity);
			} else {
				throw new RuntimeException("Entity was null");
			}
		}
		return pageContent;
	}

	/**
	 * <p>
	 * Check that the current the client has correct Cookie information to
	 * access the profile page of a user
	 * </p>
	 * <p>
	 * This is done by a GET to the login page (with redirect to profile in the
	 * parameters).<br/>
	 * If automatically redirected by the server to the profile page, nothing
	 * special is done
	 * </p>
	 * <p>
	 * Else a POST of the login form is done with the user credentials.
	 * </p>
	 */
	public void ensureLoggedIn() throws IOException {
		String pageContent = executeMethod(
				new HttpGet("https://www.geocaching.com/login/default.aspx?RESETCOMPLETE=y&redir=/profile"));
		Document document = Jsoup.parse(pageContent);
		String title = document.select("head title").text();
		String viewstate = document.select("input[name='__VIEWSTATE']").val();
		String viewstateGenerator = document.select("input[name='__VIEWSTATEGENERATOR]").val();
		if (!title.contains("Profile for User")) {
			LoggerFactory.getLogger(this.getClass()).info("ensureLoggedIn() - The profile page is not accessible will try to POST credentials");
			HttpPost post = new HttpPost(
					"https://www.geocaching.com/login/default.aspx?RESETCOMPLETE=Y&redir=http://www.geocaching.com/profile/default.aspx?");
			List<NameValuePair> parameters = Arrays.asList(new BasicNameValuePair("__EVENTTARGET", ""),
					new BasicNameValuePair("__EVENTARGUMENT", ""), new BasicNameValuePair("__VIEWSTATE", viewstate),
					new BasicNameValuePair("__VIEWSTATEGENERATOR", viewstateGenerator),
					new BasicNameValuePair("ctl00$ContentBody$tbUsername", this.gcConfiguration.getUsername()),
					new BasicNameValuePair("ctl00$ContentBody$tbPassword", this.gcConfiguration.getPassword()),
					new BasicNameValuePair("ctl00$ContentBody$cbRememberMe", "on"),
					new BasicNameValuePair("ctl00$ContentBody$btnSignIn", "Sign In"));
			post.setEntity(new UrlEncodedFormEntity(parameters));
			pageContent = executeMethod(post);
			title = Jsoup.parse(pageContent).select("head title").text();
			if (!title.contains("Profile for User")) {
				LoggerFactory.getLogger(this.getClass()).warn("ensureLoggedIn() - After the credential submit the profile page is still not accessible");
				// TODO throw a real exception and have a clear message
				throw new RuntimeException("After submitting credentials the page was not recognized");
			}else{
				ArrayList<DefaultConfiguration.Cookie> configCookies = new ArrayList<>();
				Iterator<Cookie> iterator = cookieStore.getCookies().iterator();
				while (iterator.hasNext()) {
					Cookie cookie = (Cookie) iterator.next();
					configCookies.add(new DefaultConfiguration.Cookie(cookie.getName(), cookie.getValue()));
				}
				DefaultConfiguration.getInstance().setCookies(configCookies);
			}
		}else{
			LoggerFactory.getLogger(this.getClass()).info("ensureLoggedIn() - The profile page is accessible skipped");
		}
	}
	
	String performSearch(float latitude, float longitude, int startIndex, String radius) throws IOException{
		String pageContent = null;
		HttpGet get = null;
		if(startIndex==0){
			get = new HttpGet("https://www.geocaching.com/play/search/@"+latitude+","+longitude+"?origin="+latitude+","+longitude+"&radius="+radius);
		}else{
			get = new HttpGet("https://www.geocaching.com/play/search/more-results?startIndex="+startIndex+"&inputOrigin="+latitude+","+longitude+"&sortOrigin="+latitude+"%2C"+longitude+"&fbu=false&filteredByOtherUsersFinds=false&originTreatment=0");
		}
		pageContent = executeMethod(get);
		return pageContent;
		
	}
}
