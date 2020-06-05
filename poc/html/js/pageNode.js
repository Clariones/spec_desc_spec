'use strict';
var _dumyData = {};

var pageNode = function() {
    var me = this;
    pageNode.uber.constructor.apply(this);
    console.log("startWayNode was created");

};

define([
    'require','jquery', 'common', 'baseNode'
], function(require, $, SDS, Base) {
    'use strict';
    console.log("pageNode-define");
    SDS.extend(pageNode, Base);


    pageNode.prototype.foo = function() {
        console.log("pageNode.foo()");
        
    };

    pageNode.prototype.drawNode = function(nodeData) {
        var nodeTmplate = `<div id='${nodeData.id}' class="node_item node_page">
            <label>${nodeData.extData.title}</label>
            <img src="./icons/icon_page.svg"></img>
        </div>`;
         
        var node = $(nodeTmplate);
        $(node).css("left", nodeData.x).css("top", nodeData.y);
        return node;
    };
    return pageNode;
});