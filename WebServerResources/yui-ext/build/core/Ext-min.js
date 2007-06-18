/*
 * Ext - JS Library 1.0 Alpha 3 - Rev 4
 * Copyright(c) 2006-2007, Jack Slocum.
 * 
 * http://www.extjs.com/license.txt
 */

Ext={};window["undefined"]=window["undefined"];Ext.apply=function(o,c,_3){if(_3){Ext.apply(o,_3);}if(o&&c){for(var p in c){o[p]=c[p];}}return o;};(function(){var _5=0;var ua=navigator.userAgent.toLowerCase();var _7=document.compatMode=="CSS1Compat",_8=ua.indexOf("opera")>-1,_9=ua.indexOf("webkit")>-1,_a=ua.indexOf("msie")>-1,_b=ua.indexOf("msie 7")>-1,_c=!_9&&ua.indexOf("gecko")>-1,_d=_a&&!_7,_e=(ua.indexOf("windows")!=-1||ua.indexOf("win32")!=-1),_f=(ua.indexOf("macintosh")!=-1||ua.indexOf("mac os x")!=-1);if(_a&&!_b){try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}}Ext.apply(Ext,{isStrict:_7,SSL_SECURE_URL:"javascript:false",BLANK_IMAGE_URL:"http:/"+"/www.yui-ext.com/s.gif",emptyFn:function(){},applyIf:function(o,c){if(o&&c){for(var p in c){if(typeof o[p]=="undefined"){o[p]=c[p];}}}return o;},id:function(el,_14){_14=_14||"ext-gen";el=Ext.getDom(el);var id=_14+(++_5);return el?(el.id?el.id:(el.id=id)):id;},extend:function(){var io=function(o){for(var m in o){this[m]=o[m];}};return function(sc,sp,_1b){var F=function(){},scp,spp=sp.prototype;F.prototype=spp;scp=sc.prototype=new F();scp.constructor=sc;sc.superclass=spp;if(spp.constructor==Object.prototype.constructor){spp.constructor=sp;}sc.override=function(o){Ext.override(sc,o);};scp.override=io;Ext.override(sc,_1b);return sc;};}(),override:function(_20,_21){if(_21){var p=_20.prototype;for(var _23 in _21){p[_23]=_21[_23];}}},namespace:function(){var a=arguments,o=null,i,j,d,rt;for(i=0;i<a.length;++i){d=a[i].split(".");rt=d[0];eval("if (typeof "+rt+" == \"undefined\"){"+rt+" = {};} o = "+rt+";");for(j=1;j<d.length;++j){o[d[j]]=o[d[j]]||{};o=o[d[j]];}}},urlEncode:function(o){if(!o){return "";}var buf=[];for(var key in o){var _2d=typeof o[key];if(_2d!="function"&&_2d!="object"){buf.push(encodeURIComponent(key),"=",encodeURIComponent(o[key]),"&");}}buf.pop();return buf.join("");},each:function(_2e,fn,_30){if(typeof _2e.length=="undefined"||typeof _2e=="string"){_2e=[_2e];}for(var i=0,len=_2e.length;i<len;i++){if(fn.call(_30||_2e[i],_2e[i],i,_2e)===false){return i;}}},combine:function(){var as=arguments,l=as.length,r=[];for(var i=0;i<l;i++){var a=as[i];if(a instanceof Array){r=r.concat(a);}else{if(a.length!==undefined&&!a.substr){r=r.concat(Array.prototype.slice.call(a,0));}else{r.push(a);}}}return r;},escapeRe:function(s){return s.replace(/([.*+?^${}()|[\]\/\\])/g,"\\$1");},callback:function(cb,_3a,_3b,_3c){if(typeof cb=="function"){if(_3c){cb.defer(_3c,_3a,_3b||[]);}else{cb.apply(_3a,_3b||[]);}}},getDom:function(el){if(!el){return null;}return el.dom?el.dom:(typeof el=="string"?document.getElementById(el):el);},isOpera:_8,isSafari:_9,isIE:_a,isIE7:_b,isGecko:_c,isBorderBox:_d,isWindows:_e,isMac:_f,useShims:((_a&&!_b)||(_c&&_f))});})();Ext.namespace("Ext","Ext.util","Ext.grid","Ext.dd","Ext.tree","Ext.data","Ext.form","Ext.menu","Ext.state","Ext.lib");Ext.apply(Function.prototype,{createCallback:function(){var _3e=arguments;var _3f=this;return function(){return _3f.apply(window,_3e);};},createDelegate:function(obj,_41,_42){var _43=this;return function(){var _44=_41||arguments;if(_42===true){_44=Array.prototype.slice.call(arguments,0);_44=_44.concat(_41);}else{if(typeof _42=="number"){_44=Array.prototype.slice.call(arguments,0);var _45=[_42,0].concat(_41);Array.prototype.splice.apply(_44,_45);}}return _43.apply(obj||window,_44);};},defer:function(_46,obj,_48,_49){var fn=this.createDelegate(obj,_48,_49);if(_46){return setTimeout(fn,_46);}fn();return 0;},createSequence:function(fcn,_4c){if(typeof fcn!="function"){return this;}var _4d=this;return function(){var _4e=_4d.apply(this||window,arguments);fcn.apply(_4c||this||window,arguments);return _4e;};},createInterceptor:function(fcn,_50){if(typeof fcn!="function"){return this;}var _51=this;return function(){fcn.target=this;fcn.method=_51;if(fcn.apply(_50||this||window,arguments)===false){return;}return _51.apply(this||window,arguments);};}});Ext.applyIf(String,{escape:function(_52){return _52.replace(/('|\\)/g,"\\$1");},leftPad:function(val,_54,ch){var _56=new String(val);if(ch==null){ch=" ";}while(_56.length<_54){_56=ch+_56;}return _56;},format:function(_57){var _58=Array.prototype.slice.call(arguments,1);return _57.replace(/\{(\d+)\}/g,function(m,i){return _58[i];});}});String.prototype.toggle=function(_5b,_5c){return this==_5b?_5c:_5b;};Ext.applyIf(Number.prototype,{constrain:function(min,max){return Math.min(Math.max(this,min),max);}});Ext.applyIf(Array.prototype,{indexOf:function(o){for(var i=0,len=this.length;i<len;i++){if(this[i]==o){return i;}}return -1;},remove:function(o){var _63=this.indexOf(o);if(_63!=-1){this.splice(_63,1);}}});Date.prototype.getElapsed=function(_64){return Math.abs((_64||new Date()).getTime()-this.getTime());};
