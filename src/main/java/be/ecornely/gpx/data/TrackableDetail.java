package be.ecornely.gpx.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TrackableDetail implements Serializable {

	private static final long serialVersionUID = 3735977448913502899L;
	
	@Id
	private String guid;

	@Column
	private String owner;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@Column
	private String origin;
	
	@Column
	private String coordInfoCode;
	
	@Column
	private String goal;
	
	public TrackableDetail() {
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCoordInfoCode() {
		return coordInfoCode;
	}

	public void setCoordInfoCode(String coordInfoCode) {
		this.coordInfoCode = coordInfoCode;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}
	
	
}
