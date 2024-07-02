<%--
  Created by IntelliJ IDEA.
  User: jjm42
  Date: 2024-05-17
  Time: 오후 2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <jsp:include page="../../include/title.jsp" />

    <link href="<c:url value='/resources/css/admin/create_account_form.css' />" rel="stylesheet" type="text/css">


</head>
<body>

<jsp:include page="../../include/header.jsp" />

<jsp:include page="../include/nav.jsp" />

<section>

    <div id="section_wrap">

        <div class="word">

            <h3>CREATE ACCOUNT FORM</h3>

        </div>

        <div class="create_account_form">
            <form action="<c:url value='/admin/member/createAccountConfirm'/> " name="create_account_form" method="post" >
                <input type="text" name="id" placeholder="INPUT ADMIN ID."> <br>
                <input type="password" name="password" placeholder="INPUT ADMIN PW."> <br>
                <input type="password" name="password_again" placeholder="INPUT ADMIN PW AGAIN."> <br>
                <input type="text" name="name" placeholder="INPUT ADMIN NAME."> <br>
                <select name="gender">
                    <option value="">SELECET ADMIN GENDER.</option>
                    <option value="M">Man</option>
                    <option value="W">Woman</option>
                </select> <br>
                <input type="text" name="part" placeholder="INPUT ADMIN PART."> <br>
                <input type="text" name="position" placeholder="INPUT ADMIN POSITION."> <br>
                <input type="email" name="email" placeholder="INPUT ADMIN MAIL." ><br>
                <input type="text" name="phone" placeholder="INPUT ADMIN PHONE."> <br>
                <input type="button" value="create account" onclick="createAccountForm()">
                <input type="reset" value="reset">
            </form>
        </div>

        <div class="login">

            <a href="<c:url value='/admin/member/loginForm' />">login</a>

        </div>
    </div>
</section>

<jsp:include page="../../include/footer.jsp" />

<script type="text/javascript">
    function createAccountForm() {
        console.log("createAccountForm() Called!")
        //1. form validation
        let form = document.create_account_form;
        if (form.id.value === '' ) {
            alert('input admin id');
            form.id.focus();
        }
        else if (form.password.value === '' ) {
            alert('input admin password');
            form.password.focus();
        }
        else if (form.password_again.value === '' ) {
            alert('input admin password again');
            form.password_again.focus();
        }
        else if (form.password.value !== form.password_again.value ) {
            alert('please check your pasword again');
            form.password.focus();
        }
        else if (form.name.value === '' ) {
            alert('input admin name');
            form.name.focus();
        }
        else if (form.email.value === '' ) {
            alert('input admin email');
            form.email.focus();
        }
        else {
            form.submit();
        }
    }
</script>

</body>
</html>
