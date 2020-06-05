'use strict';
var _dumyData = {};

var startWayNode = function() {
    var me = this;
    startWayNode.uber.constructor.apply(this);
    console.log("startWayNode was created");

};

define([
    'require','jquery', 'common', 'baseNode'
], function(require, $, SDS, Base) {
    'use strict';
    console.log("startWayNode-define");
    SDS.extend(startWayNode, Base);


    startWayNode.prototype.foo = function() {
        console.log("startWayNode.foo()");
        
    };

    startWayNode.prototype.drawNode = function(nodeData) {
        var nodeTmplate = `<div id='${nodeData.id}' class="node_item node_startWay">
            <img src="./icons/icon_actor.svg"></img>
            <label>${nodeData.extData.title}</label>
            <span>用户从微信，或者分享中打开小程序</span>
        </div>`;
    
        var node = $(nodeTmplate);
        $(node).css("left", nodeData.x).css("top", nodeData.y);
        return node;

    };

    return startWayNode;
});