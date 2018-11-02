<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chat</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/chat.css"/>

    <script type="text/javascript" src="js/js.js"></script>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/js/app.js"></script>
    <script src="/js/js.js"></script>
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
                <li><a href="/chat"><b><big>Chat</big></b></a></li>
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
        <div class="col-md-3" >
            <div class="panel panel-default ">
                <div class=" panel-heading">

                    <input id="chatSearch" type="text" class="form-control" placeholder="Search user" maxlength="255" autocomplete="off"/>
                </div>
                <div class="panel member_list" style="height: 654px">
                    <ul class="list-unstyled">
                    <#list chatListUsers as chatUser>
                    <li id="${chatUser.getName()}" class="left clearfix" style="cursor: pointer; display: ''" onclick="connect(${chatUser.getId()}, ${user.getId()}, '${chatUser.getName()}', '${chatUser.getAvatarUrl()}')">
                        <span class="chat-img pull-left">
                            <img src="${chatUser.getAvatarUrl()}" alt="User Avatar" class="img-circle">
                        </span>
                        <div>
                            <h4><strong>${chatUser.getName()}</strong></h4>
                        </div>
                    </li>
                    </#list>
                    </ul>

                </div>
            </div>
        </div>


        <div class="col-md-9">
            <div class="message_section panel panel-default" >
                <div class="panel panel-body" >
                    <div class="chat_area" >
                        <table id="conversation" class="table table-striped">
                            <thead>
                            <tr>
                                <th><b id="chatName" style = "font-size: 30">Chat</b><img id="avatarUrl" src='' class="pull-right img-circle" style="height: 50px; display: none"></th>
                            </tr>
                            </thead>
                            <tbody id="greetings">

                            </tbody>
                        </table>
                    </div><!--chat_area-->
                    <br>
                    <div class="message_write">
                        <textarea class="form-control" placeholder="Message..." id="name"></textarea>
                        <div class="chat_bottom">

                            <a id="send" class="btn btn-success pull-right" type="submit" onclick="sendName()">Send</a>

                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
