/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-10-19 22:38:17
 * @version $Id$
 */

 //获取用户信息cookie
	var strUserInfo = getCookie("userInfo");
	var userInfo = strUserInfo == "undefined"?null:JSON.parse(strUserInfo);
	//如果没有登录则跳转到登录页
	if(!userInfo){
		window.location.href = "login.html";
	}
var oUsernameInput = document.getElementById("username"),
	 oPasswordInput = document.getElementById("password"),
	 oSurePasswordInput = document.getElementById("sure-password"),
	 oBtnSubmit = document.getElementById("btn-submit"),
	 oTip = document.getElementById("tip");

	 oBtnSubmit.onclick = function(){
	 	if(check()){
	 		oTip.innerText = "";
	 		ajax({
	 			type:"post",
	 			url : ASKURL + "/users/createUser?selfId="+userInfo.id+"&_method=put",
	 			data : {
	 			  	  account : trim(oUsernameInput.value),
		 			  passwd : trim(oPasswordInput.value),
	 				  memberNum: 0,
					  name: "空",
					  parentId: userInfo.id,
					  pparentId: userInfo.parentId,
					  secretaryName: "空",
					  secretaryTel: "空",
					  userKind: userInfo.userKind,
					  userType: userInfo.userType + 1	
	 			},
	 			jsonType : true,
	 			success : askSuccess,
	 			error : askFail
	 		});
	 	}
	 } 

	 function askFail(json){
	 	oTip.innerText = "访问服务器失败";
	 }

	 function askSuccess(data){
	 	if(data.id < 0){
	 		oTip.style.color = "#DD4E42";
		 	oTip.innerText = "该账号已存在,请重新输入";
		 }else{
		 	oTip.style.color = "#5dB431";
		 	oTip.innerText = "创建下属账户成功";
		 }
	 }


	 function check(){
		var username = trim(oUsernameInput.value);
		var password = trim(oPasswordInput.value);
		var surePassword = trim(oSurePasswordInput.value);
		if(username.length == 0){
			oTip.innerText = "账号不能为空";
			return false;
		}
		if(username.length < 3 || username.length > 16 ){
			oTip.innerText = "账号长度在3~16之间";
			return false;
		}
		if(password.length == 0){
			oTip.innerText = "密码不能为空";
			return false;
		}
		if(password.length <6 || password.length > 16 ){
			oTip.innerText = "密码长度在6~16之间";
			return false;
		}
		if(surePassword.length == 0){
			oTip.innerText = "请确认密码";
			return false;
		}
		if(surePassword != password){
			oTip.innerText = "两次输入的密码不相同";
			return false;
		}
		return true;
	 }
