<script>
   $(document).ready(function(){
      $("#AddComputer").validate();
    });
</script>

jQuery(document).ready(function() {
	   jQuery("#formStep").validate({
	      rules: {
	         "name":{
	            "required": true,
	            "minlength": 2,
	            "maxlength": 280
	         },
	         "intro": {
	        	 "required": false
	            "maxlength": 255
	         },
	         "disc": {
	        	 "required": false
	         },
	         "companyname": {
	        	 "required": false
	         }
	         
	  })
	});