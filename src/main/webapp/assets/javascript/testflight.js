/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var myApp = angular.module('flights', []);

myApp.controller('testFlightCtrl', function ($scope, $http) {

    var self = this;
    self.flightData = [];

    $scope.getFlight = function () {
        $http.get('api/flight/testFlight')
                .success(function (data) {
                    self.flightData = data;
                }).error(function (data) {
            $scope.error = data.error + data.message;
        });
    };
});







