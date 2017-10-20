/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-10-18 15:43:21
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

		var oActivityType  = document.getElementById("activity-type"),
		oActivityWrapper = document.getElementById("activity-wrapper");

		var params = getURIParams();
		var type ="district";
		switch(params.type){
			case "DISTRICT_ACTIVITY" : {
				type = 1;
				oActivityType.innerText = "区县活动";
			}break;
			case "COMPANY_ACTIVITY" : {
				type = 2;
				oActivityType.innerText = "企业活动";
			}break;
			case "SCHOOL_ACTIVITY" : {
				type = 3;
				oActivityType.innerText = "学校活动";
			}break;
		}
		ajax({
			url : "http://cqgqt.xenoeye.org:443/activity/byKindId/"+type,
			success:askInfoSuccess
		});
		function askInfoSuccess(data){
			var html = "";
			for(var i=0;i<data.length;i++){
				html += '<div class="activity-box">';
		    	html +=		'<a href="detail-activity.html?ID='+data[i].id+'" class="clearfix">';
		    	html +=			'<img src="'+data[i].img.split(";")[0]+'" alt="">';
		    	html +=			'<div class="title">'+data[i].title+'</div>';
		    	html +=		'</a>';
		    	html +='</div>';
	    	}
	    	oActivityWrapper.innerHTML = html;
		}
	}

	
