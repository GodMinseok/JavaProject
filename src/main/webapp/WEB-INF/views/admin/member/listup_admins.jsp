<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <jsp:include page="../../include/title.jsp" />

    <link href="<c:url value='/resources/css/admin/listup_admins.css' />" rel="stylesheet" type="text/css">

</head>
<body>

<jsp:include page="../../include/header.jsp" />

<jsp:include page="../include/nav.jsp" />

<section>

    <div id="section_wrap">

        <div class="word">

            <h3>ADMIN LIST</h3>

        </div>

        <div class="admin_list">

            <table>
                <thead>
                <tr>
                    <th>계정</th>
                    <th>이름</th>
                    <th>성별</th>
                    <th>부서</th>
                    <th>직무</th>
                    <th>메일</th>
                    <th>연락처</th>
                    <th>승인</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${adminMemberVos}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.gender}</td>
                        <td>${item.part}</td>
                        <td>${item.position}</td>
                        <td>${item.email}</td>
                        <td>${item.phone}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.approval eq 0}">
                                    <c:url value='/admin/member/setAdminApproval' var='approval_url'>
                                        <c:param name='no' value='${item.no}'/>
                                    </c:url>
                                    <a href="${approval_url}">승인처리</a>
                                </c:when>
                                <c:when test="${item.approval eq 1}"> <c:out value="승인완료" /> </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>

    </div>

</section>

<jsp:include page="../../include/footer.jsp" />

</body>
</html>