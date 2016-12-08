/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var myApp = angular.module('flights', ['ngMaterial', 'ngMessages']);

myApp.controller('testFlightCtrl', function ($scope, $http) {

    var self = this;
    self.flightData = [];
    $scope.flights = {};
    $scope.passengerFirstName=[];
    $scope.passengerLastName=[];
    self.dataSize = 0;
    self.flightsGotten = false;
    self.passengerAmount = 1;
    self.passengerAmountArray = [0]; // for ng-repeat
    self.passengerPluralOrNot = "1 Passenger";
    $scope.chosenFlight = 0;

    $scope.showMsgs = false;
    $scope.myDate;


    $scope.toCities = [{"code": "BCN", "name": "Barcelona"}, {"code": "CDG", "name": "Paris"}, {"code": "CPH", "name": "Copenhagen"}, {"code": "STN", "name": "London"}, {"code": "SXF", "name": "Berlin"}];


    $scope.selectData = [];

    $scope.selectData[0] = {"code": "CPH", "name": "Copenhagen"};

    $scope.Cities = [{"code": "BCN", "name": "Barcelona"}, {"code": "CDG", "name": "Paris"}, {"code": "CPH", "name": "Copenhagen"}, {"code": "STN", "name": "London"}, {"code": "SXF", "name": "Berlin"}];

    $scope.increasePassengers = function ()
    {
        if (self.passengerAmount < 9)
        {
            self.passengerAmount += 1;
            self.passengerAmountArray.push(1);
        }
        if (self.passengerAmount > 1)
            self.passengerPluralOrNot = self.passengerAmount + " Passengers";

    };

    $scope.decreasePassengers = function ()
    {
        if (self.passengerAmount > 1)
        {
            self.passengerAmount -= 1;
            self.passengerPluralOrNot = self.passengerAmount + " Passengers";
            self.passengerAmountArray.pop();
        }

        if (self.passengerAmount <= 1)
            self.passengerPluralOrNot = self.passengerAmount + " Passenger";
    };

    self.postFlight = function (info, myDate)
    {


        if (info[1] != null)
        {
            $scope.flights = {"selectedDepature": {"code": info[0].code, "date": myDate}, "selectedDestination": {"code": info[1].code}, "passengerAmount": {"amount": self.passengerAmount}};
        } else
        {
            $scope.flights = {"selectedDepature": {"code": info[0].code, "date": myDate}, "selectedDestination": {"code": "empty"}, "passengerAmount": {"amount": self.passengerAmount}};
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

                        $scope.chosenFlightArray = [];


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

    self.reserveFlight = function (reserveeName, reserveePhone, reserveeEmail,passengerFirstName,passengerLastName)
    {
        
        
        passengers = [];
        for (i = 0; i < passengerFirstName.length; i++)
        {
            passengers.push({"firstName": passengerFirstName[i], "lastName": passengerLastName[i]});
        }

        $scope.reserve = {"flightID": $scope.chosenFlight, "numberOfSeats": self.passengerAmount, "reserveeName": reserveeName, "reservePhone": reserveePhone, "reserveeEmail": reserveeEmail, "passengers": passengers};

        $http.post('api/flight/reserveFlight', $scope.reserve)
                .success(function (data)
                {
                   alert( "Flight reserved!" + "\n" 
                    + "Flight ID: " + data.flightNumber + "\n"
                    + "Destination: " + data.destination +"\n"
                    + "Date: " + data.date + "\n"
                    + "flightTime: " + data.flightTime  + "minutes \n"
                    + "Seats: " + data.numberOfSeats  + "\n"
                    + "reservee: " + data.reserveeName
                    );
                     

                })
                .error(function (data)
                {
                    alert(data.message);
                });
                
    };

    self.getFlight = function ()
    {
        $http.get('api/flight/testFlight')
                .success(function (data) {
                    self.flightData = data;
                    self.flightsGotten = true;
                    self.dataSize = self.flightData.flights.length;






                }).error(function (data) {
            $scope.error = data.error + data.message;
        });

    };


    $scope.flightChosen = function (nr)
    {

        $scope.chosenFlight = nr;


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





