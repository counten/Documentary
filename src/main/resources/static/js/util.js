
	//全局变量
	var IMGURL = "http://oy5ic3yhc.bkt.clouddn.com/";
	var ASKURL = "http://cqgqt.xenoeye.org:443";
    // var ASKURL = "http://127.0.0.1:8080";
	/*
		util class document
	*/

	/* 
		class  name: getStyle
		description: get a attribute of a html element
		@param  obj: object
		@param attr: attribute
		return string
	*/
	function getStyle(obj , attr){
		return obj.currentStyle?obj.currentStyle[attr]: getComputedStyle(obj)[attr];
	}

	/* 
		class  name: getElementsByClass
		description: get  html elements by class name
		@param parent: the parent object of elements
		@param  cName: class name
		return array
	*/
	function getElementsByClass(cName,parent){
		parent = parent || document;
		var arr = [];
		if(parent.getElementsByClassName){
			arr = parent.getElementsByClassName(cName);
		}else{
			var aChild = parent.getElementsByTagName("*");
			for(var i=0;i<aChild.length;i++){
				var aClassName = aChild[i].className.split(" ");
				for(var j=0;j<aClassName.length;j++){
					if(aClassName[j] == cName)
						arr.push(aChild[i]);
					break;
				}
			}
		}
		return arr;
	}
	/*
		class name: ajax
		description: 
		@param type: 访问方式(get/post),默认为get,string
		@param url : 访问地址,必传，string
		@param data: 发送的数据，需要发送数据时才写，json
		@param success：请求数据成功时调用的方法，function
		@param error：请求数据失败时调用的方法，function
	*/
	function ajax(json){
		var type = json.type || "get";
		var url = json.url;
		var data = json.data;
		var jsonType = json.jsonType;
		var jointData = json.jointData === undefined?true:false; 
		var success = json.success;
		var error = json.error;
		var contentType = json.contentType || "application/x-www-form-urlencoded";
		var contentTypeNeed = json.contentTypeNeed;
		var dataStr = "";
		if(data && jointData){
			if(jsonType){
				dataStr = JSON.stringify(data);
			}else{
				for(var key in data){
					dataStr += key+"="+data[key]+"&"

				}
				/*dataStr += "_=" + new Date().getTime();*/
				if(type.toLowerCase() == "get"){
					url += "?" + dataStr;
				}
			}
		}else{
			dataStr = data;
		}
		var xhr = new XMLHttpRequest();
		xhr.open(type,url,true);
		if(jsonType){
			xhr.setRequestHeader('content-type','application/json;charset=UTF-8');
		}else{
			if(contentTypeNeed){
				xhr.setRequestHeader('content-type',contentType);
			}
		}
		xhr.send(dataStr);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status>=200&&xhr.status<300){
					try{
						var json = JSON.parse(xhr.responseText);
					}catch(Error){
						console.log("json parse error");
					}finally{
						if(json){
							success&&success(json);
						}else{
							success&&success(xhr.responseText)
						}
					}
				}else{
					error&&error(xhr.responseText);
				}
			}
		}
	}
	/*
		class name: addEvent
		description: 给某元素对象添加一个事件
		@param obj: 元素对象
		@param event : 事件名称,没有前缀on ，string
		@param fn : 事件执行的方法，function
	*/
	function addEvent( obj , event , fn ){
		document.addEventListener?obj.addEventListener( event , fn , false ):obj.attachEvent( 'on'+event , fn );
	};

	/*
		class name : clearBubble
		description: 清除某个元素的事件冒泡
		@param obj: 元素对象
		@param event : 事件名称，没有前缀on ，string
	*/
	function clearBubble(obj,event){
		addEvent(obj,event,function(e){
			e = e || event;
			e.cancelBubble = true;
		});
	}

	 function trim(str){
		return str.replace(/^\s+|\s+$/gm,'');
	 }

	 /*
		class name:getParams
		description: 获取get传递的参数
		return object 参数键值对对象
	*/
	function getURIParams(){
		var location = document.location.href;
		if(location.indexOf("?") < 0){
			return null;
		}
		var strParams = location.substring( location.indexOf("?") + 1);
		return divideParams(strParams,"&");
	}
	/*
		class name: divideParams
		description:分割 分割符分离的参数对 例: name=qy&passowrd=123456
		return object 返回参数键值对对象{name:qy,password:123456}
	*/
	function divideParams(strParams,separator){
		var params = {};
		var aStrParams = strParams.split(separator);
		for(var param in aStrParams){
			var aTempParam = aStrParams[param].trim().split("=");
			params[aTempParam[0]] = aTempParam[1];
		}
		return params;
	}
	/*
		class name:getCookie
		description:通过key获取cookie值
		return string key对应的值
	*/
	function getCookie(key){
		var params = divideParams(document.cookie,";");
		return unescape(params[key]);
	}
	/*
		class name:setCookie
		description:设置cookie
		@param key string 
		@param value string
		@param expire 整数毫秒数
	*/
	function setCookie(key,value,expire){
		var exp = new Date();
		expire?exp.setTime(exp.getTime()+expire):exp.setTime(exp.getTime()+7*24*60*60);
		document.cookie = key + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";";
	}
	/*
		class name:delCookie
		description:通过key删除cookie
	*/
	function delCookie(key){
		var exp = new Date();
		var value = getCookie(key);
		if(value){
			exp.setTime(exp.getTime()-1);
			document.cookie = key + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";";
		}
	}
	
	function delCookieAll(){
		var params = divideParams(document.cookie,";");
		for(var key in params){
			delCookie(key);
		}
	}

	function resize(callback){
	  var oHtml = document.getElementsByTagName('html')[0];
		//通过标签名('')
		run();//先执行一次abc函数
		window.onresize =run;
		function run(){
			var w = window.innerWidth//浏览器窗口大小
			var font = w/20;
			font = Math.min(60,font);//取最小值，限定最大值(10以下就OK)
			font = Math.max(16,font);//取最大值,限定最小值
			oHtml.style.fontSize = font + 'px';
			callback && callback();
		}
	}
	function loginout(){
		delCookie("userInfo");
		window.location.href = "index.html";
	}
	function back(){
		window.history.back();
	}