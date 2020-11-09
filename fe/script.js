// 5
var number = Math.random();
var x_stmnt;
if (number > 0.7) {
    x_stmnt = 1;
} else {
    x_stmnt = 0;
}

var x_expr = number > 0.7 ? 1 : 0;

console.assert(x_stmnt === x_expr);


// 6
var arr = Array(50).fill(null);

var i;
var arr2_stmnt = [];
for (i = 0; i < arr.length; i += 1) {
  var val = Math.random();
  if (val < 0.3) continue;
  val = Math.floor(val * 100);
  arr2_stmnt.push(val);
}
// console.log(arr2_stmnt);

// var arr2_expr = arr.map(arr_value =>  {
//     arr_value
// })
// console.log(arr2_expr);

// 7
var foo = function foo(x) {
    if (x > 4) {
      throw new Error('x > 4')
    }
  
    x++
  
    x *= 100
  
    return Math.floor(x)
  }
  
//   [1, 2, 3, 4, 5].map(foo)

  // 8
console.log(5 == new Number(5)); // false -> true
console.log(new Number(5) == new Number(5)); // true - false
console.log(5 === new Number(5)); // true -> false
console.log(new Number(5) === new Number(5)); // true -> false
console.log(5 == [5]); // false - true
console.log(5 === [5]); // false
console.log(0 == false); // true
console.log(1 == true); // true
console.log(2 == true); // false -> true
console.log('' == false); // true -> false
console.log('0' == false); // true
console.log('' ? true : false); // false -> true
console.log('0' ? true : false); // false -> true
console.log(false == null); // false
console.log(false == undefined); // false
console.log(null == undefined); // false -> true

// 9 
function fibonacci_function_statement(n) { 
    if (n == 0) {
        return 0
    }
    if (n == 1) {
        return 1
    }
    if (n > 1) {
        return fibonacci_function_statement(n-1) + fibonacci_function_statement(n-2)
    }
}

// 10 

var teller = function(start) {
    this.start = start;

    var metStapGrootte = function(stapGrootte) {
        var volgende = function() {
            return start + stapGrootte;
        }
    };

    return {
        metStapGrootte,
    }

}

const T = teller(5).metStapGrootte(2);
console.log(T.volgende()); // 7
console.log(T.volgende()); // 9
console.log(T.volgende()); // 11