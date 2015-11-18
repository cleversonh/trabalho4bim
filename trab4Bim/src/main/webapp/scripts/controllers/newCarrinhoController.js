
angular.module('trab4Bim').controller('NewCarrinhoController', function ($scope, $location, locationParser, CarrinhoResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.carrinho = $scope.carrinho || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Carrinhos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        CarrinhoResource.save($scope.carrinho, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Carrinhos");
    };
});