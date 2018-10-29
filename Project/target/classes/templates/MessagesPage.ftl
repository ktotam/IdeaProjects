<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Messages</title>
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
                <li><a href="/msg/"><b><big>Messages</big></b></a></li>
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
        <div class="col-md-3">
        <div class="panel panel-default">
            <div class="panel-heading"><h3>Send Message</h3></div>
            <form class="form-horizontal" role="form" method="post" action="/msg">
                <div class="panel-body" style="padding:14px;">
                    <input type="text" class="form-control" name="toId" placeholder="User ID">
                    <br>
                    <textarea class="form-control" placeholder="Your message" maxlength="255" name="text"></textarea>
                    <br>
                    <button class="btn btn-default pull-right" type="submit">Send</button>
                </div>
            </form>
        </div>
        </div>
        <div class="col-md-4 col-sm-12">

            <div class="panel panel-default">
                <div class="panel-heading"><h3>Inbox</h3></div>
                <div class="panel-body">
                    <#list inboxMessages as message>
                    <h4>${message.text}</h4>
                    <form>
                        <span class="label pull-right"><button class="btn-primary" type="submit" formaction="/users/${message.fromId}" formmethod="get">FROM ${message.fromUserName} id-${message.fromId}</button></span>
                    </form>
                    <br>
                    <hr>
                    </#list>
                </div>
            </div>
        </div>

        <div class="col-md-5 col-sm-12">

            <div class="panel panel-default">
                <div class="panel-heading"><h3>Sent</h3></div>
                <div class="panel-body">
                    <#list sentMessages as message>
                        <h4>${message.text}</h4>
                        <form>
                            <span class="label pull-right"><button class="btn-primary" type="submit" formaction="/users/${message.toId}" formmethod="get">TO ${message.toUserName}  ID:${message.toId}</button></span>
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