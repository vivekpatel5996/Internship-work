//angular.module('phonecatapp', ['phoneList']);

var phonecatapp=angular.module('phonecatapp', ['phoneList','phoneDetail','core','ngAnimate']);


phonecatapp.config(['$locationProvider','$routeProvider',function($locationProvider,$routeProvider) {
	//$locationProvider.hashPrefix('');
	$routeProvider
	.when("/phones",{
		template:"<phone-list></phone-list>"
	}).when("/phones/:phoneId",{
        template:"<phone-detail></phone-detail>"
	})
	.otherwise("/phones");

}]);


phonecatapp.controller('myController',function($scope){
	this.phones=[{name:'Moto',snippet:'Ekdam Mast faka'},{name:'Iphone',snippet:'Thik thak'}];
	this.myFunc = function() {
        this.showMe = "fgkiu";
    }
});


 	