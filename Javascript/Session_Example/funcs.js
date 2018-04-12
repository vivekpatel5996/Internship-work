function validateUser() 
{
	var name=document.getElementById("username").value;
	
	if(sessionStorage.getItem("username")==null)
	{
	  
	   sessionStorage.setItem("username",name);
	   console.log(sessionStorage.getItem("username"));
	   window.location="welcome.html";

    }
    else
    {
      document.getElementById("error").innerHTML="Already logged in";
    }
 
}