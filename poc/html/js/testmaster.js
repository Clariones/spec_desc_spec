'use strict';

var master = function() {
    var me = this;
    master.uber.constructor.apply(this);
    console.log("master was created");

};

define([
    'require','jquery', 'testcommon', 'testbase'
], function(require, $, sdsns, base) {
    'use strict';
    alert("debug master");

    console.log("master-define");
    sdsns.extend(master, base);
   
    master.prototype.runSomething = function() {
        master.uber.runSomething.apply(this);
        console.log("by test-master");
    };

    return master;
});