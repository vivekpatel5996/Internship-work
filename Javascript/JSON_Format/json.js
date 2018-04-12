function Validateformat(str)
{
 try {
        JSON.parse(str);

    } catch (e) {
    	console.log('invalid')
        return false;
    }
    console.log('valid');
    return true;
}