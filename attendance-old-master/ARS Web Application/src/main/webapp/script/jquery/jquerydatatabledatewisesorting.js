function calculate_date(dataTableDate1) {
    
    if(dataTableDate1.charAt(0) == '<'){
        while(dataTableDate1.charAt(0) == '<'){
            dataTableDate1=dataTableDate1.substr(dataTableDate1.indexOf('>', 0)+1);
        }
                
        dataTableDate1=dataTableDate1.substr(0, dataTableDate1.indexOf('<', 0));
    }
    
    var dataTableDate = dataTableDate1.replace(" ", "");
    var eu_date,year;
    if (dataTableDate.indexOf('-') > 0) {
        /*date a, format dd-mn-(yyyy) ; (year is optional)*/
        eu_date = dataTableDate.split('-');
    }
    else {
        /*date a, format dd/mn/(yyyy) ; (year is optional)*/
        eu_date = dataTableDate.split('/');
    }
    /*year (optional)*/
    if (eu_date[2]) {
        year = eu_date[2].substr(0, 4);
    } else {
        year = 0;
    }
    /*month*/
    var month = eu_date[1];
    /*day*/
    var day = eu_date[0].substr(eu_date[0].length-2, 2);
    return (year + month + day) * 1;
}
jQuery.fn.dataTableExt.oSort['eu_date-asc'] = function(a, b) {
    x = calculate_date(a);
    y = calculate_date(b);
    return ((x < y) ? -1 : ((x > y) ?  1 : 0));
};
jQuery.fn.dataTableExt.oSort['eu_date-desc'] = function(a, b) {
    x = calculate_date(a);
    y = calculate_date(b);
    return ((x < y) ? 1 : ((x > y) ?  -1 : 0));
};

function calculate_date_time(a) {
    
    if(a!='N/A'){
        if(a.charAt(0) == '<'){
            a=a.substr(a.indexOf('>', 0)+1);
            a=a.substr(0, a.indexOf('<', 0));
        }
        var frDatea = a.split(' ');
        var frTimea = frDatea[1].split(':');
        var frDatea2 = frDatea[0].split('-');
        if(frDatea[2] == 'PM'){
            if(frTimea[0] != '12'){
                frTimea[0]=Number(frTimea[0])+12;
            }
        }else{
            if(frTimea[0] == '12'){
                frTimea[0]='00';
            }
        }
        var x = (frDatea2[2] + frDatea2[1] + frDatea2[0] + frTimea[0] + frTimea[1] + frTimea[2]) * 1;
        return x;
    }else{
        return -999999;
    }
    
}

jQuery.fn.dataTableExt.oSort['eu_datetime-asc'] = function(a, b) {
    
    x = calculate_date_time(a);
    y = calculate_date_time(b);
    
    return ((x < y) ? -1 : ((x > y) ?  1 : 0));
};
jQuery.fn.dataTableExt.oSort['eu_datetime-desc'] = function(a, b) {
    x = calculate_date_time(a);
    y = calculate_date_time(b);
    
    return ((x < y) ? 1 : ((x > y) ?  -1 : 0));
};

function calculate_float(a) {
    
    if( a!='N/A'){
        if(a.charAt(0) == '<'){
            a=a.substr(a.indexOf('>', 0)+1);
            a=a.substr(0, a.indexOf('<', 0));
        }
        
        return parseFloat(a);
    }else{
        return parseFloat(999999);
    }
    
}

jQuery.fn.dataTableExt.oSort['eu_float-asc'] = function(a, b) {
    
    x = calculate_float(a);
    y = calculate_float(b);
    
    return ((x < y) ? -1 : ((x > y) ?  1 : 0));
};
jQuery.fn.dataTableExt.oSort['eu_float-desc'] = function(a, b) {
    x = calculate_float(a);
    y = calculate_float(b);
    
    return ((x < y) ? 1 : ((x > y) ?  -1 : 0));
};

function calculate_time(a) {
    
    if(a!='N/A'){
        if(a.charAt(0) == '<'){
            a=a.substr(a.indexOf('>', 0)+1);
            a=a.substr(0, a.indexOf('<', 0));
        }
        var frDatea = a.split(' ');
        var frTimea = frDatea[0].split(':');
        if(frDatea[1] == 'PM'){
            if(frTimea[0] != '12'){
                frTimea[0]=Number(frTimea[0])+12;
            }
        }else{
            if(frTimea[0] == '12'){
                frTimea[0]='00';
            }
        }
        var x = (frTimea[0] + frTimea[1] + frTimea[2]) * 1;
        return x;
    }else{
        return -999999;
    }
    
}

jQuery.fn.dataTableExt.oSort['eu_time-asc'] = function(a, b) {
    
    x = calculate_time(a);
    y = calculate_time(b);
    
    return ((x < y) ? -1 : ((x > y) ?  1 : 0));
};
jQuery.fn.dataTableExt.oSort['eu_time-desc'] = function(a, b) {
    x = calculate_time(a);
    y = calculate_time(b);
    
    return ((x < y) ? 1 : ((x > y) ?  -1 : 0));
};

//jQuery.extend( jQuery.fn.dataTableExt.oSort, {
//    "formatted-num-pre": function ( a ) {
//        if(a!='N/A'){
//            if(a.charAt(0) == '<'){
//                a=a.substr(a.indexOf('>', 0)+1);
//                a=a.substr(0, a.indexOf('<', 0));
//            }
//            alert(a)
//            return parseFloat(a);
//        }else{
//            return parseFloat(-999999);
//        }
//        
//    },
// 
//    "formatted-num-asc": function ( a, b ) {
//        return a - b;
//    },
// 
//    "formatted-num-desc": function ( a, b ) {
//        return b - a;
//    }
//} );
