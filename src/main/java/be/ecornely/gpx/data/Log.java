package be.ecornely.gpx.data;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import be.ecornely.gpx.util.DateDeserializer;

@Entity
@JsonIgnoreProperties(value="creator")
public class Log implements Serializable{
	
	private static final long serialVersionUID = 6092453959603836327L;
	
	@Id
	@JsonProperty("LogID")
	private Long logID;
	
	@Column
	@JsonProperty("CacheID")
    private Long cacheID;
	
	@Column
	@JsonProperty("LogGuid")
    private String logGuid;
	
	@Column
	@JsonProperty("Latitude")
    private Float latitude;
	
	@Column
	@JsonProperty("Longitude")
    private Float longitude;
	
	@Column
	@JsonProperty("LatLonString")
    private String latLonString;
	
	@Column
	@JsonProperty("LogType")
    private String logType;
	
	@Column
	@JsonProperty("LogTypeImage")
    private String logTypeImage;
	
	@Column
	@JsonProperty("LogText")
    private String logText;

	@Temporal(TemporalType.DATE)
	@Column
	@JsonProperty("Created")
	@JsonDeserialize(using=DateDeserializer.class)
    private Date created;
	
	@Temporal(TemporalType.DATE)
	@Column
	@JsonProperty("Visited")
	@JsonDeserialize(using=DateDeserializer.class)
    private Date visited;
	
	@Column
	@JsonProperty("UserName")
    private String userName;
	
	@Column
	@JsonProperty("MembershipLevel")
    private Integer membershipLevel;
	
	@Column
	@JsonProperty("AccountID")
    private Long accountID;
	
	@Column
	@JsonProperty("AccountGuid")
    private String accountGuid;
	
	@Column
	@JsonProperty("Email")
    private String email;
	
	@Column
	@JsonProperty("AvatarImage")
    private String avatarImage;
	
	@Column
	@JsonProperty("GeocacheFindCount")
    private Integer geocacheFindCount;
	
	@Column
	@JsonProperty("GeocacheHideCount")
    private Integer geocacheHideCount;
	
	@Column
	@JsonProperty("ChallengesCompleted")
    private Integer challengesCompleted;
	
	@Column
	@JsonProperty("IsEncoded")
    private boolean isEncoded = false;
	
	@OneToMany(targetEntity=LogImage.class, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="logid")
    @JsonProperty("Images")
    private List<LogImage> images;
	
	@Column
    @JsonIgnore
    @JsonProperty("Creator")
    private String creator;
	
    public Log() {
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

	public String getLogGuid() {
		return logGuid;
	}

	public void setLogGuid(String logGuid) {
		this.logGuid = logGuid;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public String getLatLonString() {
		return latLonString;
	}

	public void setLatLonString(String latLonString) {
		this.latLonString = latLonString;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogTypeImage() {
		return logTypeImage;
	}

	public void setLogTypeImage(String logTypeImage) {
		this.logTypeImage = logTypeImage;
	}

	public String getLogText() {
		return logText;
	}

	public void setLogText(String logText) {
		this.logText = logText;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getVisited() {
		return visited;
	}

	public void setVisited(Date visited) {
		this.visited = visited;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMembershipLevel() {
		return membershipLevel;
	}

	public void setMembershipLevel(Integer membershipLevel) {
		this.membershipLevel = membershipLevel;
	}

	public Long getAccountID() {
		return accountID;
	}

	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}

	public String getAccountGuid() {
		return accountGuid;
	}

	public void setAccountGuid(String accountGuid) {
		this.accountGuid = accountGuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(String avatarImage) {
		this.avatarImage = avatarImage;
	}

	public Integer getGeocacheFindCount() {
		return geocacheFindCount;
	}

	public void setGeocacheFindCount(Integer geocacheFindCount) {
		this.geocacheFindCount = geocacheFindCount;
	}

	public Integer getGeocacheHideCount() {
		return geocacheHideCount;
	}

	public void setGeocacheHideCount(Integer geocacheHideCount) {
		this.geocacheHideCount = geocacheHideCount;
	}

	public Integer getChallengesCompleted() {
		return challengesCompleted;
	}

	public void setChallengesCompleted(Integer challengesCompleted) {
		this.challengesCompleted = challengesCompleted;
	}

	public boolean isEncoded() {
		return isEncoded;
	}

	public void setEncoded(boolean isEncoded) {
		this.isEncoded = isEncoded;
	}

	public List<LogImage> getImages() {
		return images;
	}

	public void setImages(List<LogImage> images) {
		this.images = images;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "Log [logID=" + logID + ", cacheID=" + cacheID + ", logGuid=" + logGuid + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", latLonString=" + latLonString + ", logType=" + logType
				+ ", logTypeImage=" + logTypeImage + ", logText=" + logText + ", created=" + created + ", visited="
				+ visited + ", userName=" + userName + ", membershipLevel=" + membershipLevel + ", accountID="
				+ accountID + ", accountGuid=" + accountGuid + ", email=" + email + ", avatarImage=" + avatarImage
				+ ", geocacheFindCount=" + geocacheFindCount + ", geocacheHideCount=" + geocacheHideCount
				+ ", challengesCompleted=" + challengesCompleted + ", isEncoded=" + isEncoded + ", images=" + images
				+ ", creator=" + creator + "]";
	}
    
    
	
}
