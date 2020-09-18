function getGithubInfo(user) {
    // <----------- Create an instance of XMLHttpRequest class and send a GET request using it-------->

    // <----------- Create a request variable and assign a new XMLHttpRequest object to it ----------->
    var request = new XMLHttpRequest();
    console.log(user);

    request.onreadystatechange = function () {
        //<---- request processed and response is ready (returns the status-number of a request 200)-->
        if (this.readyState == 4 && this.status == 200) {
            showUser(JSON.parse(this.responseText));
        }
        else if (this.readyState == 4) {
            noSuchUser(user);
        }
    };

// <----------- Open a new connection, using the GET request on the URL endpoint----------------->
    var link="https://api.github.com/users/"+user;
    request.open("GET",link , true);  //"( https://api.github.com/users/" +user+ ")"

// <------------------------------------ Send request-------------------------------------------->
    request.send();
}

function showUser(user) {
    // <------ set the contents of div elements in the div '#data' with the user content---------->
    $('#name').text(user.name)
    $('#id').text(user.id)
    $('#public_repos').text(user.public_repos)
    $('#followers').text(user.followers)
    $('#location').text(user.location)
    $('#url').append('<a href= " '+user.html_url+' ">' +user.html_url+ '</a>')
    $('#avatar').append('<img src="'+user.avatar_url+'" width="300px" height="300px" padding="90px 30px 0"/>')
}

function noSuchUser(username) {
    // <----------- wrong username entered: displays error message along with user name----------->
    $('#error').text('No such Account!!  ' +username+ ' ');

}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information
            getGithubInfo(username);
        }
    })
});


