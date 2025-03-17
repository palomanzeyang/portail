
	  $(document).ready(function(){
		  $("#example #checkall").click(function () {
		          if ($("#example #checkall").is(':checked')) {
		              $("#example input[type=checkbox]").each(function () {
		                  $(this).prop("checked", true);
		              });
	
		          } else {
		              $("#mexample input[type=checkbox]").each(function () {
		                  $(this).prop("checked", false);
		              });
		          }
		      });
		      
		      $("[data-toggle=tooltip]").tooltip();
		  });
