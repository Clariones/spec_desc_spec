;
var sdsns = window.sdsns || {};

sdsns.PageFlowMaster = function() {
    var me = this;
    sdsns.BaseMaster.call(this);

    me.repaintAll = function() {
        // this.constructor.uber.repaintAll.apply(this);
        this.log("wo重绘" + this.getTypeName()+"的数据");
        for (var key in this.graphData.startWays) {  
            console.log(key);
            this.addElement(this.graphData.startWays[key]);
        }
        for (var key in this.graphData.pages) {  
            console.log(key);
            this.addElement(this.graphData.pages[key]);
        }
    };

    function drawStartWay(element) {
        var template = `<div id="${element.id}" class="element_${element.type}">
            <label>${element.chineseName}</label>
        </div>`;
        return $(template);
    }
    function drawPage(element) {
        var template = `<div id="${element.id}" class="element_${element.type}">
            <label>${element.chineseName}</label>
        </div>`;
        return $(template);
    }
    me.drawElement = function(element) {
        console.log("我要画一个"+element.type);
        switch(element.type) {
            case 'startWay':
                return drawStartWay(element);
            case 'page':
                return drawPage(element);
            default:
                console.log("不会画"+element.type);
        }
        
    }

};

sdsns.extend(sdsns.PageFlowMaster, sdsns.BaseMaster);

function dump(obj) {
    console.log(JSON.stringify(obj));
};

(function(){
    sdsns.PageFlowMaster.prototype.loadData = function(projectId, sceneId) {
        this.loadDataFromServer("pageFlow", projectId, sceneId);
        this.dumpGraphData();
    };

    sdsns.PageFlowMaster.prototype.dumpGraphData = function() {
        this.log("当前数据："+JSON.stringify(this.graphData))
    };

    sdsns.PageFlowMaster.prototype.getTypeName = function() {
        return "页面流";
    };

    sdsns.PageFlowMaster.prototype.parseResponseData = function (responseData) {
        this.graphData = responseData;
    };

    // sdsns.PageFlowMaster.prototype.repaintAll = function() {
    //     sdsns.PageFlowMaster.uber.repaintAll.apply(this);
    //     sdsns.PageFlowMaster.uber.log("wo重绘" + this.getTypeName()+"的数据");
    // };

})();