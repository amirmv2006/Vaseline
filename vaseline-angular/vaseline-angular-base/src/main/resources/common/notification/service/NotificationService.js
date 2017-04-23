angular.module('Common').service('NotificationService', function ($location) {
    var notificationService = this;
    notificationService.notifications = [];
    notificationService.addSuccessNotification = function (message) {
        notificationService.notifications.push({
            message: message,
            notifType: "success",
            autoHide: true
        });
    };
    notificationService.addWarningNotification = function (message) {
        notificationService.notifications.push({
            message: message,
            notifType: "warning",
            autoHide: false
        });
    };
    notificationService.addErrorNotification = function (message) {
        notificationService.notifications.push({
            message: message,
            notifType: "danger",
            autoHide: false
        });
    };
    notificationService.removeNotification = function (index) {
        notificationService.notifications.splice(index, 1);
    };
});