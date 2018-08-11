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
	
    <title>The Prestige Villa - Accomodations</title>
	
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
            <li><a href="index.php">HOME</a></li>
			<li class="active"><a href="#">ACCOMODATIONS</a></li>
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
	
	 <!-- Carousel
    ================================================== -->
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
        <div class="col-md-4">
		  <img src="images/diplomatic.png">
        </div>
        <div class="col-md-4">
		  <img src="images/executive_super.png">
       </div>
        <div class="col-md-4">
		  <img src="images/executive.png">
        </div>
      </div>
	  
	  <div class="row">
	    <div class="col-md-3">
		  <img src="images/small_pic1.png">
		  <img src="images/small_pic2.png">
		  <img src="images/small_pic3.png">
		  <img src="images/small_pic4.png">
        </div>
	    <div class="col-md-9" style="font-family: Candara, Helvetica, sans-serif; border: 1px solid #c0c0c0; border-radius: 20px; width: 860px;">
		  <h1>Our Accomodations</h1><br/>
		  <P>Here at <b style="color: #961B1B;">Prestige Villa</b>, we take pride in our accommodation and make sure that after you check out, your definition of luxury would change. Our rooms are spacious and well equipped with 24 hour room service, luxurious bathrooms, climate control, over 16 channels satellite digital TV, Fridge and complimentary tea/coffee items. Also, to further improve our security and efficiency of operation, our doors are now equipped with electronic locking systems which operate using key cards. With top class furniture created by our own standby furnishing factory, a choice of carpet, tiles or granite floors, and 24 hours electricity and water supply (via backup generators and storage tanks), you would never want to leave.</P><br/><br/><br/>
		  <table class="table table-bordered"> 
		     <tr>
                <th class="field-label col-md-2 active">Room Type</th>
                <th class="field-label col-md-4 active">Features</th> 
                <th class="field-label col-md-4 active">Basic Rate</th>
				<th class="field-label col-md-4 active">Deposit</th>
             </tr>
             <tr>
                <td>
                   <p>Executive Suite</p>
                </td>
                <td class="col-md-9">
                   <p>Well furnished and finished Room And Bathroom.</p>
                </td>
				<td class="col-md-9">
                   <p>&#8358 20,000</p>
                </td>
				<td class="col-md-9">
                   <p>&#8358 30,000</p>
                </td>
            </tr>
            <tr>
               <td>
                  <p>Super Executive Suite</p>
               </td>
               <td class="col-md-9">
                  <p>Large Room, well finished and furnished, living room and bathroom (Larger than the Executive Suite).</p>
               </td>
			   <td class="col-md-9">
                   <p>&#8358 25,000</p>
                </td>
				<td class="col-md-9">
                   <p>&#8358 35,000</p>
                </td>
           </tr>
           <tr>
              <td>
                <p>Diplomatic Suite</p>
              </td>
             <td class="col-md-9">
               <p>Two well finished and furnished Rooms with en suite bathrooms, kitchen, dinning room, and living room.</p>
             </td>
			 <td class="col-md-9">
                   <p>&#8358 37,000</p>
             </td>
			 <td class="col-md-9">
                   <p>&#8358 47,000</p>
             </td>
           </tr>
          </table><br/><br/><br/><br/>
        </div>
	  </div>
	  
	  <div class="footer">
        <p><b style="font-family: Bookman Old Style; font-size: 18px; font-weight: 200;"> CONNECT : &nbsp; </b>	<a><i class="fa fa-facebook"></i></a> &nbsp;&nbsp;<a><i class="fa fa-twitter"></i></a> &nbsp;&nbsp;<a><i class="fa fa-google-plus"></i></a>  &nbsp;&nbsp;<a><i class="fa fa-youtube"></i></a> &nbsp;&nbsp;<a><i class="fa fa-instagram"></i></a> &nbsp;&nbsp;<a><i class="fa fa-linkedin"></i></a> &nbsp;&nbsp;<a><i class="fa fa-painterest"></i></a> <b style="font-family: Californian FB; font-size: 14px; font-weight: 200; color: #9A8383; float: right;"><a href="reservation.php">Reservations</a> | <a href="#">Contact</a> | <a href="facilities.php">Facilities</a> | <a href="gallery.php">Gallery</a></b></p>
      </div>
	  
	  <center class="footerLogo"><img src="images/footerLogo.png"><br/>
	    <b style="font-family: Bookman Old Style; font-size: 11px; font-weight: 200; color: #9A8383;">&copy; The Prestige Villa 2014. All Rights Reserved</b>
	  </center>
	</div>
	
  </body>
</html>
