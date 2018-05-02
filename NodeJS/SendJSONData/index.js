var http=require('http');

http.createServer(function (req, res) {
    
     var data={"firstname":"Vivek","lastname":"Patel"};
     var jsondata=JSON.stringify(data);
    res.writeHead(200, {'Content-Type': 'application/JSON'});
    res.write(jsondata);
    res.end();
}).listen(8080); 