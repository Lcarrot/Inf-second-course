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
        <t:registrationForm postAction="signUp">
        <jsp:attribute name="title">
                    Sign Up
                </jsp:attribute>
            <jsp:attribute name="feilds">
                <div class="container">
                    <div class="form-group row">
                        <label for="name" class="col-sm-2 col-form-label">Login:</label>
                        <div class="col-sm-10">
                            <input name="login" id="name" type="text" class="form-control" placeholder="write your name here" required="^[a-zA-Z0-9]{3,}$">
                        </div>
                    </div>
                </div>
<div class="container">
    <div class="form-group row">
        <label for="email" class="col-sm-2 col-form-label">e-mail:</label>
        <div class="col-sm-10">
            <input name="email" id="email" type="email" class="form-control"
                   placeholder="write your e-mail here"
                   required="^[_A-Za-z0-9-+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$">
        </div>
    </div>
</div>
<div class="container">
    <div class="form-group row">
        <label for="password" class="col-sm-2 col-form-label">password:</label>
        <div class="col-sm-10">
            <input name="password" id="password" type="password" class="form-control" placeholder="write your password here"
            required="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$">
        </div>
    </div>
</div>
<div class="container">
    <div class="form-group row">
        <label for="confirmedPassword" class="col-sm-2 col-form-label">confirm password:</label>
        <div class="col-sm-10">
            <input name="confirm_password" type="password" id="confirmedPassword" class="form-control"
                   placeholder="confirm the password here">
        </div>
    </div>
</div>
<div class="container">
    <div class="form-group col-md-4">
        <label for="inputCountry">Country</label>
        <select id="inputCountry" class="form-control" name="country">
            <option selected> Choose your country...</option>
            <option value="Canada" name="country"> Canada</option>
            <option value="Russia" name="country"> Russia</option>
            <option value="Ukraine" name="country"> Ukraine</option>
        </select>
    </div>
</div>
<div class="container">
    <div class="column-overlay">
        <label for="password" class="col-sm-2 col-form-label">Gender:</label>
        <div class="form-check">
            <input type="radio" class="form-check-input" name="gender" value="female" id="female">
            <label class="form-check-label" for="female">
                Female
            </label>
        </div>
        <div class="form-check">
            <input type="radio" class="form-check-input" name="gender" value="male" id="male">
            <label class="form-check-label" for="male">
                Male
            </label>
        </div>
    </div>
    <div class="form-check">
        <input name="agree" type="checkbox" class="form-check-input" id="materialChecked2">
        <label class="form-check-label" for="materialChecked2">I agree</label>
    </div>
</div>
                <div class="container">
                    <div class="form-group row">
                <c:if test="${sessionScope.get('authorized') == 'false'}">
                    <pre>${requestScope.get("description")}</pre>
                </c:if>
                    </div>
                </div>
            </jsp:attribute>
    </t:registrationForm>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <t:footer></t:footer>
    </jsp:attribute>

</t:layout>
