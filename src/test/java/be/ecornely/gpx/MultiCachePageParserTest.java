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

public class MultiCachePageParserTest {
	
	private static Geocache geocache;
	
	@BeforeClass
	public static void prepareTestClass() throws IOException{
		String pageContent = IOUtils.toString(MultiCachePageParserTest.class.getResourceAsStream("/GC3NCFJ_multi.html"), Charset.forName("UTF-8"));
		geocache = new CachePageParser(pageContent).getGeocache();
	}
	
	@Test
	public void checkCacheId(){
		assertEquals("2997069", geocache.getCacheId());
	}
	
	@Test
	public void checkCode(){
		assertEquals("GC3NCFJ", geocache.getCode());
	}
	@Test
	public void checkDescription(){
		assertEquals("<div id=\"shortDesc\">Multi de 4,5km dans les bois de Solwaster</div><div id=\"longDesc\">Un multi de plus ou moins 4,5km à Solwaster et ses bois.<br> Solwaster, un petit village pittoresque et authentique, né il y a environ 700 ans au pied de la fagne, est un des plus beaux de la région.<br> Il a su conserver la majeure partie de son habitat traditionnel, fait de petites fermettes en pierre, toutes bâties sur le même modèle de base et, pour la plupart, construites au début du 18è.<br> Solwaster est le point de départ de nombreuses promenades partant à la découverte des rivières fagnardes Hoëgne, Statte et Sawe. La Statte rejoint la Sawe près de Solwaster, après avoir traversé tourbières et forêts. De nombreuses cascades égaient le parcours de ces ruisseaux, offrant au promeneur autant de lieu de ressourcement.<br> <br> C'est tellement magnifique que j'ai crée ce cache pour vous montrer cette région impressionnante.<br> Vous pouvez garer votre voiture au point de départ (WP1).<br> &nbsp;<br> WP1: N50 31.982 E005 58.054<br> Au départ vous voyez un panneau (La Sawe, Pomenade... Parcours)<br> Combien de flèches comme celle-ci &lt; voyez-vous sur ce panneau?<br> A= nombre de flèches<br> &nbsp;<br> WP2 N50 32.074 E005 58.475<br> Ici il y a l'entrée du chemin pour les personnes à mobilité réduite \"accompagnées\".<br> Combien de panneaux se trouvent à cette entrée?<br> B= nombre de panneaux +1 (car ils ont enleve un)<br> &nbsp;<br> WP3 N50 32.005 E005 58.762<br> Vous étés arrives à un croisement. Sur les arbres plein de petits panneaux vous indiquent le chemin. Combien il y a t- il de panneaux jaunes en tout?<br> C= nombre de panneaux jaunes<br> &nbsp;<br> WP4: N50 31.888 E005 58.494<br> Sur un arbre plein de couleurs se présentent (sur les panneaux et sur l'arbre même).<br> Quelle couleur ne se trouve pas sur l'arbre? Pour jaune= 6, rouge =8, mauve =1, vert= 3, blanc=5<br> D= La couleur qu'on ne voit pas sur l'arbre<br> &nbsp;<br> WP5 N50 31.742 E005 58.521<br> Qu'est ce que vous voyez ici? Un lac =1, deux barrières=2, une statue de cheval=3, quarte bancs=4<br> E= ?<br> &nbsp;<br> WP6 N50 31.713 E005 58.506<br> Quelle couleur ont les ronds sur les deux panneaux? Jaune= 2, rouge= 3, vert= 5, bleu= 9, noir=0<br> F= couleur des ronds?<br> &nbsp;<br> WP7 N50 31.682 E005 58.120<br> A la fin de la rue se trouvent trois panneaux de velo-tour. Quel chiffre voit-on sur tous les trois panneaux? (Premier chiffre)<br> G= premier chiffre<br> &nbsp;<br> WP8 N50 31.517 E005 58.037<br> Bienvenue à Jalhay- Sart. Pour préciser, bienvenue à Solwaster.<br> Quelle couleur a le sac à dos du bonhomme qui se promène dans toute la région?<br> Vert= 0, bleu= 2, rouge= 4, mauve = 6, brun= 8<br> H= couleur du sac à dos<br> &nbsp;<br> Ici vous pouvez vous reposer un peu et calculer le final.<br> Final: <strong>N</strong> <em>BH° FD. CEB</em><br> <strong>E</strong> <em>HHB° B(A+F).GH(A+D)</em><br> &nbsp;<br> Au final vous trouver une ferme. Ca vaut vraiment la peine d'aller la visiter et pourquoi pas aller boire un verre de lait ou d'en prendre un peu pour le chemin de retour :)<br> &nbsp;<br> Pour le retour, vous pouvez alternativement prendre le chemin qui part à la droite après la ferme (direction WP1).<br></div>", geocache.getDescription());
	}
	@Test
	public void checkDifficulty(){
		assertEquals(2.5f, geocache.getDifficulty(), 0.01f);
	}
	@Test
	public void checkFavoritePoint(){
		assertEquals(new Integer(2), geocache.getFavoritePoint());
	}
	@Test
	public void checkFoundCount(){
		assertEquals(53, geocache.getFoundCount());
	}
	@Test
	public void checkHint(){
		assertEquals("Croix ;)", geocache.getHint());
	}
	@Test
	public void checkInitialLogs(){
		assertEquals(25, geocache.getInitialLogs().size());
	}
	@Test
	public void checkLastVisited(){
		assertEquals("22/04/2015", DateDeserializer.format(geocache.getLastVisited()));
	}
	@Test
	public void checkLatitude(){
		assertEquals(50.53303f, geocache.getLatitude(), 0.000001f);
	}
	@Test
	public void checkLatlonDM(){
		assertEquals("N50 31.982 E005 58.054", geocache.getLatlonDM());
	}
	@Test
	public void checkLongitude(){
		assertEquals(5.967567f, geocache.getLongitude(), 0.000001f);
	}
	@Test
	public void checkName(){
		assertEquals("La Sawe et Solwaster", geocache.getName());
	}
	@Test
	public void checkOwner(){
		assertEquals("loupgarou7", geocache.getOwner());
	}
	@Test
	public void checkPlaceDate(){
		Assert.assertEquals("11/06/2012", DateDeserializer.format(geocache.getPlaceDate()));
	}
	@Test
	public void checkPremium(){
		assertFalse(geocache.isPremium());
	}
	@Test
	public void checkSize(){
		assertEquals("small", geocache.getSize());
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
		assertEquals("Multi-cache", geocache.getType());
	}
	@Test
	public void checkUri(){
		assertEquals("http://www.geocaching.com/geocache/GC3NCFJ_la-sawe-et-solwaster", geocache.getUri());
	}
	@Test
	public void checkWaypoints(){
		assertEquals(8, geocache.getWaypoints().size());
	}

}
