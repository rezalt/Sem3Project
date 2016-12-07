/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var myApp = angular.module('flights', ['ngMaterial', 'ngMessages']);

myApp.controller('testFlightCtrl', function ($scope, $http) {




    var self = this;
    self.flightNr = 0;
    self.flightData = [];
    $scope.flights = {};
    self.dataSize = 0;
    self.flightsGotten = false;
    self.items = 0;
    self.itemArray = [0];
    self.carouselArray = [0];

    $scope.showMsgs = false;
    $scope.myDate;
   

    $scope.toCities = [{"code": "BCN", "name": "Barcelona"}, {"code": "CDG", "name": "Paris"}, {"code": "CPH", "name": "Copenhagen"}, {"code": "STN", "name": "London"}, {"code": "SXF", "name": "Berlin"}];


    $scope.selectData = [];

    $scope.selectData[0] = {"code": "CPH", "name":"Copenhagen"};
 
    $scope.Cities = [{"code": "BCN", "name": "Barcelona"}, {"code": "CDG", "name": "Paris"}, {"code": "CPH", "name": "Copenhagen"}, {"code": "STN", "name": "London"}, {"code": "SXF", "name": "Berlin"}];

    self.postFlight = function (info, myDate)
    {

            
            if ( info[1] != null )
            {
                $scope.flights = {"selectedDepature": {"code": info[0].code, "date": myDate}, "selectedDestination": {"code": info[1].code}};
            } 
            else
            {
                $scope.flights = {"selectedDepature": {"code": info[0].code, "date": myDate}, "selectedDestination": {"code": "empty"}};
            }
            $http.post('api/flight/getFlights', $scope.flights)
                    .success(function (data)
                    {

                        if (data.hasOwnProperty('airline'))
                        {
                            self.flightData = data;
                            self.flightsGotten = true;
                            self.error = false;
                            self.dataSize = self.flightData.flights.length;

                            self.items = 0;
                            for (i = 0; i < self.dataSize; i++)
                            {
                                if (i % 3 === 0)
                                {
                                    self.items++;
                                }
                            }

                            self.itemArray = new Array(self.items);
                            self.carouselArray = new Array(1);
                        }
                        if (data.hasOwnProperty('httpError'))
                        {
                            self.flightsGotten = false;
                            self.error = true;
                            self.flightData = data;

                        }
                    })
                    .error(function (data)
                    {
                        $scope.errors = data.errors + data.message;
                    });
        
    };


    self.getFlight = function ()
    {
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
                    self.carouselArray = new Array(1);



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


myApp.filter('arrayDiff', function () {
    return function (array, diff) {
        var i, item,
                newArray = [],
                exception = Array.prototype.slice.call(arguments, 2);

        for (i = 0; i < array.length; i++) {
            item = array[i];
            if (diff.indexOf(item) < 0 || exception.indexOf(item) >= 0) {
                newArray.push(item);
            }
        }


        return newArray;

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





