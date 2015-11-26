angular.module('trab4bim').factory('CategoriaResource', function($resource){
    var resource = $resource('rest/categoria/:CategoriaId',{CategoriaId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});