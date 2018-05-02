var async = require('async');

var args = process.argv;

function main () {
    var choice = args[2];
    if (!choice) {
        console.error('Please add your choice in command line argument');
        process.exit(1);
    }
    executeChoice(choice);
}

function executeChoice(choice) {
    switch (choice) {
        case 'series':
            seriesCall();
            break;

        case 'parallel':
            parallelCall();
            break;

        case 'waterfall':
            waterfallCall();
            break;

        case 'each':
            eachCall();
            break;

        default:
            console.error('Invalid choice');
            process.exit(1);
            break;
    }
}

function seriesCall() {
    async.series([
        function (callback) {
            task(1, Math.floor(1 * 1000), callback);
        },
        function (callback) {
            task(2, Math.floor(5 * 1000), callback);
        },
        function (callback) {
            task(3, Math.floor(3 * 1000), callback);
        }
    ], final);
}

function eachCall() {
    var arr = [{
        order: 1,
        delay: 1000
    },{
        order: 2,
        delay: 750
    },{
        order: 3,   
        delay: 650
    }];
    async.each(arr, function (data, callback) {
        task(data.order, data.delay, callback); 
    }, eachFinal)   
}

function parallelCall() {
    async.parallel([
        function (callback) {
            task(1, Math.floor(Math.random() * 1000), callback);
        },
        function (callback) {
            task(2, Math.floor(Math.random() * 1000), callback);
        },
        function (callback) {
            task(3, Math.floor(Math.random() * 1000), callback);
        }
    ], final);
}

function waterfallCall() {
    async.waterfall([
        function (callback) {
            var total = 0;
            var add = 5;
            var newTotal = total + add;
            console.log('%d + %d = %d', total, add, newTotal);
            callback(null, newTotal);
        },
        function (total, callback) {
            var add = 2;
            var newTotal = total + add;
            console.log('%d + %d = %d', total, add, newTotal);
            callback(null, newTotal);
        },
        function (total, callback) {
            var add  = 3;
            var newTotal = total + add;
            console.log('%d + %d = %d', total, add, newTotal);
            callback(null, newTotal);
        }
    ], waterfallFinal);
}

function task(order, delay, callback) {
    setTimeout(function () {
      console.log('Order %d and delay %d', order, delay);
      callback(null,order);
    }, delay);
}

function final(err, result) {
    if (err) {
        console.error(err);
        process.exit(1);
    }
    console.log(result.join(''));
    console.log('Finished');
    process.exit(0);
}

function waterfallFinal(err, result) {
    if (err) {
        console.error(err);
        process.exit(1);
    }
    console.log(result);
    console.log('Finished');
    process.exit(0);
}

function eachFinal(err) {
    if (err) {
        console.error(err);
        process.exit(1);
    }
    console.log('Finished');
    process.exit(0);
}

main();