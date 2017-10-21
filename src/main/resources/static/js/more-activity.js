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
				var font = w/60;
				font = Math.min(10,font);//取最小值，限定最大值(10以下就OK)
				font = Math.max(6,font);//取最大值,限定最小值
				oHtml.style.fontSize = font + 'px';
			}

		var oActivityType  = document.getElementById("activity-type"),
		oActivityWrapper = document.getElementById("activity-wrapper");

		var params = getURIParams();
		var kind =2;
		if(!params){
			window.location.href = "index.html";
		}
		switch(params.type){
			case "CITY_ACTIVITY" : {
				kind = 2;
				oActivityType.innerText = "更多城市活动";
			}break;
			case "DISTRICT_ACTIVITY" : {
				kind = 1;
				oActivityType.innerText = "更多区县活动";
			}break;
			case "SCHOOL_ACTIVITY" : {
				kind = 3;
				oActivityType.innerText = "更多学校活动";
			}break;
			default : {
				kind = 1;
				oActivityType.innerText = "更多区县活动";
			}break;
		}
		ajax({
			url : ASKURL + "/activity/byKindId/"+kind,
			success:askInfoSuccess
		});
		function askInfoSuccess(data){
			if(data && data[0].id > 0){
				var html = "";
				for(var i=0;i<data.length;i++){
					html += '<div class="activity-box">';
			    	html +=		'<a href="detail-activity.html?ID='+data[i].id+'" class="clearfix">';
			    	html += 		'<div class="img-box">'
			    	html +=				'<img src="'+IMGURL+data[i].img.split(";")[0]+'" alt="">';
			    	html +=			'</div>';
			    	html +=			'<div class="title">'+data[i].title+'</div>';
			    	html +=		'</a>';
			    	html +='</div>';
		    	}
		    	oActivityWrapper.innerHTML = html;
		    }
		}
	}

	
