<%--
  Created by IntelliJ IDEA.
  User: olga1
  Date: 03.10.2020
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="js/index.js"></script>
    <title></title>
  </head>
  <body class="light-theme">
  <header>
    <nav class="navbar navbar-expand-md navbar-expand-sm navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" href="#">Flow Job</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-center" id="collapsibleNavbar">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="#">Лучшие предложения</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Мой профиль</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"> Быстрые заказы </a>
          </li>
        </ul>
        <div class="btn-group mr-2" role="group">
          <button type="button" id="dark" onclick="theme(id)" class="dark-theme"> Сменить тему </button>
          <button type="button" id = "green" onclick="theme(id)" class="green_theme"> Сменить тему </button>
          <button type="button" id="light" onclick="theme(id)" class="light-theme"> Сменить тему </button>
          <button type="button" id="yellow" onclick="theme(id)" class="yellow_theme "> Сменить тему </button>
          <button type="button" onclick="switch_theme()" class="btn btn-secondary"> Сменить тему </button>
          <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Найти</button>
          </form>
        </div>
      </div>
    </nav>
  </header>
  <footer>
    Сделано одним дегенератом))
  </footer>
  </body>
</html>
