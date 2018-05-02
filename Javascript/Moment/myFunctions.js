function getStartEndDate()
{
       	  var p=document.getElementById("sta_end_dates").value;
       	  p="";
          var m=moment().subtract(6,'months').startOf('month').format('YYYY-MM-DD');
          console.log(m);
          for(var i=0;i<6;i++)
          {
               var startdate=moment(m).startOf('month').format('YYYY-MM-DD');
               p+=startdate+"  To  ";

               var enddate=moment(m).endOf('month').format('YYYY-MM-DD');
               p+=enddate+ " ";
               
               m=moment(m).add(1,'months');
               p+="<br>";

          }
       	 
       	    document.getElementById("sta_end_dates").innerHTML=p;
       	  //console.log(date);
}



function getDifference()
{
    var date1=moment(momentform.date1.value);
    var date2=moment(momentform.date2.value);
     
    var diff2=date2.diff(date1,'days');
    document.getElementById("diff").innerHTML=diff2+" days";

}


function addDays(days)
{
  var date1=moment(momentform.date1.value);
  var resultdate=moment(date1).add(days,'days').format('DD-MM-YYYY');
   document.getElementById("resultdate").innerHTML=resultdate;
   console.log(resultdate);
}