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
app.get('/:user/:presentation', function(req, res){
	user = req.params;
	chat = user.user+"-"+user.presentation;

	res.sendfile("views/index.html");
	console.log("chatroom: "+ chat);
	io.of(chat)
		.on('connection', function(socket){
		usersOnline++;
		console.log("Users online: "+usersOnline);
		socket.on('disconnect', function(){
			usersOnline--;
		});

		socket.on("left", function(){
			socket.broadcast.emit("left");
		});

		socket.on("right", function(){
			socket.broadcast.emit("right");
		});

		socket.on("up", function(){
			socket.broadcast.emit("up");
		});

		socket.on("down", function(){
			socket.broadcast.emit("down");
		});
	});
});

http.listen(3000, function(){
  console.log('listening on *:3000');
});

