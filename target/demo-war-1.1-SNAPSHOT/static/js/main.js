/**
 * Created by wjr on 16-7-1.
 */
require.config({
    // The shim config allows us to configure dependencies for
    // scripts that do not call define() to register a module
    baseUrl: 'static/js',
    shim: {
        underscore: {
            exports: '_'
        },
        backbone: {
            deps: [
                'underscore',
                'jquery'
            ],
            exports: 'Backbone'
        }
    },
    paths: {
        jquery: 'thrid/jquery-2.1.0.min',
        underscore: 'thrid/underscore',
        backbone: 'thrid/backbone',
        text: 'thrid/text',
        common: 'lib/common'
    }
});
require([
    'jquery', 'backbone', 'views/order','routers/router'
], function ($, Backbone, AppView, Workspace) {
    'use strict';
    var idx = window.location.href.split('#')[1];
    var aList = $('ul li a');
    for(var i = 0 ; i < aList.size(); i++) {
        var tmpA = aList[i] ;
        if($(tmpA).attr('href') == '#' + idx){
            $(tmpA).parent('li').addClass('cur');
        }
    }
    $('ul li a').click(function(e){
        $('ul li').removeClass('cur');
        $(e.currentTarget).parent('li').addClass('cur')
    });
    // Initialize routing and start Backbone.history()
    var workspace = new Workspace();
    Backbone.history.start();
});
