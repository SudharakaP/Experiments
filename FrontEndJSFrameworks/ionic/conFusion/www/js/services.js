'use strict';

angular.module('conFusion.services', ['ionic', 'ngResource'])
    .constant("baseURL","http://192.168.0.103:3000/")
    .factory('menuFactory', ['$resource', 'baseURL', function($resource, baseURL) {
        return $resource(baseURL+"dishes/:id", null, {'update':{method:'PUT'}});
    }])

    .factory('promotionFactory', ['$resource', 'baseURL', function($resource, baseURL){
        return $resource(baseURL+"promotions/:id", null, {'update':{method:'PUT'}});
    }])

    .factory('corporateFactory', ['$resource', 'baseURL', function($resource, baseURL) {   
        return $resource(baseURL + "leadership/:id", null, {'update':{method:'PUT'}});
    }])

    .factory('feedbackFactory', ['$resource', 'baseURL', function($resource, baseURL) {       
        function postFeedback(){
            return $resource(baseURL + "feedback", null, {'update': {method: 'PUT'}});
        }

        var feedbackFac = {
            postFeedback: postFeedback
        };
        return feedbackFac;
    }])

    .factory('favoriteFactory', ['$resource', 'baseURL', '$localStorage', function ($resource, baseURL, $localStorage) {
        var favFac = {};
        var favorites = [];

        favFac.addToFavorites = function (index) {
            for (var i = 0; i < favorites.length; i++) {
                if (favorites[i].id == index)
                    return;
            }
            favorites.push({id: index});
            $localStorage.storeObject('favoriteInfo', favorites);
        };

        favFac.deleteFromFavorites = function (index) {
            for (var i = 0; i < favorites.length; i++) {
                if (favorites[i].id == index) {       
                    favorites.splice(i, 1);
                    $localStorage.storeObject('favoriteInfo', favorites);
                }
            }
        };

        favFac.getFavorites = function () {
            if (favorites == 'undefined' || favorites.length == 0){
                favorites = $localStorage.getObject('favoriteInfo', '{}');
            }
            return favorites;         
        };

        return favFac;
    }])

    .factory('$localStorage', ['$window', function($window) {
        return {
            store: function(key, value) {
                $window.localStorage[key] = value;
            },
            get: function(key, defaultValue) {
                return $window.localStorage[key] || defaultValue;
            },
            storeObject: function(key, value) {
                $window.localStorage[key] = JSON.stringify(value);
            },
            getObject: function(key,defaultValue) {
                return JSON.parse($window.localStorage[key] || defaultValue);
            }
        };
    }]);
