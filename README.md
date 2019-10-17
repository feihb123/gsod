# gsod
Hbase搭建的气象项目  
  
前端主要使用Echarts图表显示  
原始站点储存在Mysql中，后已转移至HBase  
此项目已删除站点中无数据站点，点进去的每个点都有数据显示 

气象原始数据格式：
```txt
STN--- WBAN   YEARMODA    TEMP       DEWP      SLP        STP       VISIB      WDSP     MXSPD   GUST    MAX     MIN   PRCP   SNDP   FRSHTT
007026 99999  20160622    94.7  7    66.7  7  9999.9  0  9999.9  0    6.2  4    0.0  7  999.9  999.9   100.4*   87.8*  0.00I 999.9  000000
007026 99999  20160623    88.3 24    69.7 24  9999.9  0  9999.9  0    6.2 24    0.0 24  999.9  999.9    98.6*   78.8*  0.00I 999.9  000000
```

站点原始数据格式：
```txt
"USAF","WBAN","STATION NAME","CTRY","STATE","ICAO","LAT","LON","ELEV(M)","BEGIN","END"
"007005","99999","CWOS 07005","","","","","","","20120127","20120127"
"007011","99999","CWOS 07011","","","","","","","20120101","20121129"
"007018","99999","WXPOD 7018","","","","+00.000","+000.000","+7018.0","20130710","20130730"
```

站点数据导入方式：
```SQL
创建station_info表
CREATE TABLE `station_info`(
`stn` char(7) not null, -- STN编号
`wban` char(6) not null, -- WBAN编号
`station_name` varchar(50) not null, -- 站点名称
`country_code` char(4), -- 国家编号
`state_code` char(4), -- 美国州编号
`ICAO` char(6), -- ICAO编号
`latitude` decimal(7,3), -- 纬度
`longitude` decimal(7,3), -- 经度
`elevation` decimal(6,1), -- 海拔
`begin_date` date, -- 数据开始日期
`end_date` date,-- 数据结束日期
primary key(`stn`,`wban`)
)DEFAULT CHARSET=utf8;

将isd-history.txt中的数据导入表中：
load data local infile 'D:/isd-history.txt' -- TXT文件存放路径  
into table station_info -- 要将数据导入的表名  
fields terminated by ',' optionally enclosed by '"' escaped by '"'  
lines terminated by '\n';
```
