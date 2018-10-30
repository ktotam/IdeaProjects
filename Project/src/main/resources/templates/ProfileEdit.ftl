<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">

    <script src="/js/fileUpload.js"></script>
    <title>Profile</title>
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

<div class="container" id="main">
    <div class="row">
        <div class="col-md-3 col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading"><h4>New avatar</h4></div>
                <div class="panel-body">
                    <div class="media">
                        <div class="media-body">
                            <form method="post" action="/upload" enctype="multipart/form-data">
                                <div>
                                    <label for="file"></label>
                                    <input type="file" id="file" name="file">
                                </div>
                                <br>
                                <div>
                                    <input type="submit" class="btn btn-default" value="Submit">
                                </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" id="main">
    <div class="row">
        <div class="col-md-3 col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading"><h4>Edit Profile</h4></div>
                <div class="panel-body">
                    <div class="media">
                        <div class="media-body">
                                <div class="form-group">
                                    <label for="Name">Name</label>
                                    <input type="text" class="form-control" name="name" maxlength="255" autocomplete="off">
                                </div>

                                <div class="form-group">
                                    <label for="Age">Age</label>
                                    <input type="text" class="form-control" name="age" maxlength="3" autocomplete="off">
                                </div>
                                <div>
                                    <input type="submit" formaction="/edit" formmethod="post" class="btn btn-default" value="Submit">
                                </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
