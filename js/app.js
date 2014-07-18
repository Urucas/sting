var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
  res.sendfile('index.html');
});

app.get('*.js', function(req, res){
	res.sendfile("public/"+req.path);
});

app.get('*.html', function(req,res){
	res.sendfile("public/views/"+req.path);
});

app.get('*.css', function(req, res){
	res.sendfile('public/'+req.path);
});

var usersOnline = 0;

io.on('connection', function(socket){

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

http.listen(3000, function(){
  console.log('listening on *:3000');
});

