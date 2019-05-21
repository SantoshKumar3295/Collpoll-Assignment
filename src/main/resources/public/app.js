var app = angular.module('ToDoListApp', ['ngRoute']);

app.config(function($routeProvider){
    $routeProvider
        .when('/',
        {
            controller: 'ListTasksController',
            templateUrl: '/views/login.html'
        })
        .when('/addTask',
        {
            controller: 'AddTasksController',
            templateUrl: '/views/addTasks.html'
        })
        .when('/editTask:id',
        {
            controller: 'EditTasksController',
            templateUrl: '/views/editTasks.html'
        })
        .when('/completedTasks',
        {
            controller: 'ListTasksController',
            templateUrl: '/views/completedTasks.html'
        })
        .otherwise({
            redirectTo: '/#'
        })

});
