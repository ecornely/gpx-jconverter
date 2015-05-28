package be.ecornely.gpx.data;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Waypoint implements Serializable {
	private static final long serialVersionUID = -5771984156035828575L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String prefix = null;
	@Column
	private String lookup = null;
	@Column
	private String name = null;
	@Column
	private String coords = null;
	@Column
	private String note = "";
	@Column
	private String type = null;
	
	private static final Pattern coordsPattern = Pattern
			.compile("\\s*([NS])\\s*(\\d+)\\s*.?\\s*(\\d+(?:\\.\\d+)?)\\s*([EW])\\s*(\\d+)\\s*.?\\s*(\\d+(?:\\.\\d+)?)\\s*");

	
	public float getLatitude() {
		float lat = 0f;
		if (this.getCoords() != null) {
			Matcher matcher = coordsPattern.matcher(this.getCoords());
			if (matcher.matches()) {
				lat = Integer.parseInt(matcher.group(2)) + Float.parseFloat(matcher.group(3))/60f * ( (matcher.group(1)) == "S" ? -1 : 1 );
			}
		}
		return lat;
	}

	public float getLongitude() {
		float lon = 0f;
		if (this.getCoords() != null) {
			Matcher matcher = coordsPattern.matcher(this.getCoords());
			if (matcher.matches()) {
				lon = Integer.parseInt(matcher.group(5)) + Float.parseFloat(matcher.group(6))/60f * ( (matcher.group(4)) == "W"? -1 : 1 );
			}
		}
		return lon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoords() {
		return coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static Pattern getCoordspattern() {
		return coordsPattern;
	}
	
	

	
}