var myApp =  angular.module('demo', []);

myApp.controller('FormController', function ($scope, $http, $filter){
//	get all comments from the REST endpoint
    $scope.comments = [];
    $http.get('http://localhost:8080/CommentsManagement/rest/CommentsService/allcomments').
    then(function(response) {
        $scope.comments = response.data;
    });
    
//	add a new comment
    $scope.btn_add = function() {
        if($scope.txtcomment != null && $scope.txtcomment != ""){
        	comment = {
        		        "articleId": 1,
        		        "commentId": null,
        		        "content": $scope.txtcomment,
        		        "isDeleted": 0,
        		        "numberOfLikes": 0,
        		        "parentId": -1,
        		        "timeStamp": new Date().toUTCString(),
        		        "timesReported": 0,
        		        "userName": "Anonymous"
        		    }
        	$scope.txtcomment = "";
        	$http.post('http://localhost:8080/CommentsManagement/rest/CommentsService/submitcomment',
        			JSON.stringify(comment)).
            then(function(response) {
                comment = response.data;
                $scope.comments.push(comment);
            });
        }
    }
    
//	Like a comment
    $scope.btn_like = function(cId) {
    	var comment = $filter('filter')($scope.comments, {"commentId":cId})[0]
    	comment.numberOfLikes = comment.numberOfLikes + 1 
   	$http.post('http://localhost:8080/CommentsManagement/rest/CommentsService/submitlike',
			JSON.stringify(comment)).
    then(function(response) {
        comment = response.data;
    });
    }
 
//	hard delete a comment
    $scope.btn_delete = function(cId) {
    	var comment = $filter('filter')($scope.comments, {"commentId":cId})[0]
    	comment.isDeleted = 1
   	$http.post('http://localhost:8080/CommentsManagement/rest/CommentsService/deletecomment',
			JSON.stringify(comment)).
    then(function(response) {
        var index = $scope.comments.indexOf(comment);
        $scope.comments.splice(index, 1);
        console.log(index,comment,$scope.comments)
    });
    }
});