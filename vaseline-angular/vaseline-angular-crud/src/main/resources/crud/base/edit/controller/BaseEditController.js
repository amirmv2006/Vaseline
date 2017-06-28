angular.module('CRUD')
    .factory('BaseEditController', function (
        $compile, $document, $log,
        BasePageController, NavigationService,
        PageAction
    ){
        function BaseEditController($scope, $rootScope, $location, $state, $stateParams, $controller,
                                    pageState, controllerAs,
                                    service, Model
        ) {
            var baseEditController = angular.extend(this, new BasePageController($scope, $rootScope, $location, $state, $controller, pageState));
            var bookAddPage = baseEditController.page;
            bookAddPage.pageParameters = {
                entityId:$stateParams.entityId
            };
            bookAddPage.addAction(new PageAction("Save", "fa fa-save", function () {
                baseEditController.save();
            }));
            baseEditController.editForm = {};

            baseEditController.entity = {};

            var entityId = $stateParams.entityId;
            if (entityId) {
                service.getById(entityId, function (result) {
                    angular.copy(result, baseEditController.entity);
                });
            }

            baseEditController.resetForm = function () {
                angular.copy(new Model(), baseEditController.entity);
            };


            baseEditController.save = function () {
                // if (main.bookForm.$valid) {
                if (baseEditController.entity.id) {
                    service.update(angular.copy(baseEditController.entity), function (result) {
                        baseEditController.resetForm();
                        $log.debug('RESULT', result);
                        NavigationService.goBack();
                    });
                } else {
                    service.save(angular.copy(baseEditController.entity), function (result) {
                        baseEditController.resetForm();
                        $log.debug('RESULT', result);
                        NavigationService.goBack();
                    });
                }
                // } else {
                //     console.log('Invalid');
                // }
            };
            baseEditController.resetForm();
        }
        return (BaseEditController);
    });
