<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Posts</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>

    <style type="text/css">
        body {
            background-color: #E9F2F7;
        }
    </style>

</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/user">Home</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout">Logout</a></li>
        </ul>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search posts" name="text">
                </div>
                <button type="submit" formaction="/search" formmethod="get" class="btn btn-default">Search</button>
            </form>
        </div>
    </div>
</nav>

<div class="container" style="margin-left: 8%; margin-right: 80%;">
    <div class="col-md-12">
        <#list posts as post>
        <h1>${post.headline}</h1>
        <p>${post.post}</p>
        <div>
            <span class="badge">Posted 2012-08-02 20:47:04</span><div class="pull-right"><span class="label label-primary">${post.userName}</span>
        </div>
        <hr>
        </#list>
    </div>
</div>
</body>
</html>