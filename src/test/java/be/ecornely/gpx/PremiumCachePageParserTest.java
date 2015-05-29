package be.ecornely.gpx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import be.ecornely.gpx.data.Geocache;
import be.ecornely.gpx.util.DateDeserializer;

public class PremiumCachePageParserTest {
	
	private static Geocache geocache;
	
	@BeforeClass
	public static void prepareTestClass() throws IOException{
		String pageContent = IOUtils.toString(PremiumCachePageParserTest.class.getResourceAsStream("/GC49ZMC_premium.html"), Charset.forName("UTF-8"));
		geocache = new CachePageParser(pageContent).getGeocache();
	}
	
	@Test
	public void checkCacheId(){
		assertNull(geocache.getCacheId());
	}
	
	@Test
	public void checkCode(){
		assertEquals("GC49ZMC", geocache.getCode());
	}
	@Test
	public void checkDescription(){
		assertNull(geocache.getDescription());
	}
	@Test
	public void checkDifficulty(){
		assertEquals(2.0f, geocache.getDifficulty(), 0.01f);
	}
	@Test
	public void checkFavoritePoint(){
		assertNull(geocache.getFavoritePoint());
	}
	@Test
	public void checkFoundCount(){
		assertEquals(0, geocache.getFoundCount());
	}
	@Test
	public void checkHint(){
		assertNull(geocache.getHint());
	}
	@Test
	public void checkInitialLogs(){
		assertNull(geocache.getInitialLogs());
	}
	@Test
	public void checkLastVisited(){
		assertNull(geocache.getLastVisited());
	}
	@Test
	public void checkLatitude(){
		assertEquals(0f, geocache.getLatitude(), 0.000001f);
	}
	@Test
	public void checkLatlonDM(){
		assertNull(geocache.getLatlonDM());
	}
	@Test
	public void checkLongitude(){
		assertEquals(0f, geocache.getLongitude(), 0.000001f);
	}
	@Test
	public void checkName(){
		assertEquals("Fort de Lantin", geocache.getName());
	}
	@Test
	public void checkOwner(){
		assertEquals("La bande FM", geocache.getOwner());
	}
	@Test
	public void checkPlaceDate(){
		assertNull(geocache.getPlaceDate());
	}
	@Test
	public void checkPremium(){
		assertTrue(geocache.isPremium());
	}
	@Test
	public void checkSize(){
		assertEquals("Micro", geocache.getSize());
	}
	@Test
	public void checkTerrain(){
		assertEquals(2.5f, geocache.getTerrain(), 0.01f);
	}
	@Test
	public void checkTrackables(){
		Assert.assertNull(geocache.getTrackables());
	}
	@Test
	public void checkType(){
		assertNull(geocache.getType());
	}
	@Test
	public void checkUri(){
		assertEquals("http://www.geocaching.com/seek/cache_details.aspx?wp=GC49ZMC&title=fort-de-lantin", geocache.getUri());
	}
	@Test
	public void checkWaypoints(){
		assertNull(geocache.getWaypoints());
	}

}
