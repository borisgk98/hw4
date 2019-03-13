<%--
  Created by IntelliJ IDEA.
  User: boris
  Date: 13.03.19
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.4/css/bootstrap.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/asserts/js/countries.js" />" defer></script>
</head>
<body>
    <div class="container m-5">
        <a href='${s:mvcUrl("LC#change").arg(0, "ru_RU").build()}'>Рус</a> <a href='${s:mvcUrl("LC#change").arg(0, "en_US").build()}'>Eng</a>
        <h3><s:message code="test"/></h3>
        <h3><s:message code="param.message" arguments="3.14 2.71" argumentSeparator=" "/></h3>
        <h3>${message0}</h3>
        <h3>${message1}</h3>
    </div>
    <div class="container m-5">
        <c:if test="${not empty message}">
            <h1>${message}</h1>
        </c:if>
        <form:form method="POST" modelAttribute="user">
            <form:label path="name">Name</form:label>
            <form:input path="name"/>
            <form:errors path="name" /><br>

            <form:label path="email">Email</form:label>
            <form:input path="email"/>
            <form:errors path="email" /><br>

            <form:label path="country">Country</form:label>
            <from:select id="reg-select-country" path="country" />
            <form:errors path="country" /><br>

            <form:label path="gender">Gender</form:label>
            <form:checkbox path="gender"/>
            <form:errors path="gender" /><br>

            <form:label path="subscript">Subscript</form:label>
            <form:checkbox path="subscript"/>
            <form:errors path="subscript" /><br>


            <input type="submit" value="Submit" />
        </form:form>
    </div>
</body>
</html>
