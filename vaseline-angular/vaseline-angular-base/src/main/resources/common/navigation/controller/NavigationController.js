angular.module('Common')
    .controller('NavigationCtrl', function ($scope, $state, $controller, NavigationService, PageModel, PageAction) {
        var navigationCtrl = this;
        navigationCtrl.rootPages = NavigationService.getRootPages();
        for (var i = 0; i < navigationCtrl.rootPages.length; i++) {
            navigationCtrl.rootPages[i].collapsedActiveClass = navigationCtrl.rootPages[i].isCollapsed ? '' : 'active';
            navigationCtrl.rootPages[i].childPages = NavigationService.getChildMenuPages(navigationCtrl.rootPages[i].pageState);
        }

        navigationCtrl.getCurrentPage = function () {
            var currentPage = NavigationService.getCurrentPage();
            if (currentPage && currentPage.hasBack && currentPage.parentPage) {
                currentPage.addAction(new PageAction("Back", "fa fa-arrow-left", function () {
                    NavigationService.goBack();
                }));
            }
            return currentPage;
        };

        navigationCtrl.doPageAction = function (pageAction) {
            var currentPage = NavigationService.getCurrentPage();
            currentPage.actions.forEach(function (act) {
                if (act.label === pageAction.label) {
                    act.action();
                    return;
                }
            })
        };

        navigationCtrl.toggleCollapse = function (pageModel) {
            pageModel.isCollapsed = !pageModel.isCollapsed;
            pageModel.collapsedActiveClass = pageModel.isCollapsed ? '' : 'active';
        };
        // this is on the NavigationService now... TO BE REMOVED
        // navigationCtrl.openPage = function (pageModel) {
        //     if (pageModel.childPages.length === 0) {
        //         NavigationService.openPage(pageModel.pageState);
        //     }
        // };
    });