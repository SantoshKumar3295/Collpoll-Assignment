//Handles user login flow. This controller not being used other than login.html
app.controller('UserLoginController', function($timeout, $scope, $http, $location, UserService){
      $scope.user_obj = {};

      //Create user
      $scope.create = function() {
        if($scope.baseCheckNull()) {
            return;
        }
        $http.post('createUser', $scope.user_obj)
            .then(function successCallBack(response) {
                initUser(response, false);
            })
      }

      //Check if we have empty fields for username and password
      $scope.baseCheckNull = function() {
        var name_check = $scope.user_obj.name == undefined || $scope.user_obj.name.length < 1;
        var password_check = $scope.user_obj.password == undefined || $scope.user_obj.password.length < 1;
        return (name_check || password_check)
      }

      //Login user
      $scope.login = function() {
         if($scope.baseCheckNull()) {
            return;
         }
         $http.post('login', $scope.user_obj)
            .then(function successCallBack(response) {
                initUser(response, true);
            })
      }

      //Start initialising user object
      function initUser(response, isLogin) {
            var user = response.data;
            console.log("user data", user);
            if(user && user.id) {
               user.isLoggedIn = true;
               user.userId = user.id;

               //This service will init user object which can be use anywhere
               UserService.initUser(user);

                //Redirect to home page
                $timeout(function() {
                    $location.path('/allTask');
                }, 10);
            } else {
                if(isLogin) {
                    $scope.show_login_error = "Given username & password doesn't exist, please click on CREATE to create your account.";
                } else {
                    $scope.show_login_error = "Can't create your account, please try again."
                }
            }
      }
})

/*
**Handles Task operations : List, Edit & Delete
*This controller not being used other than tasks.html
*/
app.controller('TasksController', function($timeout, $scope, $http, UserService, TasksService){
    $scope.task_obj = {};
    $scope.filterByDate = function(date){
        //To apply proper digest wait for 10ms
        $timeout(function() {
               initTask(date);
        }, 10);
     }

     //Initialise the task list
     initTask();

     function initTask(date) {
        //This service will fetch all task list of current user
        TasksService.initTaskList(date)
            .then(function successCallBack(response) {
                $scope.task_obj.task_list = response;
            })
     }

    $scope.addSubTask = function(task_id, subtask) {
        var url = "subTask/addSubTask/"

        $http.post(url+task_id, subtask)
            .then(function successCallBack(response) {
                angular.extend(subtask, response.data);
            })
    }

    $scope.deleteSubTask = function(subtask_id, task) {
        var url = "subTask/deleteSubTask/";

        $http.delete(url+subtask_id)
            .then(function successCallBack(response) {

                //delete subtask from inmemory
                angular.forEach(task.subtasks, function(subtask, index) {
                    if(subtask.id == subtask_id) task.subtasks.splice(index, 1);
                })
            })
    }

    $scope.saveTask = function(task) {
        var postData = angular.copy(task);
        postData.description = postData.description_new;

        $http.put('task/editTask', postData)
            .then(function successCallBack(response) {
                angular.extend(task, response.data);
            })
    }

    $scope.deleteTask = function(id) {
        $http.delete('task/deleteTask/'+id)
            .then(function successCallBack(response) {
                //delete Task from inmemory
                angular.forEach($scope.task_obj.task_list, function(task, index) {
                    if(task.id == id) $scope.task_obj.task_list.splice(index, 1);
                })
            })
    }
})

/*
**Handles Add of Task
* This controller not being used other than addTasks.html
*/
app.controller('AddTaskController', function($scope, $filter, $http, $timeout, $location, UserService){
    $scope.add_task = {};

    //Initialise the current time for datepicker
    $scope.add_task.now = new Date();
    $scope.add_task.now.setMinutes($scope.add_task.now.getMinutes() + 5);

    $scope.addTask = function() {
        var postData = {};
        postData.date = $filter('date')($scope.add_task.date,'yyyy-MM-dd');
        postData.description = $scope.add_task.description;

        $http.post('task/addTask/'+  UserService.getUser().id, postData)
            .then(function successCallBack(response) {
               //Redirect back to task page
               $timeout(function() {
                   $location.path('/allTask');
               }, 10);
            })
    }
})