package be.ecornely.gpx;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.ecornely.gpx.data.Geocache;
import be.ecornely.gpx.data.Log;
import be.ecornely.gpx.data.Trackable;
import be.ecornely.gpx.data.Waypoint;
import be.ecornely.gpx.util.DateDeserializer;
import be.ecornely.gpx.util.Rot13;

public class CachePageParser {
	private static Pattern patternCacheId = Pattern
			.compile("\"CacheID\":(\\d+)");
	private static Pattern patternDifficulty = Pattern
			.compile("Difficulty:</dt>[\\s\\n]*<dd>[\\s\\n]*<span.*?<img src=\"http://www.geocaching.com/images/stars/.*\\.gif\" alt=\"(\\d+(:?\\.\\d+)?)\\s*out of 5\"");
	private static Pattern patternTerrain = Pattern
			.compile("Terrain:</dt>[\\s\\n]*<dd>[\\s\\n]*<span.*?<img src=\"http://www.geocaching.com/images/stars/.*\\.gif\" alt=\"(\\d+(:?\\.\\d+)?)\\s*out of 5\"");
	private static Pattern patternLatLonDM = Pattern.compile("([NS])\\s*(\\d+)\u00B0?\\s*(\\d+.\\d+)\\s*([EW])\\s*(\\d+)\u00B0?\\s*(\\d+.\\d+)");
	private static Pattern patternLatLon = Pattern
			.compile("var lat=(\\d+(?:\\.\\d+)?), lng=(\\d+(?:\\.\\d+)?)");
	private static Pattern patternOwner = Pattern
			.compile("<a href=\"/seek/nearest\\.aspx\\?u=(.*?)\">hidden</a>");
	private static Pattern patternInitialLogs = Pattern.compile("initalLogs.*\"data\": (\\[.*\\])");
	
	private String pageContent;
	private Document document;
	private Geocache geocache = new Geocache();

	public CachePageParser(String pageContent) {
		this.pageContent = pageContent;
		this.document = Jsoup.parse(pageContent);
		try{
			this.parse();
		}catch(Throwable t){
			if(LoggerFactory.getLogger(this.getClass()).isDebugEnabled()){
				String code = this.geocache.getCode();
				if(code==null || code.length()==0){
					code="unknown";
				}
				LoggerFactory.getLogger(this.getClass()).debug("A problem occured while parsing HTML from "+code);
				String filename = "tests-output/"+code+".html";
				LoggerFactory.getLogger(this.getClass()).debug("The content pageContent will be saved under "+filename);
				try {
					IOUtils.write(pageContent, new FileOutputStream(filename));
				} catch (IOException e) {
				}
				
			}
			throw t;
		}
	}

	public Geocache getGeocache() {
		return this.geocache;
	}

	private void parse() {
		
		if(document.select("head title").text().contains("Premium Member Only Cache")){
			geocache.setPremium(true);
			geocache.setCode(readPremiumCode());
			geocache.setName(readName());
			geocache.setDifficulty(readPremiumDifficulty());
			geocache.setTerrain(readPremiumTerrain());
			geocache.setOwner(readPremiumOwner());
			geocache.setSize(readPremiumSize());
			
		}else{
			geocache.setCode(readCode());
			geocache.setName(readName());
			geocache.setDifficulty(readDifficulty());
			geocache.setTerrain(readTerrain());
			geocache.setOwner(readOwner());
			
			geocache.setSize(readSize());
			geocache.setType(readType());
			geocache.setCacheId(readCacheId());
			geocache.setDescription(readDescription());
			float[] latlon = readLatLon();
			geocache.setLatitude(latlon[0]);
			geocache.setLongitude(latlon[1]);
			geocache.setLatlonDM(readLatLonDM());
			geocache.setUri(readUri());
			geocache.setHint(Rot13.applyTo(readHints()));
			geocache.setInitialLogs(readInitialLogs());
			geocache.setWaypoints(readWaypoints());
			geocache.setFavoritePoint(readFavoritePoint());
			geocache.setPlaceDate(readPlaceDate());
			geocache.setLastVisited(readLastVisited());
			geocache.setTrackables(readTrackables());
			geocache.setFoundCount(readFoundCount());
		}		

	}

