package pojo;


public class ValueInfo {
	private String stn;//0
	private String wban;//1
	private String yearmoda;//2
	private double temp; //温度3
	private double dewp; //露点4
	private double slp; //海平面气压
	private double stp; //地面气压
	private double visib; //可见度
	private double wdsp; //风速
	private double mxspd; //当天持续最大风速
	private double gust;//瞬间最大风速
	private double max;//最高温度11
	private double min;//最低温度12
	private double prcp;//降水量
	private double sndp;//降雪深度
	private String frshtt;//恶劣天气
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
	public double getSlp() {
		return slp;
	}
	public void setSlp(double slp) {
		this.slp = slp;
	}
	public double getStp() {
		return stp;
	}
	public void setStp(double stp) {
		this.stp = stp;
	}
	public double getVisib() {
		return visib;
	}
	public void setVisib(double visiv) {
		this.visib = visiv;
	}
	public double getWdsp() {
		return wdsp;
	}
	public void setWdsp(double wdsp) {
		this.wdsp = wdsp;
	}
	public double getMxspd() {
		return mxspd;
	}
	public void setMxspd(double mxspd) {
		this.mxspd = mxspd;
	}
	public double getGust() {
		return gust;
	}
	public void setGust(double gust) {
		this.gust = gust;
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
	public double getPrcp() {
		return prcp;
	}
	public void setPrcp(double prcp) {
		this.prcp = prcp;
	}
	public double getSndp() {
		return sndp;
	}
	public void setSndp(double sndp) {
		this.sndp = sndp;
	}
	public String getFrshtt() {
		return frshtt;
	}
	public void setFrshtt(String frshtt) {
		this.frshtt = frshtt;
	}
	@Override
	public String toString() {
		return "ValueInfo [stn=" + stn + ", wban=" + wban + ", yearmoda=" + yearmoda + ", temp=" + temp + ", dewp="
				+ dewp + ", slp=" + slp + ", stp=" + stp + ", visiv=" + visib + ", wdsp=" + wdsp + ", mxspd=" + mxspd
				+ ", gust=" + gust + ", max=" + max + ", min=" + min + ", prcp=" + prcp + ", sndp=" + sndp + ", frshtt="
				+ frshtt + "]";
	}
	
	
}
