<?php

if (isset($_POST['search_date']) && isset($_POST['search_room'])) {
  $search_date = $_POST['search_date'];
  $search_room = $_POST['search_room'];

  if (!empty($search_date) && !empty($search_room)) {

    $mysql_host = 'localhost';
    $mysql_user = 'root';
    $mysql_pass = '';
    $mysql_db = 'prestige_villa_db';

    if (!@mysql_connect($mysql_host, $mysql_user, $mysql_pass) || !@mysql_select_db($mysql_db)) {
      echo "Could not connect.";
    } else {
      $query_run = mysql_query("SELECT `Room No` FROM  `rooms` WHERE  `Status` =  'Available' && `Room Type` = '$search_room' ORDER BY `Room No` ASC LIMIT 1");
      if (mysql_num_rows($query_run) > 0) {
        mysql_num_rows($query_run);
        $availableFlag = true;
      } else {
        $availableFlag = false;
      }
    }

    if ($availableFlag == true) {
      header('Location: available.html');
    } else {
      header('Location: unavailable.php');
    }
  }
}


if (isset($_POST['contact_name']) && isset($_POST['contact_email']) && isset($_POST['contact_phone']) && isset($_POST['comments'])) {
  $contact_name = strtoupper($_POST['contact_name']);
  $contact_email = strtoupper($_POST['contact_email']);
  $contact_phone = strtoupper($_POST['contact_phone']);
  $comments = $_POST['comments'];

  if (!empty($contact_name) && !empty($contact_email) && !empty($contact_phone) && !empty($comments)) {
    if (strlen($contact_name) > 25 || strlen($contact_email) > 50 || strlen($contact_phone) > 20 || strlen($comments) > 1000) {
      echo "Maximum length for one or more field exceeded";
    } else {
      $to = 'habibado2006@gmail.com';
      $subject = 'Contact Form';
      $body = 'Name: ' . $contact_name . "\n" . 'Phone No.: ' . $contact_phone . "\n" . $comments;
      $headers = 'From: $contcat_email';

      if (mail($to, $subject, $body, $headers)) {
        $contactflag = true;
      } else {
        echo "Soory and error occurs, please try again";
      }
    }
  }

  if ($contactflag == true) {
    header('Location: contacted.html');
  }

  if (empty($contact_name)) {
    $f1 = true;
  } else {
    $f1 = false;
  }
  if (empty($contact_email)) {
    $f2 = true;
  } else {
    $f2 = false;
  }
  if (empty($contact_phone)) {
    $f3 = true;
  } else {
    $f3 = false;
  }
  if (empty($comments)) {
    $f4 = true;
  } else {
    $f4 = false;
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
	
     <title>The Prestige Villa - Contact Us</title>
	     
 	 <link rel="stylesheet" href="themes/base/jquery.ui.all.css">

	 <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
     <script src="js/bootstrap.min.js"></script>
	 <script src="js/jquery-1.10.2.js"></script>
	 <script src="ui/jquery.ui.core.js"></script>
	 <script src="ui/jquery.ui.widget.js"></script>
	 <script src="ui/jquery.ui.effect.js"></script>
     <script src="ui/jquery.ui.effect-drop.js"></script>
	 <script src="ui/jquery.ui.datepicker.js"></script>

	
	 <script>
	  $(function() {
	  
	  
	  	 $("input.formDate").datepicker({ minDate: 0});
	  
		 $( "#datepicker" ).datepicker({ showAnim: "drop" });
		 $( "#datepicker" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
	  });
	  
	  function isNumberKey(evt){
	     var charCode = (evt.which) ? evt.which : event.keyCode
	     if(charCode > 31 && (charCode < 48 || charCode > 57))
	       return false;
	     return true
	  };
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
			<li class="active"><a href="#">CONTACT</a></li>
          </ul>
		  <ul class="nav navbar-nav navbar-right">
            <li><a class="btn btn-lg btn-primary" data-toggle="modal" data-target="#myModal" style="width:200px;">Check Availability</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
	
    <!-- Modal -->
       <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h3 class="modal-title" id="myModalLabel">Check Room Availability</h3>
				  <p style="color: #B7975F;">Due to limited nunber of rooms, please take a moment to check for room availability before you reserve. In the event of unavailabity of rooms, please do not hsitate to contact us directly via phone due to last mininute cancelation.</p>
               </div>
               <div class="modal-body">
 		         <form action="reservation.php" method="POST">
				   <div class="row">
				     <div class="col-xs-6">
		               <label>Arrival Date</label><br/>
                       <input type="text" name="search_date" id="datepicker" class="form-control formDate" placeholder="Select Date" required>
                     </div>
                    </div>
                   <br>
                  <div class="row">
                  <div class="col-xs-6">
				     <label>Room Type</label><br/>
                     <select class="form-control" name="search_room">
		                <option value="Diplomatic" selected="selected">Diplomatic Suite</option>
		                <option value="Executive">Executive Suite</option>
		                <option value="Super Executive">Super Executive Suite</option>
	                  </select>
                   </div>
                  </div>
                  <br>
                  <button type="submit" class="btn btn-primary">Check Availability</button>	   
			   </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              </div>
          </div><!-- /.modal-content -->
       </div><!-- /.modal-dialog -->
     </div><!-- /.modal -->
	
	<div class="gallery-picture">
	   <center><img src="images/contact-us.png"></center>
	</div>
	
	<div class="container" style="font-family: Candara, Helvetica, sans-serif;">
	   <div class="row" style="border: 1px solid #c0c0c0; border-radius: 20px; background: #ECE9E7; padding: 30px;">
	     <div class="col-md-8">
		 	     
		 <h1>Contact Us</h1><br/>
		 <p>Thank you for your interest in Prestige Villa. To contact us, please fill the form below. Note that this is not a booking or reservations area, if you would like to book or make a reservation, please click the appropriate tab on the top of the page. We want to hear from you-about your experience while staying at our boutique hotel, about our website or anything else you would like to share with us. Please take a few minutes to send us your comments or questions. If you require immediate assistance, please call the appropriate telephone number on the right.</p><br/>
		 
	  <div class="container" style="border: 1px solid #c0c0c0; background: #F4F1EF; padding: 20px; width: 700px; margin-left: 0;">
	   <?php if (isset($contact_name) && isset($contact_email) && isset($contact_phone) && isset($comments)) {
      if ($f1 == true || $f2 == true || $f3 == true || $f4 == true) {
        echo '<p><b style="color: #FF0000;">Note: </b>All fields marked with <b style="font-size: 20px; color: #FF0000;">*</b> are required.</p>';
      }
    } ?>

	  <form action="contact.php" method="POST">
	     <label>Name <?php if (isset($contact_name)) {
                    if ($f1 == true) {
                      echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                    }
                  } ?></label><br/>
         <div class="row">
           <div class="col-xs-6">
             <input type="text" name="contact_name" class="form-control" placeholder="Enter Your Name" maxlength="25" required value="<?php if (isset($contact_name)) {
                                                                                                                                        echo $contact_name;
                                                                                                                                      } ?>">
           </div>
         </div>
         <br>
		 <label>Email <?php if (isset($contact_email)) {
                  if ($f2 == true) {
                    echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                  }
                } ?></label><br/>
         <div class="row">
           <div class="col-xs-6">
             <input type="email" name="contact_email" class="form-control" placeholder="Enter Your Email" maxlength="50" required value="<?php if (isset($contact_email)) {
                                                                                                                                          echo $contact_email;
                                                                                                                                        } ?>">
           </div>
         </div>
         <br>
		 <label>Phone Number <?php if (isset($contact_phone)) {
                        if ($f3 == true) {
                          echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                        }
                      } ?></label><br/>
         <div class="row">
           <div class="col-xs-6">
             <input type="text" name="contact_phone" onkeypress="return isNumberKey(event)" class="form-control" placeholder="Enter Yor Phone Number" maxlength="20" required value="<?php if (isset($contact_phone)) {
                                                                                                                                                                                      echo $contact_phone;
                                                                                                                                                                                    } ?>">
           </div>
         </div>
         <br>
		 <label>Comments Or Requests <?php if (isset($comments)) {
                                if ($f4 == true) {
                                  echo '<b style="font-size: 20px; color: #FF0000;">*</b>';
                                }
                              } ?></label><br/>
         <div class="row">
           <div class="col-xs-9">
             <textarea name="comments" class="form-control" rows="7" placeholder="What will you like to know?" maxlength="1000" required></textarea>
           </div>
         </div>
         <br>
		 <p><b style="color: #FF0000;">Please note:</b> Your contact information will only be used to reply to you regarding any request made using this form. If you have made a request we'll be in touch with you soon.</p>
		 <button type="submit" class="btn btn-primary" style="width: 100px">SUBMIT</button>
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