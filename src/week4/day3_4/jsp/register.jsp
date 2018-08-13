<html>
    <head>
        <title>
            Self service portal
            </title>
            <style>
                body {font-family: Arial, Helvetica, sans-serif;}

            form {background-color: #f8f8f8 ;border: 3px solid #b2beb5; width: 30%; margin: 0 auto; border-radius: 10px}

            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
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

            button:hover {
                opacity: 0.8;
            }

            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
            }

            img.avatar {
                width: 20%;
                border-radius: 30%;
            }

            .container {
                padding: 16px;
                margin : 0 auto;
                width: 25em;

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
        </style>
        
    </head>
    <body>
        <div class ="signup" id = 'signup'>
            <form class = "signup-content"  name = "signUpForm" action="/action_page.php" >
                <h1 align = "center">Sign up</h1>
                <div class="imgcontainer">
                    <img src="image1.png" alt="Avatar" class="avatar">
                    <span onclick="document.getElementById('signup').style.display = 'none'" class = "close">x</span>
                </div>

                <div class="container" >
                    <label ><b>First Name</b></label>
                    <br/>
                    <input type="text" placeholder="Enter first name" id="firstName" name="firstName" onfocus="document.getElementById('first-name-check').innerHTML = ''" onblur = "validateFirstName()" required/>       
                    <br/>
                    <p id ="first-name-check"> </p>
                    <label ><b>Last Name</b></label>
                    <br/>
                    <input type="text" placeholder="Enter last name" id="lastName" name=lastName" onfocus="document.getElementById('last-name-check').innerHTML = ''" onblur = "validateLastName()" required>
                    <br/>
                    <p id ="last-name-check"></p>
                    <label ><b>Email</b></label>
                    <br/>
                    <input type="text" placeholder="Enter email" name="email" id="email" onfocus="document.getElementById('email-check').innerHTML = '';
                            document.getElementById('confirm-email').innerHTML = ''" onblur = "validateEmail()" required>
                    <br/>
                    <p id="email-check"></p>
                    <label ><b>Confirm email</b></label>
                    <br/>
                    <input type="text" placeholder="Enter email again" name="confirmEmail" id="confirmEmail" onblur="confirmEmailAddress()" onfocus="document.getElementById('confirm-email').innerHTML = ''" required>
                    <br/>  
                    <p id="confirm-email"></p>
                    <label ><b>Password</b></label>
                    <br/>
                    <input type="password" placeholder="Enter Password" name="password" id = "signupPassword" onfocus="document.getElementById('signup-password-check').innerHTML = '';
                            document.getElementById('signup-password-confirmation').innerHTML = ''" onblur="validateSignupPassword()" required>
                    <br/>     
                    <p id="signup-password-check"></p>
                    <label ><b>Confirm Password</b></label>
                    <br/>
                    <input type="password" placeholder="Enter Password again" name="confirmPassword" id='signupConfirmationPassword' onfocus="document.getElementById('signup-password-confirmation').innerHTML = ''" onblur="confirmSignupPassword()" required>
                    <div id="signup-password-confirmation"></div>  
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
Ï
