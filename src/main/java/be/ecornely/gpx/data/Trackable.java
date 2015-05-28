package be.ecornely.gpx.data;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Trackable implements Serializable {

	private static final long serialVersionUID = -1246773290514321145L;
	
	@Id
	private String guid;
	@Column
	private String name;
	@Column
	private String inCache;
	
	@OneToOne(cascade=CascadeType.ALL, optional=true, orphanRemoval=true, targetEntity=TrackableDetail.class)
	@JoinColumn(name="guid")
	private TrackableDetail detail;
	
	public Trackable() {
	}
	
	public Trackable(String guid, String name, String gcCode) {
		this.guid = guid;
		this.name = name;
		this.inCache = gcCode;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrackableDetail getDetail() {
		return detail;
	}

	public void setDetail(TrackableDetail detail) {
		this.detail = detail;
	}
	
	

}
