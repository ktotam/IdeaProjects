<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Posts</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>

    
    <style type="text/css">
        body {
            background-color: #E9F2F7;
        }
    </style>


</head>
<body>

                            <img src="file:///C:/avatars/111_avatar.jpg">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">${user.name}</h4>
                            <p><span class="label label-info">10 photos</span> <span class="label label-primary">${user.followers} followers</span></p>
                            <p>
                                <a href="#" class="btn btn-xs btn-default"><span class="glyphicon glyphicon-comment"></span> Message</a>
                                <a href="#" class="btn btn-xs btn-default"><span class="glyphicon glyphicon-heart"></span> Favorite</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading"><h4>POPULAR</h4></div>
                <div class="panel-body">
                    <div class="list-group">
                        <a href="http://bootply.com/tagged/modal" class="list-group-item">post1</a>
                        <a href="http://bootply.com/tagged/datetime" class="list-group-item">post2</a>
                        <a href="http://bootply.com/tagged/datatable" class="list-group-item">post3</a>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-8 col-sm-6">

            <div class="well">
                <form class="form" action="newPost" method="post">
                    <div class="input-group text-center">
                        <input class="form-control input-lg" placeholder="New post?" name="text">
                        <span class="input-group-btn"><button class="btn btn-lg btn-primary" type="submit">SUBMIT</button></span>
                    </div>
                </form>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading"><h3>Your posts</h3></div>
                <div class="panel-body">
                    <#list posts as post>
                        <h4>${post.post}</h4>
                        <span class="label label-primary pull-right">Posted 2012-08-02 20:47:04</span>
                        <br>
                        <hr>
                    </#list>
                </div>
            </div>

        </div>
    </div>
</div>
<form method="post" action="/upload"
      modelAttribute="uploadForm" enctype="multipart/form-data">
    <div>
        <label for="file">Choose file to upload</label>
        <input type="file" id="file" name="file">
    </div>
    <div>
        <button>Submit</button>
    </div>

</body>
</html>