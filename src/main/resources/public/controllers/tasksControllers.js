app.controller('UserLoginController', function($timeout, $scope, $http, $location, UserService){
      $scope.user_obj = {};

      $scope.login = function() {
         console.log("user obj", $scope.user_obj);
         $http.post('login', $scope.user_obj)
            .then(function successCallBack(response) {
                var user = response.data;
                console.log("user data", user);
                if(user && user.id) {
                   user.isLoggedIn = true;
                   user.userId = user.id;

                   UserService.initUser(user);

                    $timeout(function() {
                        $location.path('/allTask');
                    }, 10);
                }
            })
      }
})

app.controller('TasksController', function($timeout, $scope, $http, UserService, TasksService){
    $scope.task_obj = {};

     $scope.filterByDate = function(){
        $timeout(function() {
               initTask($scope.task_obj.date);
        }, 10);
     }

     initTask();

     function initTask(date) {
        TasksService.initTaskList(date)
            .then(function successCallBack(response) {
                $scope.task_obj.task_list = response;
            })
     }

    $scope.saveTask = function(task) {
        $http.put('task/editTask', task)
            .then(function successCallBack(response) {
                console.log("hmmmmm");
            })
    }

    $scope.deleteTask = function(id) {
        $http.delete('task/deleteTask/'+id)
            .then(function successCallBack(response) {
                console.log("task got delted");
            })
    }
})

app.controller('AddTaskController', function($scope, $filter, $http, $timeout, $location, UserService){
    $scope.add_task = {};
    $scope.add_task.now = new Date();
    $scope.add_task.now.setMinutes($scope.add_task.now.getMinutes() + 5);

    $scope.addTask = function() {
        var postData = {};
        postData.date = $filter('date')($scope.add_task.date,'yyyy-MM-dd');
        postData.name = $scope.add_task.name;
        $http.post('task/addTask/'+  UserService.getUser().id, postData)
            .then(function successCallBack(response) {
               $timeout(function() {
                   $location.path('/allTask');
               }, 10);
            })
    }
})