var common = angular.module('Common');
common.service('NavigationService', function ($state, PageModel) {
    var navigationService = this;
    navigationService.allPages = [];

    navigationService.addMainPage = function(pageState, title) {
        var pageModel = new PageModel(pageState, title, null);
        navigationService.allPages.push(pageModel);
        return pageModel;
    };
    navigationService.addChildPage = function(pageState, title, parentPage) {
        var pageModel = new PageModel(pageState, title, parentPage);
        navigationService.allPages.push(pageModel);
        return pageModel;
    };

    navigationService.findPage = function (pageState) {
        for (var i = 0; i < navigationService.allPages.length; i++) {
            if (navigationService.allPages[i].pageState === pageState) {
                return navigationService.allPages[i];
            }
        }
    };
    navigationService.getRootPages = function () {
        var rootPages = [];
        for (var i = 0; i < navigationService.allPages.length; i++) {
            if (
                navigationService.allPages[i].menuPath &&
                navigationService.allPages[i].menuPath !== '' &&
                !navigationService.allPages[i].menuPath.includes('.')
            ) {
                rootPages.push(navigationService.allPages[i]);
            }
        }
        return rootPages;
    };
    navigationService.getChildPages = function (parentPageName) {
        var childPages = [];
        navigationService.allPages.forEach(function (pageModel) {
            if (pageModel.parentPage && pageModel.parentPage.pageState === parentPageName) {
                childPages.push(pageModel);
            }
        });
        return childPages;
    };
    navigationService.getChildMenuPages = function (parentPageName) {
        var childPages = [];
        navigationService.allPages.forEach(function (pageModel) {
            if (pageModel.menuPath.includes('.')) {
                var lastIndexOfDot = pageModel.menuPath.lastIndexOf(".");
                if (pageModel.menuPath.substring(0, lastIndexOfDot) === parentPageName) {
                    childPages.push(pageModel);
                }
            }
        });
        return childPages;
    };

    navigationService.goBack = function () {
        if (navigationService.currentPage.parentPage){
            navigationService.openPage(navigationService.currentPage.parentPage.pageState);
        }
    };

    navigationService.openPage = function (pageState) {
        var pageModel = navigationService.findPage(pageState);
        $state.go(pageModel.pageState, pageModel.pageParameters);
    };

    // current page
    navigationService.currentPage = null;
    navigationService.setCurrentPage = function (pageModel) {
        navigationService.currentPage = pageModel;
    };
    navigationService.getCurrentPage = function () {
        return navigationService.currentPage;
    }
});