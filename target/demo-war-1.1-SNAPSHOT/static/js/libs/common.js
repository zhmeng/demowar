/**
 * Created by wjr on 16-7-4.
 */
require(['jquery', 'backbone'],function($, Backbone){
    $.fn.serializeEl = function(){
        var param = {};
        var input = this.find('input,select');
        input.each(function(i,item){
            item = $(item);
            param[item.attr('name')] = item.val();
        });
        return param;
    };
    $.postJSON = function(url, data, success, failure) {
        return $.ajax({
            type: "POST",
            url: url,
            data: data,
            dataType: "json",
            success: success
        });
    };
    Backbone.View.prototype.close = function(){
        this.remove();
        this.unbind();
        if(this.onClose){
            this.onClose();
        }
    };
});