/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var myApp = angular.module('flights', []);

myApp.controller('testFlightCtrl', function ($scope, $http) {

    var self = this;
    self.flightNr = 0;
    self.flightData = [];
    self.flights = [];
    self.dataSize = 0;
    self.flightsGotten = false;
    self.items = 0;
    self.itemArray = [0];
    

    self.getFlight = function () {
        $http.get('api/flight/testFlight')
                .success(function (data) {
                    self.flightData = data;
                    self.flightsGotten = true;
                    self.dataSize = self.flightData.flights.length;


                    for (i = 0; i < self.dataSize; i++)
                    {
                        if (i % 3 === 0)
                        {
                            self.items++;
                            
                        }
                    }
                    
                    self.itemArray = new Array(self.items);
                    


                }).error(function (data) {
            $scope.error = data.error + data.message;
        });



    };

    self.resetFlightNr = function ()
    {
        self.flightNr = 0;
    };
    self.incFlightNr = function ()
    {
        self.flightNr++;
    };

    self.showItem = function (num)
    {

        if (num % 3 === 0)
        {
            return true;
        }

        return false;
    };

    self.getFlightCount = function ()
    {

        return new Array(3);
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



