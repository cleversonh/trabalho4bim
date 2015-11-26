
angular.module('trab4bim').controller('NewJogoController', function ($scope, $location, locationParser, JogoResource , CategoriaResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.jogo = $scope.jogo || {};
    
    $scope.categoriaList = CategoriaResource.queryAll(function(items){
        $scope.categoriaSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("categoriaSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.jogo.categoria = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.jogo.categoria.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Jogos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        JogoResource.save($scope.jogo, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Jogos");
    };
});