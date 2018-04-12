angular.module("phoneList").component('phoneList',
{

templateUrl:'./app/phone-list/phone-list.template.html',

controller:['$http','Phone',function phoneListController($http,Phone){
var toCheckScopeOfThis=this;

    //  console.log("res"+this);

// toCheckScopeOfThis.phones = [
//         {
//           name: 'Nexus S',
//           snippet: 'Fast just got faster with Nexus S.',
//           age:5
//         }, {
//           name: 'Kotorola XOOM™ with Wi-Fi',
//           snippet: 'The Next, Next Generation tablet.',
//           age:8,

//         }, {
//           name: 'MOKTOROLA XOOM™',
//           snippet: 'The Next, Next Generation tablet.',
//           age:1

//         }
//       ];



        // $http.get('./app/phones/phones.json').then(function(response){
        //   console.log("Hello");
        //   console.log("res"+response);
        //     toCheckScopeOfThis.phones=response.data;
        //   }).catch("exp");
      

         this.phones = Phone.query();

        toCheckScopeOfThis.orderProp="-age";

        
    //      $http.get("./app/phones/phones.json").then(function mySuccess(response) {
    //   console.log("res"+response.data);
    //     toCheckScopeOfThis.phones = response.data;
    //     console.log("this"+toCheckScopeOfThis.phones);
    // }, function myError(response) {
    //     toCheckScopeOfThis.phones = response.statusText;
    // });

  //  function innerfunction(){
  //   toCheckScopeOfThis.phones.push({
  //         name: 'Lenovo',
  //         snippet: 'Fast just got faster with Nexus S.',
  //         age:3
  //       });
  //  };
  // innerfunction();
  }
  ]
});       

