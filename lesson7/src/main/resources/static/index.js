angular.module('app', []).controller('mainController', function ($scope, $http) {
    const apiPath = 'http://localhost:8189/lesson7/students/';

    $scope.fillTable = function () {
        $http.get(apiPath + "findAll")
            .then(function (response) {
                $scope.Table = response.data;
            })
    };

    $scope.delete = function (id) {
        $http({
            url: apiPath + "del",
            method: 'POST',
            params: {
                studentId: id
            }
        })
            .then(function () {
                $scope.fillTable();
            })
    };

    $scope.addStudent = function () {
        $http.post(apiPath + "add", $scope.NewStudent)
            .then(function (response) {
                $scope.NewStudent = null;
                $scope.fillTable();
            })
    }

    $scope.updateStudent = function () {
        $http.post(apiPath + "add", $scope.UpdatedStudent)
            .then(function (response) {
                $scope.UpdatedStudent = null;
                $scope.fillTable();
            })
    }

    $scope.fillTable();
})