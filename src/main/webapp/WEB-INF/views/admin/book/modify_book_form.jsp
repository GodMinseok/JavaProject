<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <jsp:include page="../../include/title.jsp" />

    <link href="<c:url value='/resources/css/admin/modify_book_form.css' />" rel="stylesheet" type="text/css">

</head>
<body>

<jsp:include page="../../include/header.jsp" />

<jsp:include page="../include/nav.jsp" />

<section>

    <div id="section_wrap">

        <div class="word">

            <h3>MODIFY BOOK FORM</h3>

        </div>

        <div class="modify_book_form">

            <form action="<c:url value='/book/admin/modifyBookConfirm' />" name="modify_book_form" method="post" enctype="multipart/form-data">

                <input type="hidden" name="no" value="${bookVo.no}">

                <input type="text" name="name" value="${bookVo.name}" placeholder="INPUT BOOK NAME."> <br>
                <input type="text" name="author" value="${bookVo.author}" placeholder="INPUT BOOK AUTHOR."> <br>
                <input type="text" name="publisher" value="${bookVo.publisher}" placeholder="INPUT BOOK PUBLISHER."> <br>
                <input type="text" name="publish_year" value="${bookVo.publishYear}" placeholder="INPUT BOOK PUBLISH YEAR."> <br>
                <input type="text" name="isbn" value="${bookVo.isbn}" placeholder="INPUT BOOK ISBN."> <br>
                <input type="text" name="call_number" value="${bookVo.callNumber}" placeholder="INPUT BOOK CALL NUMBER."> <br>
                <select name="rantal_able">
                    <option value="">SELECT BOOK RANTAL ABLE.</option>
                    <option value="0" <c:if test="${bookVo.rantalAble eq '0'}"> selected </c:if>>UNABLE.</option>
                    <option value="1" <c:if test="${bookVo.rantalAble eq '1'}"> selected </c:if>>ABLE.</option>
                </select><br>
                <input type="file" name="file"><br>
                <input type="button" value="modify book" onclick="modifyBookForm();">
                <input type="reset" value="reset">

            </form>

        </div>

    </div>

</section>

<jsp:include page="../../include/footer.jsp" />

<script type="text/javascript">

    function modifyBookForm() {
        console.log('modifyBookForm() CALLED!!');

        let form = document.modify_book_form;

        if (form.name.value == '') {
            alert('INPUT BOOK NAME.');
            form.name.focus();

        } else if (form.author.value == '') {
            alert('INPUT BOOK AUTHOR.');
            form.author.focus();

        } else if (form.publisher.value == '') {
            alert('INPUT BOOK PUBLISHER.');
            form.publisher.focus();

        } else if (form.publishYear.value == '') {
            alert('INPUT BOOK PUBLISH YEAR.');
            form.publishYear.focus();

        } else if (form.isbn.value == '') {
            alert('INPUT BOOK ISBN.');
            form.isbn.focus();

        } else if (form.callNumber.value == '') {
            alert('INPUT BOOK CALL NUMBER.');
            form.callNumber.focus();

        } else if (form.rantalAble.value == '') {
            alert('SELECT BOOK RANTAL ABLE.');
            form.rantalAble.focus();

        } else {
            form.submit();

        }

    }

</script>

</body>
</html>