var NodeFactory = new function () {
    var me = this;
    var count = 1;

    me.createNode = function (initialData) {
        count++;
        var node = $('<div id="node_'+count+'" class="node_item node_' + initialData.code+'"></div>');
        $(node).css("left", initialData.position.left).css("top", initialData.position.top);
        return node;
    }
}