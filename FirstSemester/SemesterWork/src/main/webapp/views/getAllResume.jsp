<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:attribute name="title"> Job List </jsp:attribute>
    <jsp:attribute name="header"> <t:header/></jsp:attribute>
    <jsp:attribute name="main">
        <jsp:useBean id="resumes" type="java.util.List"/>
        <div class="row"></div>
        <c:forEach items="${resumes}" var="resume">
            <div class="col-6 col-sm-3">
                <p>${resume.header}</p>
                <p>${resume.description}</p>
                <p>${resume.contact}</p>
            </div>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="footer"><t:footer /></jsp:attribute>
</t:layout>

