app.controller('UserLoginController', function($rootScope, $timeout, $scope, $http, $location){
      $rootScope.user_obj = {};

      $scope.login = function() {
         $http.post('login', $scope.user_obj)
            .then(function successCallBack(response) {
                var user = response.data;
                console.log("user data", user);
                if(user && user.id) {
                    $rootScope.user_obj.isLoggedIn = true;
                    $rootScope.user_obj.userId = user.id;
                    $timeout(function() {
                        $location.path('/allTask');
                    }, 10);
                }
            })
      }
})

app.controller('TasksController', function($rootScope, $scope, $http, TasksService){
    $scope.task_obj = {};

    if($rootScope.user_obj && $rootScope.user_obj.isLoggedIn) {
        var postData = {};
        postData.id = $rootScope.user_obj.userId;
        postData.name = $rootScope.user_obj.name;
        TasksService.initTaskList(postData)
            .then(function successCallBack(response) {
                $scope.task_obj.task_list = response;
            })
    } else {
        //Goto the login page
    }
})

app.controller('AddTaskController', function($rootScope, $scope, $filter, $http, $timeout, $location){
    $scope.add_task = {};
    $scope.add_task.now = new Date();
    $scope.add_task.now.setMinutes($scope.add_task.now.getMinutes() + 5);

    $scope.addTask = function() {
        var postData = {};
        postData.date = $filter('date')($scope.add_task.date,'yyyy-MM-dd');
        postData.name = $scope.add_task.name;
        postData.description = $scope.add_task.desc;
        postData.user_id = $rootScope.user_obj.userId;

        $http.post('task/addTask/'+  postData.user_id, postData)
            .then(function successCallBack(response) {
               $timeout(function() {
                   $location.path('/allTask');
               }, 10);
            })
    }
})