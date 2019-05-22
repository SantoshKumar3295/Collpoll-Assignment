app.service('TasksService', function($http, $q){
  var tasksList = [];
  var self = this;

  this.initTaskList = function(user){
     var deferred = $q.defer();
     $http.post('task/all', user)
         .then(function successCallBack(response) {
            tasksList = response.data;
            deferred.resolve(tasksList);
         }, function errorCallBack() {
            deferred.reject();
         })

     return deferred.promise;
  };

  this.addTask = function(newtask) {
      TasksList.push(newtask);
  };

  this.getTasks = function(){
      return TasksList;
  };
})