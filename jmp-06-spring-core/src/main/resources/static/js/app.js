var taskManagerModule = angular.module('taskManagerApp', ['ngAnimate']);

taskManagerModule.controller('taskManagerController', function ($scope, $http) {

    var urlBase = "";
    $scope.toggle = true;
    $scope.selection = [];
    $scope.statuses = ['ACTIVE', 'COMPLETED'];
    $scope.priorities = ['HIGH', 'LOW', 'MEDIUM'];
    $http.defaults.headers.post["Content-Type"] = "application/json";

    function findAllTasks() {
        $http.get(urlBase + '/tasks').
            success(function (data) {
                if (data._embedded != undefined) {
                    $scope.tasks = data._embedded.tasks;
                } else {
                    $scope.tasks = [];
                }
                for (var i = 0; i < $scope.tasks.length; i++) {
                    if ($scope.tasks[i].taskStatus == 'COMPLETED') {
                        $scope.selection.push($scope.tasks[i].taskId);
                    }
                }
                $scope.taskName = "";
                $scope.taskDesc = "";
                $scope.taskPriority = "";
                $scope.taskStatus = "";
                $scope.toggle = '!toggle';
            });
    }

    findAllTasks();

    $scope.addTask = function addTask() {
        if ($scope.taskName == "" || $scope.taskDesc == "" || $scope.taskPriority == "" || $scope.taskStatus == "") {
            alert("More info");
        }
        else {
            $http.post(urlBase + '/tasks', {
                taskName: $scope.taskName,
                taskDescription: $scope.taskDesc,
                taskPriority: $scope.taskPriority,
                taskStatus: $scope.taskStatus
            }).
                success(function (data, status, headers) {
                    findAllTasks();
                });
        }
    };

    $scope.toggleSelection = function toggleSelection(taskUri) {
        var idx = $scope.selection.indexOf(taskUri);
        if (idx > -1) {
            $http.patch(taskUri, {taskStatus: 'ACTIVE'}).
                success(function (data) {
                    findAllTasks();
                });
            $scope.selection.splice(idx, 1);
        }
        else {
            $http.patch(taskUri, {taskStatus: 'COMPLETED'}).
                success(function (data) {
                    findAllTasks();
                });
            $scope.selection.push(taskUri);
        }
    };

    $scope.archiveTasks = function archiveTasks() {
        $scope.selection.forEach(function (taskUri) {
            if (taskUri != undefined) {
                $http.patch(taskUri, {taskArchived: 1});
            }
        });
        findAllTasks();
    };

});

taskManagerModule.directive('ngConfirmClick', [
    function () {
        return {
            link: function (scope, element, attr) {
                var msg = attr.ngConfirmClick || "Are you sure?";
                var clickAction = attr.confirmedClick;
                element.bind('click', function (event) {
                    if (window.confirm(msg)) {
                        scope.$eval(clickAction);
                    }
                });
            }
        };
    }]);