angular.module('Common')
    .factory('BasePageController', function (NavigationService) {
        function BasePageController(pageName) {
            var page = NavigationService.findPage(pageName);
            if (page === null) {
                page = {
                    pageTitle:'Page Title Not Specified',
                    actions:[]
                };
            }
            NavigationService.setCurrentPage(page);
        }
        return (BasePageController);
    });