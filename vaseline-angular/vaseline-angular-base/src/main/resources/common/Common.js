var common = angular.module('Common', ['ui.bootstrap', 'ui.router']);
common.factory('PageAction', function () {
    function PageAction(label, icon, action) {
        this.label = label;
        this.icon = icon;
        this.action = action;
    }
    return (PageAction);
});
common.constant('REST_BASE_PATH', 'cxf/rest');
common.factory('Page', function ($location) {
   function Page(pageName, pageState, title, parentPage) {
       this.pageName = pageName;
       this.pageState = pageState;
       this.title = title;
       this.parentPage = parentPage;
       this.isCollapsed = true;
       this.actions = [];
       this.hasBack = true;
       this.menuPath = pageName;
       if (parentPage) {
           this.menuPath = this.menuPath + "." + parentPage.menuPath;
       }
       this.iconClass = "fa fa-question";
       this.pageParameters = {};
       this.onLeave = function () {
           angular.forEach(this.pageParameters, function (val, key) {
               $location.search(key, null);
           });
           return true;
       };
   }
   Page.prototype.addAction = function (pageAction) {
       var page = this;
       var existIndex = -1;
       for (var pageIndex = 0; pageIndex < page.actions.length; pageIndex++) {
           if (page.actions[pageIndex].label === pageAction.label) {
               existIndex = pageIndex;
               break;
           }
       }
       if (existIndex === -1) {
           page.actions.unshift(pageAction);
       } else {
           page.actions[existIndex].icon = pageAction.icon;
           page.actions[existIndex].action = pageAction.action;
       }
   };
   return (Page);
});
