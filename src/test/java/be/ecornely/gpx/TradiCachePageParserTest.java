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

public class TradiCachePageParserTest {
	
	private static Geocache geocache;
	
	@BeforeClass
	public static void prepareTestClass() throws IOException{
		String pageContent = IOUtils.toString(TradiCachePageParserTest.class.getResourceAsStream("/GC4YVRG_Tradi_Track.html"), Charset.forName("UTF-8"));
		geocache = new CachePageParser(pageContent).getGeocache();
	}
	
	@Test
	public void checkCacheId(){
		assertEquals("4172649", geocache.getCacheId());
	}
	
	@Test
	public void checkCode(){
		assertEquals("GC4YVRG", geocache.getCode());
	}
	@Test
	public void checkDescription(){
		assertEquals("<div id=\"shortDesc\"><p>Dit is mijn 1e Cache's over de grens......in de wijk De Heeg . ( een tuinCache's )&nbsp;</p> ik ben Vindbaar en ben een Tb Hotel op Nr.15 <p>Misschien dat ik er in de toekomst nog verder ga kijken of ik er in de omgeving er nog een paar kan of ga plaatsen .</p> <p>This is my first Cache over the Border in the Nederlands mayby there will be folowing more Cache's @De Heeg ( Garden Cahe's )</p> <p>&nbsp;</p> <p>&nbsp;</p></div><div id=\"longDesc\"><img alt=\"\" src=\"http://imgcdn.geocaching.com/cache/large/26f6cbad-e8e2-436f-bbba-a5b8850e8355.png\" style=\"height:80px;width:400px;\"></div>", geocache.getDescription());
	}
	@Test
	public void checkDifficulty(){
		assertEquals(1.5f, geocache.getDifficulty(), 0.01f);
	}
	@Test
	public void checkFavoritePoint(){
		assertEquals(new Integer(9), geocache.getFavoritePoint());
	}
	@Test
	public void checkFoundCount(){
		assertEquals(291, geocache.getFoundCount());
	}
	@Test
	public void checkHint(){
		assertEquals("De Coördinaten kloppen ik woon op\n\nHuis Nr 15 Zie GeoCache Logo ........ en ben niet te overzien.\n\n\n\nYou can find Me @The House Nr 15 ......", geocache.getHint());
	}
	@Test
	public void checkInitialLogs(){
		assertEquals(25, geocache.getInitialLogs().size());
	}
	@Test
	public void checkLastVisited(){
		assertEquals("27/05/2015", DateDeserializer.format(geocache.getLastVisited()));
	}
	@Test
	public void checkLatitude(){
		assertEquals(50.82695f, geocache.getLatitude(), 0.000001f);
	}
	@Test
	public void checkLatlonDM(){
		assertEquals("N50 49.617 E005 43.267", geocache.getLatlonDM());
	}
	@Test
	public void checkLongitude(){
		assertEquals(5.721117f, geocache.getLongitude(), 0.000001f);
	}
	@Test
	public void checkName(){
		assertEquals("@Tb hotel Reijershaag 15 ", geocache.getName());
	}
	@Test
	public void checkOwner(){
		assertEquals("Patje73.nl", geocache.getOwner());
	}
	@Test
	public void checkPlaceDate(){
		Assert.assertEquals("19/02/2014", DateDeserializer.format(geocache.getPlaceDate()));
	}
	@Test
	public void checkPremium(){
		assertFalse(geocache.isPremium());
	}
	@Test
	public void checkSize(){
		assertEquals("regular", geocache.getSize());
	}
	@Test
	public void checkTerrain(){
		assertEquals(1.5f, geocache.getTerrain(), 0.01f);
	}
	@Test
	public void checkTrackables(){
		assertEquals(8, geocache.getTrackables().size());
	}
	@Test
	public void checkType(){
		assertEquals("Traditional Geocache", geocache.getType());
	}
	@Test
	public void checkUri(){
		assertEquals("http://www.geocaching.com/geocache/GC4YVRG_tb-hotel-reijershaag-15", geocache.getUri());
	}
	@Test
	public void checkWaypoints(){
		Assert.assertEquals(0, geocache.getWaypoints().size());
	}

}
