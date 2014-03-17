$(document).ready(function($) {
	$('#addtable_form').submit(function() {
		console.log("enter submit");
		var form_ele = $('#addtable_form');
		var form_info = form_ele.serializeObject();
		var json_form_info = JSON.stringify(form_info);
		console.log(json_form_info);
        
        // $.post("http://localhost:5000/index_action", json_form_info,
		$.post("/addtable_action", json_form_info,
	   		function(data){
	   			console.log("ajax callback function");
	     		if(data.op_result) {
                    console.log("Successfully create table!");
                    alert("Successfully create table!");
                }
	   		}, "json"
	   	);

        return false;
    });
});
