angular.module('Common')
    .factory('BasePageController', function (NavigationService) {
        function BasePageController($scope, $rootScope, $location, $state, $controller, pageState) {
            var page = NavigationService.findPage(pageState);
            this.page = page;
            if (page === null) {
                page = {
                    pageTitle:'Page Title Not Specified',
                    actions:[]
                };
            }
            NavigationService.setCurrentPage(page);
            $rootScope.$on('$stateChangeStart',
                function(event, viewConfig){
                // Access to all the view config properties.
                // and one special property 'targetView'
                // viewConfig.targetView
                if (!page.onLeave()) {
                    event.preventDefault();
                }
            });
        }
        return (BasePageController);
    });