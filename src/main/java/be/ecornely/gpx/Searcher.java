package be.ecornely.gpx;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.ecornely.gpx.data.Geocache;
import be.ecornely.gpx.util.DateDeserializer;

public class Searcher implements Serializable {
	private static final long serialVersionUID = 1L;

	private Set<CacheListener> cacheListeners = new HashSet<>();
	
	private Integer maxIndex = null;
	private Integer lastIndex = null;

	private Downloader downloader;

	private float latitude;

	private float longitude;

	private String distance;
	
	public Searcher() {
	}
	
	public Searcher(Downloader downloader, float latitude, float longitude, String distance) {
		this.downloader = downloader;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
	}
	
	public void startSearch() throws IOException{
		parse(downloader.performSearch(latitude, longitude, 0, distance));
		while(this.lastIndex < this.maxIndex){
			int lastIndexBefore = this.lastIndex;
			parse(downloader.performSearch(latitude, longitude, this.lastIndex+1, distance));
			if(this.lastIndex < this.maxIndex && lastIndexBefore==this.lastIndex) throw new RuntimeException("Parsing could not make lastIndex("+lastIndex+") evolve from lastIndexBefore ("+lastIndexBefore+") with maxIndex ("+maxIndex+")");
		}
	}
	
	public void parse(String pageContent) throws IOException {
		if(LoggerFactory.getLogger(this.getClass()).isDebugEnabled()){
			try {
				String file = String.format("tests-output/pageContent_%1$tF_%1$tH-%1$tM-%1$tS_%1$ts.txt", Calendar.getInstance());
				LoggerFactory.getLogger(this.getClass()).debug("Parsing content saved in {} ("+this.lastIndex+"/"+this.maxIndex+")", file);
				IOUtils.write(pageContent, new FileOutputStream(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(maxIndex==null){
			Document document = Jsoup.parse(pageContent);
			maxIndex = Integer.parseInt(document.select("div.search-results-header > h2 > span:last-child").text().replaceAll("[,\\.]", ""))-1;
			this.readTableRows(document.select("table#searchResultsTable > tbody#geocaches > tr[data-rownumber]"));
		}else{
			LoggerFactory.getLogger(this.getClass()).info("Parsed "+this.lastIndex+" out of "+this.maxIndex+" search results");
			String rows = new ObjectMapper().readTree(pageContent).get("HtmlString").asText();
			Document document = Jsoup.parseBodyFragment("<table>"+rows+"</table>");
			this.readTableRows(document.select("tr[data-rownumber]"));
		}
	}

	private void readTableRows(Elements rows) {
		Iterator<Element> iterator = rows.iterator();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			boolean isPremim = element.hasAttr("data-premium");
			Geocache g = new Geocache();
			try {
				if(isPremim){
					g.setPremium(true);
				}else{
					g.setUri(element.select("a").attr("href"));
					g.setSize(this.getDataColumn(element, "ContainerSize").text());
					g.setDifficulty(Float.parseFloat(this.getDataColumn(element, "Difficulty").text()));
					g.setTerrain(Float.parseFloat(this.getDataColumn(element, "Terrain").text()));
					g.setFavoritePoint(Integer.parseInt(this.getDataColumn(element, "FavoritePoint").text()));
					g.setLastVisited(DateDeserializer.parseDate(this.getDataColumn(element, "DateLastVisited").text().replaceAll("^\\s*", "").replaceAll("\\s*$", "")));
					g.setPlaceDate(DateDeserializer.parseDate(this.getDataColumn(element, "PlaceDate").text().replaceAll("^\\s*", "").replaceAll("\\s*$", "")));
				}
				g.setCode(element.select("span.cache-details").text().replaceAll(".*\\| ", "").replaceAll("\\s*$", ""));
				g.setName(element.select("span.cache-name").text());
				g.setOwner(element.select("span.owner").text().replaceAll("by ", ""));
				g.setType(element.select("td img").attr("alt"));
			} catch (Exception e) {
				throw new RuntimeException("Unable to parse cache :"+element, e);
			}
			
			this.notifyCache(g);
			
			if(!iterator.hasNext()){
				this.lastIndex = Integer.parseInt(element.attr("data-rownumber"));
			}
		}
		
	}

	private void notifyCache(Geocache g) {
		for(CacheListener l : this.cacheListeners){
			l.newCache(g);
		}
	}

	private Elements getDataColumn(Element element, String dataColumnName) {
		Elements select = element.select("td[data-column]");
		/*Use of lambda expressions instead of 
		select.removeIf(new Predicate<Element>() {
			@Override
			public boolean test(Element t) {
				return ! t.attr("data-column").equals(dataColumnName);
			}
		});
		 */
		select.removeIf( (t) ->  ! t.attr("data-column").equals(dataColumnName) );
		return select;
	}


	public void addCacheListener(CacheListener listener){
		this.cacheListeners.add(listener);
	}
	
	public void removeCacheListener(CacheListener listener){
		this.cacheListeners.remove(listener);
	}

	public Downloader getDownloader() {
		return downloader;
	}

	public void setDownloader(Downloader downloader) {
		this.downloader = downloader;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Set<CacheListener> getCacheListeners() {
		return cacheListeners;
	}

	public void setCacheListeners(Set<CacheListener> cacheListeners) {
		this.cacheListeners = cacheListeners;
	}
	
	
}
