require.config({
    baseUrl: "js",
    urlArgs: "rv=" + (new Date()).getTime() + "_" + parseInt(Math.random() * 1000, 16),
    paths: {
        'jquery': 'jquery-3.5.1.min',
        'jquery_ui': 'jquery-ui.min',
        'jsPlumb': 'jsplumb'
    },
    shim: {
        "jsPlumb": {
            deps: ['jquery', 'jquery_ui'],
            exports: 'jsPlumb'
        }
    }
});

console.log("开始");

define(['require', 'pageFlowMaster', 'jsPlumb'], function (require, Master, jsPlumb) {
    console.log("pageFlowMain-define");

    return {
        start:function(sceneType, sceneId) {
            console.log("pageFlowMain-start")
            console.log(arguments);
            var master = new Master();
    
            // console.log(jsPlumb);
            jsPlumb.ready(function () {
                var defaultConfig = master.getDefaultConfig({type: 'jspulumb', scence: 'pageFlow'});
                var jsplumbInstance = jsPlumb.getInstance(defaultConfig);
                master.initWorkSpace(jsplumbInstance);
            });
        }
    };
    
});