var app = angular.module('ToDoListApp', ['ngRoute', 'ui.bootstrap']);

app.config(function($routeProvider){
    $routeProvider
        .when('/',
        {
            controller: 'UserLoginController',
            templateUrl: '/views/login.html'
        })
        .when('/allTask',
        {
            controller: 'TasksController',
            templateUrl: '/views/tasks.html'
        })
        .when('/addTask',
        {
            controller: 'AddTaskController',
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
