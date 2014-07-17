var socket = io();
		socket.on("next", function(u){
			var callback = sting._nextCallback;
			try { callback(u); }catch(e){ console.log(e); }
		});
		socket.on("prev", function(u){
			var callback = sting._prevCallback;
			try {	callback(u); }catch(e) { console.log(e); }
		});

function __sting__(){
	
	console.log("sting created");
	this._nextCallback = function(){
		console.log("next");
	};

	this._prevCallback = function(){
		console.log("prev");
	};

	this.next = function(callback) {
		this._nextCallback = callback;
	};
	
	this.prev = function(callback) {
		this._prevCallback = callback;
	};
	
};

var sting = new __sting__();
