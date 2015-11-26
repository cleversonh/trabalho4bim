

angular.module('trab4bim').controller('EditCategoriaController', function($scope, $routeParams, $location, CategoriaResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.categoria = new CategoriaResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Categoria");
        };
        CategoriaResource.get({CategoriaId:$routeParams.CategoriaId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.categoria);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.categoria.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Categoria");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Categoria");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.categoria.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});