//Holds the user information
app.service('UserService', function() {
    var user = {};

    this.initUser = function(user_obj) {
        user = user_obj;
    }

    this.getUser = function() {
        return user;
    }
});

//Holds Task property and Task operation function
app.service('TasksService', function($http, $q, UserService, UtilService){
  var tasksList = [];
  var self = this;

  this.initTaskList = function(date){
     var deferred = $q.defer();
     console.log(date);

     var date_str = "";

     if(date != undefined)
        date_str = "/"+UtilService.filterDate(date);

     $http.post('task/all'+ date_str, UserService.getUser())
         .then(function successCallBack(response) {
            tasksList = response.data;
            deferred.resolve(tasksList);
         }, function errorCallBack() {
            deferred.reject();
         })

     return deferred.promise;
  };

})

app.service('UtilService', function($http, $q, $filter){
   this.filterDate = function(date) {
     return $filter('date')(date,'yyyy-MM-dd');
   }
})

