'use strict';
var _dumyData = {};
var _nodeHandlers = {};

var pageFlowMaster = function() {
    pageFlowMaster.uber.constructor.apply(this);
    var me = this;
    me.nodeHandlers = {
        'start_way': new _nodeHandlers.swNode(),
        'request': new _nodeHandlers.rNode(),
        'page': new _nodeHandlers.pNode()
    };

    
    console.log("pageFlowMaster was created");

    

};

define([
    'require','jquery', 'common', 'baseMaster', 'startWayNode','requestNode','pageNode', 'dumy_pageflow_data'
], function(require, $, SDS, Base, StartWayNode, RequestNode, PageNode, dumyData) {
    'use strict';
    _dumyData = dumyData;
    _nodeHandlers = {
        swNode: StartWayNode,
        rNode: RequestNode,
        pNode: PageNode
    };
    console.log("pageFlowMaster-define");
    SDS.extend(pageFlowMaster, Base);

    pageFlowMaster.prototype.loadData = function () {
        console.log("加载页面流视图的相关数据");
        this.setGraphData(_dumyData);
        console.log(this.graphData);
    }

    return pageFlowMaster;
});