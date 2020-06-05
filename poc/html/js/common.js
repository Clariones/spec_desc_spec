define(function(){
    console.log("common-define");

    if (!window.SDS) {
        window.SDS = {};
    }
    var SDS = window.SDS;

    SDS.extend = function(childFunc, parentFunc) {
        var F = function(){};
        F.prototype = parentFunc.prototype;
        childFunc.prototype = new F();
        childFunc.prototype.constructor = childFunc;
        childFunc.uber = parentFunc.prototype;
    };

    SDS.dump = function(object) {
        for(var x in object) {
            console.log(x +"=" + object[x]);
        }
    }

    return SDS;
    
});