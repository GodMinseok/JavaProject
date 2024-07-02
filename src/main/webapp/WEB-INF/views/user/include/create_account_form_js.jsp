<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function createAccountForm() {
		console.log('createAccountForm() CALLED!!');
		
		let form = document.create_account_form;
		
		if (form.id.value == '') {
			alert('INPUT USER ID.');
			form.id.focus();
			
		} else if (form.pw.value == '') {
			alert('INPUT USER PW.');
			form.pw.focus();
			
		} else if (form.pw_again.value == '') {
			alert('INPUT USER PW AGAIN.');
			form.pw_again.focus();
			
		} else if (form.pw.value != form.pw_again.value) {
			alert('Please check your password again.');
			form.pw.focus();
			
		} else if (form.name.value == '') {
			alert('INPUT USER NAME.');
			form.name.focus();
			
		} else if (form.gender.value == '') {
			alert('SELECET USER GENDER.');
			form.gender.focus();
			
		} else if (form.email.value == '') {
			alert('INPUT USER MAIL.');
			form.email.focus();
			
		} else if (form.phone.value == '') {
			alert('INPUT USER PHONE.');
			form.phone.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>