/**
 * 
 * @authors lg
 * @date    2017-09-12 17:13:12
 * @version $Id$
 */
function FileUploadTool(json){
	this.toolWidth = json.width || 600;
	this.toolHeight = json.height || 400;
	this.url = json.url;
	this.callback = json.callback;
	this.oToolWrapper = this.getElementsByClass(json.wrapper)[0];    //整个组件容器
	this.init();     
}

FileUploadTool.prototype = {
	/*
		初始化容器元素和事件
	*/
	init : function(){
		var that = this;
		//向容器生成元素
		that.initElements(that);
		//获取元素对象
		that.getElementObjects(that);
		//初始化属性
		that.initAttribute(that);
		//添加元素事件
		that.initElementEvent(that);
	},
	/*
	 	弹出上传框
	 */
	show : function(){
		this.oToolWrapper.style.display = "block";
	},
	/*
	 	隐藏上传框
	 */
	hidden : function(){
		this.initToolFace(this);
		this.initAttribute(this);
		this.oUploadInput.files = [];
		this.oToolWrapper.style.display = "none";
		this.callback && this.callback();
	},
	/*
	 	初始化填充元素
	 */
	initElements : function(that){
		var html = "";
		html += '<div class="button-area button-area-init">';
		html += '	<input id="multifile-upload-input" class="upload-input" type="file" multiple="multiple">';
		html += '	<label for="multifile-upload-input" class="select-file-label select-file-label-init">选择文件</label>';
		html += '	<button class="upload-button">上传</button>';
		html += '</div>';
		html += '<div class="file-show-area file-show-area-hidden">';
		html += '	<h3 class="file-selected-title">已选择文件:</h3>';
		html += '	<div class="file-selected-wrapper">';
		html +=	'		<ul class="file-selected-box"></ul>';
		html +=	'	</div>';
		html += '</div>';
		html += '<div class="progress-area">';
		html +=	'	<span class="show-uploading">正在上传...</span>';
		html +=	'	<span class="upload-progress-number"></span>';
		html +=	'	<div class="upload-progress">';
		html +=	'		<div class="upload-progress-cover"></div>';
		html +=	'	</div>';
		html +=	'	<div class="upload-progress-detail-wrapper">';
		html +=	'		<div class="upload-progress-detail-box">';
		html +=	'			<ul class="upload-progress-detail"></ul>';
		html +=	'		</div>';
		html +=	'	</div>';
		html += '</div>';
		html += '<div class="upload-complete-area">';
		html +=	'	<p class="upload-complete-tip"></p>';
		html +=	'	<div class="complete-button">确定</div>';
		html += '</div>';
		that.oToolWrapper.innerHTML = html;
	},
	/*
	 	获取元素对象
	 */
	getElementObjects : function(that){
		that.oButtonArea = that.getElementsByClass("button-area",that.oToolWrapper)[0];  //按钮区容器
		that.oProgressArea = that.getElementsByClass("progress-area",that.oToolWrapper)[0]; //进度条区容器
		that.oFileShowArea = that.getElementsByClass("file-show-area",that.oToolWrapper)[0]; //已选文件区
		that.oUploadCompleteArea = that.getElementsByClass("upload-complete-area",that.oToolWrapper)[0]; //上传完成区
		that.oUploadInput = that.getElementsByClass("upload-input",that.oButtonArea)[0];  //文件input
		that.oSelectFileLabel = that.getElementsByClass("select-file-label",that.oButtonArea)[0]; //文件input对应的label
		that.oUploadButton = that.getElementsByClass("upload-button",that.oButtonArea)[0]; //上传按钮
		that.oFileSelectedBox = that.getElementsByClass("file-selected-box",that.oFileShowArea)[0];  //已选文件列表容器
		that.oUploadProgressNumber = that.getElementsByClass("upload-progress-number",that.oProgressArea)[0];  //主进度条百分比
		that.oUploadProgressCover = that.getElementsByClass("upload-progress-cover",that.oProgressArea)[0];    //主进度条
		that.oUploadProgressDetail = that.getElementsByClass("upload-progress-detail",that.oProgressArea)[0]; //分进度条容器
		that.oUploadSuccessNumber = that.getElementsByClass("upload-success-number",that.oUploadCompleteArea)[0]; //上传成功数
		that.oUploadFailedNumber = that.getElementsByClass("upload-failed-number",that.oUploadCompleteArea)[0]; //上传失败数
		that.oCompleteButton = that.getElementsByClass("complete-button",that.oUploadCompleteArea)[0]; //完成区按钮
		that.oUploadCompleteTip = that.getElementsByClass("upload-complete-tip",that.oUploadCompleteArea)[0]; //完成提示
		that.aShowFileLiList = [];  //已选择文件显示列表li
		that.aRemoveList = [];   //已选择文件移除选项
		that.aDetailProgressCoverList = [];   //分进度条
		that.aDetailProgressNumberList = [];  //分进度条百分比
	},
	/*
	 	初始化属性
	 */
	initAttribute : function(that){
		that.aFileList = [];        //已选择的文件
		that.aFileIsNeed = [];      //文件是否需要上传
		that.totalSize = 0;
		that.uploadedSize = 0;
		that.fileCounter = 0; //已传输文件个数
		that.uploadSuccessNum = 0;
		that.uploadFailedNum = 0;
	},
	/*
	 	初始化元素事件
	 */
	initElementEvent : function(that){
		//选择文件改变事件
		that.addEvent(that.oUploadInput,"change",function(){
			that.aFileList = that.oUploadInput.files;
			that.fixFileUploadList(that);
			that.selectFileChange(that);
		},false);
		
		//上传按钮点击事件
		that.addEvent(that.oUploadButton,"click",function(){
			that.showProgressArea(that);
			that.addDetailProgressIntoBox(that);
			//获取详细进度对象列表
			that.aDetailProgressCoverList = that.getElementsByClass("detail-progress-cover",that.oUploadProgressDetail);
			that.aDetailProgressNumberList = that.getElementsByClass("detail-progress-number",that.oUploadProgressDetail);
			//获取文件总大小
			for(var i=0;i<that.aFileIsNeed.length;i++){
				if(that.aFileIsNeed[i]){
					that.totalSize += that.aFileList[i].size;
				}
			}
			//上传文件
			that.uploadFiles(that,0);
		},false);
	},
	/*
		显示上传文件列表区
	*/
	showFileArea : function(that){
		that.removeClass(that.oFileShowArea,"file-show-area-hidden");
		that.addClass(that.oFileShowArea,"file-show-area-show");
	} ,
	/*
	 	隐藏文件上传区
	 */
	hiddenFileArea : function(that){
		that.removeClass(that.oFileShowArea,"file-show-area-show");
		that.addClass(that.oFileShowArea,"file-show-area-hidden");
	},
	/*
	 	初始化界面
	 */
	initToolFace : function(that){
		that.oButtonArea.style.display = "block";
		that.removeClass(that.oButtonArea,"button-area-upload");
		that.addClass(that.oButtonArea,"button-area-init");
		that.removeClass(that.oSelectFileLabel,"select-file-label-selected");
		that.addClass(that.oSelectFileLabel,"select-file-label-init")
		that.oSelectFileLabel.innerText = "选择文件";
	},
	/*
	  	显示按钮区二级状态
	 */
	showButtonArea : function(that){
		that.oButtonArea.style.display = "block";
		that.removeClass(that.oSelectFileLabel,"select-file-label-init");
		that.addClass(that.oSelectFileLabel,"select-file-label-selected");
		that.removeClass(that.oButtonArea,"button-area-init");
		that.addClass(that.oButtonArea,"button-area-upload");
		that.oSelectFileLabel.innerText = "重新选择文件";
		that.oUploadButton.style.display = "block";
	},
	/*
	 	隐藏按钮区
	 */
	hiddenButtonArea : function(that){
		that.oButtonArea.style.display = "none";
		that.removeClass(that.oSelectFileLabel,"select-file-label-selected");
		that.addClass(that.oSelectFileLabel,"select-file-label-init");
		that.removeClass(that.oButtonArea,"button-area-upload");
		that.addClass(that.oButtonArea,"button-area-init");
		that.oSelectFileLabel.innerText = "选择文件";
		that.oUploadButton.style.display = "none";
	},
	/*
		显示进度条区
	*/
	showProgressArea : function(that){
		that.initProgress(that);
		that.hiddenFileArea(that);
		that.hiddenButtonArea(that);
		that.oProgressArea.style.display = "block";
	},
	
	/*
	 	隐藏进度条区
	 */
	hiddenProgressArea : function(that){
		that.oProgressArea.style.display = "none";
	},
	/*
	 	初始化进度条
	 */
	initProgress : function(that){
		that.oUploadProgressCover.style.width = "0px";
		that.oUploadProgressNumber.innerText = "0%";
	},
	/*
	 	修改总进度条
	 */
	changeProgress : function(that,loaded){
		that.oUploadProgressCover.style.width = 500*loaded/that.totalSize + "px";
		that.oUploadProgressNumber.innerText = Math.floor(100*loaded/that.totalSize) + "%";
	},
	/*
	 	修改详细进度条
	 */
	changeDetailProgress : function(that,index,loaded,total){
		that.aDetailProgressCoverList[index].style.width = 200*loaded/total + "px";
		that.aDetailProgressNumberList[index].innerText = Math.floor(100*loaded/total) + "%";
	},
	/*
	 	显示执行完成区块
	 */
	showUploadCompleteArea : function(that){
		that.hiddenProgressArea(that);
		that.oUploadCompleteArea.style.display = "block";
		
	},
	hiddenUploadCompleteArea : function(that){
		that.oUploadCompleteArea.style.display = "none";
	},
	
	/*
		生成已选择文件列表
	*/
	addListIntoBox : function(that){
		var html = "";
		for(var i = 0;i <  that.aFileIsNeed.length;i++){
			if(that.aFileIsNeed[i]){
				html += '<li >';
				html += '<span class="file-selected-name">'+ that.aFileList[i].name +'</span>';
				html += '<span class="file-selected-remove">移除</span>';
				html += '</li>'
			}
		}
		that.oFileSelectedBox.innerHTML = html;
		//获取列表元素对象
		that.aShowFileLiList = that.oFileSelectedBox.getElementsByTagName("li");
		that.aRemoveList = that.getElementsByClass("file-selected-remove",that.oFileSelectedBox);
		//添加删除事件
		for(var i = 0;i < that.aRemoveList.length;i++){
			that.aRemoveList[i].index = i;
			that.aRemoveList[i].onclick = function(){
				that.removeSelectedFile(that,this.index);
			}
		}
	}, 
	
	/*
	 	生成详细进度表
	 */
	addDetailProgressIntoBox : function(that){
		var html = "";
		for(var i=0;i<that.aFileIsNeed.length;i++){
			if(that.aFileIsNeed[i]){
				html += '<li>';
				html += '<div class="detail-file-name">'+ that.aFileList[i].name +'</div>';
				html += '<div class="detail-progress">';
				html += '<div class="detail-progress-cover"></div>';
				html += '</div>'
				html += '<div class="detail-progress-number">0%</div>';
				html += '</li>';
			}
		}
		that.oUploadProgressDetail.innerHTML = html;
	},
	/*
		移除已选择的文件
	*/
	removeSelectedFile : function(that,index){
		//隐藏移除的文件名
		that.aShowFileLiList[index].style.display = "none";
		//标志为不上传
		that.aFileIsNeed[index] = false;
	},
	/*
	 	当选择文件改变
	 */
	selectFileChange : function(that){
		//显示上传文件列表区
		that.showButtonArea(that);
		that.showFileArea(that);
		//显示已选择文件
		that.addListIntoBox(that);
	},
	/*
		更改文件是否需要上传的列表
	*/
	fixFileUploadList : function(that){
		that.aFileIsNeed = [];
		for(var i=0;i<that.aFileList.length;i++){
			that.aFileIsNeed.push(true);
		}
	},
	/*
	 	上传操作完成
	 */
	uploadComplete : function(that){
		that.hiddenProgressArea(that);
		that.showUploadCompleteArea(that);
		if(that.uploadFailedNum > 0){
			var html = "";
			html += '<span class="upload-success-title">成功:	</span>';
			html += '<span class="upload-success-number">'+ that.uploadSuccessNum +'</span>';
			html += '<span class="upload-failed-title">失败:</span>';
			html += '<span class="upload-failed-number">'+ that.uploadFailedNum +'</span>';
			that.oUploadCompleteTip.innerHTML = html;
			that.oCompleteButton.innerText = "重新上传";
		}else{
			that.oUploadCompleteTip.style.color = "#5dB431";
			that.oUploadCompleteTip.innerText = "全部上传成功√";
			that.oCompleteButton.innerText = "确定";
		}
		that.addEvent(that.oCompleteButton,"click",function(){
			if(that.uploadFailedNum > 0){
				var tempNeeded = that.aFileIsNeed;
				that.hiddenUploadCompleteArea(that);
				that.initAttribute(that);
				that.aFileIsNeed = tempNeeded;
				that.aFileList = that.oUploadInput.files;
				that.selectFileChange(that);
			}else{
				that.hiddenUploadCompleteArea(that);
				that.hidden();
			}
		},false);
	},
	/*
		上传文件
	*/
	uploadFiles : function(that,index){
		if(index < that.aFileList.length){  //如果上传还没完成
			if(that.aFileIsNeed[index]){  //如果该文件需要上传
				var loaded = 0;
				var xhr = new XMLHttpRequest();
				xhr.upload.onprogress = function(e){
					loaded = that.uploadedSize + e.loaded;
					if(e.loaded > that.aFileList[index].size){
						loaded = that.uploadedSize + that.aFileList[index].size;
					}
					that.changeProgress(that,loaded);
					that.changeDetailProgress(that,that.fileCounter,e.loaded,e.total);
				}
				xhr.onreadystatechange = function(){
					if(xhr.readyState == 4 && xhr.status == 200){ //上传成功
						that.uploadSuccess(that,index,loaded);
						that.uploadFiles(that,index+1); //传输下一个文件
					}else if(xhr.readyState == 4 && xhr.status != 200){   //如果上传失败
						that.uploadFail(that,that.fileCounter);
						that.uploadFiles(that,index+1); //传输下一个文件
					}
				}
				xhr.open("post",that.url,true);
				var formData = new FormData();
				formData.append("file",that.aFileList[index]);
				xhr.send(formData);	//上传文件
			}else{
				that.uploadFiles(that,index+1);
			}
		}else{
			that.uploadComplete(that);
		}
	},
	/*
	 	上传成功
	 */
	uploadSuccess : function(that,index,loaded){
		that.fileCounter++;    //已执行传输个数增加
		that.uploadSuccessNum++;  //传输成功个数增加
		that.uploadedSize = loaded; //已传输文件大小
		that.aFileIsNeed[index] = false; //将已上传成功文件标记为不上传
	},
	/*
	 	上传失败
	 */
	uploadFail : function(that,index){
		that.aDetailProgressNumberList[index].style.color = "#DD4E42";
		that.aDetailProgressNumberList[index].innerText = "失败";
		that.uploadFailedNum++;  //传输失败个数增加
		that.fileCounter++;  //已执行传输个数增加
	},
	/*
		获取标签属性值
	*/
	getStyle :  function(obj , attr){
					return obj.currentStyle?obj.currentStyle[attr]: getComputedStyle(obj)[attr];
				},
	/*
		根据类选择器获取元素对象
	*/
	getElementsByClass : function(cName,parent){
							parent = typeof parent == "object"?parent:document;
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
						},
	/*
		给元素对象添加类选择器
	*/
	addClass : function(obj,className){
					className = className.trim();
					if(obj
						&& Object.prototype.toString.call(obj).slice(-8,-1) == "Element" 
						&& className 
						&& typeof className == "string"){
						
						var aClass = obj.className.split(" ");
						for(var i=0;i<aClass.length;i++){
							if(aClass[i] == className){
								return;
							}
						}
						obj.className += " " + className;
					}
				},
	/*
		移除标签的某个类选择器
	*/
	removeClass : function(obj,className){
					className = className.trim();
					if(obj
						&& Object.prototype.toString.call(obj).slice(-8,-1) == "Element" 
						&& className 
						&& typeof className == "string"){

						var aClass = obj.className.split(" ");
						for(var i=0;i<aClass.length;i++){
							if(aClass[i] == className){
								aClass[i] = "";
								break;
							}
						}
						obj.className = aClass.join(" ");
					}
				},
	addEvent : function(ele,eType,fn,bol){
		if(ele.addEventListener){
			ele.addEventListener(eType,fn,bol);
		}else if(ele.attachEvent){
			ele.attachEvent("on"+eType,fn);
		}else{
			ele["on"+eType] = fn;
		}
	},
	removeEvent : function(element,eType,fn,bol){
		if(ele.removeEventListener){
			ele.removeEventListener(eType,fn,bol);
		}else if(ele.detachEvent){
			ele.detachEvent("on"+eType,fn);
		}else{
			ele["on"+eType] = null;
		}
	}
}
