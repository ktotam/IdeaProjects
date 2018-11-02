<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://assets.transloadit.com/js/jquery.transloadit2-v3-latest.js"></script>
    <script type="text/javascript">
        // We call .transloadit() after the DOM is initialized:
        $(function() {
            $('#upload-form').transloadit({
                wait: true,
                fields: true,
                autoSubmit: false,
                triggerUploadOnFileSelection: true,
                params: {
                    auth: { key: 'ee2981d0deb311e8957707ba9032499c' },
                    steps: {
                        resize_to_150: {
                            robot: '/image/resize',
                            use: ':original',
                            width: 150,
                            height: 150
                        }
                    }
                },
                onResult: function(step, result) {
                    $('.result').attr('src', result.ssl_url)
                    var req = new XMLHttpRequest();
                    req.open('POST', '/awsupload', true);
                    req.setRequestHeader("url", result.ssl_url);
                    req.send();
                }
            });
        });
    </script>
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
                <div class="panel-heading"><h4>New avatar (local disk)</h4></div>
                <div class="panel-body">
                    <div class="media">
                        <div class="media-body">
                            <form enctype="multipart/form-data">
                                <div>
                                    <label for="file"></label>
                                    <input type="file" id="file" name="file" accept="image/*">
                                </div>
                                <br>
                                <div>
                                    <input type="submit" formmethod="post" formaction="/upload" class="btn btn-default" value="Submit">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="col-md-3 col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading"><h4>New avatar (aws)</h4></div>
                <div class="panel-body">
                    <div class="center">
                        <form id="upload-form">

                            <input type="file" name="upload" accept="image/*">

                        </form>
                        <hr>
                        <img class="result">
                    </div>
                </div>
            </div>
        </div>
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
