 <?php

$flag = false;

if (isset($_POST['modify_res_num']) && !empty($_POST['modify_res_num'])) {

  session_start();

  $_SESSION['modify_res_num'] = $_POST['modify_res_num'];
  $modify_res_num = $_SESSION['modify_res_num'];

  $mysql_host = 'localhost';
  $mysql_user = 'root';
  $mysql_pass = '';
  $mysql_db = 'prestige_villa_db';

  if (!@mysql_connect($mysql_host, $mysql_user, $mysql_pass) || !@mysql_select_db($mysql_db)) {
    echo "Could not connect.";
  } else {
    $query_run = mysql_query("SELECT * FROM  `reservations` WHERE  `Res` = '$modify_res_num' ORDER BY `Res` ASC LIMIT 1");

    if (mysql_num_rows($query_run) > 0) {

      while ($query_row = mysql_fetch_assoc($query_run)) {
        $modify_fName = $query_row['First Name'];
        $modify_lName = $query_row['Last Name'];
        $modify_phoneNo = $query_row['Phone No'];
        $modify_ID_no = $query_row['ID No'];

        $flag = true;
      }
    } else {
      header('Location: noreservation.html');
    }
  }
}

if (isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['phone']) && isset($_POST['id_no']) && isset($_POST['arrival_date']) && isset($_POST['departure_date']) && isset($_POST['email']) && isset($_POST['address'])) {

  session_start();

  $modify_res_num = $_SESSION['modify_res_num'];

  $fname = strtoupper($_POST['first_name']);
  $lname = strtoupper($_POST['last_name']);
  $phone = strtoupper($_POST['phone']);
  $country = $_POST['country'];
  $email = $_POST['email'];
  $address = strtoupper($_POST['address']);
  $id_type = $_POST['id_type'];
  $id_no = strtoupper($_POST['id_no']);

  $_SESSION['arrival'] = $_POST['arrival_date'];
  $_SESSION['departure'] = $_POST['departure_date'];
  $arrival = $_SESSION['arrival'];
  $departure = $_SESSION['departure'];

  $gender = $_POST['gender'];

  $_SESSION['room_type'] = $_POST['room_type'];
  $room_type = $_SESSION['room_type'];

  $date = date("d/m/Y");

  $formatted_arrival = substr($arrival, 6, 4) . '-' . substr($arrival, 3, 2) . '-' . substr($arrival, 0, 2);
  $formatted_departure = substr($departure, 6, 4) . '-' . substr($departure, 3, 2) . '-' . substr($departure, 0, 2);

  $arrival_difference = strtotime("$formatted_arrival");
  $departure_difference = strtotime("$formatted_departure");
  $datediff = abs($departure_difference - $arrival_difference);

  $_SESSION['no_days'] = floor($datediff / (60 * 60 * 24));
  $no_days = $_SESSION['no_days'];

  $date2 = date("m/d/Y");
  $str1 = substr($date2, 0, 2) . substr($date2, 3, 2);

  if ($room_type == 'Diplomatic') {
    $_SESSION['room_prize'] = 37000;
    $room_prize = $_SESSION['room_prize'];
  } elseif ($room_type == 'Executive') {
    $_SESSION['room_prize'] = 2000;
    $room_prize = $_SESSION['room_prize'];
  } elseif ($room_type == 'Super Executive') {
    $_SESSION['room_prize'] = 25000;
    $room_prize = $_SESSION['room_prize'];
  }

  $_SESSION['total_prize'] = $room_prize * $no_days;
  $total_prize = $_SESSION['total_prize'];

  if (!empty($fname) && !empty($lname) && !empty($phone) && !empty($id_no) && !empty($arrival) && !empty($departure) && !empty($email) && !empty($address)) {

    $mysql_host = 'localhost';
    $mysql_user = 'root';
    $mysql_pass = '';
    $mysql_db = 'prestige_villa_db';

    if (!@mysql_connect($mysql_host, $mysql_user, $mysql_pass) || !@mysql_select_db($mysql_db)) {
      echo "Could not connect.";
    }

    if ($query_run = mysql_query("SELECT `Res`, `Room No` FROM  `reservations` WHERE `Res` = '$modify_res_num'")) {
      $query_row = mysql_fetch_assoc($query_run);
      $_SESSION['resNo'] = $query_row['Res'];
      $resNo = $_SESSION['resNo'];
      $_SESSION['roomNo'] = $query_row['Room No'];
      $roomNo = $_SESSION['roomNo'];
    }

    if ($query_run = mysql_query("SELECT `Room No` FROM  `rooms` WHERE  `Status` =  'Available' && `Room Type` = '$room_type' ORDER BY `Room No` ASC LIMIT 1")) {
      $query_row = mysql_fetch_assoc($query_run);
      $available_room = $query_row['Room No'];
    }

    if ($available_room == "") {
      header('Location: unavailable.php');
    } else {

      $query = "UPDATE `prestige_villa_db`.`reservations` SET `First Name` = '$fname', `Last Name` = '$lname', `Gender` = '$gender', `Phone No` = '$phone', `Country` = '$country', `Address` = '$address', `ID Type` = '$id_type', `ID No` = '$id_no', `Arrival` = '$arrival', `Departure` = '$departure', `Days` = '$no_days', `Room No` = '$available_room', `Room Type` = '$room_type', `Room Rate` = '$room_prize', `Total Prize` = '$total_prize' WHERE `reservations`.`Res` = '$modify_res_num'";

      if ($query_run = mysql_query($query)) {

        if ($query_run = mysql_query("UPDATE `rooms` SET `Status` = 'Reserved' WHERE `Room No` = '$available_room'")) {

          $to = '$email';
          $subject = 'The Prestige Villa - Reservation details';

          $body = '<p style="text-align: justify;">Thank you for reserving our room, looking foward to see you in our Hotel.</p>
		<p style="text-align: justify;"><b style="color: #FF0000;">Reservation No.:</b>' . $resNo . '</p>
		<p style="text-align: justify;"><b style="color: #FF0000;">Arrival Date:</b>' . $arrival . '</p>
		<p style="text-align: justify;"><b style="color: #FF0000;">Departure Date:</b>' . $departure . '</p>
		<p style="text-align: justify;"><b style="color: #FF0000;">Room Type:</b>' . $room_type . '</p>
		<p style="text-align: justify;"><b style="color: #FF0000;">Prize/Night:</b>' . $room_prize . '</p>
		<p style="text-align: justify;"><b style="color: #FF0000;">Total Prize:</b>' . $total_prize . '</p>
		<p style="text-align: justify;"><b>The Prestige Villa</b></p>';

          $headers = 'From: habibado2006@gmail.com';

          mail($to, $subject, $body, $headers);

          header('Location: modifysuccess.php');
        }
        if ($query_run = mysql_query("UPDATE `rooms` SET `Status` = 'Available' WHERE `Room No` = '$roomNo'"));
      } else {
        echo mysql_error();
      }
    }
  }

  if (empty($fname)) {
    $f1 = true;
  } else {
    $f1 = false;
  }
  if (empty($lname)) {
    $f2 = true;
  } else {
    $f2 = false;
  }
  if (empty($phone)) {
    $f3 = true;
  } else {
    $f3 = false;
  }
  if (empty($id_no)) {
    $f4 = true;
  } else {
    $f4 = false;
  }
  if (empty($arrival)) {
    $f5 = true;
  } else {
    $f5 = false;
  }
  if (empty($departure)) {
    $f6 = true;
  } else {
    $f6 = false;
  }
  if (empty($address)) {
    $f7 = true;
  } else {
    $f7 = false;
  }
  if (empty($email)) {
    $f8 = true;
  } else {
    $f8 = false;
  }
}

