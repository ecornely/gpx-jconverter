package be.ecornely.gpx.db;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import be.ecornely.gpx.data.Geocache;

public class HibernateCacheSerializer {
	
	private SessionFactory sessionFactory;
	
	@Transactional(value=TxType.REQUIRED)
	public void printAllCaches(OutputStream out) throws JAXBException, IOException{
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT g FROM Geocache as g");
		@SuppressWarnings("unchecked")
		List<Geocache> geocaches = (List<Geocache>) query.list();
		Marshaller marshaller = JAXBContext.newInstance(Geocache.class).createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		for(Geocache g : geocaches){
			marshaller.marshal(g, out);
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
