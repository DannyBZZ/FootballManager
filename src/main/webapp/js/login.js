/**
 * view-controller for login.html
 *
 * M133: FootballManager
 * @Author Danny Logiurato
 */

/** register listeners
 */
$(document).ready(function () {
    $("#loginform").submit(sendLogin);
    $("#logoff").submit(sendLogoff); //korriegiren
});


/**
 * send for login-request
 * @param form the form with username/password
 */
function sendLogin(form) {
    form.preventDefault();
    $
        .ajax({
            url: "./resource/user/login",
            dataType: "text",
            type: "POST",
            data: $("#loginform").serializeToString()
        })
        .done(function (){
            window.location.href = "./index.html"
        })
        .fail(function (xhr, status, errorThrown){
            if(xhr.status == 404) {
                $("#message").text("Benutzername/Passwort unbekannt");
            }else {
                $("#message").text("Es ist ein Fehler aufgetreten");
            }
        })
}

/**
 * send logoff-reques
 */
function sendLogoff() {
    $
        .ajax({
            url: "./resource/user/logoff",
            dataType: "text",
            type: "DELETE",
        })
        .done(function (){
            window.location.href = "./login.html"
        })
        .fail(function (xhr, status, errorThrown){
        })
}