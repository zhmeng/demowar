/**
 * Created by wjr on 16-7-4.
 */
define(['backbone', 'text!business/templates/scancode/order.html', 'text!business/templates/result.html'], function(Backbone,ftlView, resultView){
    var view = Backbone.View.extend({
        tpl: _.template(ftlView),
        resultTpl: _.template(resultView),
        events: {
            'click #orderBtn' : 'orderInvoke'
        },
        orderInvoke: function(){
            var self = this;
            var params = this.$el.serializeEl();
            params['method'] = this.method;
            params['notify_url'] = ' ';
            $.postJSON('methodInvoke', params, function(d){
                if(self.resultV != undefined) {
                    self.resultV.remove();
                }
                self.resultV = $(self.resultTpl({'result':JSON.stringify(d, null, 4).trim()}));
                self.resultV.append($('<div style="font-style: italic;color: red;">请复制code_img_url到浏览器新窗口扫描</div>'));
                self.$el.append(self.resultV);
            });
        },
        initialize: function(params){
            console.log(this.method);
            this.resultV = undefined;
            this.method = params['method'];
            this.$el.empty().html(this.tpl({'orderNum': Date.now()}));
            this.delegateEvents();
        },
        render: function(){}
    });
    return view;
});