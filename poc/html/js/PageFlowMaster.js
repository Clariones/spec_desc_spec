'use strict';

var pageFlowMaster = function() {
    var me = this;
    pageFlowMaster.uber.constructor.apply(this);
    console.log("pageFlowMaster was created");

};

define([
    'require','jquery', 'common', 'baseMaster'
], function(require, $, SDS, Base) {
    'use strict';

    console.log("pageFlowMaster-define");
    SDS.extend(pageFlowMaster, Base);
   
    // pageFlowMaster.prototype.initWorkSpace = function(jpbInstance) {
    //     pageFlowMaster.uber.initWorkSpace.apply(this, [jpbInstance]);
    //     console.log("initWorkSpace by pageFlowMaster");        
    // };

    pageFlowMaster.prototype.afterInitWorkSpace = function(jpbInstance) {
        console.log("after initWorkSpace by pageFlowMaster");
        this.jsplumbInstance.connect({
            source: 'item_left',
            target: 'item_right'
        });

        this.jsplumbInstance.draggable('item_left');
        this.jsplumbInstance.draggable('item_right');
    };

    return pageFlowMaster;
});