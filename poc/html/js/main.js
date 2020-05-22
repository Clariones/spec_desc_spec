var Main = new function () {
    var me = this;
    var jsplumbInstance = null;

    function initToolkitBar() {
        console.log("我要初始化工具条");
        addNodeInToolkit("page", "tool_page.png", "页面");
        addNodeInToolkit("request", "tool_request.png", "请求");
        addSeperatorInToolkit();
        addNodeInToolkit("start_way", "tool_start.png", "打开方式");
        $("#drawing_pannel").droppable({
            scope: "ss",
            drop: function (event, ui) {
                var left = parseInt(ui.offset.left - $(this).offset().left);
                var top = parseInt(ui.offset.top - $(this).offset().top);
                me.addNodeInDrawingPannel(left, top, ui.draggable[0]);
            }
        });

        me.jsplumbInstance.bind("beforeDrop", function (params) {
            console.log(params.sourceId + "->" + params.targetId);
            // params.source is the element from which the new connection is being dragged
            // params.endpoint is the associated endpoint
            var w34Stroke = 'rgba(50, 50, 200, 1)';
            var w34HlStroke = 'rgba(180, 180, 200, 1)';
            var arrowCommon = { foldback: 0.7, fill: 'blue', width: 10 };
            var overlays = [
                [ "Arrow", { location: 1 }, arrowCommon ],
                [ "Label", { location: 0.1, label: "条件1", labelStyle:{color:'black', textAlign:'left'}}, arrowCommon ]
            ];
            me.jsplumbInstance.connect({
                source: params.sourceId,
                target: params.targetId,
                anchors:["Continuous", "Continuous"],
                overlays: overlays,
                dragOptions:{
                    cursor:'crosshair'
                  },
                  connectorStyle: { strokeWidth: 5 },
                paintStyle:{"stroke-width": 10, stroke: "#456", strokeWidth: 3},
                endpoint: "Blank"
                //endpointStyle:{ gradient : {stops:[[0, w34Stroke], [1, w34HlStroke]], offset:'78%', innerRadius:'73%'}, radius:35 }
            });
            return false;

        });
    }

    function addSeperatorInToolkit() {
        $("#tool_kit_pannel").append('<div class="tool_kit_seperator"/>');
    }

    function addNodeInToolkit(nodeCode, nodeIcon, nodeTitle) {
        var tkId = "toolKit_" + nodeCode;
        var tool = $('<div id="' + tkId + '" class="tool_kit_icon" title="' + nodeTitle + '" data-code="' + nodeCode + '"><img src="./icons/' + nodeIcon + '"></img></div>');
        $(tool).draggable({
            helper: "clone",
            scope: "ss",
        });


        // $(tool).hover(function(event){
        //     var endPoints = jsPlumb.selectEndPoints($(this));
        //     $(endPoints).each(function(){
        //         $(this).addClass("end_point_hover");
        //     }) ;
        // }, function(event){
        //     $(endPoints).each(function(){
        //         $(this).removeClass("end_point_hover");
        //     }) ;
        // });
        $("#tool_kit_pannel").append(tool);
    }

    me.initOnLoad = function (jsplumb) {
        console.log("我要初始化");
        me.jsplumbInstance = jsplumb;
        initToolkitBar();
    }

    me.addNodeInDrawingPannel = function (posLeft, posTop, srcToolkit) {
        console.log("我要添加一个" + $(srcToolkit).prop("title") + ":" + $(srcToolkit).html());
        var newNode = NodeFactory.createNode({
            code: $(srcToolkit).data('code'),
            position: {
                left: posLeft,
                top: posTop
            }
        });
        var id = $(newNode).attr("id");
        $("#drawing_pannel").append($(newNode));
        // jsPlumb.makeSource(id, {
        //     endpoint: ["Dot", {
        //         radius: 3
        //     }],
        //     anchor: "Continuous",
        //     isSource: true,
        //     isTarget: true
        // });
        jsPlumb.makeTarget(id, {
            endpoint: "Rectangle",
            anchor: "Continuous",
            isSource: false,
            isTarget: true
        });
        me.jsplumbInstance.addEndpoint(id, {
            anchors: ['Top', {
                radius: 3
            }],
            isSource: true,
            isTarget: true,
            maxConnections: -1
        });
        // $(newNode).hover(function(event){
        //     var endPoints = me.jsplumbInstance.selectEndpoints($(this));
        //     $(endPoints).each(function(idx, ep){
        //         ep.setVisible(true);
        //     }) ;
        //     //console.log(me.jsplumbInstance.selectEndpoints);
        // },
        // function (event) {
        //     setTimeout(function () {
        //         var endPoints = me.jsplumbInstance.selectEndpoints($(this));
        //         $(endPoints).each(function (idx, ep) {
        //             ep.setVisible(false);
        //         });
        //     }, 1000);
        // });

        me.jsplumbInstance.draggable(newNode);
        // me.jsplumbInstance.bind("beforeDrag", function (params) {

        //     // params.source is the element from which the new connection is being dragged
        //     // params.endpoint is the associated endpoint
        //     me.jsplumbInstance.connect({
        //         source: params.source,
        //         target: params.endpoint,
        //         //     endpoint: ['Dot', { radius: 0 }],
        //         //     overlays: [['Arrow', { width: 12, length: 12, location: 1.0 }]]
        //     });
        //     return false;

        // });
    }

    me.getDefaultConfig = function (configName) {
        return {
            DragOptions: { cursor: 'pointer', zIndex: 2000 },
            PaintStyle: { stroke: '#666' , strokeWidth: 2 },
            EndpointHoverStyle: { fill: "orange" },
            HoverPaintStyle: { stroke: "orange" },
            EndpointStyle: { width: 20, height: 16, stroke: '#666' },
            Endpoint: "Rectangle",
            Anchors: ["TopCenter", "TopCenter"],
            Container: "canvas",
            connectorStyle: { strokeWidth: 5 }
        };
    }
}