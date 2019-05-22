/*
**Handles user login flow
*/

app.controller('UserLoginController', function($timeout, $scope, $http, $location, UserService){
      $scope.user_obj = {};

      $scope.create = function() {
        $http.post('createUser', $scope.user_obj)
            .then(function successCallBack(response) {
                initUser(response);
            })
      }

      $scope.login = function() {
         console.log("user obj", $scope.user_obj);
         $http.post('login', $scope.user_obj)
            .then(function successCallBack(response) {
                initUser(response);
            })
      }

      function initUser(response) {
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
      }
})

/*
**Handles Task operations : List, Edit & Delete
*/
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
                angular.forEach(task.subtasks, function(subtask, index) {
                    if(subtask.id == subtask_id) task.subtasks.splice(index, 1);
                })
            })
    }

    $scope.saveTask = function(task) {
        $http.put('task/editTask', task)
            .then(function successCallBack(response) {
                angular.extend(task, response.data);
            })
    }

    $scope.deleteTask = function(id) {
        $http.delete('task/deleteTask/'+id)
            .then(function successCallBack(response) {
                angular.forEach($scope.task_obj.task_list, function(task, index) {
                    if(task.id = id) $scope.task_obj.task_list.splice(index, 1);
                })
            })
    }
})

/*
**Handles Add of Task
*/
app.controller('AddTaskController', function($scope, $filter, $http, $timeout, $location, UserService){
    $scope.add_task = {};
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