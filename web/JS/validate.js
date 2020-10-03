/* 
    Document   : validate
    Created on : Nov 15, 2012, 6:41:25 PM
    Author     : Shreya Appia
    Description: Validation of fields for forms in the project
*/
function validateRegistration(){
    var uname = $('#username').val();
    var pwd = $('#password').val();
    var confirmpwd = $('#confirmpassword').val();
    var email = $('#emailid').val();
    var confirmemail = $('#confirmemailid').val();
    var valid = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
    if((uname.length==0)||(pwd.length==0)||(confirmpwd.length==0)||(email.length==0)||(confirmemail==0)){
        var allfieldserror = "Please enter all fields!";
        $('#allfieldsError').html(allfieldserror);
        return false;
    }
    else if((uname.length<5)||(uname.length>20)){
        var unameerror = "Username should be between 5 to 20 characters!";
        $('#usernameError').html(unameerror);
        return false;
    }
    else if((pwd.length<5)||(pwd.length>10)) {
        var pwderror = "Password should be between 5 to 10 characters!";
        $('#passwordError').html(pwderror);
        return false; 
    }
    else if(!(confirmpwd==pwd)) {
        var cnfpwderror = "Password and Confirm Password do not match!";
        $('#cnfrmpwdError').html(cnfpwderror);
        return false;
    }
    else if(!(valid.test(email))) {
        var emailerror = "Email address is not valid!";
        $('#emailError').html(emailerror);
        return false;
    }
    else if(!(confirmemail==email)) {
        var cnfemailerror = "Email ID and Confirm Email ID do not match!";
        $('#cnfrmemailError').html(cnfemailerror);
        return false;
    }
    else
        return true;
}

function validateLogin(){
    var uname = $('#j_username').val();
    var pwd = $('#j_password').val();
    if((uname.length==0)||(pwd.length==0)){
        $('#loginError').html("Please enter all fields");
        return false;
    }
    else 
        return true;
}


function validateNewsLink(){
    var link = $('#newslink').val();
    
    if(link.length==0){
        alert("Please enter a link");
        return false;
    }  
    else if (link.length>255) {
        alert("The link is too long (max 255 characters)");
        return false;
    }
    else
        return true;
}

function validateFriend(){
    var friend = $('#friends option:selected').val();
    if(friend=='NONE') {
        alert("Please select a friend to add");
        return false;
    }
    else
        return true;
}