define(function(){
    console.log("common-define");

    if (!window.sdsns) {
        window.sdsns = {};
    }
    var sdsns = window.sdsns;

    sdsns.extend = function(Child, Parent) {
        var F = function(){};
        F.prototype = Parent.prototype;
        Child.prototype = new F();
        Child.prototype.constructor = Child;
        Child.uber = Parent.prototype;
    };

    return sdsns;
    
});