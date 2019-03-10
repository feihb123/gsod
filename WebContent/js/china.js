		var mapInfoUrl = "/GSODAnalysisWeb/data/china.json";
		var stationInfoUrl = "/GSODAnalysisWeb/stationInfo/globalStationInfoByCountryCode";
		var countryCode = "CH";
		var stationInfoUrlP = stationInfoUrl + "?countryCode=" + countryCode;//拼接带年份的查询

		$.get(mapInfoUrl, function(data) {
			//注册地图
			echarts.registerMap('china', data);
			var myChart = echarts.init(document.getElementById('china'));

			var myData = [];

			option = {
				tooltip : {
					trigger : 'item',
					formatter : '{b}'
				},
				geo : [ {
					name : '中国地图',
					type : 'map',
					map : 'china',
					roam : true,
					selectedMode : 'single',
					label : {
						normal : {
							show : false,
						},
						emphasis : {
							label : {
								show : true
							}
						}
					},
					itemStyle : { // 定义样式
						normal : { // 普通状态下的样式
							areaColor : '#323c48',
							borderColor : '#111'
						},
						emphasis : { // 高亮状态下的样式
							areaColor : '#2a333d'
						}
					}
				} ],
				backgroundColor : '#404a59', // 图表背景色
				series : [ {
					name : '站点',
					type : 'scatter',
					coordinateSystem : 'geo',
					data : myData
				// series数据内容
				} ]
			};

			//添加点击事件
			var stationName = Document().getElementById('station_name');
			myChart.on('click', function(params) {
				stationName.value = params.name;
				alert(stationName.value);
				Document().getElementById('temp').innerHtml;
				/*var urlP="http://localhost:8080/GSODAnalysisWeb//stationWeather/StationPage?stationName="+params.name;
				$.ajax({
					type : "get",
					async : false,
					url : urlP,
					dataType : "html", //预期服务器返回的数据类型
					success : function (htmlData) { 
						var newwin=window.open('','','');
						  newwin.opener = null;
						  newwin.document.write(htmlData);
						  newwin.document.close();
					} 
				});*/
			});

			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
			myChart.showLoading();

			$.get(stationInfoUrlP, function(data) {
				myChart.hideLoading();
				myChart.setOption(option = {
					series : [ {
						name : '站点',
						type : 'scatter',
						symbolSize : '3',
						coordinateSystem : 'geo',
						data : data
					} ]
				});
			});
		});