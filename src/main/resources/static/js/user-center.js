/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-10-18 16:43:05
 * @version $Id$
 */


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
		//如果账号类型为二或三级，则隐藏待审核
		if(userInfo.userType == 2 || userInfo.userType == 3){
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
				aMenuLi[index].style.backgroundColor = "#5CB451";
				aMenuLi[currentLiIndex].style.backgroundColor = "#999";
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
			html += '<li><div id="modify">修改</div></li>';
			html += '<li><span>用户名:</span><input type="text" value="'+userInfo.account+'"disabled></li>';
			html += '<li><span>密码:</span><input type="password" disabled value="'+userInfo.passwd+'"></li>'
			html += '<li style="display:none"><span>确认密码:</span><input type="password" disabled></li>';
			if(userInfo.userType == 2 || userInfo.userType == 3){
				html += '<li><span>团委书记:</span><input type="text" disabled value="'+userInfo.secretaryName+'"></li>';
				html += '<li><span>团委书记电话:</span><input type="text" disabled value="'+userInfo.secretaryTel+'"></li>';
			}else if(userInfo.userType == 4){
				html += '<li><span>团支部书记:</span><input type="text" disabled value="'+userInfo.secretaryName+'"></li>';
				html += '<li><span>团支部书记电话:</span><input type="text" disabled value="'+userInfo.secretaryTel+'"></li>';
				html += '<li><span>团委支部名称:</span><input type="text" disabled value="'+userInfo.name+'"></li>';
				html += '<li><span>团员总数:</span><input type="text" disabled value="'+userInfo.memberNum+'"></li>';
			}
			html += '<li><span>用户类型:</span><input type="text" disabled value="'+userInfo.userType+'"></li>';
			html += '<li style="display:none"><button id="btn-submit">提交</button></li>';
			html += '<li><p id="tip"></p></li>';
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
				if(trim(this.value) != userInfo.passwd){
					aInfoLi[3].style.display = "block";
					passwordChanged = true;
				}else{
					aInfoLi[3].style.display = "none";
					passwordChanged = false;
				}
			}
			if(userInfo.userType == 2 || userInfo.userType == 3){
				aInfoInput[4].onkeyup = limitNum;
			}else{
				aInfoInput[4].onkeyup = limitNum;
				aInfoInput[6].onkeyup = limitNum;
			}
			function limitNum(){
				this.value=this.value.replace(/\D/g,'');
			}
			oBtnSubmit.onclick = function(){
				if(check()){
					var dataJson;
					if(userInfo.userType == 3){
						dataJson = {
						  "account": trim(aInfoInput[0].value),
						  "id": userInfo.id,
						  "memberNum": userInfo.memberNum,
						  "name": userInfo.name,
						  "parentId": userInfo.parentId,
						  "passwd": trim(aInfoInput[1].value),
						  "pparentId": userInfo.pparentId,
						  "secretaryName": aInfoInput[3].value,
						  "secretaryTel": aInfoInput[4].value,
						  "userKind": userInfo.userKind,
						  "userType": userInfo.userType
						}
					}else{
						dataJson = {
						  "account": trim(aInfoInput[0].value),
						  "id": userInfo.id,
						  "memberNum": aInfoInput[6].value,
						  "name": trim(aInfoInput[5].value),
						  "parentId": userInfo.parentId,
						  "passwd": trim(aInfoInput[1].value),
						  "pparentId": userInfo.pparentId,
						  "secretaryName": aInfoInput[3].value,
						  "secretaryTel": aInfoInput[4].value,
						  "userKind": userInfo.userKind,
						  "userType": userInfo.userType
						}
					}
					ajax({
						type : "post",
						url : ASKURL + "/users/updateUser?_method=put",
						data :dataJson,
						jsonType:true,
						success : function(data){
							for(var i=0;i<aInfoInput.length-1;i++){
								aInfoInput[i].setAttribute("disabled",true);
								aInfoInput[i].style.backgroundColor = "#f8f8f8";
							}
							aInfoLi[aInfoLi.length-2].style.display = "none";
							aInfoLi[3].style.display = "none";
							oTip.innerText = "";
							userInfo = dataJson;
							setCookie("userInfo",JSON.stringify(userInfo));
							alert("修改信息成功");
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
			if(trim(aInfoInput[4].value).length < 6 || trim(aInfoInput[4].value).length > 11){
				oTip.innerText = "电话有误";
				return false;
			}
			
			if(trim(aInfoInput[3].value).length == 0){
				if(userInfo.userType == 3){
					oTip.innerText = "团委书记不能为空";
				}else{
					oTip.innerText = "团支部书记不能为空";
				}
				return false;
			}
			if(userInfo.userType == 4){
				if(trim(aInfoInput[5].value).length == 0){
					oTip.innerText = "团支部名称不能为空";
					return false;
				}
				if(trim(aInfoInput[6].value).length == 0){
					oTip.innerText = "团员总数不能为空";
					return false;
				}
				if(trim(aInfoInput[6].value).length > 10){
					oTip.innerText = "团员总数过大";
					return false;
				}
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

		ajax({
			url : ASKURL + "/activity/byUserId/"+userInfo.id,
			success : askUploadedSuccess
		});
		function askUploadedSuccess(data){
			for(var i=0;i<data.length;i++){
				if(data[i].state == 2){
					htmlPass +='<div class="activity-box clearfix">';
            		htmlPass +=		'<a href="detail-activity.html?ID='+data[i].id+'" class="clearfix">';
              		htmlPass += 		'<div class="img-box">'
              		htmlPass +=				'<img src="'+IMGURL+data[i].img.split(";")[0]+'" alt="">';
              		htmlPass +=			'</div>';
              		htmlPass +=			'<div class="title">'+data[i].title+'</div>';
              		htmlPass +=		'</a>';
              		htmlPass += 		'<div class="operation">';
             		htmlPass +=  	  		'<div class="state">审核通过</div>';
                  	htmlPass +=				'<div class="delete">删除</div>';
             		htmlPass +=			'</div>';
            		
          			htmlPass +='</div>';
          			dataPass.push(data[i]);
				}else if(data[i].state != 4){ 
					htmlChecking +='<div class="activity-box clearfix">';
            		htmlChecking +=		'<a href="detail-activity.html?ID='+data[i].id+'" class="clearfix">';
              		htmlChecking += 		'<div class="img-box">'
              		htmlChecking +=				'<img src="'+IMGURL+data[i].img.split(";")[0]+'" alt="">';
              		htmlChecking +=			'</div>';
              		htmlChecking +=			'<div class="title">'+data[i].title+'</div>';
            		
           			htmlChecking += 		'<div class="operation">';
           			if(data[i].state == 1){
	             		htmlChecking +=  	  	'<div class="state">正在审核</div>';
	             	}else{
	             		htmlChecking +=  	  	'<div class="state" style="color:#DD4E42">审核失败</div>';
	             	}
                  	htmlChecking +=				'<div class="delete">删除</div>';
             		htmlChecking +=			'</div>';
             		htmlChecking +=		'</a>';
          			htmlChecking +='</div>';
          			dataChecking.push(data[i]);
				}
			}
			aMenuContent[1].innerHTML = htmlPass;
			aPassDelete = getElementsByClass("delete",aMenuContent[1]);
			aPassActivity = getElementsByClass("activity-box",aMenuContent[1]);
			if(userInfo.userType == 4){
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
			if(userInfo.userType == 4){
				for(var i=0;i<aCheckingDelete.length;i++){
					aCheckingDelete[i].index = i;
					aCheckingDelete[i].onclick = function(e){
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
				url : ASKURL + "/activity/byId/"+userInfo.id+"/"+tempData.id+"?_method=delete",
				success : function(data){
					if(data.state == 4){
						deleteSuccess(data,index,state);
					}else{
						alert("操作失败");
					}
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
		resize();