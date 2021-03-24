<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Users</title>
</head>
<body>
<#list users as user>
    <tr>
        <td>${user.login}</td>
        <td>${user.country}</td>
        <td>${user.gender}</td>
        <td>${user.class}</td>
    </tr>
    <br>
</#list>
<a href="?page=${pre_page}">Предыдущая страница</a>
<p>${page}</p>
<a href="?page=${next_page}"> Следующая страница</a>
</body>
</html>