

angular.module('trab4bim').controller('EditJogoController', function($scope, $routeParams, $location, JogoResource , CategoriaResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.jogo = new JogoResource(self.original);
            CategoriaResource.queryAll(function(items) {
                $scope.categoriaSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.jogo.categoria){
                        $.each($scope.jogo.categoria, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.categoriaSelection.push(labelObject);
                                $scope.jogo.categoria.push(wrappedObject);
                            }
                        });
                        self.original.categoria = $scope.jogo.categoria;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Jogos");
        };
        JogoResource.get({JogoId:$routeParams.JogoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.jogo);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.jogo.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Jogos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Jogos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.jogo.$remove(successCallback, errorCallback);
    };
    
    $scope.categoriaSelection = $scope.categoriaSelection || [];
    $scope.$watch("categoriaSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.jogo) {
            $scope.jogo.categoria = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.jogo.categoria.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});