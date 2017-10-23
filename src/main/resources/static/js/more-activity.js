/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-10-18 15:43:21
 * @version $Id$
 */

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
				oActivityType.innerText += "更多区县活动";
			}break;
		}
		ajax({
			url : ASKURL + "/activity/byKindId/"+kind,
			success:askInfoSuccess
		});
		function askInfoSuccess(data){
			if(data && data[0].id > 0){
				var html = "";
				if(data.length == 0){
					html += '<p class="null">暂无活动</p>';
				}else{
					for(var i=0;i<data.length;i++){
						html += '<div class="activity-box clearfix">';
				    	html +=		'<a href="detail-activity.html?ID='+data[i].id+'" class="clearfix">';
				    	html += 		'<div class="img-box">'
				    	html +=				'<img src="'+IMGURL+data[i].img.split(";")[0]+'" alt="">';
				    	html +=			'</div>';
				    	html +=			'<div class="title">'+data[i].title+'</div>';
				    	html +=		'</a>';
				    	html +='</div>';
			    	}
			    }
		    	oActivityWrapper.innerHTML = html;
		    }
		}
		resize();

	
