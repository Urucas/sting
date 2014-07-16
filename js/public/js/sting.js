var sting = function(options){
	
	this.socket = io();
	
	this.options = options || {
		"next" : function(){
			console.log("sting > next");
		},
		"prev" : function(){
			console.log("sting > prev");
		}
	}

	this._nextCallback = this.options.next;
	this._prevCallback = this.options.prev;

	this.next = function(callback) {
		this._nextCallback = callback;
	};
	
	this.prev = function(callback) {
		this._prevCallback = callback;
	};

	this.socket.on("next", function(u){
		var calllback = this._nextCallback;
		if(callback != undefined && callback instanceof Function) {
			callback(u);
		}
	});

	this.socket.on("prev", function(u){
		var callback = this._prevCallback;
		if(callback != undefined && callback instanceof Function) {
			callback(u);
		}
	});

};