	private int readFoundCount() {
		Elements images = document.select("p.LogTotals img[title]");
		if(images.size()>0){
			images.removeIf((t) -> {return ! t.attr("title").equals("Found it");});
			if(images.size()==1){
				Node nextSibling = images.get(0).nextSibling();
				if(nextSibling instanceof TextNode){
					TextNode count = (TextNode) nextSibling;
					String foundCount = count.text().replaceAll("[\u00A0 ,\\.]", "");
					Integer.parseInt(foundCount);
				}
			}
		}
		return 0;
	}

	private List<Trackable> readTrackables() {
		if(document.select("div#ctl00_ContentBody_uxTravelBugList_uxNoTrackableItems").size()>0){
			return null;
		}else{
			ArrayList<Trackable> trackables = new ArrayList<>();
			Elements widgets = document.select("div.CacheDetailNavigationWidget");
			widgets.removeIf( (t) -> {return ! t.select("h3.WidgetHeader span").text().equals("Inventory");});
			
			Iterator<Element> iterator = widgets.select("div.WidgetBody ul li").iterator();
			while (iterator.hasNext()) {
				Element li = (Element) iterator.next();
				String guid = li.select("a").attr("href").replaceAll(".*guid=", "");
				String name = li.select("a span").text();
				trackables.add(new Trackable(guid, name, this.geocache.getCode()));
			}
			return trackables;
		}
	}

	private String readPremiumSize() {
		return document.select("p.PMCacheInfoSpacing img").get(0).attr("alt").replaceAll("Size: ", "");
	}

	private String readPremiumOwner() {
		return document.select("span#ctl00_ContentBody_uxCacheType").text().replaceAll("A cache by ", "");
	}

	private float readPremiumTerrain() {
		String src = document.select("p.PMCacheInfoSpacing img").get(2).attr("src");
		Matcher matcher = Pattern.compile(".*/images/stars/stars(\\d.*)\\.gif").matcher(src);
		if(matcher.matches()){
			return Float.parseFloat(matcher.group(1).replaceAll("_", "."));
		}
		return -1;
	}

	private float readPremiumDifficulty() {
		String src = document.select("p.PMCacheInfoSpacing img").get(1).attr("src");
		Matcher matcher = Pattern.compile(".*/images/stars/stars(\\d.*)\\.gif").matcher(src);
		if(matcher.matches()){
			return Float.parseFloat(matcher.group(1).replaceAll("_", "."));
		}
		return -1;
	}

	private String readPremiumCode() {
		Matcher matcher = Pattern.compile(".*\\((GC.*)\\)").matcher(document.select("div#ctl00_divContentMain h2").text());
		if(matcher.matches()){
			return matcher.group(1);
		}
		return null;
	}

	private Date readLastVisited() {
		return DateDeserializer.parseDate(document.select("span.LogDate").eq(0).text().replaceAll("\\s*Hidden\\s*:\\s*", "").replaceAll("\\s*$", ""));
	}

	private Date readPlaceDate() {
		return DateDeserializer.parseDate(document.select("#ctl00_ContentBody_mcd2").text().replaceAll("\\s*Hidden\\s*:\\s*", "").replaceAll("\\s*$", ""));
	}

	private Integer readFavoritePoint() {
		Elements favorite = document.select("span.favorite-value");
		if(favorite.size()>0){
			return Integer.parseInt(favorite.text());
		}else{
			return 0;
		}
	}

	private List<Waypoint> readWaypoints() {
		Elements table = document.select("table#ctl00_ContentBody_Waypoints");
		Elements lines = table.select("tr");
		List<Waypoint> wpList = new ArrayList<>();
		Iterator<Element> linesIterator = lines.iterator();
		Waypoint wp=null;
		while(linesIterator.hasNext()) {
			Element line = linesIterator.next();
			Elements normalCells = line.select("td");
			if(!normalCells.isEmpty()){
				if(normalCells.size()==8){
					wp = new Waypoint();
					wpList.add(wp);
					wp.setType(normalCells.get(2).select("img").attr("title"));
					wp.setPrefix(normalCells.get(3).text());
					wp.setLookup(normalCells.get(4).text());
					wp.setName(normalCells.get(5).text());
					wp.setCoords(normalCells.get(6).text().replaceAll("\u00A0", ""));
				}
				if(normalCells.size()==3){
					if(wp!=null) wp.setNote(normalCells.get(2).text());
				}
			}
		}
		return wpList;
	}

