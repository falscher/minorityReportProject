$(document).ready(function() {
	$('#submit').click(function(e) {
		var query = $('query').val();


		$.ajax({
			type: "POST",
			url: "DatabaseInfo.java",
			data: query,
			dataType: "json",

			success: function(data, textStatus, jqXHR) {
				if (data.success) {
					//append information
				}
				else {
					//append wrong
				}
			},

			error: function (jqXHR, textStatus, errorThrown) {
				console.log("DANG! ERROR!" + textStatus);
				//append to html
			},

			beforeSend: function (jqXHR) {
				settings.data += "&dummyData=whatever";

				$('#submit').attr("disabled", true);
			},

			complete: function (jqXHR, textStatus) {
				$('#submit').attr("disabled", true);
			}
		});
	});
});