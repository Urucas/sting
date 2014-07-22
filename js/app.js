var app = require('express')();

var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
  res.sendfile('views/error.html');
});

app.get('*.js', function(req, res){
	res.sendfile("public/"+req.path);
});

app.get('*.html', function(req,res){
	res.sendfile("public/views/"+req.path);
});

app.get('.woff', function(){
	res.sendfile("public/"+req.path);
});

app.get('.ttf', function(){
	res.sendfile("public/"+req.path);
});

app.get('.svg', function(){
	res.sendfile("public/"+req.path);
});

app.get('*.css', function(req, res){
	res.sendfile('public/'+req.path);
});

var user, chat, usersOnline = 0;
var namespaces = {};

app.get('/:namespace', function(req, res){
	chat = req.params.namespace;
	console.log(chat);

	if(namespaces.chat == undefined) {
		var nsp = io.of("/"+chat);
		nsp.on('connection', function(socket){
			socket.on("first", function(){
				console.log("move to first");
				socket.broadcast.emit("first");
			});

			socket.on("right", function(){
				socket.broadcast.emit("right");
			});

			socket.on("left", function(){
				socket.broadcast.emit("left");
			});

			socket.on("up", function(){
				socket.broadcast.emit("up");
			});

			socket.on("down", function(){
				socket.broadcast.emit("down");
			});
		});
		namespaces.chat = nsp;
	}
})

app.get('/:user/:presentation', function(req, res){
	
	user = req.params;
	chat = user.user+"-"+user.presentation;
	res.sendfile("views/index.html");
	console.log("chatroom: /"+ chat);
	
	if(namespaces.chat == undefined) {
		console.log("new namespace");
		var nsp = io.of("/"+chat);
		nsp.on('connection', function(socket){
		
			socket.on("right", function(){
				socket.broadcast.emit("right");
			});

			socket.on("left", function(){
				socket.broadcast.emit("left");
			});

			socket.on("up", function(){
				socket.broadcast.emit("up");
			});

			socket.on("down", function(){
				socket.broadcast.emit("down");
			});
		
			socket.on("first", function(){
				socket.broadcast.emit("first");
			});
		
		});
		namespaces.chat = nsp;
	}
});

http.listen(3000, function(){
	console.log("Server running");
});

