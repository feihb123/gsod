package pojo;
/**
 * 从数据库中查到的数据
 * @author feihb
 *
 */
public class StationInfo {
	private String stn;
	private String wban;
	private double lat;
	private double lon;
	@Override
	public String toString() {
		return "StationInfo [stn=" + stn + ", wban=" + wban + ", lat=" + lat + ", lon=" + lon + "]";
	}
	public String getStn() {
		return stn;
	}
	public void setStn(String stn) {
		this.stn = stn;
	}
	public String getWban() {
		return wban;
	}
	public void setWban(String wban) {
		this.wban = wban;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
}
