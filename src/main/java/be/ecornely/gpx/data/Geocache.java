package be.ecornely.gpx.data;

import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class Geocache implements Serializable {
	
	private static final long serialVersionUID = 2038448846615747984L;
	private String name;
	private String code;
	private String cacheId;
	private String description;
	private String owner;
	private String latlonDM;
	private float[] latlon;
	private URI uri;
	private String size;
	private String type;
	private float difficulty;
	private float terrain;
	private String hint;
	private List<Log> initialLogs;
	private List<Waypoint> waypoints;
	
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

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
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

	public float[] getLatlon() {
		return latlon;
	}

	public void setLatlon(float[] latlon) {
		this.latlon = latlon;
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
	
	public float getLatitude(){
		return this.getLatlon()[0];
	}
	
	public float getLongitude(){
		return this.getLatlon()[1];
	}

	@Override
	public String toString() {
		return "Geocache [name=" + name + ", code=" + code + ", cacheId="
				+ cacheId + ", owner=" + owner + ", latlonDM=" + latlonDM
				+ ", latlon=" + Arrays.toString(latlon) + ", uri=" + uri
				+ ", size=" + size + ", type=" + type + ", difficulty="
				+ difficulty + ", terrain=" + terrain + ", hint=" + hint + "]";
	}

	
}
