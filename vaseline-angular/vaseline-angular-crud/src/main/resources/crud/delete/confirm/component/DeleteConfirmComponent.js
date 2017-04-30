angular.module('CRUD')
    .component('deleteConfirmComponent', {
            templateUrl: "vaseline/angular-base/crud/delete/confirm/template/DeleteConfirm.html",
            bindings: {
                resolve: '=',
                close: '&',
                dismiss: '&'
            },
            controllerAs: 'deleteConfirmController',
            controller: function () {
                var deleteModelCtrl = this;
                deleteModelCtrl.ok = function () {
                    deleteModelCtrl.resolve.del();
                    deleteModelCtrl.close({$value: 'ok'});
                };
                deleteModelCtrl.cancel = function () {
                    deleteModelCtrl.dismiss({$value: 'cancel'});
                };
            }
        }
    );