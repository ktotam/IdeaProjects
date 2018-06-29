<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>${user.name}</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
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

<div class="container" id="main">
    <div class="row">
        <div class="col-md-3 col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading"><a href="/edit" class="pull-right">Edit</a> <h4>${user.name}</h4></div>
                <div class="panel-body">
                    <div class="media">
                        <div class="thumbnail">
                            <img src=${avatar.url}>
                        </div>
                        <div class="media-body">
                            <p>
                            <h4><span class="label label-info">Age ${user.age}</span></h4>
                            <span class="label label-primary"> ID ${user.id}</span>
                            <span class="label label-primary">${user.postsCount} Posts</span>
                            <span class="label label-primary">${user.likes} Likes</span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading"><h4>Popular posts</h4></div>
                <div class="panel-body">
                    <div class="list-group">
                        <#list popularPosts as popularPost>
                            <a href="/users/${popularPost.userId}" class="list-group-item">${popularPost.post}</a>
                        </#list>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading"><a href="/allusers" class="pull-right">All Users</a><h4>Popular users</h4></div>
                <div class="panel-body">
                    <div class="list-group">
                        <#list popularUsers as popularUser>
                        <a href="/users/${popularUser.id}" class="list-group-item">${popularUser.name}</a>
                        </#list>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-8 col-sm-6">

            <div class="well">
                <form class="form" action="newPost" method="post">
                    <div class="input-group text-center">
                        <input class="form-control input-lg" placeholder="New post?" name="text" maxlength="255">
                        <span class="input-group-btn"><button class="btn btn-lg btn-primary" type="submit">SUBMIT</button></span>
                    </div>
                </form>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading"><h3>Posts</h3></div>
                <div class="panel-body">
                    <#list posts as post>
                        <h4>${post.post}</h4>
                        <span class="label label-primary pull-right">${post.likes} Likes</button></span>
                        <br>
                        <hr>
                    </#list>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>