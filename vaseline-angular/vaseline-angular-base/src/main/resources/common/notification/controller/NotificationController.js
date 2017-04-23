angular.module('Common')
    .controller('NotificationCtrl', function ($scope, $location, $controller, NotificationService) {
        var notificationCtrl = this;
        notificationCtrl.getAllNotifications = function () {
            return NotificationService.notifications;
        };
        notificationCtrl.closeNotification = function (index) {
            NotificationService.removeNotification(index);
        };
    });