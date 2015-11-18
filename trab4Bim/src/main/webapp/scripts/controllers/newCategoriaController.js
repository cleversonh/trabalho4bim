
angular.module('trab4Bim').controller('NewCategoriaController', function ($scope, $location, locationParser, CategoriaResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.categoria = $scope.categoria || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Categoria/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        CategoriaResource.save($scope.categoria, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Categoria");
    };
});