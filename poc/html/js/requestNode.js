'use strict';
var _dumyData = {};

var requestNode = function() {
    var me = this;
    requestNode.uber.constructor.apply(this);
    console.log("requestNode was created");

};

define([
    'require','jquery', 'common', 'baseNode'
], function(require, $, SDS, Base) {
    'use strict';
    console.log("requestNode-define");
    SDS.extend(requestNode, Base);


    requestNode.prototype.foo = function() {
        console.log("requestNode.foo()");
        
    };

    requestNode.prototype.drawNode = function(nodeData) {
        var nodeTmplate = `<div id='${nodeData.id}' class="node_item node_request">
        <label>${nodeData.extData.title}</label>
        <img src="./icons/icon_request.svg"></img>
    </div>`;
        var node = $(nodeTmplate);
        $(node).css("left", nodeData.x).css("top", nodeData.y);
        return node;
    };

    return requestNode;
});