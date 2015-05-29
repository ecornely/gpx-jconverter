package be.ecornely.gpx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import be.ecornely.gpx.data.Geocache;
import be.ecornely.gpx.util.DateDeserializer;

public class EventCachePageParserTest {
	
	private static Geocache geocache;
	
	@BeforeClass
	public static void prepareTestClass() throws IOException{
		String pageContent = IOUtils.toString(EventCachePageParserTest.class.getResourceAsStream("/GC5VVVM_event.html"), Charset.forName("UTF-8"));
		geocache = new CachePageParser(pageContent).getGeocache();
	}
	
	@Test
	public void checkCacheId(){
		assertEquals("5006863", geocache.getCacheId());
	}
	
	@Test
	public void checkCode(){
		assertEquals("GC5VVVM", geocache.getCode());
	}
	@Test
	public void checkDescription(){
		assertEquals("<div id=\"shortDesc\"></div><div id=\"longDesc\"><p style=\"text-align:center;\"><span style=\"font-family:cursive;\"><span style=\"font-size:medium;\">Cultuur snuiven en nieuwe plekjes ontdekken.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-family:cursive;\"><span style=\"font-size:medium;\">Geocaching is een stukje wereld verkennen.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-family:cursive;\"><span style=\"font-size:medium;\">Is dit niet waar geocaching voor staat?</span></span></p> <p style=\"text-align:center;\"><img alt=\"\" height=\"286\" src=\"http://files.umwblogs.org/blogs.dir/9539/files/2015/01/12201910/digital-art-17.jpg\" width=\"509\"></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Om deze rede hebben wij onze hoofden bij elkaar gestoken en nodigen we jullie uit op :</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:xx-large;\"><span style=\"color:rgb(255, 0, 0);\"><span style=\"font-family:cursive;\">Art in Riemst 2015.</span></span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Wat hebben we jullie aan te bieden?</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Een Geo-art gemaakt op het grondgebied van de gemeente Riemst.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">De event-locatie laat jullie alvast een stukje lokale geschiedenis kennen.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Het event zal plaats vinden op de archeologische site van De Waterburcht te Millen.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">De Geo-art zal bestaan uit meer dan 40 mysteries die jullie de gemeente</span></span> <span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Riemst laten ontdekken.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Jullie zijn van harte welkom vanaf 9u30 uur tot 17 uur.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Listings worden vrijgegeven vanaf 10 uur.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Inschrijving bedraagt <u>5&nbsp; euro per account</u>.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">We zouden het apprecieren moest er een will attend geplaatst worden met ook het aantal deelnemers per account.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Hou er aub rekening mee dat het oppervlakte van Riemst 57,88km</span><span style=\"font-family:cursive;\"><sup>2</sup> bedraagt</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">en dat de caches verspreidt liggen.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Een fiets is misschien een ideale voertuig voor deze dag.</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Verdere info betreffende het event zal op deze pagina en via FB gepubliceerd worden.</span></span></p> <p style=\"text-align:center;\">&nbsp;</p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">We hopen jullie allemaal te mogen begroeten tijdens AIR 2015!!!</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">ALfa-BVMJ</span></span></p> <p style=\"text-align:center;\">&nbsp;</p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">Iedereen die aanwezig was op dit Event kan onderstaande</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">banner toevoegen aan zijn/haar profiel</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">BannerCode:</span></span></p> <p style=\"text-align:center;\"><span style=\"font-size:medium;\"><span style=\"font-family:cursive;\">&nbsp;</span></span><strong>&lt;a href=\"http://coord.info/GC5VVVM” target=\"_blank\"&gt;&lt;img src=\"https://d1u1p2xjjiahg3.cloudfront.net/146dd8db-ca65-4b76-83dc-606b05485194_l.jpg\" title=”Art In Riemst\" border=\"0\" width=\"240\"height=\"300\"/&gt;&lt;/a&gt;</strong></p> <p style=\"text-align:center;\"><strong><img alt=\"\" src=\"https://d1u1p2xjjiahg3.cloudfront.net/146dd8db-ca65-4b76-83dc-606b05485194_l.jpg\" style=\"width:300px;height:222px;\"></strong></p> <p style=\"text-align:center;\">&nbsp;</p></div>", geocache.getDescription());
	}
	@Test
	public void checkDifficulty(){
		assertEquals(1.0f, geocache.getDifficulty(), 0.01f);
	}
	@Test
	public void checkFavoritePoint(){
		assertEquals(new Integer(0), geocache.getFavoritePoint());
	}
	@Test
	public void checkFoundCount(){
		assertEquals(0, geocache.getFoundCount());
	}
	@Test
	public void checkHint(){
		assertEquals("", geocache.getHint());
	}
	@Test
	public void checkInitialLogs(){
		assertEquals(15, geocache.getInitialLogs().size());
	}
	@Test
	public void checkLastVisited(){
		assertEquals("28/05/2015", DateDeserializer.format(geocache.getLastVisited()));
	}
	@Test
	public void checkLatitude(){
		assertEquals(50.78602f, geocache.getLatitude(), 0.000001f);
	}
	@Test
	public void checkLatlonDM(){
		assertEquals("N50 47.161 E005 33.539", geocache.getLatlonDM());
	}
	@Test
	public void checkLongitude(){
		assertEquals(5.558983f, geocache.getLongitude(), 0.000001f);
	}
	@Test
	public void checkName(){
		assertEquals("Art in Riemst (AIR)", geocache.getName());
	}
	@Test
	public void checkOwner(){
		assertEquals("alfa-bvmj", geocache.getOwner());
	}
	@Test
	public void checkPlaceDate(){
		Assert.assertNull(geocache.getPlaceDate());
	}
	@Test
	public void checkPremium(){
		assertFalse(geocache.isPremium());
	}
	@Test
	public void checkSize(){
		assertEquals("other", geocache.getSize());
	}
	@Test
	public void checkTerrain(){
		assertEquals(1.5f, geocache.getTerrain(), 0.01f);
	}
	@Test
	public void checkTrackables(){
		Assert.assertNull(geocache.getTrackables());
	}
	@Test
	public void checkType(){
		assertEquals("Event Cache", geocache.getType());
	}
	@Test
	public void checkUri(){
		assertEquals("http://www.geocaching.com/geocache/GC5VVVM_art-in-riemst-air", geocache.getUri());
	}
	@Test
	public void checkWaypoints(){
		assertEquals(2, geocache.getWaypoints().size());
	}

}
