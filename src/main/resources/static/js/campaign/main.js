$(document).ready(function(){
	$("#campaignAdd").click(function() {
		$('#campaignModal').modal('hide')
		$("#campaignModal .modal-content").load("/campaign/modal/addForm");
		$("#campaignModal").modal("show");
	});
});
