/*
 * Ext - JS Library 1.0 Alpha 3 - Rev 4
 * Copyright(c) 2006-2007, Jack Slocum.
 * 
 * http://www.extjs.com/license.txt
 */

Ext.EventManager=function(){var _1,_2,_3=false;var _4,_5,_6,_7;var E=Ext.lib.Event;var D=Ext.lib.Dom;var _a=function(){if(!_3){_3=true;if(_2){clearInterval(_2);}if(Ext.isGecko||Ext.isOpera){document.removeEventListener("DOMContentLoaded",_a,false);}if(_1){_1.fire();_1.clearListeners();}}};var _b=function(){_1=new Ext.util.Event();if(Ext.isGecko||Ext.isOpera){document.addEventListener("DOMContentLoaded",_a,false);}else{if(Ext.isIE){document.write("<s"+"cript id=\"ie-deferred-loader\" defer=\"defer\" src=\"/"+"/:\"></s"+"cript>");E.on("ie-deferred-loader","readystatechange",function(){if(this.readyState=="complete"){_a();}});}else{if(Ext.isSafari){_2=setInterval(function(){var rs=document.readyState;if(rs=="loaded"||rs=="complete"){_a();}},10);}}}E.on(window,"load",_a);};var _d=function(h,o){var _10=new Ext.util.DelayedTask(h);return function(e){e=new Ext.EventObjectImpl(e);_10.delay(o.buffer,h,null,[e]);};};var _12=function(h,el,_15,fn){return function(e){Ext.EventManager.removeListener(el,_15,fn);h(e);};};var _18=function(h,o){return function(e){e=new Ext.EventObjectImpl(e);setTimeout(function(){h(e);},o.delay||10);};};var _1c=function(_1d,_1e,opt,fn,_21){var o=(!opt||typeof opt=="boolean")?{}:opt;fn=fn||o.fn;_21=_21||o.scope;var el=typeof _1d=="string"?document.getElementById(_1d):_1d;if(!el){throw "Error listening for "+_1e+". Element "+_1d+" doesn't exist.";}var h=function(e){e=Ext.EventObject.setEvent(e);var t;if(o.delegate){t=e.getTarget(o.delegate,el);if(!t){return;}}else{t=e.target;}if(o.stopEvent===true){e.stopEvent();}if(o.preventDefault===true){e.preventDefault();}if(o.stopPropagation===true){e.stopPropagation();}if(o.normalized===false){e=e.browserEvent;}fn.call(_21||el,e,t,o);};if(o.delay){h=_18(h,o);}if(o.single){h=_12(h,el,_1e,fn);}if(o.buffer){h=_d(h,o);}fn._handlers=fn._handlers||[];fn._handlers.push([Ext.id(el),_1e,h]);E.on(el,_1e,h);if(_1e=="mousewheel"&&el.addEventListener){el.addEventListener("DOMMouseScroll",h,false);}return h;};var _27=function(el,_29,fn){var id=Ext.id(el),hds=fn._handlers;if(hds){for(var i=0,len=hds.length;i<len;i++){var h=hds[i];if(h[0]==id&&h[1]==_29){var hd=h[2];hds.splice(i,1);return E.un(el,_29,hd);}}}E.un(el,_29,fn);if(_29=="mousewheel"){E.un(el,"DOMMouseScroll",fn);}};var _31=/^(?:scope|delay|buffer|single|stopEvent|preventDefault|stopPropagation|normalized)$/;var pub={wrap:function(fn,_34,_35){return function(e){Ext.EventObject.setEvent(e);fn.call(_35?_34||window:window,Ext.EventObject,_34);};},addListener:function(_37,_38,fn,_3a,_3b){if(typeof _38=="object"){var o=_38;for(var e in o){if(_31.test(e)){continue;}if(typeof o[e]=="function"){_1c(_37,e,o,o[e],o.scope);}else{_1c(_37,e,o[e]);}}return;}return _1c(_37,_38,_3b,fn,_3a);},removeListener:function(_3e,_3f,fn){return _27(_3e,_3f,fn);},onDocumentReady:function(fn,_42,_43){if(_3){fn.call(_42||window,_42);return;}if(!_1){_b();}_1.addListener(fn,_42,_43);},onWindowResize:function(fn,_45,_46){if(!_4){_4=new Ext.util.Event();_5=new Ext.util.DelayedTask(function(){_4.fire(D.getViewWidth(),D.getViewHeight());});E.on(window,"resize",function(){if(Ext.isIE){_5.delay(50);}else{_4.fire(D.getViewWidth(),D.getViewHeight());}});}_4.addListener(fn,_45,_46);},onTextResize:function(fn,_48,_49){if(!_6){_6=new Ext.util.Event();var _4a=new Ext.Element(document.createElement("div"));_4a.dom.className="x-text-resize";_4a.dom.innerHTML="X";_4a.appendTo(document.body);_7=_4a.dom.offsetHeight;setInterval(function(){if(_4a.dom.offsetHeight!=_7){_6.fire(_7,_7=_4a.dom.offsetHeight);}},this.textResizeInterval);}_6.addListener(fn,_48,_49);},removeResizeListener:function(fn,_4c){if(_4){_4.removeListener(fn,_4c);}},fireResize:function(){if(_4){_4.fire(D.getViewWidth(),D.getViewHeight());}},ieDeferSrc:false,textResizeInterval:50};pub.on=pub.addListener;pub.un=pub.removeListener;return pub;}();Ext.onReady=Ext.EventManager.onDocumentReady;Ext.onReady(function(){var bd=Ext.get(document.body);var cls=Ext.isIE?"ext-ie":Ext.isGecko?"ext-gecko":Ext.isOpera?"ext-opera":Ext.isSafari?"ext-safari":"";if(Ext.isBorderBox){cls+=" ext-border-box";}if(Ext.isStrict){cls+=" ext-strict";}bd.addClass(cls);});Ext.EventObject=function(){var E=Ext.lib.Event;var _50={63234:37,63235:39,63232:38,63233:40,63276:33,63277:34,63272:46,63273:36,63275:35};var _51=Ext.isIE?{1:0,4:1,2:2}:(Ext.isSafari?{1:0,2:1,3:2}:{0:0,1:1,2:2});Ext.EventObjectImpl=function(e){if(e){this.setEvent(e.browserEvent||e);}};Ext.EventObjectImpl.prototype={browserEvent:null,button:-1,shiftKey:false,ctrlKey:false,altKey:false,BACKSPACE:8,TAB:9,RETURN:13,ENTER:13,SHIFT:16,CONTROL:17,ESC:27,SPACE:32,PAGEUP:33,PAGEDOWN:34,END:35,HOME:36,LEFT:37,UP:38,RIGHT:39,DOWN:40,DELETE:46,F5:116,setEvent:function(e){if(e==this||(e&&e.browserEvent)){return e;}this.browserEvent=e;if(e){this.button=e.button?_51[e.button]:(e.which?e.which-1:-1);this.shiftKey=e.shiftKey;this.ctrlKey=e.ctrlKey||e.metaKey;this.altKey=e.altKey;this.keyCode=e.keyCode;this.charCode=e.charCode;this.target=E.getTarget(e);this.xy=E.getXY(e);}else{this.button=-1;this.shiftKey=false;this.ctrlKey=false;this.altKey=false;this.keyCode=0;this.charCode=0;this.target=null;this.xy=[0,0];}return this;},stopEvent:function(){if(this.browserEvent){E.stopEvent(this.browserEvent);}},preventDefault:function(){if(this.browserEvent){E.preventDefault(this.browserEvent);}},isNavKeyPress:function(){var k=this.keyCode;k=Ext.isSafari?(_50[k]||k):k;return (k>=33&&k<=40)||k==this.RETURN||k==this.TAB||k==this.ESC;},isSpecialKey:function(){var k=this.keyCode;return k==9||k==13||k==40||k==27||(k==16)||(k==17)||(k>=18&&k<=20)||(k>=33&&k<=35)||(k>=36&&k<=39)||(k>=44&&k<=45);},stopPropagation:function(){if(this.browserEvent){E.stopPropagation(this.browserEvent);}},getCharCode:function(){return this.charCode||this.keyCode;},getKey:function(){var k=this.keyCode||this.charCode;return Ext.isSafari?(_50[k]||k):k;},getPageX:function(){return this.xy[0];},getPageY:function(){return this.xy[1];},getTime:function(){if(this.browserEvent){return E.getTime(this.browserEvent);}return null;},getXY:function(){return this.xy;},getTarget:function(_57,_58,_59){return _57?Ext.fly(this.target).findParent(_57,_58,_59):this.target;},getRelatedTarget:function(){if(this.browserEvent){return E.getRelatedTarget(this.browserEvent);}return null;},getWheelDelta:function(){var e=this.browserEvent;var _5b=0;if(e.wheelDelta){_5b=e.wheelDelta/120;if(window.opera){_5b=-_5b;}}else{if(e.detail){_5b=-e.detail/3;}}return _5b;},hasModifier:function(){return ((this.ctrlKey||this.altKey)||this.shiftKey)?true:false;},within:function(el,_5d){var t=this[_5d?"getRelatedTarget":"getTarget"]();return t&&Ext.fly(el).contains(t);},getPoint:function(){return new Ext.lib.Point(this.xy[0],this.xy[1]);}};return new Ext.EventObjectImpl();}();
