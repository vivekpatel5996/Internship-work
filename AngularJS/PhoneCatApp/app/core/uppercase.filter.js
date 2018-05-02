angular.module('core').filter('uppercase',function()
{
    return function(input)
    {
    	return angular.uppercase("by custom filter:"+input);
    }
});