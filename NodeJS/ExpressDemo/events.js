var events=require("events");

var eventsEmitter = new events.EventEmitter();

eventsEmitter.on("myevent",listener1);
eventsEmitter.addListener("myevent", listener2);

function listener1(){console.log("Ths is listner1");}

function listener2(){console.log("Ths is listner2");}

eventsEmitter.emit("myevent");