<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function modifyAccountForm() {
		console.log('modifyAccountForm() CALLED!!');
		
		let form = document.modify_account_form;
		
		if (form.name.value == '') {
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