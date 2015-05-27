package be.ecornely.gpx.db;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

import be.ecornely.gpx.GCCachePageParser;
import be.ecornely.gpx.GCDownloader;
import be.ecornely.gpx.data.Geocache;

public class HibernateGeocacheUpdater {
	
	private SessionFactory sessionFactory;
	
	private GCDownloader downloader;
	
	
	@Transactional(value=TxType.REQUIRED)
	public void update(){
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT g.code FROM Geocache as g");
		@SuppressWarnings("unchecked")
		List<String> codes = (List<String>) query.list();
		for(String code : codes){
			try{
				LoggerFactory.getLogger(this.getClass()).debug("Updating cache "+code);
				Geocache geocache = new GCCachePageParser(downloader.getCachePage(code)).getGeocache();
				this.updateGeocache(geocache);
				LoggerFactory.getLogger(this.getClass()).debug("Updated cache "+code);
			}catch(IOException | URISyntaxException e){
				LoggerFactory.getLogger(this.getClass()).warn("Unable to update cache : "+code, e);
			}
		}
	}
	
	@Transactional(value=TxType.REQUIRES_NEW)
	private void updateGeocache(Geocache geocache){
		sessionFactory.getCurrentSession().update(geocache);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GCDownloader getDownloader() {
		return downloader;
	}

	public void setDownloader(GCDownloader downloader) {
		this.downloader = downloader;
	}
	
	
	
	
}
