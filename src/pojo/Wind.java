package pojo;

public class Wind {
	String stn;
	String wban;
	String yearmoda;
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
	public String getYearmoda() {
		return yearmoda;
	}
	public void setYearmoda(String yearmoda) {
		this.yearmoda = yearmoda;
	}
	public Double getWdsp() {
		return wdsp;
	}
	public void setWdsp(Double wdsp) {
		this.wdsp = wdsp;
	}
	public Double getMxspd() {
		return mxspd;
	}
	public void setMxspd(Double mxspd) {
		this.mxspd = mxspd;
	}
	public Double getGust() {
		return gust;
	}
	public void setGust(Double gust) {
		this.gust = gust;
	}
	Double wdsp;
	Double mxspd;
	Double gust;
	
}
