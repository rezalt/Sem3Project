/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var myApp = angular.module('flights', []);

myApp.controller('testFlightCtrl', function ($scope, $http) {

    var self = this;
    self.flightData = [];
    self.dataSize = self.flightData.length + 1;
    self.flightsGotten = false;
    $scope.getFlight = function () {
        $http.get('api/flight/testFlight')
                .success(function (data) {
                    self.flightData = data;
                    self.flightsGotten = true;
                    self.dataSize = self.flightData.length + 1 ;
                }).error(function (data) {
            $scope.error = data.error + data.message;
        });

    

    };


});



myApp.filter('traveltime', function () {
    return function (input, traveltime) {
        var h = Math.floor(input / 60);
        var m = (input - (h * 60));

        if (m > 0)
            var time = h + 'h ' + m + 'm';
        else if (m === 0)
            var time = h + 'h ';
        return time;
    };
});



