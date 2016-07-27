/**
 * Created by wjr on 16-7-4.
 */
/** 实现单页面跳转业务逻辑 */
define(['backbone'], function(Backbone){
    var app = {
        currentView: null,
        router: null,
        init: function(){
            console.log('app init');
        },
        showView: function(view){
            if (this.currentView){
                this.currentView.close();
            }
            this.currentView = view;
            $("#auto_center").html(this.currentView.el);
        },
        loadHmtlByJs: function(url) {
            var self = this;
            require([url], function(index){
                self.showView(new index({method: url}));
            });
        }
    };
    var Router = Backbone.Router.extend({
        routes: {
            "*actions": "defaultRoute"
        }
    });
    app.router = new Router();
    app.router.on('route:defaultRoute', function(actions){
        if(actions != undefined && actions != null){
            console.log(actions);
            app.loadHmtlByJs(actions);
        }
    });
    Backbone.history.start();
    return app;
});