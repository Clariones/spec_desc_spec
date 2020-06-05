'use strict';
var base = function () {
    var me = this;
    console.log("base-node was created");
};



define([
    'require', 'jquery', 'common'
], function (require, $, SDS) {
    'use strict';
    console.log("base-node-define");

    base.prototype.drawNode = function(nodeData) {
        console.log(this+"需要实现自己的 drawNode() 方法");
        
    };

    return base;
});