?>

<!DOCTYPE html>
<html>
  <head>
     <meta charset="utf-8">
     <meta http-equiv="X-UA-Compatible" content="IE=edge">
     <meta name="viewport" content="width=device-width, initial-scale=1">
     <meta name="description" content="">
     <meta name="author" content="">
	
     <title>The Prestige Villa - Reservation</title>
	 
	<link rel="stylesheet" href="themes/base/jquery.ui.all.css">

	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script src="js/jquery-1.10.2.js"></script>
	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.effect.js"></script>
    <script src="ui/jquery.ui.effect-drop.js"></script>
	<script src="ui/jquery.ui.datepicker.js"></script>
	<script src="js/jquery.poptrox.min.js"></script>
		
	<script>
	$(function() {

	    $("input.formDate").datepicker({ minDate: 0});

	    $( "#datepicker1" ).datepicker({ showAnim: "drop" });
		$( "#datepicker1" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
		$( "#datepicker2" ).datepicker({ showAnim: "drop" });
		$( "#datepicker2" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
		$( "#datepicker3" ).datepicker({ showAnim: "drop" });
		$( "#datepicker3" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
	});
	
	function isNumberKey(evt){
	   var charCode = (evt.which) ? evt.which : event.keyCode
	   if(charCode > 31 && (charCode < 48 || charCode > 57))
	     return false;
	   return true
	};
	
	$(function() {
	   var foo = $('#gallery');
	   foo.poptrox({
	   });
	});
	
	$(function() {
	   var foo = $('#gallery1');
	   foo.poptrox({
	   });
	});
	</script>
 	 
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- My style-->
    <link href="css/style.css" rel="stylesheet">
	
    <!-- Font Awesome style -->  
    <link href="css/font-awesome.css" rel="stylesheet">
	
   </head>
  
   <body style="background: #D0CECE;">
   
   <!-- Fixed navbar -->
    <div class="navbar navbar-default" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">The Prestige Villa</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="index.php">HOME</a></li>
			<li><a href="accomodation.php">ACCOMODATIONS</a></li>
            <li><a href="reservation.php">RESERVATIONS</a></li>
            <li><a href="facilities.php">FACILITIES</a></li>
            <li><a href="gallery.php">GALLERY</a></li>			
			<li><a href="contact.php">CONTACT</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
	
    <!-- Modal -->
       <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h3 class="modal-title" id="myModalLabel">Modify Reservation</h3>
               </div>
               <div class="modal-body">
 		         <form action="modifyres.php" method="POST">
				   <label>Reservation No.</label><br/>
                   <div class="row">
                      <div class="col-xs-6">
                         <input type="text" name="modify_res_num" class="form-control" placeholder="Reservation Number" maxlength="25" required >
                      </div>
                  </div>
                  <br>
                  <button type="submit" class="btn btn-primary">Search Reservation</button>	   
			   </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              </div>
          </div><!-- /.modal-content -->
       </div><!-- /.modal-dialog -->
     </div><!-- /.modal -->
		
	<div class="container" style="font-family: Candara, Helvetica, sans-serif;">
	   <div class="row" style="border: 1px solid #c0c0c0; border-radius: 20px; background: #ECE9E7; padding: 30px;">
	     <div class="col-md-8">
		 		 
		 <h1>Modify Reservation</h1><br/>
		 <p>To modify your reservation, please click on the <b style="color: #FF0000;">'Retrieve Reservation'</b> button. A dialog box will appear, enter your reservation number, if the reservaton has been found, the reservation form will be filled with your previous data. Edit the daata you want and press the <b style="color: #FF0000;">'Modify'</b> button. If the modification is successful, a page will appear with your new details and a copy of the details will be sent to your email address for refrence.</p><br/>
		 <a class="btn btn-lg btn-primary" data-toggle="modal" data-target="#myModal2" style="width:200px;">Retrieve Reservation</a> <br/><br/>

	 <div class="container" style="border: 1px solid #c0c0c0; background: #F4F1EF; padding: 20px; width: 700px; margin-left: 0;">
	     <?php if (isset($fname) && isset($lname) && isset($phone) && isset($id_no) && isset($arrival) && isset($departure) && isset($email) && isset($address)) {
        if ($f1 == true || $f2 == true || $f3 == true || $f4 == true || $f5 == true || $f6 == true || $f7 == true || $f8 == true) {
          echo '<p><b style="color: #FF0000;">Note: </b>All fields marked with <b style="font-size: 20px; color: #FF0000;">*</b> are required.</p>';
        }
      } ?>
	  <form action="modifyres.php" method="POST">
         <div class="row">
           <div class="col-xs-5">
		     <label>First Name <?php if (isset($fname)) {
                          if ($f1 == true) {
                            echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                          }
                        } ?></label><br/> 
             <input type="text" name="first_name" class="form-control" maxlength="30" required placeholder="Enter Your First Name" value="<?php if (isset($modify_fName)) {
                                                                                                                                            echo $modify_fName;
                                                                                                                                          } ?>">
           </div>
           <div class="col-xs-5">
		     <label>Last Name <?php if (isset($lname)) {
                          if ($f2 == true) {
                            echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                          }
                        } ?></label><br/>
             <input type="text" name="last_name" class="form-control" maxlength="30" required  placeholder="Enter Your Last Name" value="<?php if (isset($modify_lName)) {
                                                                                                                                          echo $modify_lName;
                                                                                                                                        } ?>">
           </div>
         </div>
         <br>
         <div class="row">
           <div class="col-xs-5">
		     <label>Phone Number <?php if (isset($phone)) {
                            if ($f3 == true) {
                              echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                            }
                          } ?></label><br/>
             <input type="someid" name="phone" onkeypress="return isNumberKey(event)" class="form-control" maxlength="20" required placeholder="Enter Your Phone No." value="<?php if (isset($modify_phoneNo)) {
                                                                                                                                                                              echo $modify_phoneNo;
                                                                                                                                                                            } ?>">
           </div>
           <div class="col-xs-5">
		     <label>Country</label><br/>
             <select class="form-control" name="country">
		        <option value="Belarus" selected="selected">Belarus</option>
		        <option value="China">China</option>
		        <option value="England">England</option>
				<option value="Germany">Germany</option>
				<option value="Ghana">Ghana</option>
				<option value="Gungary">Hungary</option>
				<option value="Nigeria">Nigeria</option>
				<option value="Niger">Niger</option>
				<option value="Poland">Poland</option>
				<option value="Ukraine">Ukraine</option>
				<option value="USA">USA</option>
	         </select>
           </div>
         </div>
         <br>
         <div class="row">
           <div class="col-xs-5">
		     <label>ID Type</label><br/>
             <select class="form-control" name="id_type">
		        <option value="Drivers Licence" selected="selected">Drivers Licence</option>
		        <option value="National ID">Nation ID</option>
		        <option value="School ID">School ID</option>
				<option value="Other">Other</option>
	         </select>
           </div>
           <div class="col-xs-5">
		     <label>ID Number <?php if (isset($id_no)) {
                          if ($f4 == true) {
                            echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                          }
                        } ?></label><br/>
             <input type="text" name="id_no"class="form-control" maxlength="30" required placeholder="Enter Your ID No." value="<?php if (isset($modify_ID_no)) {
                                                                                                                                  echo $modify_ID_no;
                                                                                                                                } ?>">
           </div>
         </div>
         <br>
         <div class="row">
           <div class="col-xs-5">
		     <label>Arrival Date <?php if (isset($arrival)) {
                            if ($f5 == true) {
                              echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                            }
                          } ?></label><br/>
             <input type="text" name="arrival_date" id="datepicker1" class="form-control formDate" maxlength="20" required placeholder="Select Date" value="<?php if (isset($arrival)) {
                                                                                                                                                              echo $arrival;
                                                                                                                                                            } ?>">
           </div>
           <div class="col-xs-5">
		     <label>Departure Date <?php if (isset($departure)) {
                              if ($f6 == true) {
                                echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                              }
                            } ?></label><br/>
             <input type="text" name="departure_date" id="datepicker2" class="form-control formDate" maxlength="20" required placeholder="Select Date" value="<?php if (isset($departure)) {
                                                                                                                                                                echo $departure;
                                                                                                                                                              } ?>">
           </div>
         </div>
         <br/>
         <div class="row">
           <div class="col-xs-5">
		     <label>Gender</label><br/>
             <select class="form-control" name="gender">
		        <option value="Male">Male</option>
		        <option value="Female">Female</option>
	         </select>
           </div>
           <div class="col-xs-5">
		     <label>Room Type</label><br/>
             <select class="form-control" name="room_type">
		       <option value="Diplomatic" selected="selected">Diplomatic Suite</option>
		       <option value="Executive">Executive Suite</option>
		       <option value="Super Executive">Super Executive Suite</option>
	         </select>
           </div>
         </div>
         <br/>
         <div class="row">
           <div class="col-xs-5">
		     <label>Email <?php if (isset($email)) {
                      if ($f8 == true) {
                        echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                      }
                    } ?></label><br/>
             <input type="email" name="email" class="form-control" maxlength="50" required placeholder="Enter Email" value="<?php if (isset($email)) {
                                                                                                                              echo $email;
                                                                                                                            } ?>">
           </div>
         </div>
         <br/>
		 <p><b style="color: #FF0000;">Please note:</b> Your email address will only be used to send you reservation details including your reservation number and amount.</p>
		 <label>Address <?php if (isset($address)) {
                    if ($f7 == true) {
                      echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                    }
                  } ?></label><br/>
         <div class="row">
           <div class="col-xs-9">
             <textarea name="address" class="form-control" rows="5" maxlength="100" required  placeholder="Enter Your Address"></textarea>
           </div>
         </div>
         <br/>
		 <p><b style="color: #FF0000;">Please note:</b> Your contact information will only be used in regard to your reservation. Your information is stored using the latest state-of-the-art technology, so your information is safe with us.</p>
		 <?php if ($flag == true) {
    echo '<button type="submit" class="btn btn-primary" style="width: 100px">MODIFY</button>';
  } ?>
      </form>
	 </div>
    </div>
	<br/><br/><br/><br/>
	 <div class="col-md-4" style="border: 1px solid #c0c0c0; border-radius: 20px; box-shadow: 2px 3px 2px 2px #7A7A7A; color: #720D0D; padding: 10px; background: #F4F1EF; height: 700px;">
		<h3>Location</h3>
	    <p>No. 34a Sokoto Road, Kano, <br/> 
		 Kano State, Nigeria.<br/><br/>
		 <img src="images/map.jpg" style="border: 1px solid #c0c0c0;">
		 <h4>Call us on:</h4>
		  +2348051256120<br/>
		  +2348066127299<br/>
		  +2348026211251<br><br/>
		  <a href="mailto:habibado2006@gmail.com">Email Us &raquo;</a></p><br/><br/>
	  </div>
   </div>
	   
	  <div class="footer" style="border-top: 1px solid #B8B1B1; border-bottom: 1px solid #B8B1B1;">
        <p><b style="font-family: Bookman Old Style; font-size: 18px; font-weight: 200;"> CONNECT : &nbsp; </b>	<a><i class="fa fa-facebook"></i></a> &nbsp;&nbsp;<a><i class="fa fa-twitter"></i></a> &nbsp;&nbsp;<a><i class="fa fa-google-plus"></i></a>  &nbsp;&nbsp;<a><i class="fa fa-youtube"></i></a> &nbsp;&nbsp;<a><i class="fa fa-instagram"></i></a> &nbsp;&nbsp;<a><i class="fa fa-linkedin"></i></a> &nbsp;&nbsp;<a><i class="fa fa-painterest"></i></a> <b style="font-family: Californian FB; font-size: 14px; font-weight: 200; color: #9A8383; float: right;"><a href="reservation.php">Reservations</a> | <a href="#">Contact</a> | <a href="facilities.php">Facilities</a> | <a href="gallery.php">Gallery</a></b></p>
      </div>
	  
	  <center class="footerLogo"><img src="images/footerLogo.png"><br/>
	    <b style="font-family: Bookman Old Style; font-size: 11px; font-weight: 200; color: #9A8383;">&copy; The Prestige Villa 2014. All Rights Reserved</b>
	  </center>
	</div>
   </body>
</html>