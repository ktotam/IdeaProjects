<#ftl encoding='UTF-8'>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>All Users</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>

    <script type="text/javascript" src="js/js.js"></script>
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
            <ul class="nav navbar-nav">
                <li><a href="/user"><big>Home</big></a></li>
                <li><a href="/chat"><big>Chat</big></a></li>
                <li><a href="/feed"><big>Feed</big></a></li>
            </ul>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><big>Logout</big></a></li>
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

<div class="container" style="width: 85%; margin-left: 15%">
    <div class="row">
        <div class="col-md-6 col-sm-6">
        <#list users as user>
            <div class="panel panel-default">

                <div class="panel-heading" ><h3><span><a href="/user${user.id}"><big>${user.name}</big></a></span></h3></div>

                <div class="panel-body">

                    <div class="well well-sm">
                        <div class="media">
                        <div class="media-body">
                            <a class="thumbnail pull-left" href="/user${user.id}">
                                <img width="163" height="163" src=${user.avatarUrl}>
                            </a>
                        </div>
                        <div class="media-body">
                            <p>
                            <h4><span class="label label-info">Age ${user.age}</span>
                                <span class="label label-info"> ID ${user.id}</span></h4>
                            <span class="label label-primary">${user.postsCount} Posts</span>
                            <span class="label label-primary">${user.followersCount} Followers</span>
                            </p>
                        </div>
                        </div>
                    </div>

                </div>

            </div>
        </#list>
        </div>
    </div>
</div>

</body>
</html>