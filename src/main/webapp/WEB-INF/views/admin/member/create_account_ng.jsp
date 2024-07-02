<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <jsp:include page="../../include/title.jsp" />

    <link href="<c:url value='/resources/css/admin/create_account_result.css' />" rel="stylesheet" type="text/css">

</head>
<body>

<jsp:include page="../../include/header.jsp" />

<jsp:include page="../include/nav.jsp" />

<section>

    <div id="section_wrap">

        <div class="word">

            <h3>CREATE ACCOUNT Fail!!</h3>

        </div>

        <div class="others">

            <a href="<c:url value='admin/member/createAccountForm' />">create account</a>
            <a href="<c:url value='/admin/member/loginForm' />">login</a>

        </div>

    </div>

</section>

<jsp:include page="../../include/footer.jsp" />

</body>
</html>