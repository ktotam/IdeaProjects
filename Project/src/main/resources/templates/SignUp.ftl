<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <style type="text/css">
        body {
            background-image: url(http://www.joburgchiropractor.co.za/images/background.jpg);
        }
    </style>
</head>
<body>

<div class="container" style="margin-top: 5%;">
    <div class="row">
        <div class="col-sm-4"> </div>
        <div class="col-md-4">

            <h1 class="text-center text-success"> Register page</h1>
            <br/>
                <div class="tab-content">
                    <div id="home" class="tab-pane fade in active">

                        <form action="/user" method="post">

                            <div class="form-group">
                                <label for="UserName">Login</label>
                                <input type="text" class="form-control" name="login">
                            </div>

                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" name="password">
                            </div>

                            <div class="form-group">
                                <label for="Name">Name</label>
                                <input type="text" class="form-control" name="name">
                            </div>

                            <div class="form-group">
                                <label for="Age">Age</label>
                                <input type="text" class="form-control" name="age">
                            </div>

                            <button type="submit" class="btn btn-default btn-lg">Sign Up</button>


                        </form>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
