/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-10-18 16:43:05
 * @version $Id$
 */

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
			}
	
	}

	//获取用户信息cookie
	var strUserInfo = getCookie("userInfo");
	var userInfo = strUserInfo == "undefined"?null:JSON.parse(strUserInfo);
	//如果没有登录则跳转到登录页
	if(!userInfo){
		window.location.href = "login.html";
	}

	var oMenu = document.getElementById("menu"),
		aMenuLi = oMenu.getElementsByTagName("li"),
		aMenuContent = getElementsByClass("menu-content"),
		currentLiIndex = 1,
		length = 3;
		//初始显示0
		selected(0);
		//如果账号类型为二级，则隐藏待审核
		if(userInfo.type == 2){
			aMenuLi[2].style.display = "none";
			length = 2;
		}
		//添加点击事件
		for(var i=0;i<length;i++){
			aMenuLi[i].index = i;
			aMenuLi[i].style.width = 100/length + "%";
			aMenuLi[i].onclick = function(){
				selected(this.index);
			}
		}
		//选中
		function selected(index){
			if(index != currentLiIndex){
				aMenuLi[index].style.backgroundColor = "#444";
				aMenuLi[currentLiIndex].style.backgroundColor = "#666";
				aMenuContent[index].style.display = "block";
				aMenuContent[currentLiIndex].style.display = "none";
				currentLiIndex = index;
			}
		}

		//获取个人信息元素
	var aInfoLi,
		aInfoInput,
		oModify,
		oBtnSubmit,
		oTip,
		passwordChanged = false;

		

		if(userInfo){
			var html = '';
			html += '<ul>';
			if(userInfo.type == 2){
				html += '<li><div id="modify">修改</div></li>';
			}
			html += '<li><span>用户名:</span><input type="text" value="'+userInfo.user.name+'"disabled></li>';
			if(userInfo.type == 2){
				html += '<li><span>密码:</span><input type="password" disabled value="'+userInfo.user.passwd+'"></li>';
				html += '<li style="display:none"><span>确认密码:</span><input type="password" disabled></li>';
				html += '<li><span>描述:</span><input type="text" disabled value="'+userInfo.user.descr+'"></li>'
				html += '<li><span>团支部书记:</span><input type="text" disabled value="'+userInfo.user.admin+'"></li>';
				html += '<li><span>团支部副书记:</span><input type="text" disabled value="'+userInfo.user.admin2+'"></li>';
				html += '<li><span>团支部委员:</span><input type="text" disabled value="'+userInfo.user.admin3+'"></li>';
				html += '<li><span>联系方式:</span><input type="text" disabled value="'+userInfo.user.tel+'"></li>';
			}
			html += '<li><span>用户类型:</span><input type="text" disabled value="'+userInfo.user.type+'"></li>';
			if(userInfo.type == 2){
				html += '<li style="display:none"><button id="btn-submit">提交</button></li>';
				html += '<li><p id="tip"></p></li>';
			}
			html += '</ul>';
			aMenuContent[0].innerHTML = html;
			aInfoLi = aMenuContent[0].getElementsByTagName("li"),
			aInfoInput = aMenuContent[0].getElementsByTagName("input"),
			oModify = document.getElementById("modify");
			oBtnSubmit = document.getElementById("btn-submit");
			oTip = document.getElementById("tip");
			defineEvent();
		}else{
			window.location.href = "login.html";
		}

		//添加事件
		function defineEvent(){
			//修改事件
			if(oModify){
				oModify.onclick = function(){
					for(var i=0;i<aInfoInput.length-1;i++){
						aInfoInput[i].removeAttribute("disabled");
						aInfoInput[i].style.backgroundColor = "#fff";
						aInfoLi[aInfoLi.length-2].style.display = "block";
					}
				}
			}

			//密码更改事件
			aInfoInput[1].onblur = function(){
				if(trim(this.value) != userInfo.user.passwd){
					aInfoLi[3].style.display = "block";
					passwordChanged = true;
				}else{
					aInfoLi[3].style.display = "none";
					passwordChanged = false;
				}
			}
			if(aInfoInput[7]){
				aInfoInput[7].onkeyup = function(){
					this.value=this.value.replace(/\D/g,'');
				}
			}
			oBtnSubmit.onclick = function(){
				if(check() && userInfo.type == 2){
					var dataJson = {
						  admin: aInfoInput[4].value,
						  admin2: aInfoInput[5].value,
						  admin3: aInfoInput[6].value,
						  descr: aInfoInput[3].value,
						  id: userInfo.user.id,
						  name: trim(aInfoInput[0].value),
						  parentId: userInfo.user.parentId,
						  passwd: trim(aInfoInput[1].value),
						  tel: aInfoInput[7].value,
						  type: aInfoInput[8].value
					}
					ajax({
						type : "post",
						url :"http://120.77.219.167:9191/users/updateUser?_method=put",
						data :dataJson,
						jsonType:true,
						success : function(data){
							oTip.innerText = "修改信息成功";
							for(var i=0;i<aInfoInput.length-1;i++){
								aInfoInput[i].setAttribute("disabled");
								aInfoInput[i].style.backgroundColor = "#f6f6f6";
								aInfoLi[aInfoLi.length-2].style.display = "none";
								aInfoLi[3].style.display = "none";
								oTip.innerText = "";
							}

						},
						fail : function(){
							oTip.innerText = "访问服务器失败";
						}
					});
				}
			}
		}
		
		function check(){
			if(trim(aInfoInput[0].value).length < 3 || trim(aInfoInput[0].value).length > 16){
				oTip.innerText = "用户名长度必须在3~16之间";
				return false;
			}
			if(trim(aInfoInput[1].value).length < 6 || trim(aInfoInput[1].value).length >16){
				oTip.innerText = "密码长度必须在6~16之间";
				return false;
			}
			if(passwordChanged){
				if(trim(aInfoInput[1].value) != trim(aInfoInput[2].value)){
					oTip.innerText = "两次输入的密码不相同";
					return false;
				}
			}
			if(trim(aInfoInput[7].value).length < 6){
				oTip.innerText = "联系方式有误";
				return false;
			}
			
			return true;

		}
		//已发表
		var aPassDelete = [],
			aPassActivity = [],
			aCheckingDelete = [],
			aCheckingActivity = []
			dataPass = [],
			dataChecking = [],
			htmlPass = "",
			htmlChecking = "";
		var url = "http://120.77.219.167:9191/activitys/byUserId/"+userInfo.user.id;
		if(userInfo.type == 1){
			url += "/" + userInfo.user.grade;
		}else{
			url += "/THIRD";
		}
		ajax({
			url : url,
			success : askUploadedSuccess
		});
		function askUploadedSuccess(data){
			for(var i=0;i<data.length;i++){
				if(data[i].state = "passing"){
					htmlPass +='<div class="activity-box clearfix">';
            		htmlPass +=		'<a href="detail-activity.html?ID='+data[i].id+'" class="clearfix">';
              		htmlPass +=			'<img src="http://120.77.219.167:9192'+data[i].img.split(";")[0]+'" alt="">';
              		htmlPass +=				'<div class="title">'+data[i].title+'</div>';
            		htmlPass +=		'</a>';
           			htmlPass += 	'<div class="operation">';
             		htmlPass +=  	  	'<div class="state">审核通过</div>';
                  	htmlPass +=			'<div class="delete">删除</di2>';
             		htmlPass +=		'</div>';
          			htmlPass +='</div>';
          			dataPass.push(data[i]);
				}else{ 
					htmlChecking +='<div class="activity-box clearfix">';
            		htmlChecking +=		'<a href="detail-activity.html?ID='+data[i].id+'" class="clearfix">';
              		htmlChecking +=			'<img src="http://120.77.219.167:9192'+data[i].img.split(";")[0]+'" alt="">';
              		htmlChecking +=				'<div class="title">'+data[i].title+'</div>';
            		htmlChecking +=		'</a>';
           			htmlChecking += 	'<div class="operation">';
           			if(data[i].state == "checking"){
	             		htmlChecking +=  	  	'<div class="state">正在审核</div>';
	             	}else{
	             		htmlChecking +=  	  	'<div class="state" style="color:#DD4E42">审核失败</div>';
	             	}
                  	htmlChecking +=			'<div class="delete">删除</di2>';
             		htmlChecking +=		'</div>';
          			htmlChecking +='</div>';
          			dataChecking.push(data[i]);
				}
			}
			aMenuContent[1].innerHTML = htmlPass;
			aPassDelete = getElementsByClass("delete",aMenuContent[1]);
			aPassActivity = getElementsByClass("activity-box",aMenuContent[1]);
			if(userInfo.type == 2){
				aMenuContent[2].innerHTML = htmlChecking;
				aCheckingDelete = getElementsByClass("delete",aMenuContent[2]);
				aCheckingActivity = getElementsByClass("activity-box",aMenuContent[2]);
			}
			setUploadedDeleteEvent();
		}
		
		function setUploadedDeleteEvent(){
			for(var i=0; i<aPassDelete.length;i++){
				aPassDelete[i].index = i;
				aPassDelete[i].onclick = function(e){
					e = e || event;
					e.cancelBubble = true;
					if(confirm('是否删除"'+dataPass[this.index].title+'"活动')){
						deleteActivity(this.index,"passing");
					}
				}
			}
			if(userInfo.type == 2){
				for(var i=0;i<aCheckingDelete.length;i++){
					aPassDelete[i].index = i;
					aPassDelete[i].onclick = function(e){
						e = e || event;
						e.cancelBubble = true;
						if(confirm("是否删除"+dataChecking[this.index].title+"活动")){
							deleteActivity(this.index,"checking");
						}
					}
				}
			}
		}

		function deleteActivity(index,state){
			var tempData = {};
			if(state == "passing"){
				tempData = dataPass[index];
			}else{
				tempData = dataChecking[index];
			}
			ajax({
				type:"post",
				url : "http://120.77.219.167:9191/activitys/byId/"+tempData.id+"?_method=delete",
				success : function(data){
					//deleteSuccess(data,index,state);
					console.log(data)
				}
			});
		}

		function deleteSuccess(dataResp,index,state){
			if(state == "passing"){
				aMenuContent[1].removeChild(aPassActivity[index]);
			}else{
				aMenuContent[2].removeChild(aCheckingActivity[index]);
			}
		}