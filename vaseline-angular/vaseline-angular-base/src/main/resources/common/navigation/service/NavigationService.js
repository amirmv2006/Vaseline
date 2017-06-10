var common = angular.module('Common');
common.service('NavigationService', function ($state, Page) {
    var navigationService = this;
    navigationService.allPages = [];

    navigationService.addMainPage = function(pageName, pageState, title) {
        var page = new Page(pageName, pageState, title, null);
        navigationService.allPages.push(page);
        return page;
    };
    navigationService.addChildPage = function(pageName, pageState, title, parentPage) {
        var page = new Page(pageName, pageState, title, parentPage);
        navigationService.allPages.push(page);
        return page;
    };

    navigationService.findPage = function (pageName) {
        for (var i = 0; i < navigationService.allPages.length; i++) {
            if (navigationService.allPages[i].pageName === pageName) {
                return navigationService.allPages[i];
            }
        }
    };
    navigationService.getRootPages = function () {
        var rootPages = [];
        for (var i = 0; i < navigationService.allPages.length; i++) {
            if (!navigationService.allPages[i].menuPath.includes('.')) {
                rootPages.push(navigationService.allPages[i]);
            }
        }
        return rootPages;
    };
    navigationService.getChildPages = function (parentPageName) {
        var childPages = [];
        navigationService.allPages.forEach(function (page) {
            if (page.menuPath.includes('.')) {
                var lastIndexOfDot = page.menuPath.lastIndexOf(".");
                if (page.menuPath.substring(0, lastIndexOfDot) === parentPageName) {
                    childPages.push(page);
                }
            }
        });
        return childPages;
    };

    navigationService.goBack = function () {
        $state.go(navigationService.currentPage.parentPage.pageState);
    };

    // current page
    navigationService.currentPage = null;
    navigationService.setCurrentPage = function (page) {
        navigationService.currentPage = page;
    };
    navigationService.getCurrentPage = function () {
        return navigationService.currentPage;
    }
});