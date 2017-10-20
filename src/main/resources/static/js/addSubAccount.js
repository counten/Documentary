/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-10-19 22:38:17
 * @version $Id$
 */
var oUsernameInput = document.getElementById("username"),
	 oPasswordInput = document.getElementById("password"),
	 oSurePasswordInput = document.getElementById("sure-password"),
	 oBtnSubmit = document.getElementById("btn-submit"),
	 oTip = document.getElementById("tip");

	 oBtnSubmit.onclick = function(){
	 	if(check()){
	 		oTip.innerText = "";
	 		ajax({
	 			type:"get",
	 			url : "http://cqgqt.xenoeye.org:443/users/login/",
	 			data : {
	 				name : trim(oUsernameInput.value),
	 				passwd : trim(oPasswordInput.value)
	 			},
	 			success : askSuccess,
	 			error : askFail
	 		});
	 	}
	 } 

	 function askFail(json){
	 	oTip.innerText = "访问服务器失败";
	 }

	 function askSuccess(data){
	 	
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
