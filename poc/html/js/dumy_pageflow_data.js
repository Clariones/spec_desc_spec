define(function(require, factory) {
    'use strict';
    
    return {
        nodes:{
            "n1" : {
                id: "n1",
                type: "start_way",
                x: 10,
                y: 100,
                extData: {
                    title: "打开小程序"
                }
            },
            "n2" : {
                id: "n2",
                type: "request",
                x: 210,
                y: 100,
                extData: {
                    title: "查看首页"
                }
            },
            "n3" : {
                id: "n3",
                type: "page",
                x: 430,
                y: 100,
                extData: {
                    title: "项目大厅"
                }
            },
            "n4" : {
                id: "n4",
                type: "page",
                x: 430,
                y: 180,
                extData: {
                    title: "我的页面"
                }
            }
        },
        connections: {
            "c1": {
                fromNode: "n1",
                toNode: "n2",
                extData: {

                }
            },
            "c2": {
                fromNode: "n2",
                toNode: "n3",
                extData: {
                    
                }
            }
        }
    };
});