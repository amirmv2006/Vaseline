var common = angular.module('Common');
common.factory('PageModel', function ($location) {
    function Page(pageState, title, parentPage) {
        this.pageState = pageState;
        this.title = title;
        this.parentPage = parentPage;

        this.isCollapsed = true;
        this.actions = [];
        this.hasBack = true;
        this.menuPath = pageState;
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
