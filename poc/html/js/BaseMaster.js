;
var sdsns = window.sdsns || {};

sdsns.BaseMaster = function() {
    var me = this;
    me.graphData = {comments:'没初始化'};

    me.log = function(message) {
        console.log("[     LOG]" + message);
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
        this.log("加载项目"+projectId+"/场景"+sceneId+"的"+typeName+"数据");
        this.graphData = {typeName: typeName, projectId:projectId};
    };

    sdsns.BaseMaster.prototype.repaintAll = function() {
        this.log("开始重绘" + this.getTypeName()+"的数据")
    };

})();