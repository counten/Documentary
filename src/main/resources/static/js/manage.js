/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-10-18 21:24:26
 * @version $Id$
 */
 	var oMenu = document.getElementById("menu"),
		aMenuLi = oMenu.getElementsByTagName("li"),
		aMenuContent = getElementsByClass("menu-content"),
		currentLiIndex = 1;
		//初始显示0
		selected(0);

		//添加事件
		for(var i=0;i<aMenuLi.length;i++){
			aMenuLi[i].index = i;
			aMenuLi[i].onclick = function(){
				selected(this.index);
			}
		}

		function selected(index){
			if(index != currentLiIndex){
				aMenuLi[index].style.backgroundColor = "#444";
				aMenuLi[currentLiIndex].style.backgroundColor = "#666";
				aMenuContent[index].style.display = "block";
				aMenuContent[currentLiIndex].style.display = "none";
				currentLiIndex = index;
			}
		}

	var oChart = echarts.init(document.getElementById('chart')); 
		option = {
		title : {
		    text: '发表活动统计',
		    x:'center',
		    fontSize:"2rem"
		},
		tooltip : {
		    trigger: 'item',
		    formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend: {
		    orient : 'vertical',
		    x : 'left',
		    data:['已发表','已删除','正在审核','审核失败']
		},
		lable:{
	        normal:{
	            textStyle:{
	                fontsize:'1.6rem'
	            }
	        }
	    },
		toolbox: {
		    show : true,
		    feature : {
		        mark : {show: true},
		        magicType : {
		            show: true, 
		            type: ['pie', 'funnel'],
		            option: {
		                funnel: {
		                    x: '25%',
		                    width: '50%',
		                    funnelAlign: 'left',
		                    max: 10000
		                }
		            }
		        }
		    }
		},
		calculable : true,
		series : [
		    {
		        name:'访问来源',
		        type:'pie',
		        radius : '55%',
		        center: ['50%', '60%'],
		        data:[
		            {value:310, name:'审核失败'},
		            {value:234, name:'正在审核'},
		            {value:135, name:'已删除'},
		            {value:1548, name:'已发表'}
		        ]
		    }
		]
		};
                    
 
	// 使用刚指定的配置项和数据显示图表。 
	oChart.setOption(option); 







	//审核
	
	window.onload = function(){
		var oHtml = document.getElementsByTagName('html')[0];

			//通过标签名('')
			run();//先执行一次abc函数
			window.onresize =run;
			function run(){
				var w = window.innerWidth//浏览器窗口大小
				var font = w/100;
				font = Math.min(10,font);//取最小值，限定最大值(10以下就OK)
				font = Math.max(6,font);//取最大值,限定最小值
				oHtml.style.fontSize = font + 'px';
				oChart.resize();
			}
	}
