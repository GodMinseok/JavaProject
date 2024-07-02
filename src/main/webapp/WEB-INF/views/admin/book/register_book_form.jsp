<%--
  Created by IntelliJ IDEA.
  User: jjm42
  Date: 2024-06-07
  Time: 오후 2:15
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

  <link href="<c:url value='/resources/css/admin/register_book_form.css' />" rel="stylesheet" type="text/css">

</head>
<body>

<jsp:include page="../../include/header.jsp" />

<jsp:include page="../include/nav.jsp" />

<section>

  <div id="section_wrap">

    <div class="word">

      <h3>REGISTER BOOK FORM</h3>

    </div>

    <div class="register_book_form">

      <form action="<c:url value='/book/admin/registerBookConfirm' />" name="register_book_form" method="post" enctype="multipart/form-data">

        <input type="text" name="name" placeholder="INPUT BOOK NAME."> <br>
        <input type="text" name="author" placeholder="INPUT BOOK AUTHOR."> <br>
        <input type="text" name="publisher" placeholder="INPUT BOOK PUBLISHER."> <br>
        <input type="text" name="publishYear" placeholder="INPUT BOOK PUBLISH YEAR."> <br>
        <input type="text" name="isbn" placeholder="INPUT BOOK ISBN."> <br>
        <input type="text" name="callNumber" placeholder="INPUT BOOK CALL NUMBER."> <br>
        <select name="rentalAble">
          <option value="">SELECT BOOK RENTAL ABLE.</option>
          <option value="0">UNABLE.</option>
          <option value="1">ABLE.</option>
        </select><br>
        <input type="file" name="file"><br>
        <input type="button" value="register book" onclick="registerBookForm();">
        <input type="reset"	value="reset">

      </form>

    </div>

  </div>

</section>

<jsp:include page="../../include/footer.jsp" />

<script type="text/javascript">
  function registerBookForm() {
    console.log('registerBookForm() CALLED!!');

    let form = document.register_book_form;

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

    } else if (form.rentalAble.value == '') {
      alert('SELECT BOOK RANTAL ABLE.');
      form.rentalAble.focus();

    } else if (form.file.value == '') {
      alert('SELECT IMAGE FILE');
      form.thumbnail.focus();

    } else {
      form.submit();

    }

  }
</script>
</body>
</html>