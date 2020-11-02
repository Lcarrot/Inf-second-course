<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">Your profile</jsp:attribute>
    <jsp:attribute name="header"> <t:header/></jsp:attribute>
    <jsp:attribute name="main">
        <div class="container">
            <p> Login : ${requestScope.get("login")}</p>
            <p> Email : ${requestScope.get("email")}</p>
            <p> Country : ${requestScope.get("country")}</p>
            <p> Gender : ${requestScope.get("gender")}</p>
            <a href="${pageContext.request.contextPath}/service/profile/change"> Change your data</a>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer"> <t:footer/></jsp:attribute>
</t:layout>
