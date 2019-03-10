package pojo;
/**
 * 用来封装前台显示温度类图表所需的数据
 * @author 化十
 *
 */


public class Temp {
	private String stn;
	private String wban;
	private String yearmoda;
	private double temp;
	private double dewp;
	private double max;
	private double min;
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
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getDewp() {
		return dewp;
	}
	public void setDewp(double dewp) {
		this.dewp = dewp;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	@Override
	public String toString() {
		return "Temp [stn=" + stn + ", wban=" + wban + ", yearmoda=" + yearmoda + ", temp=" + temp + ", dewp=" + dewp
				+ ", max=" + max + ", min=" + min + "]";
	}
	
	
}
