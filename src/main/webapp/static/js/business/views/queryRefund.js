/**
 * Created by wjr on 16-7-4.
 */
define(['backbone', 'text!business/templates/queryRefund.html', 'text!business/templates/result.html'], function(Backbone, ftlView, resultView){
    var view = Backbone.View.extend({
        resultTpl: _.template(resultView),
        events: {
            'click #queryRefundBtn': 'queryRefundBtnInvoke'
        },
        queryRefundBtnInvoke: function(){
            var self = this;
            var params = this.$el.serializeEl();
            params['method'] = this.method;
            $.postJSON('methodInvoke', params, function(d){
                if(self.resultV != undefined){
                    self.resultV.remove();
                }
                self.resultV = $(self.resultTpl({'result':JSON.stringify(d, null, 4).trim()}));
//                self.$el.append(self.resultV);
                self.$('#testResult').val(JSON.stringify(d, null, 4).trim());
            });
        },
        initialize: function(params){
            this.resultV = undefined;
            this.method = params['method'];
            this.$el.empty().html(ftlView);
            this.delegateEvents();
        },
        render: function(){}
    });
    return view;
});