<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cash Dispenser Assignment</title>
        
        <style type="text/css">
            
            .col_ful {
                width: 100%;
            }
            
            .col_sml {
                width: 20%;
            }
            
            .col_med {
                width: 25%;
            }
            
            .col_lrg {
                width: 50%;
            }
            
            .col_ful, .col_lrg, .col_med, .col_sml {
                float: left;
                padding: 5px;
            }
            
        </style>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        
        <script type="text/javascript" >
            
            function login(){
        
                var username = $("#username").val();
                var pass = $("#password").val();
                
                $.ajax({
                    type: 'GET',
                    url: '/WSDispenseSvc/UserLogin?username=' + username + '&password=' + pass,
                    dataType: 'json',
                    success: function (responseData, textStatus, jqXHR) {
                        if (null === responseData) {
                            alert("Login error. Please try again.");
                        } else {
                            if (responseData.key === "OK"){
                                $("#username").val("");
                                $("#password").val("");
                                $("#login").hide();
                                $("#dispenser").show();
                            } else {
                                alert(responseData.value);
                            }
                        }
                    },
                    error: function (responseData, textStatus, errorThrown) {
                        alert('Error during login. ' + errorThrown);
                    }

                });
            }
            
            function dispense(value){
                
                $("#cost").html("");
                
                $.ajax({
                    type: 'GET',
                    url: '/WSDispenseSvc/Dispense?snack_type=' + value,
                    dataType: 'json',
                    success: function (responseData, textStatus, jqXHR) {
                        if (null === responseData) {
                            alert("Dispensing error. Please try again.");
                        } else {
                            if (responseData.key === "OK"){
                                $("#cost").html(responseData.value);
                            } else {
                                alert(responseData.Value);
                            }
                        }
                    },
                    error: function (responseData, textStatus, errorThrown) {
                        alert('Dispensing error. ' + errorThrown);
                    }

                });
        
            }
            
            function calculate(){
                
                var charge = $("#cost").html();
                var moneyin = $("#moneyin").val();
                $("#breakdown").html("");
                
                $.ajax({
                    type: 'GET',
                    url: '/WSDispenseSvc/Calculate?moneyin=' + moneyin + '&charge=' + charge,
                    dataType: 'json',
                    success: function (responseData, textStatus, jqXHR) {
                        if (null === responseData) {
                            alert("Dispensing error. Please try again.");
                        } else {
                            if (responseData.key === "OK"){
                                $("#dispenser").hide();
                                $("#dispenserOutput").show();
                                
                                var totalChangeObj = responseData.value;
                                var breakdowns = totalChangeObj.changeBreakdown;
                                if (null === breakdowns || breakdowns.length === 0){
                                    alert("Dispensing error. Please try again.");
                                } else {
                                    var brkString = "";
                                    for (var x = 0; x < breakdowns.length; x++){
                                        if (breakdowns[x].count > 0)
                                            brkString += breakdowns[x].count + " x " + breakdowns[x].note + "<br/>";
                                    }
                                    $("#breakdown").html(brkString);
                                    $("#totalChange").html(totalChangeObj.totalChange);
                                }
                            } else {
                                alert(responseData.value);
                            }
                        }
                    },
                    error: function (responseData, textStatus, errorThrown) {
                        alert('Dispensing error. ' + errorThrown);
                    }

                });
        
            }
            
            function reset(){
                $("#dispenser").show();
                $("#dispenserOutput").hide();
            }
            
        </script>
        
    </head>

    <body style="background-color: grey;" >
        <table style="text-align:center;" width="100%">
            <tr>
                <td align="center" >
                    <div style="width:30%; text-align:center;">
                        <div class="col_ful" style="color:white; background-color:blue; height:20px;" >
                            <span>Cash Dispenser</span>
                        </div>
                        <div id="login" class="col_ful" style="background-color: white;">
                            <div class="col_ful">
                                <p>Enter username and password to log in.</p>
                            </div>
                            <div class="col_ful">
                                <div class="col_med">
                                    <label>Username</label>
                                </div>
                                <div class="col_med">
                                    <input id="username" type="text" value="" placeholder="Your username"/>
                                </div>
                            </div>
                            <div class="col_ful">
                                <div class="col_med">
                                    <label>Password</label>
                                </div>
                                <div class="col_med">
                                    <input id="password" type="password" value="" />
                                </div>
                            </div>
                            <div class="col_ful">
                                <div class="col_med">
                                    <input type="button" value="Login" onclick="login();" />
                                </div>
                            </div>
                        </div>
                        <div id="dispenser" class="col_ful" style="background-color: white; display:none;">
                            <div class="col_ful">
                                <div class="col_sml">
                                    <input type="button" value="Chips" onclick="dispense('chips');" />
                                </div>
                                <div class="col_sml">
                                    <input type="button" value="Coke" onclick="dispense('coke');" />
                                </div>
                                <div class="col_sml">
                                    <input type="button" value="Nuts" onclick="dispense('nuts');" />
                                </div>
                            </div>
                            <div class="col_ful">
                                <p>Amount Due: <span id="cost"></span> </p>
                            </div>
                            
                            <div class="col_ful">
                                Capture Rand note denomination
                            </div>
                            <div class="col_ful">
                                <input type="text" id="moneyin" maxlength="3"/>
                            </div>
                            <div class="col_ful">
                                <div class="col_med">
                                    <input type="button" value="Submit" onclick="calculate();" />
                                </div>
                            </div>
                            
                        </div>
                        <div id="dispenserOutput" class="col_ful" style="background-color: white; display:none;">
                            <div class="col_ful">
                                <p>Breakdown of cash to dispense is: </p>
                            </div>
                            
                            <div class="col_ful">
                                <span id="breakdown">
                                    
                                </span>
                            </div>
                            <div class="col_ful">
                                <p>Total Change:
                                <span id="totalChange"></span></p>
                            </div>
                            <div class="col_ful">
                                <div class="col_med">
                                    <input type="button" value="Reset" onclick="reset();" />
                                </div>
                            </div>
                            
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </body>
</html>
