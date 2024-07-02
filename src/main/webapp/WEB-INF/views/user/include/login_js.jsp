<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function loginForm() {
		console.log('loginForm() CALLED!!');
		
		let form = document.login_form;
		
		if (form.id.value == '') {
			alert('INPUT USER ID.');
			form.id.focus();
			
		} else if (form.pw.value == '') {
			alert('INPUT USER PW.');
			form.pw.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>