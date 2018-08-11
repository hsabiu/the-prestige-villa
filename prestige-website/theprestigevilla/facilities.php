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
?>

<!DOCTYPE html>
<html>
  <head>
     <meta charset="utf-8">
     <meta http-equiv="X-UA-Compatible" content="IE=edge">
     <meta name="viewport" content="width=device-width, initial-scale=1">
     <meta name="description" content="">
     <meta name="author" content="">
	
     <title>The Prestige Villa - Facilities</title>
	 
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
			
		 $( "#datepicker" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
		 $( "#datepicker" ).datepicker({ showAnim: "drop" });
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
            <li class="active"><a href="#">FACILITIES</a></li>
            <li><a href="gallery.php">GALLERY</a></li>			
			<li><a href="contact.php">CONTACT</a></li>
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
	   <center><img src="images/facilities.png"></center>
	</div>
	
	<div class="container" style="font-family: Candara, Helvetica, sans-serif;">
	   <div class="row" style="border: 1px solid #c0c0c0; border-radius: 20px; background: #ECE9E7;">
	   <h1 style="font-family: Vivaldi, Helvetica, sans-serif; font-size: 70px; font-weight: 200; color: #961B1B;">&nbsp;&nbsp;Our Facilities</h1><br/>
	    <div class="col-md-5">
	      <p>Although we are primarily known for having the most luxurious and comfortable rooms at exceedingly good rates, we also have many facilities that make your stay worthwhile; our In-room and reception wireless internet access ensure that you access the world wide web at unimaginable speed. Our own travel agency can handle all your bookings, luggage and airport clearance. With our very classy restaurant, offering world class cuisines and buffets, you are guaranteed great meals all round. Our laundry services offer you the best using the latest technology in fabric care.</p>
		  <h3>Our Facilities includes:</h3>
		  <ul>
		    <li>Meals</li></br>
			<li>Gym</li></br>
			<li>Laundry</li></br>
		  </ul>
		</div>
		<div class="col-md-7">
		  <img src="images/drinks.png">
		</div>
        <div class="col-md-4">
          <h2>Meals</h2>
		  <img src="images/facilities-1.png">
		  <h4>Refresh Every Moment of your stay</h4>
          <p>Pamper your palates with our wide range of local and international cuisines delivered to your room on request. You also have free beverages in your room. </p>
        </div>
        <div class="col-md-4">
          <h2>Gym</h2>
		  <img src="images/facilities-2.png">
		  <h4>Stay Fit Everytime</h4>
          <p>Our modern gym facilities lets you stay fit everytime even when you are away from home. Here at The Prestige Villa it's always our goal to let you be at 'Home' while you are away from 'Home'. </p><br/>
       </div>
        <div class="col-md-4">
          <h2>Laundry</h2>
		  <img src="images/facilities-3.png">
		  <h4>Your Cloths Are Always Clean</h4>
          <p>Our laundry services offer you the best using the latest technology in fabric care. We'll make sure your cloths are always clean while you stay with us; Remember you are in a 'Home Away From Home'.</p>
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
