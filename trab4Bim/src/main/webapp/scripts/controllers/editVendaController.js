

angular.module('trab4bim').controller('EditVendaController', function($scope, $routeParams, $location, VendaResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.venda = new VendaResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Vendas");
        };
        VendaResource.get({VendaId:$routeParams.VendaId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.venda);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.venda.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Vendas");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Vendas");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.venda.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});