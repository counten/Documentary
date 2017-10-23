/**
 * 
 * @authors liugang (742230063@qq.com)
 * @date    2017-10-18 08:26:46
 * @version 1.0
 */
	//获取用户信息cookie
	var strUserInfo = getCookie("userInfo");
	var userInfo = strUserInfo == "undefined"?null:JSON.parse(strUserInfo);
	//如果没有登录则跳转到登录页
	if(!userInfo){
		window.location.href = "login.html";
	}

 var oActivityTitle = document.getElementById("activity-title"),
	  oActivityMember = document.getElementById("activity-member"),
	  oactivityParticipantsNum = document.getElementById("activity-participantsNum"),
 	 oActivityDate = document.getElementById("activity-date"),
 	 oActivityLocation = document.getElementById("activity-location"),
 	 oActivityContent = document.getElementById("activity-content"),
 	 obtnUpload = document.getElementById("btn-upload"),
 	 oActivityImg = document.getElementById("activity-img"),
 	 oTip = document.getElementById("tip"),
 	 oImgBox = document.getElementById("img-box"),
 	 aImgUpload = [],
 	 imgNum = 0,
 	 aImgUploadNeed = [],
 	 aImgPreviewBox = [],
 	 aImgDelete = [];
 	 obtnUpload.onclick = function(){
 	 	if(check()){
 	 		var data = new FormData();
 	 		data.append("userId",userInfo.id);
 	 		data.append("userName",userInfo.name);
 	 		data.append("userAccount",userInfo.account);
 	 		data.append("userType",userInfo.userType);
 	 		data.append("userKind",userInfo.userKind);
 	 		data.append("title",trim(oActivityTitle.value));
 	 		data.append("time",trim(oActivityDate.value));
 	 		data.append("location",trim(oActivityLocation.value));
			data.append("participants",trim(oActivityMember.value));
			data.append("participantsNum",trim(oactivityParticipantsNum.value)); 
 	 		data.append("content",oActivityContent.value);
 	 		for(var i=0;i<aImgUpload.length;i++){
 	 			if(aImgUploadNeed[i]){
		 	 		data.append("files",aImgUpload[i]);
		 	 	}
	 	 	}
 	 		oTip.style.color = "#5dB431";
 	 		oTip.innerText = "正在上传图片....";
 	 		ajax({
	 			type : "post",
	 			url : ASKURL + "/activity/uploadActivity",
	 			data : data,
	 			contentTypeNeed : false,
	 			jointData : false,
	 			success : function(data){
	 				if(data.id > 0){
		 				if(data.state == 1){
		 					alert("提交成功，等待审核");
			 				oTip.innerText = "提交成功，等待审核";
			 			}else{
			 				alert("发表成功");
			 				oTip.innerText = "发表成功";
			 			}
			 		}else{
			 			oTip.style.color = "#DD4E42";
 	 					oTip.innerText = "输入数据有误";
			 		}
	 			},
	 			error : uploadFail
	 		});
 	 	}
 	 }

 	 oActivityImg.onchange = function(){
 	 	if(imgNum < 3){
	 	 	var html = "";
	 	 	var windowURL = window.URL || window.webkitURL;
			if(oActivityImg.files){
				for(var i=0;i<oActivityImg.files.length;i++){
					if(imgNum < 3){
						aImgUpload.push(oActivityImg.files[i]);
						aImgUploadNeed.push(true);
						imgNum++;
					}else{
						alert("最多添加3张图片");
					}
				}
				for(var i=0;i<aImgUploadNeed.length;i++){
					if(aImgUploadNeed[i]){
						html += '<div class="img-preview-box">';
						html += '<img src="'+ windowURL.createObjectURL(aImgUpload[i])+'"/>'
						html += '<div class="img-delete">删除</div>';
						html += '</div>';
					}
				}
			}else{
				html += '<div style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + oMessageImg.value + '\');"></div>';
			}
			oImgBox.innerHTML = html;
			aImgPreviewBox = getElementsByClass("img-preview-box",oImgBox);
			aImgDelete = getElementsByClass("img-delete",oImgBox);
			setImgDeleteEvent();
		}else{
			alert("最多添加3张图片");
		}
 	 }

 	 function setImgDeleteEvent(){
 	 	for(var i=0;i<aImgDelete.length;i++){
 	 		aImgDelete[i].index = i;
 	 		aImgDelete[i].onclick = function(){
 	 			var count = 0;
 	 			for(var i=0;i<aImgUploadNeed.length;i++){
 	 				if(aImgUploadNeed[i]){
 	 					if(count == this.index){
 	 						aImgUploadNeed[i] = false;
 	 						oImgBox.removeChild(aImgPreviewBox[this.index]);
 	 						imgNum--;
 	 						setImgDeleteEvent();
 	 						break;
 	 					}
 	 					count++;
 	 				}
 	 			}
 	 		}
 	 	}
 	 }

 	 function uploadFail(){
 	 	oTip.style.color = "#DD4E42";
 	 	oTip.innerText = "连接服务器失败，请稍后重试";
 	 }
 	 function check(){
 	 	if(trim(oActivityTitle.value).length < 6 || trim(oActivityTitle.value).length > 36){
 	 		oTip.innerText = "标题字数必须在6~36字之间";
 	 		return false;
 	 	}
 	 	if(trim(oActivityMember.value).length < 2){
 	 		oTip.innerText = "请输入参与活动的人员";
 	 		return false;
 	 	}
 	 	if(trim(oActivityDate.value).length < 0){
			oTip.innerText = "请输入活动时间";
 	 		return false;
 	 	}
 	 	if(trim(oActivityLocation.value).length < 2){
 	 		oTip.innerText = "请输入活动地点";
 	 		return false;
 	 	}
 	 	if(trim(oActivityContent.value).length < 20){
 	 		oTip.innerText = "内容不能少于20字";
 	 		return false;
 	 	}
 	 	if(oActivityImg.files.length == 0){
 	 		oTip.innerText = "至少添加1张图片";
 	 		return false;
 	 	}
 	 	if(imgNum > 3){
 	 		oTip.innerText = "最多添加3张图片";
 	 		return false;
 	 	}
 	 	var count = 0;
 	 	for(var i=0;i<aImgUploadNeed.length;i++){
 	 		if(aImgUploadNeed[i]){
	 	 		if(aImgUpload[i].size > 4*1024*1024){
	 	 			oTip.innerText = "第"+(count+1)+"张图片大小超过4M";
	 	 			return false;
	 	 		}
	 	 		count++;
	 	 	}
 	 	}
 	 	return true;
 	 }
 	 resize();
 	 

