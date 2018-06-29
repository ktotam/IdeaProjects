<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Posts</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>

    <script type="text/javascript">
        function show(id)
        {
            var div = document.getElementById(id)
            if(div.style.display == '')
                div.style.display = 'none'
            else
                div.style.display = ''
        }
    </script>

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
                <div class="panel-heading"><h4>${user.name}</h4></div>
                <div class="panel-body">
                    <div class="media">
                        <div class="thumbnail">
                            <img src=${avatar.url}>
                        </div>
                        <div class="media-body">
                            <p>
                                <span class="label label-info">${user.postsCount} Posts</span>
                                <span class="label label-primary">${user.likes} Likes</span>
                            </p>
                            <p><a id="sendMessage" href="#" onclick="show('methodCaesar')" class="btn btn-xs btn-default"><span class="glyphicon glyphicon-comment"></span> Send Message</a>                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div id="methodCaesar" style="display:none">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Send Message</h3></div>
                <form class="form-horizontal" role="form" method="post" action="/msgto/${user.id}">
                    <div class="panel-body" style="padding:14px;">
                        <textarea class="form-control" placeholder="Your message" maxlength="255" name="text"></textarea>
                        <br>
                        <button class="btn btn-default pull-right" type="submit">Send</button>
                    </div>
                </form>
            </div>
            </div>
        </div>
        <div class="col-md-8 col-sm-6">

            <div class="panel panel-default">
                <div class="panel-heading"><h3>Posts</h3></div>
                <div class="panel-body">
                    <#list posts as post>
                        <h4>${post.post}</h4>
                        <form>
                            <span class="label pull-right"><button class="btn-primary" type="submit" formaction="/like/${post.id}&${user.id}" formmethod="post">${post.likes} Likes</button></span>
                        </form>
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