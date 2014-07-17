var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
  res.sendfile('index.html');
});

app.get('*.js', function(req, res){
	res.sendfile("public/js/"+req.path);
});

app.get('*.html', function(req,res){
	res.sendfile("public/views/"+req.path);
});

app.get('*.css', function(req, res){
	res.sendfile('public/css/'+req.path);
});

Array.prototype.shuffle = function(){
	var o = this;
	for(var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
	return o;
}

io.on('connection', function(socket){

	console.log("connection");
	socket.on('disconnect', function(){
	});

	socket.on("next", function(){
		console.log("n");
		socket.broadcast.emit("next");
	});

	socket.on("prev", function(){
		socket.broadcast.emit("prev");
	});

});

http.listen(3000, function(){
  console.log('listening on *:3000');
});

