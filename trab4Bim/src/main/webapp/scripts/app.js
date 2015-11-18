'use strict';

angular.module('trab4Bim',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Carrinhos',{templateUrl:'views/Carrinho/search.html',controller:'SearchCarrinhoController'})
      .when('/Carrinhos/new',{templateUrl:'views/Carrinho/detail.html',controller:'NewCarrinhoController'})
      .when('/Carrinhos/edit/:CarrinhoId',{templateUrl:'views/Carrinho/detail.html',controller:'EditCarrinhoController'})
      .when('/Categoria',{templateUrl:'views/Categoria/search.html',controller:'SearchCategoriaController'})
      .when('/Categoria/new',{templateUrl:'views/Categoria/detail.html',controller:'NewCategoriaController'})
      .when('/Categoria/edit/:CategoriaId',{templateUrl:'views/Categoria/detail.html',controller:'EditCategoriaController'})
      .when('/Jogos',{templateUrl:'views/Jogo/search.html',controller:'SearchJogoController'})
      .when('/Jogos/new',{templateUrl:'views/Jogo/detail.html',controller:'NewJogoController'})
      .when('/Jogos/edit/:JogoId',{templateUrl:'views/Jogo/detail.html',controller:'EditJogoController'})
      .when('/Usuarios',{templateUrl:'views/Usuario/search.html',controller:'SearchUsuarioController'})
      .when('/Usuarios/new',{templateUrl:'views/Usuario/detail.html',controller:'NewUsuarioController'})
      .when('/Usuarios/edit/:UsuarioId',{templateUrl:'views/Usuario/detail.html',controller:'EditUsuarioController'})
      .when('/Vendas',{templateUrl:'views/Venda/search.html',controller:'SearchVendaController'})
      .when('/Vendas/new',{templateUrl:'views/Venda/detail.html',controller:'NewVendaController'})
      .when('/Vendas/edit/:VendaId',{templateUrl:'views/Venda/detail.html',controller:'EditVendaController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
