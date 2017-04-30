angular.module('CRUD')
    .factory('BaseListController', function (
        $compile, $document,
        $uibModal,
        BasePageController,
        PageAction
    ) {
        function BaseListController($scope, $location, $controller,
                                    DTOptionsBuilder, DTColumnBuilder,
                                    pageName, pageBaseUrl, controllerAs, /*/book*/
                                    service, Model) {
            var baseListController = angular.extend(this, new BasePageController($scope, $location, $controller, pageName));

            baseListController.table = {};
            baseListController.table.refresh = function () {
                baseListController.dtInstance._renderer.rerender();
            };
            baseListController.table.getColumns = function () {
                var columns = [];
                angular.forEach(new Model(), function (val, key) {
                    columns.push(DTColumnBuilder.newColumn(key).withTitle(baseListController.table.getTitleFor(key)));
                });
                columns.push(DTColumnBuilder.newColumn('id').withTitle('-').withOption('render', function (data, type, full) {
                    return '<a href="javascript:;" class="ng-scope" ng-click="' + controllerAs + '.edit(' + data + ')"><span class="fa fa-pencil"></span></a>&nbsp;' +
                        '<a href="javascript:;" class="ng-scope" ng-click="' + controllerAs + '.delete(' + data + ')"><span class="fa fa-trash"></span></a>'
                }));
                return columns;
            };
            baseListController.table.getTitleFor = function (key) {
                var result = "";
                for (var index = 0; index < key.length; index++) {
                    var charAt = key.charAt(index);
                    if (index === 0) {
                        result += charAt.toUpperCase();
                    } else if (charAt === charAt.toUpperCase()) {
                        result += " " + charAt;
                    } else {
                        result += charAt;
                    }
                }
                return result;
            };
            baseListController.table.callService = function (searchObject, pagingDto, draw, fnCallback) {
                service.searchByExamplePaged(searchObject, pagingDto, function (result) {
                    const records = {
                        draw: draw,
                        recordsTotal: result.totalCount,
                        recordsFiltered: result.afterFilterCount,
                        // recordsFiltered: result.headers('x-meta-total'),
                        data: result.records
                    };
                    fnCallback(records);
                });
            };

            var baseListPage = baseListController.page;
            baseListPage.addAction(new PageAction("Add", "fa fa-plus", function () {
                $location.path(pageBaseUrl + '/add');
            }));
            baseListPage.addAction(new PageAction("Search", "fa fa-search", function () {
                baseListController.table.refresh();
            }));

            baseListController.dtOptions = DTOptionsBuilder
                .newOptions().withFnServerData(function (sSource, aoData, fnCallback, oSettings) {
                        var pagingDto = {
                            pageNumber: aoData[3].value / aoData[4].value,
                            pageSize: aoData[4].value
                        };
                        var orderList = [];
                        for (var orderIndex = 0; orderIndex < aoData[2].value.length; orderIndex++) {
                            var orderColumnIndex = aoData[2].value[orderIndex].column;
                            var orderPropertyName = aoData[1].value[orderColumnIndex].data;
                            var sortDto = {
                                propertyName: orderPropertyName,
                                ascending: aoData[2].value[orderIndex].dir.toLowerCase() === "asc"
                            };
                            orderList.unshift(sortDto);
                        }
                        pagingDto.sortList = orderList;

                        baseListController.table.callService(baseListController.searchObject, pagingDto, aoData[0], fnCallback);

                    }
                )
                .withPaginationType('full_numbers')
                .withOption('bFilter', false)
                .withOption('serverSide', true)
                .withOption('pagingType', 'simple_numbers');

            baseListController.dtInstance = {};

            baseListController.dtColumns = baseListController.table.getColumns();

            baseListController.edit = function (id) {
                $location.path(pageBaseUrl + '/add').search({id: id});
            };
            baseListController.delete = function (id) {
                var parentElem = angular.element($document[0].querySelector('.modal-demo'));
                var modalInstance = $uibModal.open({
                    animation: true,
                    component: 'deleteConfirmComponent',
                    resolve: {
                        del: function () {
                            return function () {
                                service.deleteById(id, function (result) {
                                    baseListController.dtInstance._renderer.rerender();
                                });
                            }
                        }
                    }
                });
            };

            baseListController.dtInstanceCallback = function (dtInstance) {
                baseListController.dtInstance = dtInstance;
                dtInstance.DataTable.on('draw.dt', function () {
                    var elements = angular.element("#" + dtInstance.id + " .ng-scope");
                    angular.forEach(elements, function (element) {
                        $compile(element)($scope);
                    });
                });
            };

            baseListController.hideSearch = true;
            baseListController.searchObject = new Model();
        }

        return (BaseListController);
    });