var app = angular.module('ToDoListApp', ['ngRoute', 'ui.bootstrap']);

app.run(['$rootScope', '$location', 'UserService', function ($rootScope, $location, UserService) {
    $rootScope.$on('$routeChangeStart', function (event) {

        if(UserService == undefined) {
            $location.path('/');
            return;
        }

        if (!UserService.getUser().isLoggedIn && $location.path() != '/') {
            console.log('DENY');

            event.preventDefault();
            $location.path('/');
        } else if($location.path() == '/' && UserService.getUser().isLoggedIn){
            event.preventDefault();
        } else {
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
        .otherwise({
            redirectTo: '/#'
        })
});
