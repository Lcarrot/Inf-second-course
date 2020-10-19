<%@tag description="form for registration new user" pageEncoding="UTF-8" %>
<%@attribute name="title" fragment="true" %>

<form action="registration" method="post">
    <div style="text-align: center">
        <jsp:invoke fragment="title"/>
    </div>
    <div class="container">
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Login:</label>
            <div class="col-sm-10">
                <input name="name" id="name" class="form-control" placeholder="write your name here">
            </div>
        </div>
    </div>
    <div class="container">
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">e-mail:</label>
            <div class="col-sm-10">
                <input name="email" id="email" class="form-control" placeholder="write your e-mail here">
            </div>
        </div>
    </div>
    <div class="container">
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">password:</label>
            <div class="col-sm-10">
                <input name="password" id="password" class="form-control" placeholder="write your password here">
            </div>
        </div>
    </div>
    <div class="container">
        <div class="form-group row">
            <label for="confirmedPassword" class="col-sm-2 col-form-label">confirm password:</label>
            <div class="col-sm-10">
                <input name="confirmed_password" id="confirmedPassword" class="form-control"
                       placeholder="confirm the password here">
            </div>
        </div>
    </div>
    <div class="container">
        <div class="form-group col-md-4">
            <label for="inputCountry">State</label>
            <select id="inputCountry" class="form-control">
                <option selected> Choose your country...</option>
                <option value="Canada"> Canada</option>
                <option value="Russia"> Russia</option>
                <option value="Ukraine"> Ukraine</option>
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
            <input type="checkbox" class="form-check-input" id="materialChecked2">
            <label class="form-check-label" for="materialChecked2">I agree</label>
        </div>
    </div>
    <div class="container">
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary" value="submit"> Sign up</button>
            </div>
        </div>
    </div>
</form>