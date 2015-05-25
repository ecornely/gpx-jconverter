package be.ecornely.gpx.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Waypoint {
	String wpPrefix = null;
	String wpLookup = null;
	String wpName = null;
	String wpCoords = null;
	String wpNote = "";
	String wpType = null;

	private static final Pattern coordsPattern = Pattern
			.compile("\\s*([NS])\\s*(\\d+)\\s*.?\\s*(\\d+(?:\\.\\d+)?)\\s*([EW])\\s*(\\d+)\\s*.?\\s*(\\d+(?:\\.\\d+)?)\\s*");

	public String getWpPrefix() {
		return wpPrefix;
	}

	public void setWpPrefix(String wpPrefix) {
		this.wpPrefix = wpPrefix;
	}

	public String getWpLookup() {
		return wpLookup;
	}

	public void setWpLookup(String wpLookup) {
		this.wpLookup = wpLookup;
	}

	public String getWpName() {
		return wpName;
	}

	public void setWpName(String wpName) {
		this.wpName = wpName;
	}

	public String getWpCoords() {
		return wpCoords.replaceAll("[\\s�]$", "");
	}

	public void setWpCoords(String wpCoords) {
		this.wpCoords = wpCoords;
	}

	public String getWpNote() {
		return wpNote;
	}

	public void setWpNote(String wpNote) {
		this.wpNote = wpNote;
	}

	public float getLatitude() {
		float lat = 0f;
		if (this.getWpCoords() != null) {
			Matcher matcher = coordsPattern.matcher(this.getWpCoords());
			if (matcher.matches()) {
				lat = Integer.parseInt(matcher.group(2)) + Float.parseFloat(matcher.group(3))/60f * ( (matcher.group(1)) == "S" ? -1 : 1 );
			}
		}
		return lat;
	}

	public float getLongitude() {
		float lon = 0f;
		if (this.getWpCoords() != null) {
			Matcher matcher = coordsPattern.matcher(this.getWpCoords());
			if (matcher.matches()) {
				lon = Integer.parseInt(matcher.group(5)) + Float.parseFloat(matcher.group(6))/60f * ( (matcher.group(4)) == "W"? -1 : 1 );
			}
		}
		return lon;
	}

	public String getWpType() {
		return wpType;
	}

	public void setWpType(String wpType) {
		this.wpType = wpType;
	}
	
	@Override
	public String toString() {
		return "Waypoint [wpPrefix=" + wpPrefix + ", wpLookup=" + wpLookup
				+ ", wpName=" + wpName + ", wpCoords=" + wpCoords + ", wpNote="
				+ wpNote + "]";
	}

}