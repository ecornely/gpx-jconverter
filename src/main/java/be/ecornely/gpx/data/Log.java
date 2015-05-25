package be.ecornely.gpx.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import be.ecornely.gpx.util.JsonDateDeserializer;

/*
{
    "LogID":494782979,
    "CacheID":2997069,
    "LogGuid":"a0ad1c25-a597-4581-a33e-b0aa178da383",
    "Latitude":null,
    "Longitude":null,
    "LatLonString":"",
    "LogType":"Found it",
    "LogTypeImage":"2.png",
    "LogText":"Profitant d'une reconnaissance pour l'Event de Solwaster le 01 mai 2015, J'ai loggue quelques caches.<br />Merci pour celle-ci",
    "Created":"22/04/2015",
    "Visited":"22/04/2015",
    "UserName":"Papilou-Nanou",
    "MembershipLevel":3,
    "AccountID":4654537,
    "AccountGuid":"88dd27f3-75d1-429b-a07c-4356e3b33d3f",
    "Email":"",
    "AvatarImage":"47e0c27f-1bd5-4a66-8295-3bc66eebd03a.jpg",
    "GeocacheFindCount":400,
    "GeocacheHideCount":7,
    "ChallengesCompleted":0,
    "IsEncoded":false,
    "creator":{
        "GroupTitle":"Premium Member",
        "GroupImageUrl":"/images/icons/prem_user.gif"
    },
    "Images":[
		{
		    "ImageID":23932255,
		    "ImageGuid":"02ea08ce-94b8-49ff-b574-055761630deb",
		    "Name":"la Saw et Soulwaster ",
		    "Descr":"",
		    "FileName":"02ea08ce-94b8-49ff-b574-055761630deb.jpg",
		    "Created":"17/08/2014",
		    "LogID":435960399,
		    "CacheID":2997069,
		    "ImageUrl":null
		}
    ]
}
 * */
public class Log implements Serializable{
	
	private static final long serialVersionUID = 6092453959603836327L;
	

	/*
	   {
		    "ImageID":23932255,
		    "ImageGuid":"02ea08ce-94b8-49ff-b574-055761630deb",
		    "Name":"la Saw et Soulwaster ",
		    "Descr":"",
		    "FileName":"02ea08ce-94b8-49ff-b574-055761630deb.jpg",
		    "Created":"17/08/2014",
		    "LogID":435960399,
		    "CacheID":2997069,
		    "ImageUrl":null
		}
	 */
	public static class Image implements Serializable{
		private static final long serialVersionUID = -8274740344410107482L;
		@JsonProperty("ImageID")
		private Long imageID;
		@JsonProperty("ImageGuid")
	    private String imageGuid;
		@JsonProperty("Name")
	    private String name;
		@JsonProperty("Descr")
	    private String descr;
		@JsonProperty("FileName")
	    private String fileName;
		@JsonProperty("Created")
		@JsonDeserialize(using=JsonDateDeserializer.class)
	    private Date created;
		@JsonProperty("LogID")
	    private Long logID;
		@JsonProperty("CacheID")
	    private Long cacheID;
		@JsonProperty("ImageUrl")
	    private String imageUrl;
	    public Image() {
	    	
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
		@Override
		public String toString() {
			return "Image [imageID=" + imageID + ", name=" + name + ", descr="
					+ descr + ", fileName=" + fileName + "]";
		}
	    
		
	    
	}
	
	@JsonProperty("LogID")
	private Long logID;
	@JsonProperty("CacheID")
    private Long cacheID;
	@JsonProperty("LogGuid")
    private String logGuid;
	@JsonProperty("Latitude")
    private Float latitude;
	@JsonProperty("Longitude")
    private Float longitude;
	@JsonProperty("LatLonString")
    private String latLonString;
	@JsonProperty("LogType")
    private String logType;
	@JsonProperty("LogTypeImage")
    private String logTypeImage;
	@JsonProperty("LogText")
    private String logText;
	@JsonProperty("Created")
	@JsonDeserialize(using=JsonDateDeserializer.class)
    private Date created;
	@JsonProperty("Visited")
	@JsonDeserialize(using=JsonDateDeserializer.class)
    private Date visited;
	@JsonProperty("UserName")
    private String userName;
	@JsonProperty("MembershipLevel")
    private Integer membershipLevel;
	@JsonProperty("AccountID")
    private Long accountID;
	@JsonProperty("AccountGuid")
    private String accountGuid;
	@JsonProperty("Email")
    private String email;
	@JsonProperty("AvatarImage")
    private String avatarImage;
	@JsonProperty("GeocacheFindCount")
    private Integer geocacheFindCount;
	@JsonProperty("GeocacheHideCount")
    private Integer geocacheHideCount;
	@JsonProperty("ChallengesCompleted")
    private Integer challengesCompleted;
	@JsonProperty("IsEncoded")
    private boolean isEncoded = false;
    @JsonProperty("Images")
    private List<Image> images;
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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Log [logID=" + logID + ", cacheID=" + cacheID + ", logText="
				+ logText + ", created=" + created + ", visited=" + visited
				+ ", userName=" + userName + ", geocacheFindCount="
				+ geocacheFindCount + ", geocacheHideCount="
				+ geocacheHideCount + ", isEncoded=" + isEncoded + ", images="
				+ images + "]";
	}
	
	
    
    
	
}
