const Hapi=require("hapi");
const joi=require("joi");

const server=new Hapi.Server({"host":"localhost","port":3000});



server.start(error=>{
    if(error)
    {
        throw error;
    }
    console.log("Server is running at port 3000 "+server.info.uri);
});

server.route({
    method:"GET",
    path:"/",
    handler:function(request,response){
        return 'Hello !node';
    }
});

server.route({
    method:"GET",
    path:"/getdata/{name}",
    handler:function(request,response){
      return 'Hello '+request.params.name;
    }

});


server.route({
    method:"POST",
    path:"/adddata",
    handler:(request,response)=>{
        response(request,response);
    }

});