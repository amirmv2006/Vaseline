angular.module('CRUD')
    .factory('BaseCrudService', function ($http, BaseService) {
        function CrudService($http, entityPath, REST_BASE_PATH) {
            var crudService = angular.extend(this, new BaseService($http, entityPath, REST_BASE_PATH));
            crudService.getById = function (id, callback, failCallback) {
                this.getByRest(crudService.getParameterizedUrl("/{id}", {
                    id: id
                }), callback, failCallback);
            };
            crudService.countAll = function (callback, failCallback) {
                this.getByRest(crudService.getUrl("count"), callback, failCallback);
            };
            crudService.getAll = function (callback, failCallback) {
                this.getByRest(crudService.getUrl(""), callback, failCallback);
            };
            crudService.getAllPaged = function (pagingDto, callback, failCallback) {
                this.postByRest(crudService.getUrl("getAllPaged"), pagingDto, callback, failCallback);
            };
            crudService.countByExample = function (example, callback, failCallback) {
                this.postByRest(crudService.getUrl("countByExample"), example, callback, failCallback);
            };
            crudService.searchByExample = function (example, callback, failCallback) {
                this.postByRest(crudService.getUrl("searchByExample"), example, callback, failCallback);
            };
            crudService.searchByExamplePaged = function (example, pagingDto, callback, failCallback) {
                this.postByRest(crudService.getUrl("searchByExamplePaged"), {
                    example:example,
                    pagingDto:pagingDto
                }, callback, failCallback);
            };
            crudService.save = function (object, callback, failCallback) {
                this.postByRest(crudService.getUrl(""), object, callback, failCallback);
            };
            crudService.update = function (object, callback, failCallback) {
                this.putByRest(crudService.getUrl(""), object, callback, failCallback);
            };
            crudService.deleteById = function (id, callback, failCallback) {
                this.deleteByRest(crudService.getParameterizedUrl("/{id}", {
                    id:id
                }), callback, failCallback);
            };
        }
        return (CrudService);
    });