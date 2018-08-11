<?php
session_start();
$arrival = $_SESSION['arrival'];
$departure = $_SESSION['departure'];
$room_type = $_SESSION['room_type'];
$room_prize = $_SESSION['room_prize'];
$total_prize = $_SESSION['total_prize'];
$resNo = $_SESSION['resNo'];
$no_days = $_SESSION['no_days'];

session_destroy();

?>

<!DOCTYPE HTML>
<html>
 <head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <meta name="description" content="">
   <meta name="author" content="">
   
   <script src="js/bootstrap.min.js"></script>
   
   <!-- Bootstrap core CSS -->
   <link href="css/bootstrap.min.css" rel="stylesheet">
   
   <!-- My style-->
   <link href="css/style.css" rel="stylesheet">
   
    <!-- Font Awesome style -->  
    <link href="css/font-awesome.css" rel="stylesheet">
	
 </head>
 
 <body>
 
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
	
  <div class="container" style="font-family: Candara, Helvetica, sans-serif;">
	<div class="row">
      <div class="col-md-8" style="border: 1px solid #c0c0c0; border-radius: 20px; color: #720D0D; padding: 10px; background: #F4F1EF; text-align: justify; margin-up: 20px; margin-bottom: 100px; margin-left: 200px; margin-right: 200px;">
	  <h1 style="color: #FF0000; text-align: justify;">Reservation successful</h1>
	  <p>Congratulations, your reservation has been successfully created. Looking foward to see you in our Hotel. Below is the details for your reservaiton, a copy of this has been sent to your email address for refrence. Hope you will enjoy staying with us, Thank You!</p>
	  <ul>
	    <li><b>Reservation No.:</b> &nbsp; <?php echo $resNo; ?></li><br/>
		<li><b>Arrival Date:</b> &nbsp; <?php echo $arrival; ?></li><br/>
		<li><b>Departure Date:</b> &nbsp; <?php echo $departure; ?></li><br/>
		<li><b>Room Type:</b> &nbsp; <?php echo $room_type ?></li><br/>
		<li><b>Prize/Night:</b> &nbsp; &#8358 <?php echo $room_prize ?></li><br/>
		<li><b>No. of days:</b> &nbsp; <?php echo $no_days ?></li><br/>
		<li><b>Total Prize:</b> &nbsp; &#8358 <?php echo $total_prize ?></li><br/>
	   </ul>
	 </div>
   </div>
  </div>

      <div class="footer">
        <p><b style="font-family: Bookman Old Style; font-size: 18px; font-weight: 200;"> CONNECT : &nbsp; </b>	<a><i class="fa fa-facebook"></i></a> &nbsp;&nbsp;<a><i class="fa fa-twitter"></i></a> &nbsp;&nbsp;<a><i class="fa fa-google-plus"></i></a>  &nbsp;&nbsp;<a><i class="fa fa-youtube"></i></a> &nbsp;&nbsp;<a><i class="fa fa-instagram"></i></a> &nbsp;&nbsp;<a><i class="fa fa-linkedin"></i></a> &nbsp;&nbsp;<a><i class="fa fa-painterest"></i></a> <b style="font-family: Californian FB; font-size: 14px; font-weight: 200; color: #9A8383; float: right;"><a href="reservation.php">Reservations</a> | <a href="#">Contact</a> | <a href="facilities.php">Facilities</a> | <a href="gallery.php">Gallery</a></b></p>
      </div>
	  
	 <center> 
	  <div class="footerLogo"><img src="images/footerLogo.png"><br/>
	    <b style="font-family: Bookman Old Style; font-size: 11px; font-weight: 200; color: #9A8383;">&copy; The Prestige Villa 2014. All Rights Reserved</b>
	  </div></center>
    </div> <!-- /container -->

  </body>
</html>