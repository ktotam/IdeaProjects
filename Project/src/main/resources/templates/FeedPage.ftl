<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Feed</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>

    <script type="text/javascript" src="js/js.js"></script>
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
            <ul class="nav navbar-nav">
                <li><a href="/user"><big>Home</big></a></li>
                <li><a href="/chat"><big>Chat</big></a></li>
                <li><a href="/feed"><b><big>Feed</big></b></a></li>
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

<div class="container" id="main">
    <div class="row">
    <div class="col-md-3 col-sm-6">
        <div class="panel panel-default">
            <div class="panel-heading"><a href="/edit" class="pull-right">Edit</a> <h4><big>${user.name}</big></h4></div>
            <div class="panel-body">
                <div class="media">
                    <div class="thumbnail">
                        <img src=${user.avatarUrl}>
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

        <div class="panel panel-default">
            <div class="panel-heading"><a href="/search?text=" class="pull-right">All Posts</a><h4>Popular posts</h4></div>
            <div class="panel-body">
                <div class="list-group">
                        <#list popularPosts as popularPost>
                            <a href="/user${popularPost.userId}" class="list-group-item">${popularPost.post}</a>
                        </#list>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading"><a href="/allusers" class="pull-right">All Users</a><h4>Popular users</h4></div>
            <div class="panel-body">
                <div class="list-group">
                        <#list popularUsers as popularUser>
                            <a href="/user${popularUser.id}" class="list-group-item">${popularUser.name}</a>
                        </#list>
                </div>
            </div>
        </div>

    </div>
    <div class="col-md-8 col-sm-6">
    <#list posts as post>

        <div class="panel panel-default" >
                <div class="panel-heading" style="min-height: 70px">
                    <a class="pull-left" href="/user${post.userId}">
                        <img class="img-circle" width="45" src=${post.getAvatarUrl()}/>
                    </a>
                    <div class="pull-left">
                        <a href="/user${post.getUserId()}" style="text-decoration: none; cursor: pointer"><b>&nbsp<big>${post.getUserName()}</big></b></a>
                        <br>
                        <span class="text-muted">&nbsp<small>${post.date.toLocalDate()} ${post.date.toLocalTime()}</small></span>
                    </div>
                </div>
            <div class="panel-body">
                <p class="h3">${post.post}</p>
                <hr>
                <div style="max-width: 150px;">
                        <#if likedPosts?seq_contains(post.id)>
                            <a style="text-decoration: none; cursor: pointer; font-size: larger" onclick="dislike('like_${post.id}', ${post.id}, ${post.likes})" title="Like">
                                <h3><span id="like_${post.id}" class="glyphicon glyphicon-heart">${post.likes}</span></a>
                        <#else>
                            <a style="text-decoration: none; cursor: pointer; font-size: larger" onclick="like('like_${post.id}', ${post.id}, ${post.likes})" title="Like">
                                <h3><span id="like_${post.id}" class="glyphicon glyphicon-heart-empty">${post.likes}</span></a>
                        </#if>
                    <a id="like_${post.id}" style="text-decoration: none; cursor: pointer;" onclick="repost(${post.id})" class="glyphicon glyphicon-share-alt" title="Repost"></a>
                </div>
            </div>

        </div>
    </#list>
    </div>
    </div>
</div>
</body>
</html>