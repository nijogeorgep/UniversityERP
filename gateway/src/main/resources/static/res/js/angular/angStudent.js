/*
// Created by Academy on 20/10/16
// Angular JS Module for managing Students
*/

// Configure the Angular Module 
// Client side routes for 
// the Student pages
angmodule.config(function($stateProvider) {
    var templateDir = '/pages/student/';

    $stateProvider.state('home.studentList', {
        url: '^/college/:id/students',
        controller: StudentListCtrl,
        templateUrl: templateDir+'studentList.html'
    }).state('home.student', {
        url: '^/college/:id/student',
        controller: StudentCtrl,
        templateUrl: templateDir+'student.html'
    }).state('home.editStudent', {
        url: '^/college/:id/student/:studentId',
        controller: StudentCtrl,
        templateUrl: templateDir+'student.html'
    });
});

// Write the Controller here for the Student pages
function StudentListCtrl($scope, $state, $stateParams, CrudService, $timeout){

    CrudService.get('/college/'+$stateParams.id).then( function(response){
        $scope.college = response.data;
    }, function(errorResponse){
        console.log(errorResponse);
    });

    $scope.collegeId = $stateParams.id;

    var studentList = function(){
        CrudService.list('/college/'+$stateParams.id+'/students').then(function(response){
            if(response.code ==200){
                $scope.students = response.data;
            }
        }, function(errorResponse){
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    };
    studentList();

    $scope.search1 = '';
    $scope.test = function(search){
        $scope.search1 = angular.copy(search)
    };

    $scope.activate = function(itemId){

                CrudService.update('/student/' + itemId + '/activate').then(function (response) {
                    console.log(response);
                    if (response.code == 200) {
                        studentList();
                    }
                }, function (errorResponse) {
                    console.log(errorResponse.error);
                    //$scope.error = errorResponse.error;
                });

    };

    $scope.deactivate = function(itemId){

                CrudService.update('/student/' + itemId + '/deactivate').then(function (response) {
                    console.log(response);
                    if (response.code == 200) {
                        studentList();
                    }
                }, function (errorResponse) {
                    console.log(errorResponse.error);
                    //$scope.error = errorResponse.error;
                });
    };

    $scope.editStudent =function(item){
        $state.go('home.editStudent',{id: $stateParams.id, studentId: item._id});
    };

    $scope.reset = function(){
        $scope.notFoundList = [];
        $scope.errorList = [];
        $scope.existList = [];
        $scope.message = '';
        $scope.item = '';
    }
};

function StudentCtrl($scope, $state, $stateParams, CrudService){
    $scope.collegeId = $stateParams.id;
    if($stateParams.studentId != undefined){
        CrudService.get('/student/'+$stateParams.studentId).then(function(response){
            if(response.code == 200){
                $scope.item = response.data;
            }

        }, function(errorResponse){
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    }

    var getHostelList = function(){
        CrudService.list('/college/'+$stateParams.id+'/activeHostels').then(function(response){
            if(response.code == 200){
                console.log(response.data);
                $scope.hostels = response.data;
            }
        }, function(errorResponse){
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    };
    getHostelList();

    $scope.maxDate = new Date();

    $scope.addStudent = function(item){
        if(item._id){
            CrudService.update('/student/'+$stateParams.studentId, item).then(function(response){
                if(response.code == 200){
                    $state.go('home.studentList',{id: $stateParams.id});
                }
            }, function(errorResponse){
                //console.log(errorResponse.error);
                $scope.error = errorResponse.error;
            })
        }
        else{
            CrudService.create('/college/'+$stateParams.id+'/student', item).then(function(response){
                if(response.code == 200){
                    $state.go('home.studentList',{id: $stateParams.id});
                }
            }, function(errorResponse){
                console.log(errorResponse.error);
                $scope.error = errorResponse.error;
            })
        }
    };
}