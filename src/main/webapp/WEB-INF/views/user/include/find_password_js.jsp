<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function findPasswordForm() {
		console.log('findPasswordForm() CALLED!!');
		
		let form = document.find_password_form;
		
		if (form.id.value == '') {
			alert('INPUT USER ID.');
			form.id.focus();
			
		} else if (form.name.value == '') {
			alert('INPUT USER NAME.');
			form.name.focus();
			
		} else if (form.email.value == '') {
			alert('INPUT USER MAIL.');
			form.email.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>