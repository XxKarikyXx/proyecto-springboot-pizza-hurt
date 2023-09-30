  document.addEventListener("DOMContentLoaded", function(event) {
  	var containerSum = document.getElementsByClassName("super-sum");
  	var elementsToSum;
  	for (var i = 0; i < containerSum.length; i++) {
  		elementsToSum = containerSum[i].getElementsByClassName("sum");
  		sumElements(elementsToSum);
  		for (var j = 0; j < elementsToSum.length; j++) {  	
		  	elementsToSum[j].addEventListener("change", function(event) {
			  	sumElements(elementsToSum);
		  	});
		  
		  
		}
  	}
 
  });
  
  function sumElements(sumElements){
		var total = 0;
	  	var element;
	  	for (var k = 0; k < sumElements.length; k++) {
	  		element = sumElements[k].options[sumElements[k].selectedIndex].getAttribute("price");
	  		if (element != null){
	  			total += parseFloat(element);
	  		}  		
	  	}
	  	var totals = sumElements[0].closest(".super-sum").getElementsByClassName("total");		  	
	  	for (var m = 0; m < totals.length; m++) {
	  		element = totals[m].innerText = "$" + total;
	  	}
  }