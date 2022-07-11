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
            window.location.href = "./footballmanager.html"
        })
}

/**
 * send logoff-reques
 */
function sendLogoff() {

}