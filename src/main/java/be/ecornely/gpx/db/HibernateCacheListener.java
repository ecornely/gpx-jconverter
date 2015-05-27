package be.ecornely.gpx.db;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import be.ecornely.gpx.CacheListener;
import be.ecornely.gpx.data.Geocache;

public class HibernateCacheListener implements CacheListener {

	private static final long serialVersionUID = 2114211730617980062L;
	private SessionFactory sessionFactory;
	
	public HibernateCacheListener() {
	}
	
	@Override
	@Transactional(value=TxType.REQUIRED)
	public void newCache(Geocache g) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(g);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
}
