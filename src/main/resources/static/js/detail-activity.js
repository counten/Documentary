/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-10-18 16:05:46
 * @version $Id$
 */

window.onload = function(){
		
		var html = document.getElementsByTagName('html')[0];

			//通过标签名('')
			run();//先执行一次abc函数
			window.onresize =run;
			function run(){
				var w = window.innerWidth//浏览器窗口大小
				var font = w/60;
				font = Math.min(10,font);//取最小值，限定最大值(10以下就OK)
				font = Math.max(6,font);//取最大值,限定最小值
				html.style.fontSize = font + 'px';
			}
	
	}

	var oActivityTitle = document.getElementById("activity-title"),
		oActivityImg = document.getElementById("activity-img"),
		oActivityContent = document.getElementById("activity-content"),
		aActivityInfo = getElementsByClass("info");

		var params = getURIParams();
		if(!params){
			window.location.href = "index.html";
		}else{
			ajax({
				url : "http://cqgqt.xenoeye.org:443/activity/byActId/"+params.ID,
				success : askInfoSuccess
			});
		}

		/*askInfoSuccess({
			  "content": "那倒是可能国际法的开始能够克服的数据库高科技双方都不挂科精神病的价格表副书记的看不惯就开始变得更快举报方式的空间办公室减肥的恐怖",
			  "id": 1,
			  "img": "img/me.jpg;img/test.jpg;img/test.jpg;img/me.jpg;img/test.jpg;img/test.jpg;img/me.jpg;img/test.jpg;img/test.jpg;",
			  "location": "西南大学",
			  "member": "你今年撒打开机房内啥飞机的",
			  "state": "string",
			  "time": "2017-10-18",
			  "title": "你上课多晶硅的富士康",
			  "userGrade": "2",
			  "userId": 1,
			  "userName": "幽影",
			  "userType": "学校"

		});*/
		function askInfoSuccess(data){
			if(data.id > 0){
				oActivityTitle.innerText = data.title;
				aActivityInfo[0].innerText = data.userName;
				aActivityInfo[1].innerText = data.userType;
				aActivityInfo[2].innerText = data.id;
				var state = "";
				if(data.state == 2){
					state = "审核通过";
				}else if(data.state == 1){
					state = "正在审核";
				}else{
					state = "审核失败";
				}
				aActivityInfo[3].innerText = state;
				aActivityInfo[4].innerText = data.time;
				aActivityInfo[5].innerText = data.location;
				aActivityInfo[6].innerText = data.participants;
				aActivityInfo[7].innerText = data.userKind;
				aActivityInfo[8].innerText = data.createTime;

				//图片
				var html = "";
				var imgUrl = data.img.split(";");
				for(var i=0;i<imgUrl.length;i++){
					html += '<div class="img-box">';
					html +=		'<img src="http://cqgqt.xenoeye.org:9192'+imgUrl[i]+'" alt="">';
					html += '</div>';
				}
				oActivityImg.innerHTML = html;
				//内容
				oActivityContent.innerText = data.content;
			}else{
				oActivityTitle.innerText = "访问数据有误";
			}
		}

		