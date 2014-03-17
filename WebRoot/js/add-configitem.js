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
    ajax_get_info({ data_type : "glf_table"}, "add_item_tb_name");
    
    // wait for a second, then make a ajax request to get all columns
    var ajax_get_column = function() {
        var default_tb_name = $("#add_item_tb_name").val();
        ajax_get_info({ data_type : "glf_unconfig", tb_name : default_tb_name }, "add_item_cl_name");
    }
    setTimeout(ajax_get_column, 1200);
    
    $("#add_item_tb_name").change( function() {
  		var selected_tb_name = $("#add_item_tb_name").val();
        ajax_get_info({ data_type : "glf_unconfig", tb_name : selected_tb_name }, "add_item_cl_name");
	});
    
	$('#addconfigitem_form').submit(function() {
		console.log("enter submit");
		var form_ele = $('#addconfigitem_form');
		var form_info = form_ele.serializeObject();
		var json_form_info = JSON.stringify(form_info);
		console.log(json_form_info);
        
        // $.post("http://localhost:5000/index_action", json_form_info,
		$.post("/addconfigitem_action", json_form_info,
	   		function(data){
	   			console.log("ajax callback function");
	     		if(data.op_result) {
                    console.log("Successfully add config item!");
                    alert("Successfully!");
                }
	   		}, "json"
	   	);

        return false;
    });
});
