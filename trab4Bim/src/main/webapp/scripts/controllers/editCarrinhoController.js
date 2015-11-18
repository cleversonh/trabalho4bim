

angular.module('trab4Bim').controller('EditCarrinhoController', function($scope, $routeParams, $location, CarrinhoResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.carrinho = new CarrinhoResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Carrinhos");
        };
        CarrinhoResource.get({CarrinhoId:$routeParams.CarrinhoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.carrinho);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.carrinho.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Carrinhos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Carrinhos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.carrinho.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});