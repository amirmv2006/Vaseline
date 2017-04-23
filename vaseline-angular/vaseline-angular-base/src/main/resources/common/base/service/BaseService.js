angular.module('Common')
    .factory('BaseService', function (NotificationService, $log) {
        function BaseService($http, entityPath, REST_BASE_PATH) {
            this.$http = $http;
            this.appendToUrl = function (url, appendee) {
                if (url.endsWith('/')) {
                    return url + (appendee.startsWith('/') ? appendee.substr(1) : appendee);
                } else {
                    return url + (appendee.startsWith('/') ? appendee : '/' + appendee)
                }
            };
            this.entityServicePath = this.appendToUrl('', entityPath);
            this.entityServiceUrl = this.appendToUrl(REST_BASE_PATH, this.entityServicePath);
            this.getUrl = function (methodUrl) {
                return this.appendToUrl(this.entityServiceUrl, methodUrl);
            };
            this.getParameterizedUrl = function (methodUrl, paramsMap) {
                var replaceParams = function (url, params) {
                    var result = url;
                    angular.forEach(params, function (val, key) {
                        result = result.replace('{' + key + '}', val);
                    });
                    return result;
                };
                var methodReplacedUrl = replaceParams(methodUrl, paramsMap);
                return this.appendToUrl(this.entityServiceUrl, methodReplacedUrl);
            };
            this.baseCallback = function (callback) {
                return function (result) {
                    callback(result.data)
                };
            };
            this.baseFail = function (callback) {
                return function (reason) {
                    NotificationService.addErrorNotification(reason.data.detailMessage);
                    $log.debug('ERROR', reason);
                    if (callback) {
                        callback(reason);
                    }
                }
            };
            this.getByRest = function (url, callback, failCallback) {
                this.$http.get(url).then(this.baseCallback(callback), this.baseFail(failCallback));
            };
            this.deleteByRest = function (url, callback, failCallback) {
                this.$http.delete(url).then(this.baseCallback(callback), this.baseFail(failCallback));
            };
            this.postByRest = function (url, object, callback, failCallback) {
                this.$http.post(url, object).then(this.baseCallback(callback), this.baseFail(failCallback));
            };
            this.putByRest = function (url, object, callback, failCallback) {
                this.$http.put(url, object).then(this.baseCallback(callback), this.baseFail(failCallback));
            };
        }

        return (BaseService);
    })
;