/*
// Created by Academy on 20/10/16
// This is your main Angular JS Module
// Define your Angular JS Application here
*/

//Angular Application definition
var angmodule = angular.module('angmodule', ['ui.bootstrap', 'ui.router', 'ngSanitize',  'ngAnimate', 'objectTable']);

angmodule.constant('http_url','http://college-service');

//Angular JS Factory for CRUD Service common for all Masters
angmodule.factory('CrudService', function($http, $q,http_url) {
    return {
        'list': function(reqUrl) {
            var defer = $q.defer();
            $http.get(http_url+reqUrl).success(function(resp){
                defer.resolve(resp);
            }).error( function(err) {
                defer.reject(err);
            });
            return defer.promise;
        },
        'get': function(reqUrl) {
            var defer = $q.defer();
            $http.get(http_url+reqUrl).success(function(resp){
                defer.resolve(resp);
            }).error( function(err) {
                defer.reject(err);
            });
            return defer.promise;
        },
        'create': function(reqUrl, data) {
            var defer = $q.defer();
            $http.post(http_url+reqUrl, data).success(function(resp){
                defer.resolve(resp);
            }).error( function(err) {
                defer.reject(err);
            });
            return defer.promise;
        },
        'update': function(reqUrl, data){
            var deferred = $q.defer();
            $http.put(http_url+reqUrl, data).success(function (response) {
                deferred.resolve(response);
            }).error(function (err) {
                 deferred.reject(err)
            });
            return deferred.promise;
        },
        'remove': function(reqUrl) {
            var defer = $q.defer();
            $http.delete(http_url+reqUrl).success(function(resp){
                defer.resolve(resp);
            }).error( function(err) {
                defer.reject(err);
            });
            return defer.promise;
        }
    }
});

//Base Client side router for /home
function angmoduleRouteConfig($stateProvider, $urlRouterProvider) {

    $urlRouterProvider
        .when('/home', '/colleges')
        .otherwise('/colleges');

    $stateProvider.state('home', {
        url: '/home',
        controller: HomeCtrl,
        templateUrl: '/pages/home.html'
    });
};

angmodule.config(angmoduleRouteConfig);

//Home Controller
function HomeCtrl($scope, $state){
    $scope.$state = $state;
}

//Angular Directive for Modal Dialogs
angmodule.directive('myModal', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            scope.dismiss = function() {
                element.modal('hide');
            };
        }
    }
});

//Angular Service for Modal Dialogs
angmodule.service('ModalInstance', function($q,$uibModal){
    this.confirmModal = function(data){
        var status = $q.defer();
        var modalInstance = $uibModal.open({
            templateUrl: '../../../pages/confirmationModal.html',
            controller: ModalInstanceCtrl,
            keyboard: false,
            backdrop: 'static',
            size: 'md',
            resolve: {
                content: function () {
                    return data;
                }
            }
        });

        modalInstance.result.then(function (item) {
            status.resolve({status: true, reason: item });
        }, function () {
            status.resolve({status: false});
        });

        return status.promise;
    }
});

//Controller for the Modal Dialogs
function ModalInstanceCtrl($scope, $uibModalInstance, content) {

    $scope.msgContent = content;

    $scope.ok = function (item) {
        $uibModalInstance.close(item);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

};

//Date Time Filter
angmodule.filter('utc', function(){
    return function(val){
        var date = new Date(val);
        return new Date(date.getUTCFullYear(),
            date.getUTCMonth(),
            date.getUTCDate(),
            date.getUTCHours(),
            date.getUTCMinutes(),
            date.getUTCSeconds());
    };
});