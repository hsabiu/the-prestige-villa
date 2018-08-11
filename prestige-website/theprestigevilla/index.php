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
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>The Prestige Villa - Home</title>


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
	</script>
 	 
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- My style-->
    <link href="css/style.css" rel="stylesheet">
	
    <!-- Font Awesome style -->  
    <link href="css/font-awesome.css" rel="stylesheet">
		
  </head>
  
  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
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
            <li class="active"><a href="#">HOME</a></li>
			<li><a href="accomodation.php">ACCOMODATIONS</a></li>
            <li><a href="reservation.php">RESERVATIONS</a></li>
            <li><a href="facilities.php">FACILITIES</a></li>
            <li><a href="gallery.php">GALLERY</a></li>			
			<li><a href="contact.php">CONTACT</a></li>
          </ul>
		  <ul class="nav navbar-nav navbar-right">
			<li><a class="btn btn-lg btn-primary" data-toggle="modal" data-target="#myModal" style="width:200px;">Check Availability</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
	
	 <!-- Carousel -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
		<li data-target="#myCarousel" data-slide-to="3"></li>
      </ol>
      <div class="carousel-inner">
        <div class="item active">
          <img src="images/image1.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>The Best Place To Stay</h1>
              <p>The Prestige Villa is with no doubt the best place to spend the night while you are in Kano. We have fully qualified staffs who are always at your services, we offer the best rooms and food in town.</p>
              <center><a class="btn btn-lg btn-primary" data-toggle="modal" data-target="#myModal">Book Now</a></center>
            </div>
          </div>
        </div>
        <div class="item">
          <img src="images/image2.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption" style="  height: 300px; width: 400px;">
              <h1>Fully Equiped Rooms</h1>
              <p>The Prestige Villa offers guest with ensuite and carefully designed rooms, with air condition, wireless internet access, latest LED flat screen TV's.</p>
              <center><a class="btn btn-lg btn-primary" data-toggle="modal" data-target="#myModal">Book Now</a></center>
            </div>
          </div>
        </div>
        <div class="item">
          <img src="images/image3.jpg" alt="Third slide">
        </div>
		<div class="item">
          <img src="images/image4.jpg" alt="Third slide">
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
    </div><!-- /.carousel -->
	
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
    
	<div class="container" style="font-family: Candara, Helvetica, sans-serif;">
	    <div class="row">
		   <div class="col-md-8">
		       <center><h2 style="font-family: Vivaldi, Helvetica, sans-serif; font-size: 40px; font-weight: 200; color: #961B1B;">The Prestige Villa Welcomes You</h2></center>
			   <img src="images/welcome.png">
			   <p>The exquisite <b style="color: #961B1B;">prestige villa</b>, situated in the government residential area of Nassarawa GRA Kano offers guest a superb accomodation in a tranquil and safe environment. Acclaimed to be one of the best guest houses in Kano, it's a "Home Away From Home".</p>
		       <p><b style="color: #961B1B;">The Prestige Villa</b> offers the discerning traveller with air conditioned ensuite and carefully designed rooms, ranging from super executive to diplomatic suites, with complementary wireless internet access, working deck, latest LED flat screen TV's with over 16 satelite chennels.</P>
			   <p>In addition, our bathrooms are designed to give you a state-of-the-art spa experience and luxury at your service. Our beds and bedding are made from the finest fabrics to ensure your maximum comfort.</P>
		       <h3>Attractions</h3>
			   <ul>
			     <li>Walking distance from the Kano state Government House</li><br/>
			     <li>5 minutes drive to Kano club Golf Course</li><br/>
			     <li>10 minutes drive to the zoo/children amusement park</li><br/>
			     <li>10 minutes drive to the Emir's Palace</li><br/>
			     <li>10 minutes drive to the dyeing pit of kano</li><br/>
			   </ul>
		  </div>
		   <br/><br/><br/>
		   <div class="col-md-4" style="border: 1px solid #c0c0c0; border-radius: 20px; color: #720D0D; background: #F4F1EF;">
		       <h3>Special Features</h3>
			     <ul>
				   <li>Flat screen TV with numerous satellite TV stations</li>
				   <li>Jacuzzi in bathrooms</li>
				   <li>FREE drinks and beverages in rooms</li>
				   <li>Free 24-hours Internet service</li>
				   <li>24 hours Kitchen service</li>
				   <li>Laundry Service</li>
				 </ul>
		       <h3>Location</h3>
			   <p>No. 34a Sokoto Road, Kano, <br/> 
			   Kano State, Nigeria.<br/><br/>
			   <img src="images/map.jpg" style="padding-left: 0px; padding-right: 0px; border: 1px solid #c0c0c0;">
			   <h4>Call us on:</h4>
			   +2348051256120<br/>
			   +2348066127299<br/>
			   +2348026211251<br><br/>
			   <a href="mailto:habibado2006@gmail.com">Email Us &raquo;</a></p><br/><br/>
		   </div>
		</div>
	</div>
	
	<div class="container" style="font-family: Candara, Helvetica, sans-serif;">
      <div class="row">
        <div class="col-md-4">
          <h3>Diplomatic Suite</h3>
		  <img src="images/diplomatic.png">
          <p><ul>
		     <li>Two well finished and furnished Rooms</li>
			 <li>En suite bathrooms</li>
			 <li>kitchen</li>
			 <li>dinning room</li>
			 <li>living room</li>
		  </ul></p>
          <p><a class="btn btn-default" data-toggle="modal" data-target="#myModal">Book Now @ &#8358 37,000 &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h3>Super Executive Suite</h3>
		  <img src="images/executive_super.png">
          <p><ul>
		     <li>Large Room</li>
		     <li>Well finished and furnished</li>
			 <li>living room</li>
			 <li>Bathroom</li>
		  </ul></p><br/>
          <p><a class="btn btn-default" data-toggle="modal" data-target="#myModal">Book Now @ &#8358 25,000 &raquo;</a></p>
       </div>
        <div class="col-md-4">
          <h3>Executive Suite</h3>
		  <img src="images/executive.png">
          <p><ul>
		     <li>Well finished and furnished Room</li>
			 <li>Bathroom</li>
		  </ul></p><br/><br/><br/>
          <p><a class="btn btn-default" data-toggle="modal" data-target="#myModal">Book Now @ &#8358 20,000 &raquo;</a></p>
        </div>
      </div>
	
      <div class="footer">
        <p><b style="font-family: Bookman Old Style; font-size: 18px; font-weight: 200;"> CONNECT : &nbsp; </b>	<a><i class="fa fa-facebook"></i></a> &nbsp;&nbsp;<a><i class="fa fa-twitter"></i></a> &nbsp;&nbsp;<a><i class="fa fa-google-plus"></i></a>  &nbsp;&nbsp;<a><i class="fa fa-youtube"></i></a> &nbsp;&nbsp;<a><i class="fa fa-instagram"></i></a> &nbsp;&nbsp;<a><i class="fa fa-linkedin"></i></a> &nbsp;&nbsp;<a><i class="fa fa-painterest"></i></a> <b style="font-family: Californian FB; font-size: 14px; font-weight: 200; color: #9A8383; float: right;"><a href="reservation.php">Reservations</a> | <a href="#">Contact</a> | <a href="facilities.php">Facilities</a> | <a href="gallery.php">Gallery</a></b></p>
	  </div>
	  
	 <center> 
	  <div class="footerLogo"><img src="images/footerLogo.png"><br/>
	    <b style="font-family: Bookman Old Style; font-size: 11px; font-weight: 200; color: #9A8383;">&copy; The Prestige Villa 2014. All Rights Reserved</b>
	  </div>
	 </center>
    </div> <!-- /container -->

  </body>
</html>
