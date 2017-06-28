var common = angular.module('Common');
common.factory('PageAction', function () {
    function PageAction(label, icon, action) {
        this.label = label;
        this.icon = icon;
        this.action = action;
    }
    return (PageAction);
});
