<#ftl encoding='UTF-8'>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Posts</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>


    <style type="text/css">
        a:hover {
            text-decoration: none;
        }
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

<div class="container" id="main">
    <div class="row">
        <div class="col-md-6 col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading"> <h4>Users</h4></div>
                <div class="panel-body">
                <#list users as user>
                    <div class="well well-sm">
                    <div class="media">
                        <div class="media-body">


                            <a class="thumbnail pull-left" href="/users/${user.id}">
                                <img src=${user.avatarUrl}>
                            </a>
                        </div>
                        <div class="media-body">
                            <h3><span><a href="/users/${user.id}">${user.name}</a></span></h3>
                            <br>
                            <h4><span class="label label-info">Age ${user.age}</span></h4>
                            <br>
                            <span class="label label-primary"> ID ${user.id}</span>
                            <span class="label label-primary">${user.postsCount} Posts</span>
                            <span class="label label-primary">${user.likes} Likes</span>
                        </div>

                    </div>


                </div>
                                             <hr>
                                         </#list>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>