(function(window){var svgSprite='<svg><symbol id="icon-fire" viewBox="0 0 1024 1024"><path d="M320.997969 1024c-68.253867-142.011562-31.903003-223.385019 20.543358-300.022624 57.438205-83.965376 72.253742-167.066779 72.253742-167.066779s45.150589 58.686166 27.103153 150.491297c79.773507-88.797225 94.813037-230.264804 82.781413-284.439111 180.314365 126.012062 257.367957 398.835536 153.531202 601.037218 552.366739-312.534233 137.403706-780.135621 65.149964-832.837974 24.095247 52.670354 28.639105 141.851567-19.999375 185.114215-82.333427-312.278241-285.975063-376.276241-285.975063-376.276241 24.095247 161.050967-87.293272 337.141464-194.681916 468.721352-3.775882-64.221993-7.775757-108.540608-41.534702-169.978688-7.583763 116.668354-96.732977 211.737383-120.860223 328.62973-32.702978 158.267054 24.479235 274.167432 241.752445 396.595606z"  ></path></symbol></svg>';var script=function(){var scripts=document.getElementsByTagName("script");return scripts[scripts.length-1]}();var shouldInjectCss=script.getAttribute("data-injectcss");var ready=function(fn){if(document.addEventListener){if(~["complete","loaded","interactive"].indexOf(document.readyState)){setTimeout(fn,0)}else{var loadFn=function(){document.removeEventListener("DOMContentLoaded",loadFn,false);fn()};document.addEventListener("DOMContentLoaded",loadFn,false)}}else if(document.attachEvent){IEContentLoaded(window,fn)}function IEContentLoaded(w,fn){var d=w.document,done=false,init=function(){if(!done){done=true;fn()}};var polling=function(){try{d.documentElement.doScroll("left")}catch(e){setTimeout(polling,50);return}init()};polling();d.onreadystatechange=function(){if(d.readyState=="complete"){d.onreadystatechange=null;init()}}}};var before=function(el,target){target.parentNode.insertBefore(el,target)};var prepend=function(el,target){if(target.firstChild){before(el,target.firstChild)}else{target.appendChild(el)}};function appendSvg(){var div,svg;div=document.createElement("div");div.innerHTML=svgSprite;svgSprite=null;svg=div.getElementsByTagName("svg")[0];if(svg){svg.setAttribute("aria-hidden","true");svg.style.position="absolute";svg.style.width=0;svg.style.height=0;svg.style.overflow="hidden";prepend(svg,document.body)}}if(shouldInjectCss&&!window.__iconfont__svg__cssinject__){window.__iconfont__svg__cssinject__=true;try{document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>")}catch(e){console&&console.log(e)}}ready(appendSvg)})(window)