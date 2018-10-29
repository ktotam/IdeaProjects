<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>${user.name}</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>

    <script type="text/javascript" src="js/js.js"></script>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/js/app.js"></script>

    <style type="text/css">
        body {
            background-color: #E9F2F7;
        }
    </style>

</head>
<body>
123
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <ul class="nav navbar-nav">
                <li><a href="/user"><b><big>Home</big></b></a></li>
                <li><a href="/msg/"><big>Messages</big></a></li>
                <li><a href="/feed"><big>Feed</big></a></li>
                <li><a href="/chat"><big>Chat</big></a></li>
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

                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading"><a href="/allusers" class="pull-right">All Users</a><h4>Popular users</h4></div>
                <div class="panel-body">
                    <div class="list-group">

                    </div>
                </div>
            </div>

        </div>
        <div id="main-content" class="container">
            <div class="row">
                <div class="col-md-6">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="connect">WebSocket connection:</label>
                            <button id="connect" class="btn btn-default" type="submit" onsubmit="connect()">Connect</button>
                        </div>
                    </form>
                </div>
                <div class="col-md-6">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="name">Message</label>
                            <input type="text" id="name" class="form-control" placeholder="Message">
                        </div>
                        <button id="send" class="btn btn-default" type="submit" onsubmit="sendMessage()">Send</button>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table id="conversation" class="table table-striped">
                        <thead>
                        <tr>
                            <th>Chat</th>
                        </tr>
                        </thead>
                        <tbody id="greetings">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
