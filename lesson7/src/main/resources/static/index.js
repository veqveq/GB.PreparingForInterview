angular.module('app',[]).controller('mainController', function ($scope, $http) {
    const apiPath = 'http://localhost:8189/lesson7/students/';

    $scope.fillTable = function () {
        $http.get(apiPath+"findAll")
            .then(function (response) {
                console.log(response);
                $scope.Table = response.data;
            })
    }

    $scope.fillTable();
})