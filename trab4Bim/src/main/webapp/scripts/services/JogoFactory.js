angular.module('trab4bim').factory('JogoResource', function($resource){
    var resource = $resource('rest/jogos/:JogoId',{JogoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});