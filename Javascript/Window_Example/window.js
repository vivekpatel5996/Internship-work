var myWindow;
function openWindow()
{
	 myWindow = window.open("", "", "width=500,height=500"); 
	 parent.document.body.style.backgroundColor = "red"

	 
}


function closeWindow()
{
	myWindow.close();
}