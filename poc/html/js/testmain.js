require.config({
    baseUrl: "js",
    urlArgs: "rv=" + (new Date()).getTime() +"_" + parseInt(Math.random()*1000, 16),
    paths: {
        'jquery' :'jquery-3.5.1.min',
        'jquery_ui':'jquery-ui.min',
        'jsPlumb':'jsplumb'
    },
    shim: {
        "jsPlumb": {
            deps: ['jquery','jquery_ui'],
            exports: 'jsPlumb'
        }
    }
});

console.log("开始");

define(['require', 'testmaster', 'jsPlumb'],function (require, Master, jsPlumb) {
    console.log("main-define");
    
   var master = new Master();
   master.runSomething();
   console.log(jsPlumb);
   jsPlumb.ready(function(){
       console.log("run jsPlumb.ready");
   });
});