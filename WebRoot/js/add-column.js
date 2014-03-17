// transfer_data: data transfer to back end
// select_dom_id: the id of the related select box 
function ajax_get_info(transfer_data, select_dom_id) {
    $.get("/get_info", transfer_data, function(data){
	 	console.log(data);
        var select_content = "";
        id_arr_str = data.id_array;
        id_arr = id_arr_str.split(",");
        for(var id in id_arr) {
            select_content += ('<option value="' + id_arr[id] + '">' + id_arr[id] + '</option>');
        }
        $("#" + select_dom_id).html(select_content);
	}, "JSON");
}

$(document).ready(function($) {
	ajax_get_info({ data_type : "glf_table"}, "add_cl_tb_name");

	$('#addcolumn_form').submit(function() {
		console.log("enter submit");
		var form_ele = $('#addcolumn_form');
		var form_info = form_ele.serializeObject();
		var json_form_info = JSON.stringify(form_info);
		console.log(json_form_info);
        
        // $.post("http://localhost:5000/index_action", json_form_info,
		$.post("/addcolumn_action", json_form_info,
	   		function(data){
	   			console.log("ajax callback function");
	     		if(data.op_result) {
                    console.log("Successfully add column!");
                    alert("Successfully add column!");
                }
	   		}, "json"
	   	);

        return false;
    });
});
