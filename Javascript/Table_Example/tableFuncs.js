function addData ()
{
	var fname=document.getElementById("Firstname").value;
	var lname=document.getElementById("Lastname").value;
	var table=document.getElementById("data");
	var row=table.insertRow(0);



	var checkbox = document.createElement('input');
    checkbox.type = "checkbox";
    checkbox.name = "cb";
    checkbox.value = "value";

	var cell1=row.insertCell(0);
	cell1.appendChild(checkbox);
    
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    
    cell2.innerHTML = fname;
    cell3.innerHTML = lname;
}


function deleteData()
{
	 var table=document.getElementById("data");
	 var rc=table.rows.length;
	 for(var i=0;i<rc;i++)
	 {
	 	var row=table.rows[i];
	 	var checkbox=row.cells[0].childNodes[0];
	 	if(checkbox.checked)
	 	{
	 		table.deleteRow(i);
            i--;
	 	    rc--;
	    }
	 }
}