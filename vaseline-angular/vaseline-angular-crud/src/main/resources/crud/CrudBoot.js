function bootCrud(afterCrudLoad) {
    var crudCallback = function () {
        head.load(
            // crud
            'vaseline/angular-base/crud/CRUD.js',
            'vaseline/angular-base/crud/base/service/BaseCrudService.js',
            afterCrudLoad
        );
    };
    bootCommon(crudCallback);
}