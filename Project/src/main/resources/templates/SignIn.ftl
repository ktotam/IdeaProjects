<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div class="form-style-6">
    <form action="/signIn" method="post">
        <input type="text" name="login" placeholder="Login" />
        <input type="password" name="password" placeholder="Password" />
        <input type="submit" value="Sign In"/>
    </form>
    <form action="/signUp" method="get">
        <input type="submit" value="Sign Up">
    </form>
</div>
</body>
</html>