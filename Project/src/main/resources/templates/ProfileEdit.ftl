<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
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
                <div class="panel-heading"><h4>New avatar</h4></div>
                <div class="panel-body">
                    <div class="media">
                        <div class="media-body">
                            <form method="post" action="/upload"
                                  modelAttribute="uploadForm" enctype="multipart/form-data">
                                <div>
                                    <label for="file"></label>
                                    <input type="file" id="file" name="file">
                                </div>
                                <br>
                                <div>
                                    <button  class="btn btn-default">Submit</button>
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
                            <form>
                                <div class="form-group">
                                    <label for="Name">Name</label>
                                    <input type="text" class="form-control" name="name" maxlength="255">
                                </div>

                                <div class="form-group">
                                    <label for="Age">Age</label>
                                    <input type="text" class="form-control" name="age" maxlength="3">
                                </div>
                                <div>
                                    <button type="submit" formaction="/edit" formmethod="post" class="btn btn-default">Submit</button>
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
