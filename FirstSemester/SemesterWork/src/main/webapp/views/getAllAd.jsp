<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:attribute name="title"> Job List </jsp:attribute>
    <jsp:attribute name="header"> <t:header/></jsp:attribute>
    <jsp:attribute name="main">
        <jsp:useBean id="ads" scope="request" type="java.util.List"/>
        <div class="row"></div>
        <c:forEach items="${ads}" var="ads">
            <div class="col-6 col-sm-3">
                <p>${ads.header}</p>
                <p>${ads.description}</p>
                <p>${ads.contact}</p>
                <p>${ads.price}</p>
            </div>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="footer"><t:footer /></jsp:attribute>
</t:layout>
