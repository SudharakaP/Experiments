'use strict';

angular.module('confusionApp')

    .controller('MenuController', ['$scope', 'menuFactory', function($scope, menuFactory) {

        $scope.tab = 1;
        $scope.filtText = '';
        $scope.showDetails = false;
        $scope.showMenu = false;
        $scope.message = "Loading ...";

        menuFactory.getDishes().query(
            function (response){
                $scope.dishes = response;
                $scope.showMenu = true;
            }, 
            function (response){
                $scope.message = "Error: " + response.status + " " + response.statusText;
            }
        );

        $scope.select = function(setTab) {
            $scope.tab = setTab;

            if (setTab === 2) {
                $scope.filtText = "appetizer";
            }
            else if (setTab === 3) {
                $scope.filtText = "mains";
            }
            else if (setTab === 4) {
                $scope.filtText = "dessert";
            }
            else {
                $scope.filtText = "";
            }
        };

        $scope.isSelected = function (checkTab) {
            return ($scope.tab === checkTab);
        };

        $scope.toggleDetails = function() {
            $scope.showDetails = !$scope.showDetails;
        };
    }])

    .controller('ContactController', ['$scope', function($scope) {

        $scope.feedback = {mychannel:"", firstName:"", lastName:"", agree:false, email:"" };

        var channels = [{value:"Tel.", label:"Tel."}, {value:"Email",label:"Email"}];

        $scope.channels = channels;
        $scope.invalidChannelSelection = false;

    }])

    .controller('FeedbackController', ['$scope', 'feedbackFactory', function($scope, feedbackFactory) {

        $scope.sendFeedback = function() {

            console.log($scope.feedback);

            if ($scope.feedback.agree && ($scope.feedback.mychannel === "")) {
                $scope.invalidChannelSelection = true;
                console.log('incorrect');
            }
            else {
                $scope.invalidChannelSelection = false;
                feedbackFactory.postFeedback().save($scope.feedback);
                $scope.feedbackForm.$setPristine();
                $scope.feedback = {mychannel:"", firstName:"", lastName:"", agree:false, email:"" };
                console.log($scope.feedback);
            }
        };
    }])

    .controller('DishDetailController', ['$scope', '$stateParams', 'menuFactory', function($scope, $stateParams, menuFactory) {
        $scope.showDish = false;
        $scope.message = "Loading ...";
        menuFactory.getDishes().get({id:parseInt($stateParams.id,10)}).$promise.then(
            function(response){
                $scope.dishDetail = response;
                $scope.showDish = true;
            }, 
            function(response){
                $scope.message = "Error: " + response.status + " " + response.statusText;
            }
        );
    }])

    .controller('DishCommentController', ['$scope', 'menuFactory', function($scope, menuFactory) {
        $scope.myComment = {rating:"5", comment:"", author:"", date:""};

        $scope.submitComment = function () {
            $scope.myComment.date = new Date().toISOString();
            $scope.myComment.rating = parseInt($scope.myComment.rating);
            console.log($scope.myComment);

            $scope.dishDetail.comments.push($scope.myComment);
            
            menuFactory.getDishes().update({id:$scope.dishDetail.id}, $scope.dishDetail);

            $scope.commentForm.$setPristine();

            $scope.myComment = {rating:"5", comment:"", author:"", date:""};
        };
    }])

    // implement the IndexController and About Controller here
    .controller('IndexController', ['$scope', '$stateParams', 'menuFactory', 'corporateFactory', function($scope, $stateParams, menuFactory, corporateFactory) {
        $scope.showDish = false;
        $scope.showPromotion = false;
        $scope.showExecutiveChef = false;
        $scope.message="Loading ...";

        menuFactory.getDishes().get({id:0}).$promise.then(
            function(response){
                $scope.dish = response;
                $scope.showDish = true;
            },
            function(response){
                $scope.message = "Error: " + response.status + " " + response.statusText;
            }
        );
        
        menuFactory.getPromotions().get({id:0}).$promise.then(
            function(response){
                $scope.promotion = response;
                $scope.showPromotion = true;
            },
            function(response){
                $scope.message = "Error: " + response.status + " " + response.statusTest;
            }
        );
        
        corporateFactory.getLeaders().get({id:3}).$promise.then(
            function(response){
                $scope.executiveChef = response;
                $scope.showExecutiveChef = true;
            },
            function(response){
                $scope.message = "Error: " + response.status + " " + response.statusTest;
            }
        );
    }])

    .controller('AboutController', ['$scope', '$stateParams', 'corporateFactory', function($scope, $stateParams, corporateFactory) {
        $scope.showLeaders = false;
        $scope.message = "Loading ...";
        corporateFactory.getLeaders().query(
            function (response){
                $scope.leaders = response;
                $scope.showLeaders = true;
            }, 
            function (response){
                $scope.message = "Error: " + response.status + " " + response.statusText;
            }
        );
    }]);