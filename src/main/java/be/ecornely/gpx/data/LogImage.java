package be.ecornely.gpx.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import be.ecornely.gpx.util.DateDeserializer;

@Entity
public class LogImage implements Serializable{
	
	private static final long serialVersionUID = -8274740344410107482L;
	
	@Id
	@JsonProperty("ImageID")
	private Long imageID;
	
	@Column
	@JsonProperty("ImageGuid")
    private String imageGuid;
	
	@Column
	@JsonProperty("Name")
    private String name;
	
	@Column
	@JsonProperty("Descr")
    private String descr;
	
	@Column
	@JsonProperty("FileName")
    private String fileName;

	@Temporal(TemporalType.DATE)
	@Column
	@JsonProperty("Created")
	@JsonDeserialize(using=DateDeserializer.class)
	private Date created;
	
	@Column
	@JsonProperty("LogID")
	private Long logID;
	
	@Column
	@JsonProperty("CacheID")
	private Long cacheID;
	
	@Column
	@JsonProperty("ImageUrl")
	private String imageUrl;
    
	public LogImage() {
	}

	public Long getImageID() {
		return imageID;
	}

	public void setImageID(Long imageID) {
		this.imageID = imageID;
	}

	public String getImageGuid() {
		return imageGuid;
	}

	public void setImageGuid(String imageGuid) {
		this.imageGuid = imageGuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getLogID() {
		return logID;
	}

	public void setLogID(Long logID) {
		this.logID = logID;
	}

	public Long getCacheID() {
		return cacheID;
	}

	public void setCacheID(Long cacheID) {
		this.cacheID = cacheID;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}