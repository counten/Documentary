
/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-10-18 21:24:26
 * @version $Id$
 */
 	//获取用户信息cookie
	var strUserInfo = getCookie("userInfo");
	var userInfo = strUserInfo == "undefined"?null:JSON.parse(strUserInfo);
	var addUserMenu = document.getElementById("addAccountOrNot");
    if(userInfo.userType != 1) {
		addUserMenu.innerHTML = ('<a href="addSubAccount.html">添加下属账户</a>');
	}

	//如果没有登录则跳转到登录页
	if(!userInfo || userInfo.userType == 4){
		window.location.href = "login.html";
	}

	var oChartWrapper = document.getElementById('chart');
	var oChart = echarts.init(oChartWrapper);
	var oListChart = document.getElementById("list-chart");
	var oDividePage = document.getElementById("divide-page");
	var aPageLi = [];
	var aNames = [];
	var aPassNum = [];	
	var isAsked = false;
		
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
		if(userInfo.userType == 1){
			oListChart.style.display = "block";
			aMenuLi[1].style.width = "50%";
			aMenuLi[2].style.display = "none";
			aMenuLi[0].style.width = "50%";
		}
		function selected(index){
			if(index != currentLiIndex){
				aMenuLi[index].style.backgroundColor = "#DD4E42";
				aMenuLi[currentLiIndex].style.backgroundColor = "#aaa";
				aMenuContent[index].style.display = "block";
				aMenuContent[currentLiIndex].style.display = "none";
				currentLiIndex = index;
				switch(currentLiIndex){
					case 0:{
						if(!isAsked){
							if(userInfo.userType == 1){
								oChartWrapper.style.display = "none";
								ajax({
						 			url : ASKURL + "/users/getBelongsNumPass?selfId="+userInfo.id,
						 			success : function(data){
						 				var oMenuDataType = document.getElementById("type-menu");
						 				var aMenuDataTypeLi = oMenuDataType.getElementsByTagName("li");
						 				var oShowBox = document.getElementById("show-box");
						 				var aDataWrapper = getElementsByClass("data-wrapper",oShowBox);
						 				var currentDataIndex = 0;
						 				aMenuDataTypeLi[0].style.backgroundColor = "#DD4E42"
						 				aMenuDataTypeLi[0].style.color = "#fff";
						 				aDataWrapper[0].style.display = "block";
						 				for(var i=0;i<aMenuDataTypeLi.length;i++){
						 					aMenuDataTypeLi[i].index = i;
						 					aMenuDataTypeLi[i].onclick = function(){
						 						if(currentDataIndex != this.index){
							 						aMenuDataTypeLi[this.index].style.backgroundColor = "#DD4E42"
							 						aMenuDataTypeLi[this.index].style.color = "#fff";
							 						aMenuDataTypeLi[currentDataIndex].style.backgroundColor = "#fff"
							 						aMenuDataTypeLi[currentDataIndex].style.color = "#454647";
							 						aDataWrapper[currentDataIndex].style.display = "none";
							 						aDataWrapper[this.index].style.display = "block";
							 						currentDataIndex = this.index;
							 					}
						 					}
						 				}
						 				var html1 = "",html2 = "",html3 = "";
						 				html1 += "<ul>";
						 				html2 += "<ul>";
						 				html3 += "<ul>";
						 				for(var i=0;i<data.length;i++){
						 					var tempHtml = "";
						 					var name = data[i].name.length > 20 ? data[i].name.substring(0,20) + "...":data[i].name;
						 					tempHtml += "<li>";
						 					tempHtml += '<span class="org-name">'+name+'</span>';
						 					tempHtml += '<span>发布活动量:   </span>';
						 					tempHtml += '<span class="info">'+data[i].numPass+'</span>';
						 					tempHtml += '<span>活动参与人次:   </span>';
						 					tempHtml += '<span class="info">'+data[i].participantsNum+'</span>';
					 						tempHtml += "</li>";
					 						if(data[i].userKind == 1){
					 							html1 += tempHtml;
					 						}else if(data[i].userKind == 2){
					 							html2 += tempHtml;
					 						}else{
					 							html3 += tempHtml;
					 						}
						 				}
						 				html1 += "</ul>";
						 				html2 += "</ul>";
						 				html3 += "</ul>";
						 				aDataWrapper[1].innerHTML = html1;
						 				aDataWrapper[2].innerHTML = html2;
						 				aDataWrapper[3].innerHTML = html3;
						 				ajax({
						 					url :ASKURL + "/users/getAllMeberNumAndAccout4",
						 					success : function(countData){
							 						var html = "";
									 				var total = 0;
									 				for(var i=0;i<data.length;i++){
									 					total += data[i].participantsNum;
									 				}
									 				html += '<div class="total">团支部总数:&nbsp;&nbsp;'+countData.account4Count+'</div>';
									 				html += '<div class="total">团员总数:&nbsp;&nbsp;'+countData.memberCount+'</div>';
									 				html += '<div class="total">区县团支部总数:&nbsp;&nbsp;'+countData.account4_district+'</div>';
									 				html += '<div class="total">区县团员总数:&nbsp;&nbsp;'+countData.member_district+'</div>';
									 				html += '<div class="total">城市团支部总数:&nbsp;&nbsp;'+countData.account4_city+'</div>';
									 				html += '<div class="total">城市团员总数:&nbsp;&nbsp;'+countData.member_city+'</div>';
									 				html += '<div class="total">学校团支部总数:&nbsp;&nbsp;'+countData.account4_school+'</div>';
									 				html += '<div class="total">学校团员总数:&nbsp;&nbsp;'+countData.member_school+'</div>';
									 				html += '<div class="total">活动参与总人次:&nbsp;&nbsp;'+total+'</div>';
									 				aDataWrapper[0].innerHTML = html;
									 				isAsked = true;
									 			}
							 				});
						 			},
						 			error : function(){
						 				//oChart.setOption(option2);
						 			}
						 		});
							}else{
								ajax({
					 			url : ASKURL + "/users/login/",
					 			data : {
					 				account : userInfo.account,
					 				passwd : userInfo.passwd
					 			},
					 			success : function(data){
					 				if(data.id > 0){

							 				userInfo = data;
							 				var	option = {
												title : {
												    text: '发表活动统计',
												    x:'center'
												},
												tooltip : {
												    trigger: 'item',
												    formatter: "{a} <br/>{b} : {c} ({d}%)"
												},
												toolbox: {
											        show : true,
											        feature : {
											            dataView : {show: true, readOnly: false},
											            saveAsImage : {show: true}
											        }
										    		},
												calculable : true,
												series : [
													    {
													        name:'活动量',
													        type:'pie',
													        radius : '55%',
													        center: ['50%', '60%'],
													        data:[
													        	 {
													            	value:userInfo.numPass, 
													            	name:'已发表 ' + userInfo.numPass ,
													            	itemStyle : {
													            		normal : {color : "rgba(0,255,0,0.8)"}
													            	}
													            },
													            {
													            	value:userInfo.numCheck, 
													            	name:'正在审核 ' + userInfo.numCheck,
													            	itemStyle : {
													            		normal : {color : "rgba(255,255,0,0.8)"}
													            	}
													            },
													            {
													            	value:userInfo.numNotPass, 
													            	name:'审核失败 ' + userInfo.numNotPass,
													            	itemStyle : {
													            		normal : {color : "rgba(255,0,0,0.5)"}
													            	}
													            },
													            {
													            	value:userInfo.numDelete, 
													            	name:'已删除 ' + userInfo.numDelete,
													            	itemStyle : {
													            		normal : {color : "rgba(255,0,0,0.8)"}
													            	}
													            }
													        ]
													    }
													]
												};
							 				// 使用刚指定的配置项和数据显示图表。 
											oChart.setOption(option); 
							 				setCookie("userInfo",JSON.stringify(data),12*3600*1000);
							 				isAsked = true;
							 			}
						 			}
						 		});
							}
						}
					}break;
					case 1:{
						getSubAccountData();
					}break;
					case 2:{
						getCheckingData();
					}break;
					case 3:{

					}break;
				}
			}
		}

	
                    
 
	


	//下属账户管理--------
	//搜索事件
	var aDeleteSubData = [],  //下属账户信息
		aDeleteSub = [],  //删除按钮
		aUpdatePasswdSub = [],
		oSubAccountBox = document.getElementById("sub-account-box"),
		aSubAccount = [];
	//	oSearchSubInput = document.getElementById("search-sub-input",aMenuContent[1]),
		//oSearchSub = document.getElementById("search-sub");

		function getSubAccountData(){
			ajax({
				url : ASKURL + "/users/getUserByParentId?parentId="+userInfo.id,
				success : function(data){
					var type = ["","一级","二级","三级","四级"];
					var html = "";
					html += '<div class="sub-total">下属账号总数:  '+data.length+'</div>';
					for(var i=0;i<data.length;i++){
						html += '<div class="sub-account clearfix">';
                 		html += 	'<ul class="clearfix">';
                    	/*html +=			'<li>';
                    	html += 			'<span>ID:</span><span class="info">'+data[i].id+'</span>';
                    	html +=			'</li>';*/
                   		html += 		'<li>';
                   		html +=  			 '<span>账户名 : </span><span class="info">'+data[i].account+'</span>';
                   		html += 		'</li>';
                   		html += 		'<li>';
                   		html +=  			 '<span>团委（支部）名称 : </span><span class="info">'+data[i].name+'</span>';
                   		html += 		'</li>';
                   		html += 		'<li>';
                   		html +=  			 '<span>团委（支部）书记 : </span><span class="info">'+(data[i].secretaryName || "空")+'</span>';
                   		html += 		'</li>';
                   		html += 		'<li>';
                   		html +=  			 '<span>联系方式 : </span><span class="info">'+(data[i].secretaryTel || "空")+'</span>';
						html += 		'</li>';
						html += 		'<li>';
                   		html +=  			 '<span>账户级别 : </span><span class="info">'+type[data[i].userType]+'</span>';
                   		html += 		'</li>';
                   		if(data[i].userType == 4){
	                   		html += 		'<li>';
	                   		html +=  			 '<span>团员总数 : </span><span class="info">'+data[i].memberNum+'</span>';
	                   		html += 		'</li>';
                   		}
                        html += 		'<li>';
                        html +=  			 '<span>发表活动数 : </span><span class="info">'+data[i].numPass+'</span>';
                        html += 		'</li>';
                		html +=  	'</ul>';
						  html +=		'<div class="delete-account">删除</div>';
						  html +=		'<div class="update-passwd">重置密码</div>';
             			html += '</div>';
					}
					oSubAccountBox.innerHTML = html;
					aDeleteSubData = data;
					aSubAccount = getElementsByClass("sub-account",oSubAccountBox);
					aUpdatePasswdSub = getElementsByClass("update-passwd",oSubAccountBox);					
					aDeleteSub = getElementsByClass("delete-account",oSubAccountBox);
					setDeleteSubEvent();
				}
			});
			
		}
		function setDeleteSubEvent(){
	    // --- 修改密码 ---
			for(var i=0;i<aUpdatePasswdSub.length;i++){
				aUpdatePasswdSub[i].index = i;
				aUpdatePasswdSub[i].onclick = function(){
					var index = this.index;
					var dataJson = aDeleteSubData[index];
					dataJson.passwd = "cqtw"+aDeleteSubData[index].account;
					if(confirm('是否重置 "'+aDeleteSubData[index].account+'" 的密码')){
						ajax({
							type : "post",
							url : ASKURL + "/users/updateUser?_method=put",
							data :dataJson,
							jsonType:true,
							success : function(data){
								if(data != null){
									alert("密码重置为："+data.passwd);
								}else{
									alert("操作失败");
								}
							},
							error : function(){
								alert("操作失败");
							}
						});
					}
				}
			}	
		// -- 删除用户
			for(var i=0;i<aDeleteSub.length;i++){
				aDeleteSub[i].index = i;
				aDeleteSub[i].onclick = function(){
					var index = this.index;
					if(confirm('是否删除 "'+aDeleteSubData[index].account+'" 下属账号')){
						ajax({
							type:"post",
							url : ASKURL + "/users/deleteUser?selfId="+userInfo.id+"&userId="+aDeleteSubData[this.index].id+"&_method=delete",
							success : function(data){
								if(data == 0){
									aSubAccount[index].style.display = "none";
								}else{
									alert("操作失败");
								}
							},
							error : function(){
								alert("操作失败");
							}
						});
					}
				}
			}
		}




		
	//---审核-----
	var aActivityBox = [],
		aPass = [],
		aStop = [],
		aData = [];

		//请求待审核数据
		function getCheckingData(){
			ajax({
				url : ASKURL + "/activity/bySelfId/"+userInfo.id,
				success : function(data){
					var html = "";
					if(data.length == 0){
						html += '<p class="null">暂无待审核活动</p>';
					}else{
						for(var i=0;i<data.length;i++){
							html += '<div class="activity-box clearfix">'
	          				html += 	'<a href="detail-activity.html?ID='+data[i].id+'" class="clearfix">';
	          				html += 		'<div class="img-box">'
	            			html += 			'<img src="'+IMGURL+data[i].img.split(";")[0]+'" alt="">';
	            			html += 		'</div>';
	           				html += 		'<div class="title">'+data[i].title+'</div>';
	          				html += 	'</a>';
	          				html += 	'<div class="operation">';
	          				html += 		'<div class="pass">通过</div>';
	           				html += 		'<div class="stop">禁止</div>';
	          				html += 	'</div>';
	         				html += '</div>';
						}
					}
					aMenuContent[2].innerHTML = html;
					aActivityBox = getElementsByClass("activity-box",aMenuContent[2]);
					aPass = getElementsByClass("pass",aMenuContent[2]);
					aStop = getElementsByClass("stop",aMenuContent[2]);
					aData = data;
					setOperationEvent();
				}
			});
		}

		
		function setOperationEvent(){
			for(var i=0;i<aPass.length;i++){
				aPass[i].index = i;
				aPass[i].onclick = function(){
					if(confirm('是否同意 "'+aData[this.index].title+'" 活动发布?')){
						checkPass(1,this.index);
					}
				}
			}

			for(var i=0;i<aStop.length;i++){
				aStop[i].index = i;
				aStop[i].onclick = function(){
					if(confirm('是否禁止 "'+aData[this.index].title+'" 活动发布?')){
						checkPass(0,this.index);
					}
				}
			}
		}

		function checkPass(sign,index){
			ajax({
				type:"post",
				url : ASKURL + "/activity/checkPass/byId/"+userInfo.id+"/"+aData[index].id+"/"+sign+"?_method=put",
				success : function(){
					aMenuContent[2].removeChild(aActivityBox[index]);
				},
				error : function(){
					alert("操作失败");
				}
			});
		}

		resize(function(){
			oChart.resize();
		});

