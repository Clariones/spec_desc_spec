;
var sdsns = window.sdsns || {};
/**
 * authorization: eyJraWQiOiJhbm9ueW1vdXMiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbktleSI6ImFiY3FueFdxbzhRWjQ4YUpqOTZpeCIsImVudlR5cGUiOiJkZXZfc2RzX2NsYXJpb25lcyIsImlzcyI6ImNsYXJpb25lcyIsImV4cCI6MTU5MDczMTgwOCwiaWF0IjoxNTkwMTI3MDA4LCJ1c2VyVXBsb2FkSG9tZSI6InVwbG9hZC9hbm9ueW11c2UiLCJ0YWdzIjpbImFub255bW91cyJdfQ.5ZwWUNXrwJi0uB4XeGkQxhu5RrRirUbbThdvmwb4rQY
 */
sdsns.BaseMaster = function() {
    var me = this;
    var baseUrl = "http://127.0.0.1:8080/sds/";
    me.graphData = {comments:'没初始化'};

    me.log = function(message) {
        console.log("[     LOG]" + message);
    }
    var w34Stroke = 'rgba(50, 50, 200, 1)';
    var w34HlStroke = 'rgba(180, 180, 200, 1)';
    var arrowCommon = { foldback: 0.7, fill: 'blue', width: 10 };
    var overlays = [
        [ "Arrow", { location: 1 }, arrowCommon ],
        [ "Label", { location: 0.1, label: "条件1", labelStyle:{color:'black', textAlign:'left'}}, arrowCommon ]
    ];
    function connect(srcId, tgtId) {
        
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
    }

    me.initWorkSpace = function(jpbInstance) {
        me.jsplumbInstance = jpbInstance;
        $("#drawing_pannel").droppable({
            scope: "ss",
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
            
            connect(params.sourceId, params.targetId);
            return false;

        });
    }
    me.jsonAjax = function(param) {
        var response = {};
        $.ajax({
            async: false,
            url: baseUrl+param.url,
            headers: {
                authorization: 'eyJraWQiOiJTVTAwMDAwMiIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2In0.eyJ0b2tlbktleSI6ImFiY3FueFdxbzhRWjQ4YUpqOTZpeCIsImVudlR5cGUiOiJkZXZfc2RzX2NsYXJpb25lcyIsImlzcyI6ImNsYXJpb25lcyIsImV4cCI6MTU5MDczMjcwOCwiaWF0IjoxNTkwMTI3OTA4LCJ1c2VyVXBsb2FkSG9tZSI6InVwbG9hZC9TZWNVc2VyL1NVMDAwMDAyIiwidGFncyI6WyJTVTAwMDAwMiJdfQ.Pl2-0EOXp7PcneCa_EndRCiNIkzf2fNZPX0eEBcrsRE'
            },
            dataType: 'json'
        }).done(function (data, textStatus, jqXHR) {
                    console.log("ajax成功");
                    response.success = true;
                    response.data = data;
        }).fail(function (jqXHR, textStatus, errorThrow) {
                    console.log("ajax失败");
                    response.success = false;
                    response.message = textStatus;
        });
        return response;
    }
};

(function(){
    sdsns.BaseMaster.prototype.getDefaultConfig = function() {
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

    // sdsns.BaseMaster.prototype.log = function(message) {
    //     console.log(message);
    // }

    sdsns.BaseMaster.prototype.loadDataFromServer = function(typeName, projectId, sceneId) {
        // this.log("加载项目"+projectId+"/场景"+sceneId+"的"+typeName+"数据");
        var me = this;
        var returnedData = this.jsonAjax({url: 'clientService/customerLoadSpecData/' + typeName + '/' + sceneId + '/'});
        if (returnedData.success){
            me.log(JSON.stringify(returnedData));
            this.parseResponseData(returnedData.data);
        }
    };

    sdsns.BaseMaster.prototype.repaintAll = function() {
        this.log("开始重绘" + this.getTypeName()+"的数据");
    };

    sdsns.BaseMaster.prototype.addElement = function(elementData) {
        var elem = this.drawElement(elementData);
        $(elem).addClass("node_item");

        $(elem).css("left", 100).css("top", 100);
        $("#drawing_pannel").append(elem);

        var id = elementData.id;
       
        // jsPlumb.makeSource(id, {
        //     endpoint: ["Dot", {
        //         radius: 3
        //     }],
        //     anchor: "Continuous",
        //     isSource: true,
        //     isTarget: true
        // });
        this.jsplumbInstance.makeTarget(id, {
            endpoint: "Rectangle",
            anchor: "Continuous",
            isSource: false,
            isTarget: true
        });
        this.jsplumbInstance.addEndpoint(id, {
            anchors: ['Top', {
                radius: 3
            }],
            isSource: true,
            isTarget: true,
            maxConnections: -1
        });
        this.jsplumbInstance.draggable(elem);
    };

})();

define(function (){
    console.log("BaseMaster was load");
    return sdsns.BaseMaster;
});