   $(document).ready(function(){
      $("#AddComputerForm").validate({
	      rules: {
		         "name": {
		            "required": true,
		            "minlength": 1,
		            "maxlength": 280
		         },
		         "intro": {
		        	 "required": false
		         },
		         "disc": {
		        	 "required": false
		         },
		         "companyname": {
		        	 "required": false
		         }
	      }
      });

   
   $("AddComputerForm").submit(function(event){
	  var intro = $('#intro').val();
	  var disc = $('#disc').val();
	  if  (new Date(intro).getTime() > new Date(disc).getTime())	{
		  alert("Date of discontinuation must be superior to the date of introduction.");
	  }
   });
   
});