	private List<Log> readInitialLogs() {
		Matcher matcher = patternInitialLogs.matcher(pageContent);
		if (matcher.find()) {
			List<Log> list = new ArrayList<>();
			String initialJson = matcher.group(1);
			//LoggerFactory.getLogger(this.getClass()).debug("Parsing initalLogs json :"+initialJson);
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				JsonNode data = objectMapper.readTree(initialJson);
				Iterator<JsonNode> iterator = data.iterator();
				while(iterator.hasNext()){
					JsonNode logJson = iterator.next();
					try{
						//LoggerFactory.getLogger(this.getClass()).debug("Log object json:"+logJson);
						Log l = objectMapper.convertValue(logJson, Log.class);
						list.add(l);
					}catch(IllegalArgumentException e){
						LoggerFactory.getLogger(this.getClass()).warn("Unable to unserialize Log : "+logJson, e);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
		} else {
			return null;
		}
	}

	private String readHints() {
		Elements select = document.select("div#div_hint");
		if (!select.isEmpty()) {
			return select.html().replaceAll("<[bB][rR]\\s*/?>", "\n");
		} else {
			return null;
		}
	}

	private String readName() {
		Elements select = document.select("html head meta[name=og:title]");
		if (!select.isEmpty()) {
			return select.attr("content");
		} else {
			return null;
		}
	}

	private String readUri() {
		Elements select = document.select("form");
		if (!select.isEmpty())
			return "http://www.geocaching.com"+ select.attr("action");
		else
			return null;
	}

	private String readType() {
		Elements select = document
				.select("div#cacheDetails p.cacheImage a img");
		if (!select.isEmpty())
			return select.attr("title").replace("Size: ", "");
		else
			return null;
	}

	private String readSize() {
		Elements select = document.select("div.CacheSize img");
		if (!select.isEmpty())
			return select.attr("title").replace("Size: ", "");
		else
			return null;
	}

	private String readOwner() {
		Matcher matcher = patternOwner.matcher(pageContent);
		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return null;
		}
	}

	private String readLatLonDM() {
		if (!document.select("div#ctl00_divNotSignedIn").isEmpty())
			throw new RuntimeException("Not signed in"); //TODO Throw a notlogged exception or something more precise
		
		String text = document.select("span#uxLatLon").text().replaceAll("\u00A0", " ");
		Matcher matcher = patternLatLonDM.matcher(text);
		if (matcher.matches()) {
			return matcher.group(1) + "" + matcher.group(2) + " "
					+ matcher.group(3) + " " + matcher.group(4) + ""
					+ matcher.group(5) + " " + matcher.group(6);
		} else {
			return null;
		}
	}

	private float[] readLatLon() {
		if (!document.select("div#ctl00_divNotSignedIn").isEmpty())
			throw new RuntimeException("Not signed in"); //TODO Throw a notlogged exception or something more precise

		Matcher matcher = patternLatLon.matcher(pageContent);
		if (matcher.find()) {
			return new float[] { Float.parseFloat(matcher.group(1)), Float.parseFloat(matcher.group(2)) };
		} else {
			return null;
		}

	}

	private float readTerrain() {
		Matcher matcher = patternTerrain.matcher(pageContent);
		if (matcher.find()) {
			return Float.parseFloat(matcher.group(1));
		} else {
			return -1f;
		}
	}

	private float readDifficulty() {
		Matcher matcher = patternDifficulty.matcher(pageContent);
		if (matcher.find()) {
			return Float.parseFloat(matcher.group(1));
		} else {
			return -1f;
		}
	}

	private String readDescription() {
		Elements select = this.document
				.select("span#ctl00_ContentBody_LongDescription");
		if (!select.isEmpty())
			return select.html();
		else
			return null;
	}

	private String readCacheId() {
		Matcher matcher = patternCacheId.matcher(pageContent);
		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return null;
		}
	}

	private String readCode() {
		Elements select = document.select("span.CoordInfoCode");
		if (!select.isEmpty())
			return select.text();
		else
			return null;
	}

}
