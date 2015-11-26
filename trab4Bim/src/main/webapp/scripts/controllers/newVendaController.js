
angular.module('trab4bim').controller('NewVendaController', function ($scope, $location, locationParser, VendaResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.venda = $scope.venda || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Vendas/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        VendaResource.save($scope.venda, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Vendas");
    };
});