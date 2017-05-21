function bootCrud(afterCrudLoad) {
    var crudCallback = function () {
        head.load(
            // crud
            'vaseline/angular-base/crud/CRUD.js',
            'vaseline/angular-base/crud/delete/confirm/component/DeleteConfirmComponent.js',
            'vaseline/angular-base/crud/base/service/BaseCrudService.js',
            'vaseline/angular-base/crud/base/list/controller/BaseListController.js',
            'vaseline/angular-base/crud/base/edit/controller/BaseEditController.js',
            afterCrudLoad
        );
    };
    bootCommon(crudCallback);
}