'use strict';

angular.module('conFusion.services', ['ionic', 'ngResource'])
    .constant("baseURL","http://localhost:3000/")
    .service('menuFactory', ['$resource', 'baseURL', function($resource, baseURL) {

        this.getDishes = function(){
            return $resource(baseURL+"dishes/:id", null, {'update':{method:'PUT'}});
        };

        // implement a function named getPromotion
        // that returns a selected promotion.
        this.getPromotions = function() {
            return $resource(baseURL+"promotions/:id", null, {'update':{method:'PUT'}});
        };

    }])

    .factory('corporateFactory', ['$resource', 'baseURL', function($resource, baseURL) {   

        // Implement two functions, one named getLeaders,
        // the other named getLeader(index)
        // Remember this is a factory not a service
        function getLeaders() {
            return $resource(baseURL + "leadership/:id", null, {'update':{method:'PUT'}});
        }

        var corpfac = {
            getLeaders: getLeaders
        };

        return corpfac;
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

    .factory('favoriteFactory', ['$resource', 'baseURL', function ($resource, baseURL) {
        var favFac = {};
        var favorites = [];

        favFac.addToFavorites = function (index) {
            for (var i = 0; i < favorites.length; i++) {
                if (favorites[i].id == index)
                    return;
            }
            favorites.push({id: index});
        };

        return favFac;
    }]);
