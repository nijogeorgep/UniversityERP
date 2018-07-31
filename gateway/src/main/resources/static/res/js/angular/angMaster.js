/*
// Created by Academy on 20/10/16
// Angular JS Module for managing the Masters
*/

// Configure the Angular Module 
// Client side routes for 
// the Masters Pages
angmodule.config(function($stateProvider) {

    var masterDir = '/pages/master/';

    $stateProvider.state('home.country', {
        url: '^/countries',
        controller: CountryListCtrl,
        templateUrl: masterDir+'country.html'
    }).state('home.state', {
        url: '^/states',
        controller: StateListCtrl,
        templateUrl: masterDir+'state.html'
    }).state('home.city', {
        url: '^/cities',
        controller: CityListCtrl,
        templateUrl: masterDir+'city.html'
    });
});

// Write the Controller here for the Country Master pages
function CountryListCtrl($scope, $state, CrudService, $timeout){

    var countryList = function(){
        CrudService.get('/countries').then(function(response){
            console.log(response)
            if(response.code == 200){
                $scope.countries = response.data;
            }
        }, function(errorResponse){
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    };

    countryList();


    $scope.editCountry = function(item){
        $scope.country = angular.copy(item);
    }

    $scope.addCountry = function(item){
        if(item._id){
            console.log(item)

            CrudService.update('/country/'+item._id, item).then(function(response){
                console.log(response);
                if(response.code ==200){
                    $scope.dismiss();
                    countryList()
                }
            }, function(errorResponse){
                console.log(errorResponse.error);
                $scope.error = errorResponse.error;
            })
        }
        else{
            CrudService.create('/country', item).then(function(response){
                console.log(response);
                if(response.code ==200){
                    $scope.dismiss();
                    countryList();
                }

            }, function(errorResponse){
                console.log(errorResponse.error);
                $scope.error = errorResponse.error;
            });
        }
    };


    $scope.resetForm = function(){
        $scope.country = '';
        $scope.error = '';
        $scope.countryForm.$setPristine();
    };

    $scope.activate = function(itemId){
        CrudService.update('/country/' + itemId + '/activate').then(function (response) {
            console.log(response);
            if (response.code == 200) {
                countryList();
            }
        }, function (errorResponse) {
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    };

    $scope.deactivate = function(itemId){
        CrudService.update('/country/' + itemId + '/deactivate').then(function (response) {
            console.log(response);
            if (response.code == 200) {
                countryList();
            }
        }, function (errorResponse) {
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    }
}

// Write the Controller here for the State Master pages
function StateListCtrl($scope, $state, CrudService, $timeout){

    var stateList = function(){
        CrudService.get('/states').then(function(response){
            console.log(response)
            if(response.code == 200){
                $scope.states = response.data;
            }
        }, function(errorResponse){
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    };

    stateList();

    $scope.getCountryList = function(){
        CrudService.get('/activeCountries').then(function(response){
            console.log(response);
            if(response.code ==200){
                $scope.countries = response.data;
            }
        }, function(errorResponse){
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    };

    $scope.editState = function(item){
        $scope.state = angular.copy(item);
        $scope.state.country = item.country._id;
        $scope.getCountryList();
    };

    $scope.addState = function(item){
        if(item._id){
            console.log(item)

            CrudService.update('/state/'+item._id, item).then(function(response){
                console.log(response);
                $scope.dismiss();
                stateList();
            }, function(errorResponse){
                console.log(errorResponse.error);
                $scope.error = errorResponse.error;
            })
        }
        else{
            CrudService.create('/state', item).then(function(response){
                console.log(response);
                $scope.dismiss();
                stateList();

            }, function(errorResponse){
                console.log(errorResponse.error);
                $scope.error = errorResponse.error;
                $scope.stateForm.$setPristine();
            })
        }
    };

    $scope.resetForm = function(){
        $scope.state = '';
        $scope.error = '';
        $scope.stateForm.$setPristine();
    };

    $scope.activate = function(itemId){

                CrudService.update('/state/' + itemId + '/activate').then(function (response) {
                    console.log(response);
                    stateList();
                }, function (errorResponse) {
                    console.log(errorResponse.error);
                    //$scope.error = errorResponse.error;
                });

    }

    $scope.deactivate = function(itemId){
                CrudService.update('/state/' + itemId + '/deactivate').then(function (response) {
                    console.log(response);
                    stateList();
                }, function (errorResponse) {
                    console.log(errorResponse.error);
                    //$scope.error = errorResponse.error;
                });

    }
}

// Write the Controller here for the City Master pages
function CityListCtrl($scope, $state, $stateParams, CrudService){

    var getCityList = function(){
        CrudService.list('/cities').then(function(response){
            console.log(response);
            if(response.code ==200){
                $scope.cities = response.data;
            }
        }, function(errorResponse){
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    };

    getCityList();

    $scope.activate = function(itemId){
                CrudService.update('/city/'+itemId+'/activate').then(function(response){
                    console.log(response);
                    if(response.code ==200){
                        getCityList();
                    }
                }, function(errorResponse){
                    console.log(errorResponse.error);
                    //$scope.error = errorResponse.error;
                });
    };

    $scope.deactivate = function(itemId){

                CrudService.update('/city/' + itemId + '/deactivate').then(function (response) {
                    console.log(response);
                    if (response.code == 200) {
                        getCityList();
                    }
                }, function (errorResponse) {
                    console.log(errorResponse.error);
                    //$scope.error = errorResponse.error;
                });
    };

    $scope.getCountryList = function(){
        CrudService.list('/activeCountries').then(function(response){
            console.log(response);
            if(response.code ==200){
                $scope.countries = response.data;
            }
        }, function(errorResponse){
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    };

    $scope.getStateList = function(){
        CrudService.list('/country/'+$scope.city.country+'/states').then(function(response){
            console.log(response);
            if(response.code ==200){
                $scope.states = response.data;
            }
        }, function(errorResponse){
            console.log(errorResponse.error);
            //$scope.error = errorResponse.error;
        });
    };

    $scope.addCity = function(item){
        if(item._id){
            console.log(item);

            CrudService.update('/city/'+item._id, item).then(function(response){
                console.log(response);
                if(response.code ==200){
                    $scope.dismiss();
                    getCityList()
                }
            }, function(errorResponse){
                $scope.error = errorResponse.error;
            })
        }
        else{
            CrudService.create('/city', item).then(function(response){
                console.log(response);
                if(response.code ==200) {
                    $scope.dismiss();
                    getCityList();
                }

            }, function(errorResponse){
                console.log(errorResponse);
                $scope.error = errorResponse.error;
            })
        }

    };


    $scope.editCity = function(item){
        $scope.city = angular.copy(item);
        $scope.city.country = item.state.country;
        $scope.city.state = item.state._id;
        console.log(item)
        $scope.getCountryList();
        $scope.getStateList();
    };

    $scope.resetForm = function(){
        $scope.city = '';
        $scope.error = '';
        $scope.cityForm.$setPristine();
    };
}
