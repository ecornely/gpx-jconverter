package be.ecornely.gpx.data;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Geocache implements Serializable {
	
	private static final long serialVersionUID = 2038448846615747984L;
	@Column
	private String name;
	@Id
	private String code;
	@Column
	private String cacheId;
	@Column
	private String description;
	@Column
	private String owner;
	@Column
	private String latlonDM;
	@Column
	private float latitude;
	@Column
	private float longitude;
	@Column
	private String uri;
	@Column
	private String size;
	@Column
	private String type;
	@Column
	private float difficulty;
	@Column
	private float terrain;
	@Column
	private String hint;
	@Column
	private Integer favoritePoint;
	@Column
	private boolean premium = false;
	
	
	@OneToMany(targetEntity=Log.class, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="gccode")
	private List<Log> initialLogs;
	
	@OneToMany(targetEntity=Waypoint.class, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="gccode")
	private List<Waypoint> waypoints;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date lastVisited;
	@Column
	@Temporal(TemporalType.DATE)
	private Date placeDate;
	
	//TODO add trackables
	
	public Geocache() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCacheId() {
		return cacheId;
	}

	public void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(float difficulty) {
		this.difficulty = difficulty;
	}

	public float getTerrain() {
		return terrain;
	}

	public void setTerrain(float terrain) {
		this.terrain = terrain;
	}

	public List<Log> getInitialLogs() {
		return initialLogs;
	}

	public void setInitialLogs(List<Log> initialLogs) {
		this.initialLogs = initialLogs;
	}
	
	public String getLatlonDM() {
		return latlonDM;
	}

	public void setLatlonDM(String latlonDM) {
		this.latlonDM = latlonDM;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public List<Waypoint> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}
	
	public Integer getFavoritePoint() {
		return favoritePoint;
	}

	public void setFavoritePoint(Integer favoritePoint) {
		this.favoritePoint = favoritePoint;
	}
	
	public Date getLastVisited() {
		return lastVisited;
	}

	public void setLastVisited(Date lastVisited) {
		this.lastVisited = lastVisited;
	}

	public Date getPlaceDate() {
		return placeDate;
	}

	public void setPlaceDate(Date placeDate) {
		this.placeDate = placeDate;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "Geocache [name=" + name + ", code=" + code + ", cacheId=" + cacheId + ", description=" + description
				+ ", owner=" + owner + ", latlonDM=" + latlonDM + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", uri=" + uri + ", size=" + size + ", type=" + type + ", difficulty=" + difficulty + ", terrain="
				+ terrain + ", hint=" + hint + ", favoritePoint=" + favoritePoint + ", lastVisited=" + lastVisited
				+ ", placeDate=" + placeDate + "]";
	}
	
	

	
}
