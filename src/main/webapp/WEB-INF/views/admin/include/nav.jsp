<%@ page import="daelim.book.rental.admin.member.AdminMemberVo" %><%--
  Created by IntelliJ IDEA.
  User: jjm42
  Date: 2024-05-03
  Time: 오후 3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/resources/css/admin/include/nav.css' /> " rel="stylesheet" type="text/css">
<jsp:include page="./nav_js.jsp"/>

<nav>
    <div id="nav_wrap">
        <%
            AdminMemberVo adminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
            if (adminMemberVo != null) {
        %>
        <div class="menu">
            <ul>
                <li><a href="<c:url value='/admin/member/logoutConfirm' />">로그아웃(${loginedAdminMemberVo.id})</a></li>
                <li><a href="<c:url value='/admin/member/modifyAccountForm' />">계정수정</a></li>

                <c:if test="${loginedAdminMemberVo.id eq 'system'}">
                    <li><a href="<c:url value='/admin/member/listupAdmin' />">관리자목록</a></li>
                </c:if>

                <li><a href="<c:url value='/book/admin/getRentalBooks' />">대출도서</a></li>
                <li><a href="<c:url value='/admin/member/getAllBooks' />">전체도서</a></li>
                <li><a href="<c:url value='/book/admin/getHopeBooks' />">희망도서(입고처리)</a></li>
                <li><a href="<c:url value='/book/admin/registerBookForm' />">도서등록</a></li>
            </ul>
        </div>
        <%
        } else {
        %>
        <div class="menu">
            <ul>
                <li><a href="<c:url value='/admin/member/loginForm' /> ">로그인</a></li>
                <li><a href="<c:url value='/admin/member/createAccountForm' /> ">회원가입</a></li>
            </ul>
        </div>
        <%
            }
        %>

        <div class="search">

            <form action="<c:url value='/book/admin/searchBookConfirm' />" name="search_book_form" method="get">
                <input type="text" name="name" placeholder="Enter the name of the book you are looking for.">
                <input type="button" value="search" onclick="searchBookForm();">
            </form>

        </div>

    </div>


</nav>
