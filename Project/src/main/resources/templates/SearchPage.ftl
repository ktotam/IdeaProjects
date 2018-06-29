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
            <a class="navbar-brand" href="/user">Home</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/msg/">Messages</a></li>
            </ul>
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
    </div>
</nav>

<div class="container" style="margin-left: 8%; margin-right: 80%;">
        <div class="col-md-9 col-sm-9">

            <div class="panel panel-default">
                <div class="panel-heading"><h3>Posts</h3></div>
                <div class="panel-body">
                    <#list posts as post>
                        <h4>${post.post}</h4>
                        <form>
                            <span class="label pull-right"><button class="btn-primary" type="submit" formaction="/users/${post.userId}" formmethod="get">From ${post.userName}</button></span>
                        </form>
                        <br>
                        <hr>
                    </#list>
                </div>
            </div>

        </div>
</div>
</body>
</html>