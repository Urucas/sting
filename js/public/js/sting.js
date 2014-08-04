function Sting() {
	this._left = this._right = this._up = this._down = this._first = this._welcome = null;
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
	this.first = function(callback) {
		this._first = callback;
	}
	this.welcome = function(callback) {
		this._welcome = callback;
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

var socket = io(chat);
socket.on("left", function(u){
var callback = sting()._left;
	try { callback(u); }catch(e){ console.log(e); }
});
socket.on("right", function(u){
	console.log("move to right");
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
socket.on("first", function(u){
	var callback = sting()._first;
	try { callback(u)} catch(e) { console.log(e); }
});
socket.on("welcome", function(u){
	var callback = sting()._welcome;
	try { callback(u) } catch(e){ console.log(e); }
})

// load user content and create reveal.js slide
$(document).ready(function(){
	$.ajax({
		data: { n : chat },
		url : "http://sting.urucas.com/api/getslides",
		dataType: 'json',
		type: "POST",
		success: function(r){
			var s = r.slides;
			if(s.type =="reveal.js") { prepareRevealJS(s) }
			else if(s.type =="slideshare") { prepareSlideshare(s); }
		},
		error: function(e){
			alert(e);
		}
	});
});

function prepareSlideshare(slide) {
	var s = sting();
	s.right(function(){ $(".btnNext").trigger("click");	});
	s.left(function(){ $(".btnPrev").trigger("click");	});
	s.first(function(){ $(".btnFirst").trigger("click"); });
	s.welcome(function(c){ 
		var e = jQuery.Event("keypress"); e.which = 13; e.keyCode = 13; 
		$(".goToSlideLabel").find("input[type='text']").val(c.cslide);
		$(".goToSlideLabel").find("input[type='text']").trigger(e);
	});
	document.getElementById("reveal-content").innerHTML = slide.content;
	
	$(".nav").css("visibility","hidden");
	$(".btnFullScreen").hide();
	$(".goToSlideLabel").find("input[type='text']").attr("disabled","disabled");
}

function prepareReveal(slide){
	// console.log(slide);
	$("#reveal-content").html(slide.content);
	Reveal.initialize({
		controls: false,
		progress: true,
		history: true,
		center: true,
		keyboard: false,
		touch:false,
		theme: Reveal.getQueryHash().theme, // available themes are in /css/theme
		transition: Reveal.getQueryHash().transition || 'default', // default/cube/page/concave/zoom/linear/fade/none
		/*
		dependencies: [
			{ src: '/reveal/lib/js/classList.js', condition: function() { return !document.body.classList; } },
			{ src: '/reveal/plugin/markdown/marked.js', condition: function() { return !!document.querySelector( '[data-markdown]' ); } },
			{ src: '/reveal/plugin/markdown/markdown.js', condition: function() { return !!document.querySelector( '[data-markdown]' ); } },
			{ src: '/reveal/plugin/highlight/highlight.js', async: true, callback: function() { hljs.initHighlightingOnLoad(); } },
			{ src: '/reveal/plugin/zoom-js/zoom.js', async: true, condition: function() { return !!document.body.classList; } },
			{ src: '/reveal/plugin/notes/notes.js', async: true, condition: function() { return !!document.body.classList; } }
		]*/
	});

	var s = sting();
	s.right(function(){ Reveal.right();	});
	s.left(function(){ Reveal.left();	});
	s.up(function(){ Reveal.up(); });
	s.down(function(){ Reveal.down(); });
	s.first(function(){ Reveal.slide(0); });
	s.welcome(function(c){ Reveal.slide(c.cslide); });
	
	socket.emit("welcome"); 
} 

