<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>${user.name}</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/chat.css"/>

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
        <div class="col-md-3 panel panel-default ">
                <div class=" panel-heading">

                        <input type="text" class="form-control" placeholder="Search user" maxlength="255" autocomplete="off"/>
                        <span class="input-group-btn "><button class="btn" type="submit"><span style="font-size: 1.5em" class="glyphicon glyphicon-search glyphicon-"></span></button></span>
                </div>
                <div class="panel member_list">
                    <ul class="list-unstyled">


                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg" alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    09:45AM</strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong> <span class="badge pull-right">3</span>
                                </div>
                            </div>
                        </li>

                    </ul>
                </div>
        </div>


        <div class="col-md-8 message_section panel panel-default pull-right">

                <div class="chat_area">
                    <ul class="list-unstyled">
                        <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg" alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body1 clearfix">
                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                                <div class="chat_time pull-right">09:40PM</div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg" alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body1 clearfix">
                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                                <div class="chat_time pull-right">09:40PM</div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg" alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body1 clearfix">
                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                                <div class="chat_time pull-right">09:40PM</div>
                            </div>
                        </li>
                        <li class="left clearfix admin_chat">
                     <span class="chat-img1 pull-right">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg" alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body1 clearfix">
                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                                <div class="chat_time pull-left">09:40PM</div>
                            </div>
                        </li>
                        <li class="left clearfix admin_chat">
                     <span class="chat-img1 pull-right">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg" alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body1 clearfix">
                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                                <div class="chat_time pull-left">09:40PM</div>
                            </div>
                        </li>




                    </ul>
                </div><!--chat_area-->
                <div class="message_write">
                    <textarea class="form-control" placeholder="Message..."></textarea>
                    <div class="chat_bottom">
                        <a href="#" class="pull-right btn btn-success">
                            Send
                        </a>
                    </div>
                    <br><br><br>
                </div>


        </div>
    </div>
</div>
</body>
</html>
