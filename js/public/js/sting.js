function Sting() {
	this._left = this._right = this._up = this._down = null;
	this.left = function(callback) {
		this._left = callback;
	}
	this.right = function(callback) {
		this._right = callback;
	}
	this.up = function(callback) {
		this._up = callback;
	}
	this.down = function(callback) {
		this._down = callback;
	}
}

var __sting__;
function sting() {
	if(__sting__ == undefined) {
		__sting__ = new Sting();
	}
	return __sting__;
};

var chat = [];
var vec  = window.location.pathname.split("/");
for(i in vec) { if(!vec[i]) continue; chat.push(vec[i]) }
chat = "/"+chat.join("-")
console.log("chatroom: "+chat);
var socket = io.connect(chat);
socket.on("left", function(u){
var callback = sting()._left;
	try { callback(u); }catch(e){ console.log(e); }
});
socket.on("right", function(u){
	var callback = sting()._right;
	try {	callback(u); }catch(e) { console.log(e); }
});
socket.on("up", function(u){
	var callback = sting()._up;
	try {	callback(u); }catch(e) { console.log(e); }
});
socket.on("down", function(u){
	var callback = sting()._down;
	try {	callback(u); }catch(e) { console.log(e); }
});
