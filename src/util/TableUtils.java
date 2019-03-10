package util;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * Hbase数据库中gsod表的相关信息
 */
public class TableUtils {
	public static final String tableName = "gsod";
	public static final String stationName = "station";
	
	public static byte[] family = Bytes.toBytes("data");
	
	public static byte[] latitude = Bytes.toBytes("latitude");
	public static byte[] longitude = Bytes.toBytes("longitude");
	
	public static final byte[] GSOD_TEMP = Bytes.toBytes("temp");
	public static final byte[] GSOD_DEWP = Bytes.toBytes("dewp");
	public static final byte[] GSOD_SLP = Bytes.toBytes("slp");
	public static final byte[] GSOD_STP = Bytes.toBytes("stp");
	public static final byte[] GSOD_VISIB = Bytes.toBytes("visib");
	public static final byte[] GSOD_WDSP = Bytes.toBytes("wdsp");
	public static final byte[] GSOD_MXSPD = Bytes.toBytes("mxspd");
	public static final byte[] GSOD_GUST = Bytes.toBytes("gust");
	public static final byte[] GSOD_MAX = Bytes.toBytes("max");
	public static final byte[] GSOD_MIN = Bytes.toBytes("min");
	public static final byte[] GSOD_PRCP = Bytes.toBytes("prcp");
	public static final byte[] GSOD_SNDP = Bytes.toBytes("sndp");
	public static final byte[] GSOD_FRSHTT = Bytes.toBytes("frshtt");
	
	
	public static final byte[][] cols = {
			GSOD_TEMP, GSOD_DEWP, GSOD_SLP, GSOD_STP, GSOD_VISIB, GSOD_WDSP, GSOD_MXSPD, GSOD_GUST,
			GSOD_MAX, GSOD_MIN, GSOD_PRCP, GSOD_SNDP, GSOD_FRSHTT
	};
	public static final byte[][] cols2={
		latitude,longitude
	};
	
}
