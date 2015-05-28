package be.ecornely.gpx.db;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

import be.ecornely.gpx.CachePageParser;
import be.ecornely.gpx.Downloader;
import be.ecornely.gpx.data.Geocache;

public class HibernateCacheUpdater {
	
	private SessionFactory sessionFactory;
	
	private Downloader downloader;
	
	
	@Transactional(value=TxType.REQUIRED)
	public void update(){
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT g.code FROM Geocache as g");
		@SuppressWarnings("unchecked")
		List<String> codes = (List<String>) query.list();
		int i=0;
		for(String code : codes){
			LoggerFactory.getLogger(this.getClass()).debug("Updating cache "+code +" ("+(i+1)+"/"+codes.size()+")");
			this.update(code);
			i++;
		}
	}
	
	private void update(String code){
		try{
			Geocache geocache = new CachePageParser(downloader.getCachePage(code)).getGeocache();
			this.updateGeocache(geocache);
			LoggerFactory.getLogger(this.getClass()).trace(geocache.toString());
		}catch(IOException | URISyntaxException e){
			LoggerFactory.getLogger(this.getClass()).warn("Unable to update cache : "+code, e);
		}
	}
	
	@Transactional(value=TxType.REQUIRED)
	public void updateCache(String code){
		LoggerFactory.getLogger(this.getClass()).debug("Updating single cache "+code);
		this.update(code);
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

	public Downloader getDownloader() {
		return downloader;
	}

	public void setDownloader(Downloader downloader) {
		this.downloader = downloader;
	}
	
	
	
	
}
