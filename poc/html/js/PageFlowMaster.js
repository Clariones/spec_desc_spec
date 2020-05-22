;
var sdsns = window.sdsns || {};

sdsns.PageFlowMaster = function() {
    sdsns.BaseMaster.call(this);
};

sdsns.extend(sdsns.PageFlowMaster, sdsns.BaseMaster);

function dump(obj) {
    console.log(JSON.stringify(obj));
};

(function(){
    sdsns.PageFlowMaster.prototype.loadData = function(projectId, sceneId) {
        this.loadDataFromServer("pageFlow", projectId, sceneId);
        this.dumpGraphData();
    };

    sdsns.PageFlowMaster.prototype.dumpGraphData = function() {
        this.log("当前数据："+JSON.stringify(this.graphData))
    };

    sdsns.PageFlowMaster.prototype.getTypeName = function() {
        return "页面流";
    };

})();