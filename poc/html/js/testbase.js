'use strict';
var base = function() {
    var me = this;
    console.log("base was created");
    
};



define([
    'require','jquery', 'testcommon'
], function(require, $, sdsns) {
    'use strict';
    console.log("base-define");

    base.prototype.runSomething = function() {
        console.log("testmaster is running...");
    };

    return base;
});