function like(element, postId, postLikes) {
    var btn = document.getElementById(element);
    var pl = postLikes;

    if (btn.className === "glyphicon glyphicon-heart-empty") {
        pl++;
        btn.classList.remove("glyphicon-heart-empty");
        btn.classList.add("glyphicon-heart");
        btn.innerText = pl;
    } else {
        btn.classList.remove("glyphicon-heart");
        btn.classList.add("glyphicon-heart-empty");
        btn.innerText = pl;
    }
    req = new XMLHttpRequest();
    req.open('POST', '/like/' + postId, true);
    req.send();
}

function dislike(element, postId, postLikes) {
    var btn = document.getElementById(element);
    var pl = postLikes;

    if (btn.className === "glyphicon glyphicon-heart-empty") {
        btn.classList.remove("glyphicon-heart-empty");
        btn.classList.add("glyphicon-heart");
        btn.innerText = pl;
    } else {
        pl--;
        btn.classList.remove("glyphicon-heart");
        btn.classList.add("glyphicon-heart-empty");
        btn.innerText = pl;
    }
    req = new XMLHttpRequest();
    req.open('POST', '/like/' + postId, true);
    req.send();
}
function repost(postId) {
    req = new XMLHttpRequest();
    req.open('POST', '/repost/' + postId, true);
    req.send();
}
function show(userId)
{
    req = new XMLHttpRequest();
    req.open('POST', '/chat/' + userId, true);
    req.send();
    req.onload = () => {
        window.location.href = "https://app-sn.herokuapp.com/chat";
    }
}
function follow(toId, follow, unfollow) {
    var div = document.getElementById(follow);
    var div1 = document.getElementById(unfollow);
    div.style.display = 'none';
    div1.style.display = '';
    req = new XMLHttpRequest();
    req.open('GET', '/follow/' + toId, true);
    req.send();
}
function unfollow(toId, unfollow, follow) {
    var div1 = document.getElementById(follow);
    var div = document.getElementById(unfollow);
    div.style.display = 'none';
    div1.style.display = '';
    req = new XMLHttpRequest();
    req.open('GET', '/unfollow/' + toId, true);
    req.send();
}
$(function search() {
    var data = [];
    data = document.getElementsByClassName('left clearfix');
    var chatSearch = document.getElementById("chatSearch");
    var isEmpty = function () {
        var re = /^\s*$/;
        return function (str) {
            return re.test(str)
        }
    }();

    chatSearch.oninput = function () {
        if (isEmpty(this.value)) {
            for (var i = 0; i < data.length; i++) {
                document.getElementById(data[i].id).style.display ='';
            }
            return;
        }
        for (i = 0; i < data.length; i++) {
            if (data[i].id.toLowerCase().search(this.value.toLowerCase()) === -1)  document.getElementById(data[i].id).style.display ='none';
            else document.getElementById(data[i].id).style.display ='';

        }
    }
});
