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


	//下属账户管理--------
	//搜索事件
	var aDeleteSubData = [],  //下属账户信息
		aDeleteSub = [],  //删除按钮
		oSubAccountBox = document.getElementById("sub-account-box"),
		aSubAccount = [],
		oSearchSubInput = document.getElementById("search-sub-input",aMenuContent[1]),
		oSearchSub = document.getElementById("search-sub");

		oSearchSub.onclick = function(){
			if(checkSubId()){
				
			}
		}
		
		function checkSubId(){
			if(oSearchSubInput.value.length == 0){
				oSearchActivityTip.innerText = "id不能为空";
				return false;
			}
			if(oSearchSubInput.value.length > 11){
				oSearchActivityTip.innerText = "id过大";
				return false;
			}
			return true;
		}

		function setDeleteSubEvent(){
			for(var i=0;i<aDeleteSub.length;i++){
				aDeleteSub[i].index = i;
				aDeleteSub[i].onclick = function(){

				}
			}
		}

	//---审核-----
	var aActivityBox = [],
		aPass = [],
		aStop = [],
		aData = [];
		aData[0] = {
			title:"那是你金口难开"
		}
		aActivityBox = getElementsByClass("activity-box",aMenuContent[2]);
		aPass = getElementsByClass("pass",aMenuContent[2]);
		aStop = getElementsByClass("stop",aMenuContent[2]);
		setOperationEvent();
		function setOperationEvent(){
			for(var i=0;i<aPass.length;i++){
				aPass[i].index = i;
				aPass[i].onclick = function(){
					if(confirm('是否同意 "'+aData[this.index].title+'" 活动发布?')){
						console.log("pass");
					}
				}
			}

			for(var i=0;i<aStop.length;i++){
				aStop[i].index = i;
				aStop[i].onclick = function(){
					if(confirm('是否禁止 "'+aData[this.index].title+'" 活动发布?')){
						console.log("stop");
					}
				}
			}
		}




	//删除活动-----------
	var oActivityWrapper = document.getElementById("activity-wrapper"),
		oActivityIdInput = document.getElementById("activity-id-input",aMenuContent[3]),
		oSearchActivity = document.getElementById("search-activity"),
		oSearchActivityTip = document.getElementById("search-activity-tip"),
		aOperationDelete = [],
		aActivityDeleteBox = [],
		aDeleteData = [];

		//搜索事件
		oSearchActivity.onclick = function(){
			if(checkActivityId()){

			}
		}
		function checkActivityId(){
			if(oActivityIdInput.value.length == 0){
				oSearchActivityTip.innerText = "id不能为空";
				return false;
			}
			if(oActivityIdInput.value.length > 11){
				oSearchActivityTip.innerText = "id过大";
				return false;
			}

			return true;
		}
		//只允许输入数字
		oActivityIdInput.onkeyup = function(){
			if(this.value.length = 1 && this.value == "0"){
				this.value = "";
			}
			this.value=this.value.replace(/\D/g,'');
		}
		

		aOperationDelete = getElementsByClass("delete",oActivityWrapper);
		setDeleteEvent();
		function setDeleteEvent(){
			for(var i=0;i<aOperationDelete.length;i++){
				aOperationDelete[i].index = i;
				aOperationDelete[i].onclick = function(){
					var index = this.index;
					if(confirm('是否删除"'+dataPass[index].title+'"活动')){
						ajax({
							type:"post",
							url : "http://cqgqt.xenoeye.org:443/activity/byId/"+userInfo.id+"/"+tempData.id+"?_method=delete",
							success : function(data){
								if(data.state == 4){
									oActivityWrapper.removeChild(aActivityDeleteBox[index]);
								}
							},
							fail : function(){
								alert("删除失败");
							}
						});
					}
				}
			}
		}
	
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
