<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <jsp:include page="../../include/title.jsp" />

    <link href="<c:url value='/resources/css/admin/book_detail.css' />" rel="stylesheet" type="text/css">

</head>
<body>

<jsp:include page="../../include/header.jsp" />

<jsp:include page="../include/nav.jsp" />

<section>

    <div id="section_wrap">

        <div class="word">

            <h3>BOOK DETAIL</h3>

        </div>

        <div class="book_detail">

            <ul>
                <li>
                    <img src="<c:url value="/resources/upload/${bookVo.thumbnail}"/>">
                </li>
                <li>
                    <table>
                        <tr>
                            <td>도서명</td>
                            <td>${bookVo.name}</td>
                        </tr>
                        <tr>
                            <td>저자</td>
                            <td>${bookVo.author}</td>
                        </tr>
                        <tr>
                            <td>발행처</td>
                            <td>${bookVo.publisher}</td>
                        </tr>
                        <tr>
                            <td>발행년도</td>
                            <td>${bookVo.publishYear}</td>
                        </tr>
                        <tr>
                            <td>ISBN</td>
                            <td>${bookVo.isbn}</td>
                        </tr>
                        <tr>
                            <td>청구기호</td>
                            <td>${bookVo.callNumber}</td>
                        </tr>
                        <tr>
                            <td>대출가능</td>
                            <td>
                                <c:choose>
                                    <c:when test="${bookVo.rentalAble eq '0'}"> <c:out value="X" /> </c:when>
                                    <c:when test="${bookVo.rentalAble eq '1'}"> <c:out value="O" /> </c:when>
                                    <c:otherwise> <c:out value="X" /> </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td>등록일</td>
                            <td>${bookVo.regDate}</td>
                        </tr>
                        <tr>
                            <td>수정일</td>
                            <td>${bookVo.modDate}</td>
                        </tr>
                    </table>
                </li>
            </ul>

        </div>

        <div class="buttons">

            <c:url value='/admin/member/modifyBookForm' var='modify_url'>
                <c:param name='no' value='${bookVo.no}'/>
            </c:url>
            <a class="modify_book_button" href="${modify_url}">도서 수정</a>

            <c:url value='/book/admin/deleteBookForm' var='delete_url'>
                <c:param name='b_no' value='${bookVo.no}'/>
            </c:url>
            <a class="delete_book_button" href="#none" onclick="deleteBook(${bookVo.no},'${bookVo.name}')">도서 삭제</a>

        </div>

    </div>

</section>

<jsp:include page="../../include/footer.jsp" />

<script type="text/javascript">

    function deleteBook(no, name) {
        console.log('deleteBook() CALLED!!');

        let result = confirm('도서(' + name + ')를(을) 정말 삭제 하시겠습니까?');

        if (result)
            location.href = "<c:url value='/book/admin/deleteBookForm?no='/>" + no;

    }

</script>
</body>
</html>