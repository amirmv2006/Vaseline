angular.module('Common')
    .factory('BasePageController', function (NavigationService) {
        function BasePageController($scope, $location, $controller, pageName) {
            var page = NavigationService.findPage(pageName);
            if (page === null) {
                page = {
                    pageTitle:'Page Title Not Specified',
                    actions:[]
                };
            }
            NavigationService.setCurrentPage(page);
            $scope.$on('$locationChangeStart', function( event ) {
                if (!page.onLeave()) {
                    event.preventDefault();
                }
            });
        }
        return (BasePageController);
    });