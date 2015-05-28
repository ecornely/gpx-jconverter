package be.ecornely.gpx.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcDistanceUpdater {
	private float longitude;
	private float latitude;
	
	private DataSource dataSource;

	public JdbcDistanceUpdater() {
	}
	
	public JdbcDistanceUpdater(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	private class Data{
		private Float latitude;
		private Float longitude;
		private String code;
		public Data(String code, Float latitude, Float longitude) {
			super();
			this.code = code;
			this.latitude = latitude;
			this.longitude = longitude;
		}
		public float getLatitude() {
			return latitude;
		}
		public float getLongitude() {
			return longitude;
		}
		public String getCode() {
			return code;
		}
	}
	
	@Transactional(value=TxType.REQUIRED)
	public void updateDistances(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Data> datas = jdbcTemplate.query("SELECT code, latitude, longitude FROM Geocache WHERE premium = 0 and latitude <> 0 and longitude <> 0", new RowMapper<Data>(){
			@Override
			public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Data(rs.getString("code"), rs.getFloat("latitude"), rs.getFloat("longitude"));
			}
		});
		for (Data data : datas) {
			updateDistance(jdbcTemplate, data);
		}
	}
	
	@Transactional(value=TxType.REQUIRES_NEW)
	private void updateDistance(JdbcTemplate jdbcTemplate, Data data) {
		float cacheLat = data.getLatitude();
		float cacheLon = data.getLongitude();
		if(cacheLat!=0 || cacheLon!=0){
			float distance = calculateDistance(cacheLat, cacheLon);
			jdbcTemplate.update("INSERT INTO Distance (code, distance) VALUES (?, ?)", data.getCode(), distance);
		}
	}
	
	private float calculateDistance(float latitude, float longitude){
	    return calculateDistance(this.latitude, this.longitude, latitude, longitude);
	}
	
	public static float calculateDistance(float fromLat, float fromLon, float latitude, float longitude){
		int R = 6371;
	    double fLat = Math.toRadians(fromLat);
	    double fLon = Math.toRadians(fromLon);
	    double tLat = Math.toRadians(latitude);
	    double tLon = Math.toRadians(longitude);
	    double dstLat = tLat - fLat;
	    double dstLon = tLon - fLon;

	    double a = Math.sin(dstLat/2) * Math.sin(dstLat/2) + Math.cos(fLat) * Math.cos(tLat) * Math.sin(dstLon/2) * Math.sin(dstLon/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double d = R * c;

	    return (float) (d*1000);
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
}
