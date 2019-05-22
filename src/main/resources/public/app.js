var app = angular.module('ToDoListApp', ['ngRoute', 'ui.bootstrap']);

app.run(['$rootScope', '$location', function ($rootScope, $location, UserService) {
    $rootScope.$on('$routeChangeStart', function (event) {
        var user = UserService.getUser();

        if (!user.isLoggedIn) {
            console.log('DENY');
            event.preventDefault();
            $location.path('/');
        }
        else {
            console.log('ALLOW');
        }
    });
}]);

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
