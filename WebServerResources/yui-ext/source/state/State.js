/*
 * Ext - JS Library 1.0 Alpha 3 - Rev 4
 * Copyright(c) 2006-2007, Jack Slocum.
 * 
 * http://www.extjs.com/license.txt
 */

/**
 * @class Ext.state.Provider
 * Abstract base class for provider implementations. This class provides methods
 * for encoding and decoding <b>typed</b> variables including dates and defines the 
 * Provider interface.
 */
Ext.state.Provider = function(){
    Ext.state.Provider.superclass.constructor.call(this);
    /**
     * @event statechange
     * Fires when a state change occurs.
     * @param {Provider} this
     * @param {String} key The state key which was changed
     * @param {String} value The encoded value for the state
     */
    this.events = {
        "statechange": true
    };
    this.state = {};
};
Ext.extend(Ext.state.Provider, Ext.util.Observable, {
    /**
     * Get the current value for a key.
     * @param {String} name
     * @param {Mixed} defaultValue
     * @return {Mixed}
     */
    get : function(name, defaultValue){
        return typeof this.state[name] == "undefined" ?
            defaultValue : this.state[name];
    },
    
    /**
     * Clear a value from the state.
     */
    clear : function(name){
        delete this.state[name];
        this.fireEvent("statechange", this, name, null);
    },
    
    /**
     * Set the value for a key.
     * @param {String} name
     * @param {Mixed} value
     */
    set : function(name, value){
        this.state[name] = value;
        this.fireEvent("statechange", this, name, value);
    },
    
    /**
     * Decodes a string previously encoded with {@link #encodeValue}.
     * @param {String} value
     * @return {Mixed} The value
     */
    decodeValue : function(cookie){
        var re = /^(a|n|d|b|s|o)\:(.*)$/;
        var matches = re.exec(unescape(cookie));
        if(!matches || !matches[1]) return; // non state cookie
        var type = matches[1];
        var v = matches[2];
        switch(type){
            case "n":
                return parseFloat(v);
            case "d":
                return new Date(Date.parse(v));
            case "b":
                return (v == "1");
            case "a":
                var all = [];
                var values = v.split("^");
                for(var i = 0, len = values.length; i < len; i++){
                    all.push(this.decodeValue(values[i]));
                }
                return all;
           case "o":
                var all = {};
                var values = v.split("^");
                for(var i = 0, len = values.length; i < len; i++){
                    var kv = values[i].split("=");
                    all[kv[0]] = this.decodeValue(kv[1]);
                }
                return all;
           default:
                return v;
        }
    },
    
    /**
     * Encode a value including type information.
     * @param {Mixed} value
     * @return {String}
     */
    encodeValue : function(v){
        var enc;
        if(typeof v == "number"){
            enc = "n:" + v;
        }else if(typeof v == "boolean"){
            enc = "b:" + (v ? "1" : "0");
        }else if(v instanceof Date){
            enc = "d:" + v.toGMTString();
        }else if(v instanceof Array){
            var flat = "";
            for(var i = 0, len = v.length; i < len; i++){
                flat += this.encodeValue(v[i]);
                if(i != len-1) flat += "^";
            }
            enc = "a:" + flat;
        }else if(typeof v == "object"){
            var flat = "";
            for(var key in v){
                if(typeof v[key] != "function"){
                    flat += key + "=" + this.encodeValue(v[key]) + "^";
                }
            }
            enc = "o:" + flat.substring(0, flat.length-1);
        }else{
            enc = "s:" + v;
        }
        return escape(enc);        
    }
});

/**
 * @class Ext.state.Manager
 * This is the global state manager. By default all components that are "state aware" check this class
 * for state information if you don't pass them a custom state provider. In order for this class
 * to be useful, it must be initialized with a provider when your application initializes.
 <pre><code>
// in your initialization function
init : function(){
   Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
   ...
   // supposed you have a {@link Ext.BorderLayout}
   var layout = new Ext.BorderLayout(...);
   layout.restoreState();
   // or a {Ext.BasicDialog}
   var dialog = new Ext.BasicDialog(...);
   dialog.restoreState();
 </code></pre>
 * @singleton
 */
Ext.state.Manager = function(){
    var provider = new Ext.state.Provider();
    
    return {
        /**
         * Configures the default provider for your application.
         * @param {Provider} stateProvider
         */
        setProvider : function(stateProvider){
            provider = stateProvider;
        },
        
        /**
         * Get the current value for a key.
         * @param {String} name
         * @param {Mixed} defaultValue
         * @return {Mixed}
         */
        get : function(key, defaultValue){
            return provider.get(key, defaultValue);
        },
        
        /**
         * Set the value for a key.
         * @param {String} name
         * @param {Mixed} value
         */
         set : function(key, value){
            provider.set(key, value);
        },
        
        /**
         * Clear a value from the state.
         */
        clear : function(key){
            provider.clear(key);
        },
        
        /**
         * Gets the currently configured provider.
         * @return {Provider}
         */
        getProvider : function(){
            return provider;
        }
    };
}();

/**
 * @class Ext.state.CookieProvider
 * @extends Ext.state.Provider
 * The default Provider implementation. The example below includes all valid configuration options and their
 * default values.
 <pre><code>
   var cp = new Ext.state.CookieProvider({
       path: "/",
       expires: new Date(new Date().getTime()+(1000*60*60*24*7)); //7 days
       domain: null,
       secure: false       
   })
   Ext.state.Manager.setProvider(cp);
 </code></pre>
 * @constructor
 * Create a new CookieProvider
 * @param {Object} config The configuration object
 */
Ext.state.CookieProvider = function(config){
    Ext.state.CookieProvider.superclass.constructor.call(this);
    this.path = "/";
    this.expires = new Date(new Date().getTime()+(1000*60*60*24*7)); //7 days
    this.domain = null;
    this.secure = false;
    Ext.apply(this, config);
    this.state = this.readCookies();
};

Ext.extend(Ext.state.CookieProvider, Ext.state.Provider, {
    set : function(name, value){
        if(typeof value == "undefined" || value === null){
            this.clear(name);
            return;
        }
        this.setCookie(name, value);
        Ext.state.CookieProvider.superclass.set.call(this, name, value);
    },
        
    clear : function(name){
        this.clearCookie(name);
        Ext.state.CookieProvider.superclass.clear.call(this, name);
    },
        
    readCookies : function(){
        var cookies = {};
        var c = document.cookie + ";";
        var re = /\s?(.*?)=(.*?);/g;
    	var matches;
    	while((matches = re.exec(c)) != null){
            var name = matches[1];
            var value = matches[2];
            if(name && name.substring(0,3) == "ys-"){
                cookies[name.substr(3)] = this.decodeValue(value);
            }
        }
        return cookies;
    },
    
    setCookie : function(name, value){
        document.cookie = "ys-"+ name + "=" + this.encodeValue(value) +
           ((this.expires == null) ? "" : ("; expires=" + this.expires.toGMTString())) +
           ((this.path == null) ? "" : ("; path=" + this.path)) +
           ((this.domain == null) ? "" : ("; domain=" + this.domain)) +
           ((this.secure == true) ? "; secure" : "");
    },
    
    clearCookie : function(name){
        document.cookie = "ys-" + name + "=null; expires=Thu, 01-Jan-70 00:00:01 GMT" +
           ((this.path == null) ? "" : ("; path=" + this.path)) +
           ((this.domain == null) ? "" : ("; domain=" + this.domain)) +
           ((this.secure == true) ? "; secure" : "");
    }
});
