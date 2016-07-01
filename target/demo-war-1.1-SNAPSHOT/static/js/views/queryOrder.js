/**
 * Created by wjr on 16-7-1.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/order.html',
    'common'
], function( $, _, Backbone, statsTemplate, Common ) {
    var AppView = Backbone.View.extend({
        el: '#pay_platform',
        // Delegated events for creating new items, and clearing completed ones.
        events: {
            'click #hello': 'Hello'
        },
        Hello: function(){
            alert('Hello')
        },
        menuInvoke: function(){
            alert("menuInvoke");
        },
        initialize: function() {
            var self = this;
            this.center = this.$el.find('#auto_center');
            this.center.html('<div>HELLO</div>')
        },
        orderInfo: function(){
            alert('orderInfo')
        },
        queryOrder: function(){
            alert('queryOrder')
        }
    });

    return AppView;
});