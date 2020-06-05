'use strict';
var w34Stroke = 'rgba(50, 50, 200, 1)';
var w34HlStroke = 'rgba(180, 180, 200, 1)';
var arrowCommon = { foldback: 0.5, fill: 'blue', width: 8 };
var endpointsVisibility = {};
function markEndPointShouldVisible(elementId, visible, reason) {
    console.log((visible?"show end-point":"hide end-point in 1s") + " because " + elementId + " " + reason);
}

var base = function () {
    var me = this;
    console.log("base was created");

    me.graphData = {};
    me.nodeHandlers = {};
    me.jsplumbInstance = null;

    me.setGraphData = function (data) {
        me.graphData = data;
    };

    me.appendNodeElement = function(nodeData, elem) {
        $("#drawing_pannel").append(elem);
        var id = nodeData.id;
        me.jsplumbInstance.makeTarget(id, {
            endpoint: ["Blank", {cssClass: 'image_endpoint'} ],
            anchor: "Continuous",
            isSource: false,
            isTarget: true
        });
        
        me.jsplumbInstance.draggable(elem);

        var commonParam = {
            isSource: true,
            isTarget: true,
            maxConnections: 1
        };
        var ep = me.jsplumbInstance.addEndpoint(id, {
            id: id+"_ep",
            anchors: [0.85,0.85,1,0],
            endpoint: ["Blank", {cssClass: 'image_endpoint'} ],
            stroke: "#00f",
        }, commonParam);
        ep.setVisible(false);
        // console.info(ep);
        // console.log("End point 以上");
        // $( ep.endpoint ).draggable({
		// 	start: function() {
		// 		markEndPointShouldVisible(id, true, "endpoint start drag");
		// 	},
		// 	stop: function() {
		// 		markEndPointShouldVisible(id, false, "endpoint stop drag");
		// 	}
        // });
        // ep.bind("mousedown", function(ep, evt){
        //     markEndPointShouldVisible(ep.elementId, true, "endpoint mouse down");
        // });
        // ep.bind("mouseup", function(ep, evt){
        //     markEndPointShouldVisible(ep.elementId, false, "endpoint mouse up");
        // });
        // me.jsplumbInstance.addEndpoint(id, {
        //     anchors: ['Top']
        // }, commonParam);
        // me.jsplumbInstance.addEndpoint(id, {
        //     anchors: ['Left']
        // }, commonParam);
        // me.jsplumbInstance.addEndpoint(id, {
        //     anchors: ['Right']
        // }, commonParam);
        // me.jsplumbInstance.addEndpoint(id, {
        //     anchors: ['Bottom']
        // }, commonParam);
        $(elem).hover(function(){
            var id = $(this).attr("id");
            var epList = me.jsplumbInstance.selectEndpoints({source:id});
            endpointsVisibility[id] = {visible: true};
            epList.each(function(it,idx){
                it.setVisible(true);
                markEndPointShouldVisible(id, true, "hover element");
            });
        },function(){
            var id = $(this).attr("id");
            var epList = me.jsplumbInstance.selectEndpoints({source:id});
            setTimeout(function() {
                epList.each(function (it, idx) {
                    it.setVisible(false);
                    markEndPointShouldVisible(id, false, "mouse out element");
                });
            }, 5000);
            
        });
    }

    me.connect = function(srcId, tgtId) {
        var name = "OK";
        var overlays = [
            [ "Arrow", { location: 1 }, arrowCommon ],
            [ "Label", { location: 0.1, label: name, labelStyle:{color:'black', textAlign:'left'}}, arrowCommon ]
        ];
        
        me.jsplumbInstance.connect({
            source: srcId,
            target: tgtId,
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

        var epList = me.jsplumbInstance.selectEndpoints({source:srcId});
        epList.each(function (it, idx) {
            it.setVisible(false);
        });
    }
    
};



define([
    'require', 'jquery', 'common'
], function (require, $, SDS) {
    'use strict';
    console.log("base-define");

    base.prototype.getDefaultConfig = function() {
        console.log("return default configuration from baseMaster");
        return {
            DragOptions: { cursor: 'pointer', zIndex: 2000 },
            // PaintStyle: { stroke: '#666' , strokeWidth: 2 },
            EndpointHoverStyle: { fill: 'orange' },
            HoverPaintStyle: { stroke: "orange"},
            EndpointStyle: { stroke: '#888', fill:'none', cssClass: 'image_endpoint' },
            Endpoint: "Dot",
            Anchors: ["TopCenter", "TopCenter"],
            Container: "canvas",
            connectorStyle: { strokeWidth: 5 }
        };
    };

    base.prototype.initWorkSpace = function (jpbInstance) {
        console.log("initWorkSpace by baseMaster");
        var me = this;
        me.jsplumbInstance = jpbInstance;
        $("#drawing_pannel").droppable({
            scope: "sds",
            // drop: function (event, ui) {
            //     var left = parseInt(ui.offset.left - $(this).offset().left);
            //     var top = parseInt(ui.offset.top - $(this).offset().top);
            //     me.addNodeInDrawingPannel(left, top, ui.draggable[0]);
            // }
        });

        me.jsplumbInstance.bind("beforeDrop", function (params) {
            console.log(params.sourceId + "->" + params.targetId);
            // params.source is the element from which the new connection is being dragged
            // params.endpoint is the associated endpoint

            me.connect(params.sourceId, params.targetId);
            return false;

        });

        me.jsplumbInstance.bind("beforeDrop", function (params) {
            console.log(params.sourceId + "->" + params.targetId);
            // params.source is the element from which the new connection is being dragged
            // params.endpoint is the associated endpoint

            me.connect(params.sourceId, params.targetId);
            return false;

        });

        me.loadData();
        me.initToolsBar();
        me.initMenu();
        me.drawAllLoadedData();
    };

    base.prototype.loadData = function () {
        console.log("loadData 尚未实现");
    };

    base.prototype.initToolsBar = function () {
        console.log("initToolsBar 尚未实现");
    };

    base.prototype.initMenu = function () {
        console.log("initMenu 尚未实现");
    };

    base.prototype.drawAllLoadedData = function () {
        // console.log("drawAllLoadedData 尚未实现");
        for(var key in this.graphData.nodes) {
            var nodeData = this.graphData.nodes[key];
            var handler = this.nodeHandlers[nodeData.type];
            var node = handler.drawNode(nodeData);
            if (!node){
                continue;
            }
            this.appendNodeElement(nodeData, $(node));
        }
    };

    return base;
});