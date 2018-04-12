window.onload=function(){
    func();
};

function validateUsername()
{
	var uname=document.forms["loginform"]["firstname"].value;
    var letterNumber = /^[0-9a-zA-Z]+$/;
    var regex=new RegExp(letterNumber);
     
    if(uname.length==0)
    {
    	alert("Enter username");
    	uname.focus();
    	return false;
    }
    else if(regex.test(uname)==false)
    {  

       document.getElementById("uname_error").innerHTML="Enter valid username!";
       uname.focus();
       return false;
    }
    
    document.forms["loginform"]["password"].focus();
    return true;
}


function validatePassword()
{
	
  
    var pwd=document.forms["loginform"]["password"].value;
          	
	 if(pwd.length<7)
    {
        alert("enter password with min 7 letters!");
        pwd.focus();
        return false;
    }
    else if(pwd.length==0)
	{
		alert("enter password");
        pwd.focus();
        return false;
	}	
    

    return true;
}

function changeCities()
{
	var state=document.getElementById("State").value;
    var city=document.getElementById("City");

    var option=document.createElement("option");
    console.log(state);
	if(state=="Gujarat")
	{
		city.options.length=0;
		 var option1=document.createElement("option");
		option1.text=option1.value="Ahmedabad";
        city.add(option1,0);
         var option2=document.createElement("option");
        option2.text=option2.value="Vadali";
        city.add(option2,1);

	}
	else if(state="Rajasthan")
	{
		city.options.length=0;
		 var option1=document.createElement("option");
        option1.text=option1.value="Udaipur";
        city.add(option1,0);
         var option2=document.createElement("option");
        option2.text=option2.value="Jaipur";
        city.add(option2,1);

	}


}


function func()	
{
	console.log("here");
	var state=document.getElementById("State").value;
    var city=document.getElementById("City");

    var option=document.createElement("option");
    console.log(state);
	
		city.options.length=0;
		var option1=document.createElement("option");
		option1.text=option1.value="Ahmedabad";
        city.add(option1,0);
        var option2=document.createElement("option");
        option2.text=option2.value="Vadali";
        city.add(option2,1);

	
}


function validateEmail()
{
     var email=document.getElementById("email").value;
     var regex=/^[\w]+[.\w]*@gmail.com$/;
     var regexemail=new RegExp(regex);

     if(regexemail.test(email)==false)
     { 
       alert("Enter valid email");
       email.focus();
       return false;
     }
}


function validateContact()
{
    var contact=document.getElementById("contact").value;
     var regex=/^[\d]{10}$/;
     var regexcontact= new RegExp(regex);

     if(regexcontact.test(contact)==false)
     {
        alert("Enter valid contact");
        contact.focus();
        return false;
     }
     return true;
}

function validateDob(dob)
{
    
     var isValid=new Date(dob.value);
     if(!isValid)
     	alert("Enter valid data");
}