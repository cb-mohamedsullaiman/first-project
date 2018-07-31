<html>
    <head>
        <title>
            Self service portal
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            
            @import "default.css";
            form {background-color: #f8f8f8 ;border: 3px solid #b2beb5; width: 30%; margin: 0 auto; border-radius: 10px; }

            input[type=text], input[type=password] {
                flex:1;
                width: 100%;
                padding: 12px 20px;
                margin: 2% 3%;
                display: inline-block;
                box-sizing: border-box;
                border: none;
                border-bottom: 1px solid #111111;
                background-color: #f8f8f8 ;
            }

            button {
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                border-radius: 5px;
            }
            div.head-line{
                padding-bottom: 0px;
            }
            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
            }

            img.avatar {
                width: 20%;
                border-radius: 30%;
            }          
            span.psw {
                float: right;
                padding : 10px;
            }
            .signupbutton {
                width: auto;
                padding: 10px 18px;
                background-color: #f44336;
            }
            .signup{
                display: none;
                z-index: 1;
                position: fixed;
                top:0;
                left:0;
                width:100%;
                height:100%;
                overflow: auto;
                background-color: rgb(0,0,0);
                background-color: rgba(0,0,0,0.4);
                padding-top: 60px;
            }
            .signup-content {
                background-color: #fefefe;
                margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
                border: 1px solid #888;
                width: 80%; /* Could be more or less, depending on screen size */
            }
            .close {
                position: absolute;
                right: 25px;
                top: 0;
                color: #000;
                font-size: 35px;
                font-weight: bold;
            }
            .close:hover,
            .close:focus {
                color: red;
                cursor: pointer;
            }
            #user-name-check, #password-check,#first-name-check,#last-name-check,#email-check,#confirm-email,#signup-password-check,#signup-password-confirmation{
                background-color: red;
                color:white;
            }
            #login{
                width: 100%;
                margin-left: 25px;
                display: inline-block;

            }
            #loginButton{
                font-size: 12pt;
                display:inline-block;
                width:50%;
                background-color:  orangered;

            }
            #forgotPassword{
                display:inline-block;
                word-wrap:  break-word;
                max-width:  160px;
                vertical-align: middle;
                padding-left: 2%;
            }
            #signUpDiv{
                text-align:  center;
                background-color: blue;
                color: white;  
                width: auto;               
            }
            #signUpDiv:hover{
                cursor: pointer;
                opacity: 0.8;
            }
            #loginForm{
                position: relative;
                top: 50%;
                transform: translateY(-50%); /* or try 50% */

            }
            
        </style>
        <script type ="text/javascript">
            function signup() {
                document.getElementById('signup').style.display = "block";
            }
            function validateUserName() {
                var userName = document.getElementById('uname').value;
                if (userName === "") {
                    document.getElementById('user-name-check').innerHTML = "*User name required";
                }
                else {
                    document.getElementById('user-name-check').innerHTML = "";
                }
            }
            function validatePassword() {
                var password = document.getElementById('psw').value;
                if (password === "") {
                    document.getElementById('password-check').innerHTML = "*Password required";
                }
                else {
                    document.getElementById('password-check').innerHTML = "";
                }
            }
            function validateFirstName() {
                var firstName = document.getElementById('firstName').value;
                if (firstName === "") {
                    document.getElementById('first-name-check').innerHTML = "*First name required";
                }
                else {
                    document.getElementById('first-name-check').innerHTML = "";
                }
            }
            function validateLastName() {
                var lastName = document.getElementById('lastName').value;
                if (lastName === "") {
                    document.getElementById('last-name-check').innerHTML = "*Last name required";
                }
                else {
                    document.getElementById('last-name-check').innerHTML = "";
                }
            }
            function validateEmail() {
                var email = document.getElementById('email').value;
                if (email === "") {
                    document.getElementById('email-check').innerHTML = "*Email required";
                }
                else {
                    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                    if (email.match(mailformat)) {
                        document.getElementById('email-check').innerHTML = "";
                        // var confirmationMail = document.
                    }
                    else {
                        document.getElementById('email-check').innerHTML = "*Invalid email address";
                    }
                    var confirmationMail = document.getElementById('confirmEmail').value;

                    if (confirmationMail !== "" && confirmationMail !== email) {
                        document.getElementById('confirm-email').innerHTML = "*Emails does not match";
                    }
                }
            }
            function confirmEmailAddress() {
                var confirmationMail = document.getElementById('confirmEmail').value;
                var email = document.getElementById('email').value;
                if (confirmationMail === "") {
                    document.getElementById('confirm-email').innerHTML = "*This field cannot be empty";
                }
                else if (email === "") {
                    document.getElementById('confirm-email').innerHTML = "*Email field cannot be empty";
                }
                else if (confirmationMail !== email) {
                    document.getElementById('confirm-email').innerHTML = "*Emails does not match";
                }
                else {
                    document.getElementById('confirm-email').innerHTML = "";
                }
            }
            function validateSignupPassword() {
                var password = document.getElementById('signupPassword').value;
                if (password === "") {
                    document.getElementById('signup-password-check').innerHTML = "Password cannot be empty";
                }
                else if (password.length < 8) {
                    document.getElementById('signup-password-check').innerHTML = "Password cannot be less than eight characters";
                }
                else if (!/[a-z]/.test(password)) {
                    document.getElementById('signup-password-check').innerHTML = "It should contain atleast one lower case letter";
                }
                else if (!/[A-Z]/.test(password)) {
                    document.getElementById('signup-password-check').innerHTML = "It should contain atleast one upper case letter";
                }
                else if (!/[0-9]/.test(password)) {
                    document.getElementById('signup-password-check').innerHTML = "It should contain alteast one digit";
                }
                else {
                    var confirmationPassword = document.getElementById('signupConfirmationPassword').value;
                    if (confirmationPassword !== "" && password !== confirmationPassword) {
                        document.getElementById('signup-password-confirmation').innerHTML = "Passwords does not match";
                    }
                    else {
                        document.getElementById('signup-password-check').innerHTML = "";
                    }
                }

            }
            function confirmSignupPassword() {
                var confirmationPassword = document.getElementById('signupConfirmationPassword').value;
                var password = document.getElementById('signupPassword').value;
                if (confirmationPassword === "") {
                    document.getElementById('signup-password-confirmation').innerHTML = "This field cannot be empty";
                }
                else if (password === "") {
                    document.getElementById('signup-password-confirmation').innerHTML = "First enter password";
                }
                else if (password !== confirmationPassword) {
                    document.getElementById('signup-password-confirmation').innerHTML = "Password does not match";
                }
                else {
                    document.getElementById('signup-password-confirmation').innerHTML = "";
                }
            }
            function validateUser() {
                var xmlHttp = new XMLHttpRequest();
                xmlHttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        document.getElementById('login-message').innerHTML = xmlHttp.responseText;
                    }
                };
                xmlHttp.open("GET", "http://localhost:8080/mySelfServicePortal/sayhello", true);
                xmlHttp.send();
            }
        </script>

    </head>
    <body>

        <div id = "loginForm">    
            <form action="login" style="width: 30%" method="POST">
                <div class="head-line">Space Portal</div>
                <div class="small-head">Login</div>
                <div class="imgcontainer">
                    <img src="image1.png" alt="Avatar" class="avatar">
                </div>

                <div class="container" >
                    <label ><b>Username</b></label>
                    <br/>
                    <input type="text" placeholder="Enter Username" id="uname" name="userName" onfocus="document.getElementById('user-name-check').innerHTML = ''" onblur="validateUserName()" required/>
                    <br/>
                    <p id = "user-name-check"></p>
                    <label ><b>Password</b></label>
                    <br/>
                    <input type="password" placeholder="Enter Password" id="psw" name="password" onfocus="document.getElementById('password-check').innerHTML = ''" onblur="validatePassword()" required>
                    <p id="password-check"></p>
                    <%
                        String login_msg=(String)request.getAttribute("error");  
                        if(login_msg!=null)
                        out.println("<font color=red size=4px>"+login_msg+"</font>");
                    %>                    
                    <div id="login" >
                        <button type="submit" id="loginButton">Login</button>

                        <label id='forgotPassword'>
                            <a href="#"  >Don't remember your password?</a>
                        </label>

                    </div>
                    <label>
                        <input type="checkbox" checked="checked" name="remember"> Remember me
                    </label>
                </div>

                <div class="container" onclick="signup()" id='signUpDiv'>
                    New around here?

                </div>
            </form>
        </div>

        <div class ="signup" id = 'signup'>
            <form class = "signup-content"  name = "signUpForm" action="/mySelfServicePortal/register" method="POST" >
                <h1 align = "center">Sign up</h1>
                <div class="imgcontainer">
                    <img src="image1.png" alt="Avatar" class="avatar">
                    <span onclick="document.getElementById('signup').style.display = 'none'" class = "close">x</span>
                </div>

                <div class="container" >
                    <label ><b>First Name</b></label>
                    <br/>
                    <input type="text" placeholder="Enter first name" id="firstName" name="firstName" onfocus="document.getElementById('first-name-check').innerHTML = ''" onblur = "validateFirstName()" required/>       
                    <p id ="first-name-check"> </p>
                    <%
                         String firstNameError=(String)request.getAttribute("firstName");  
                         if(firstNameError!=null){
                             out.println("<script>document.getElementById('signup').style.display = 'block';</script>");
                             out.println("<font color=red size=4px>"+firstNameError+"</font>");
                         }
                    %>                 
                    <br/>

                    <label ><b>Last Name</b></label>
                    <br/>
                    <input type="text" placeholder="Enter last name" id="lastName" name="lastName" onfocus="document.getElementById('last-name-check').innerHTML = ''" onblur = "validateLastName()" required>
                    <br/>
                    <p id ="last-name-check"></p>
                    <%
                        String lastNameError=(String)request.getAttribute("lastName");  
                        if(lastNameError!=null){
                            out.println("<script>document.getElementById('signup').style.display = 'block';</script>");
                            out.println("<font color=red size=4px>"+lastNameError+"</font>");
                        }
                    %>                    
                    <br/>
                    <label ><b>Email</b></label>
                    <br/>
                    <input type="text" placeholder="Enter email" name="email" id="email" onfocus="document.getElementById('email-check').innerHTML = '';
                            document.getElementById('confirm-email').innerHTML = ''" onblur = "validateEmail()" required>
                    <br/>
                    <p id="email-check"></p>
                    <%
                        String emailError=(String)request.getAttribute("email");  
                        if(emailError!=null){
out.println("<script>document.getElementById('signup').style.display = 'block';</script>");
                            
                        out.println("<font color=red size=4px>"+emailError+"</font>");
}
                    %>                    
                    <br/>
                    <label ><b>Confirm email</b></label>
                    <br/>
                    <input type="text" placeholder="Enter email again" name="confirmEmail" id="confirmEmail" onblur="confirmEmailAddress()" onfocus="document.getElementById('confirm-email').innerHTML = ''" required>
                    <br/>  
                    <p id="confirm-email"></p>
                    <%
                        String confirmationMailError=(String)request.getAttribute("confirmEmail");  
                        if(confirmationMailError!=null){
                            out.println("<script>document.getElementById('signup').style.display = 'block';</script>");                         
                            out.println("<font color=red size=4px>"+confirmationMailError+"</font>");
}
                    %>                    
                    <br/>
                    <label ><b>Password</b></label>
                    <br/>
                    <input type="password" placeholder="Enter Password" name="registrationPassword" id = "signupPassword" onfocus="document.getElementById('signup-password-check').innerHTML = '';
                            document.getElementById('signup-password-confirmation').innerHTML = ''" onblur="validateSignupPassword()" required>
                    <br/>     
                    <p id="signup-password-check"></p>
                    <%
                        String passwordError=(String)request.getAttribute("password");  
                        if(passwordError!=null){
                            out.println("<script>document.getElementById('signup').style.display = 'block';</script>");                           
                            out.println("<font color=red size=4px>"+passwordError+"</font>");
                        }
                    %>                    
                    <br/>
                    <label ><b>Confirm Password</b></label>
                    <br/>
                    <input type="password" placeholder="Enter Password again" name="confirmRegistrationPassword" id='signupConfirmationPassword' onfocus="document.getElementById('signup-password-confirmation').innerHTML = ''" onblur="confirmSignupPassword()" required>
                    <div id="signup-password-confirmation"></div> 
                    <%
                        String passwordConfirmationError=(String)request.getAttribute("confirmPassword");  
                        if(passwordConfirmationError!=null){
                            out.println("<script>document.getElementById('signup').style.display = 'block';</script>");                           
                            out.println("<font color=red size=4px>"+passwordConfirmationError+"</font>");
                        }
                    %>                    
                    <br/>
                    <label>
                        <input type="checkbox" checked="checked" name="remember"> Remember me
                    </label>
                    <button type="submit" style="background-color:orangered; font-size: 15pt" >Sign up!</button>

                </div>
                <script>
                    var modal = document.getElementById("signup");
                    window.onclick = function (event) {
                        if (event.target === modal) {
                            modal.style.display = "none";
                        }
                    }
                </script>

            </form>
        </div>  

    </body>
</html>
