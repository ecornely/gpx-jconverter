package be.ecornely.gpx.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Waypoint {
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String wpPrefix) {
		this.prefix = wpPrefix;
	}

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String wpLookup) {
		this.lookup = wpLookup;
	}

	public String getName() {
		return name;
	}

	public void setName(String wpName) {
		this.name = wpName;
	}

	public String getCoords() {
		return coords.replaceAll("[\\s�]$", "");
	}

	public void setCoords(String wpCoords) {
		this.coords = wpCoords;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String wpNote) {
		this.note = wpNote;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String wpType) {
		this.type = wpType;
	}
	
	@Override
	public String toString() {
		return "Waypoint [wpPrefix=" + prefix + ", wpLookup=" + lookup
				+ ", wpName=" + name + ", wpCoords=" + coords + ", wpNote="
				+ note + "]";
	}

}