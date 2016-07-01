/**
 * Created by wjr on 16-7-1.
 */
define([
    'backbone',
    '../views/order',
    '../views/queryOrder'
], function(Backbone, orderView, queryOrderView) {
    var Workspace = Backbone.Router.extend({
        routes:{
            'orderInfo': 'orderInfoInvoke',
            'queryOrder': 'queryInfo'
        },
        orderInfoInvoke:function(){
            new orderView()
        },
        queryInfo: function(){
            new queryOrderView()
        }
    });
    return Workspace;
});
