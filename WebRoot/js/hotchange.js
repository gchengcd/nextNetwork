$(document).ready(function($) {
	$("#hotchange").click(function(){
		console.log("1111111111111111111111111");
		$.post("servlet/HotLinkChangeServlet" , function(data){
			console.log(data.module_html);
			$("#appid-4 .widget-content ul").html(data.module_html);
        },"json");
	});
	
});

