angular.module("phoneDetail").component('phoneDetail',{

templateUrl:'./app/phone-detail/phone-detail.template.html',

controller:['$http','$routeParams',function phoneDetailController($http,$routeParams){
               console.log("outside get");

var self=this;      
self.phoneId=$routeParams.phoneId;

self.setImage=function setImage(imageurl)
{
   self.mainImgUrl=imageurl;
};

$http.get('./app/phones/'+this.phoneId+'.json').then(function(response){
               console.log("inside get");
               self.phone=response.data;
               console.log(self.phone.images[0]);
               self.setImage(self.phone.images[0]);
           });
   }
  ]
});