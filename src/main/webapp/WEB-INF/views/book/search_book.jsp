<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <jsp:include page="../include/title.jsp" />

    <link href="<c:url value='/resources/css/admin/search_book.css' />" rel="stylesheet" type="text/css">

</head>
<body>

<jsp:include page="../include/header.jsp" />

<jsp:include page="../admin/include/nav.jsp" />

<section>

    <div id="section_wrap">

        <div class="word">

            <h3>BOOK SEARCH RESULTS</h3>

        </div>

        <div class="book_list">

            <table>
                <thead>
                <tr>
                    <th>도서명</th>
                    <th>저자</th>
                    <th>발행처</th>
                    <th>발행연도</th>
                    <th>ISBN</th>
                    <th>청구기호</th>
                    <th>대출가능</th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="item" items="${bookVos}">
                    <tr>
                        <td>
                            <c:url value='/book/admin/bookDetail' var='detail_url'>
                                <c:param name='no' value='${item.no}'/>
                            </c:url>
                            <a href="${detail_url}">${item.name}</a>
                        </td>
                        <td>${item.author}</td>
                        <td>${item.publisher}</td>
                        <td>${item.publishYear}</td>
                        <td>${item.isbn}</td>
                        <td>${item.callNumber}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.rentalAble eq '0'}"> <c:out value="대출중" /> </c:when>
                                <c:when test="${item.rentalAble eq '1'}"> <c:out value="대출가능" /> </c:when>
                                <c:otherwise> <c:out value="X" /> </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>

            </table>

        </div>

    </div>

</section>

<jsp:include page="../include/footer.jsp" />

<script>
    function searchBookForm() {
        console.log('searchBookForm() CALLED!!');

        let form = document.search_book_form;

        if (form.name.value == '') {
            alert('Enter the name of the book you are looking for.');
            form.name.focus();

        } else {
            form.submit();

        }

    }
</script>
</body>
</html>