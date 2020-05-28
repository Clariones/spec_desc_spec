'use strict';
var base = function () {
    var me = this;
    console.log("base was created");

    me.getDefaultConfig = function() {
        console.log("return default configuration from baseMaster");
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
    };

    
    
};



define([
    'require', 'jquery', 'common'
], function (require, $, SDS) {
    'use strict';
    console.log("base-define");

    base.prototype.initWorkSpace = function (jpbInstance) {
        var me = this;
        console.log("initWorkSpace by baseMaster");
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

            // connect(params.sourceId, params.targetId);
            return false;

        });

        me.afterInitWorkSpace();
    };

    return base;
});