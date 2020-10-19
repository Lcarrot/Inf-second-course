<%--
  Created by IntelliJ IDEA.
  User: olga1
  Date: 14.10.2020
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title"> Registration </jsp:attribute>
    <jsp:attribute name="header">
        <t:header></t:header>
    </jsp:attribute>
    <jsp:attribute name="main">
        <t:registrationForm>
        <jsp:attribute name="title">
                    <h3>Registration</h3>
                </jsp:attribute>
    </t:registrationForm>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <t:footer></t:footer>
    </jsp:attribute>

</t:layout>
