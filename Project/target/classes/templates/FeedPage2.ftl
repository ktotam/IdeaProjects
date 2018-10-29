<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Feed</title>
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
            <a class="navbar-brand" href="/user">Home</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/msg/">Messages</a></li>
                <li><a href="/feed">Feed</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout">Logout</a></li>
            </ul>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <form class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search posts" name="text" autocomplete="off">
                    </div>
                    <button type="submit" formaction="/search" formmethod="get" class="btn btn-default">Search</button>
                </form>
            </div>
        </div>
    </div>
</nav>

<div class="container" style="width: 80%; margin-left: 20%;">
    <div class="col-md-9 col-sm-9">
        <div class="panel panel-default">
            <div class="panel-heading"><h3>Feed</h3></div>
            <div class="panel-body">
            <#list posts as post>
            <div class="media">
            <div class="media-body">
                <h4>${post.post}</h4>
            </div>
                <div class="media-body">
                    <a class="thumbnail pull-right" href="/users/${post.userId}">
                        <img width="56" height="60" src=${post.getAvatarUrl()} >
                    </a>
                    <br><br><br><br><br>
                    <form>
                        <span class="label pull-right"><button class="btn-primary" type="submit" formaction="/users/${post.userId}" formmethod="get">From ${post.userName}</button></span>
                    </form>
                </div>
                <hr>
            </#list>
            </div>
            </div>
        </div>
    </div>
</body>
</html>