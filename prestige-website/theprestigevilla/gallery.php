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
	
     <title>The Prestige Villa - Gallaries</title>
	 
	 <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
	 
	 <script src="js/jquery-1.10.2.js"></script>
	 <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	 <script src="js/jquery.poptrox.min.js"></script>
	 <script src="js/bootstrap.min.js"></script>
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

	 <script>
	  $(function() {
		var foo = $('#gallery');
		foo.poptrox({
		  fadeSpeed: 400, 
		  popupSpeed: 400,
          popupTextColor: '#B7975F', 
		  popupCloserBackgroundColor: '#B7975F',
		  usePopupCaption: true,
		  usePopupNav: true,
		  popupIsFixed: true,
		  popupWidth: 620,
          popupHeight: 480
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
            <li class="active"><a href="#">GALLERY</a></li>			
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
	   <center><img src="images/gallery-img.png"></center>
	</div>
	
	<div class="container">
	   <div class="row" style="border: 1px solid #c0c0c0; border-radius: 20px; background: #ECE9E7;">
	     <div class="col-md-12">
		    <div id="gallery">
			  <h1 style="font-family: Vivaldi, Helvetica, sans-serif; font-size: 70px; font-weight: 200; color: #961B1B;">Picture Gallery</h1><br/>
			  
			  <h3 style="font-family: Vivaldi, Helvetica, sans-serif; font-size: 40px; font-weight: 200; color: #961B1B;">&nbsp;&nbsp;The Hotel</H3>
			  <!-- Regular images -->
			  <a href="images/gallery_1.jpg"><img src="images/gallery_1_thumb.jpg" alt="" title="Diplomatic Suite" /></a>		
			  <a href="images/gallery_2.jpg"><img src="images/gallery_2_thumb.jpg" alt="" title="Super Executive Suite" /></a>	
			  <a href="images/gallery_3.jpg"><img src="images/gallery_3_thumb.jpg" alt="" title="Executive Suite" /></a>	
			  <a href="images/gallery_4.jpg"><img src="images/gallery_4_thumb.jpg" alt="" title="Super Executive Suite Living Room" /></a>	
			  <a href="images/gallery_5.jpg"><img src="images/gallery_5_thumb.jpg" alt="" title="Super Executive Suite Living Room" /></a>	
			  <a href="images/gallery_6.png"><img src="images/gallery_6_thumb.png" alt="" title="Executive Suite Living Room" /></a>	
			  <a href="images/gallery_7.jpg"><img src="images/gallery_7_thumb.jpg" alt="" title="Diplomatic Suite Toilet" /></a>	
			  <a href="images/gallery_8.jpg"><img src="images/gallery_8_thumb.jpg" alt="" title="Diplomatic Suite" /></a>	
			  <a href="images/gallery_9.jpg"><img src="images/gallery_9_thumb.jpg" alt="" title="Super Executive Working deck" /></a>	
			  <a href="images/gallery_10.jpg"><img src="images/gallery_10_thumb.jpg" alt="" title="Executive Suite Toilet" /></a>	
			  <a href="images/gallery_11.jpg"><img src="images/gallery_11_thumb.jpg" alt="" title="Executive Suite Toilet" /></a>	
			  <a href="images/gallery_12.jpg"><img src="images/gallery_12_thumb.jpg" alt="" title="Corridor" /></a>	
			  <a href="images/gallery_13.jpg"><img src="images/gallery_13_thumb.jpg" alt="" title="Super Executive Suite Toilet" /></a>	
			  <a href="images/gallery_14.jpg"><img src="images/gallery_14_thumb.jpg" alt="" title="Super Executive Suite Toilet" /></a>	
			  <a href="images/gallery_15.jpg"><img src="images/gallery_15_thumb.jpg" alt="" title="Super Executive Suite Toilet" /></a>	
			  <a href="images/gallery_16.jpg"><img src="images/gallery_16_thumb.jpg" alt="" title="Super Executive Suite Living Room" /></a>	
			  <a href="images/gallery_17.jpg"><img src="images/gallery_17_thumb.jpg" alt="" title="Main Entrance" /></a>	
			  <a href="images/gallery_18.jpg"><img src="images/gallery_18_thumb.jpg" alt="" title="Super Executive Suite" /></a>	
			  <a href="images/gallery_19.jpg"><img src="images/gallery_19_thumb.jpg" alt="" title="Super Executive Suite Working Deck" /></a>	
			  <a href="images/gallery_20.jpg"><img src="images/gallery_20_thumb.jpg" alt="" title="Side View" /></a>	
			  <a href="images/gallery_21.jpg"><img src="images/gallery_21_thumb.jpg" alt="" title="Super Executive Suite Toilet" /></a>	
			  <a href="images/gallery_22.jpg"><img src="images/gallery_22_thumb.jpg" alt="" title="Super Executive Suite Toilet" /></a>	
			  <a href="images/gallery_23.jpg"><img src="images/gallery_23_thumb.jpg" alt="" title="Reception" /></a>	
			  <a href="images/gallery_24.jpg"><img src="images/gallery_24_thumb.jpg" alt="" title="Chandelier" /></a>	
			  
			  <br/></br><br/>
			  
			  <h3 style="font-family: Vivaldi, Helvetica, sans-serif; font-size: 40px; font-weight: 200; color: #961B1B;">&nbsp;&nbsp;Dining</h3>
			  <!-- Regular images -->
			  <a href="images/food1.jpg"><img src="images/food1_thumb.jpg" alt="" title="Nigerian Soup" /></a>		
			  <a href="images/food2.jpg"><img src="images/food2_thumb.jpg" alt="" title="Fried Rice" /></a>	
			  <a href="images/food3.jpg"><img src="images/food3_thumb.jpg" alt="" title="Salad With Chiken" /></a>	
			  <a href="images/food4.jpg"><img src="images/food4_thumb.jpg" alt="" title="Salad" /></a>		
			  <a href="images/food5.jpg"><img src="images/food5_thumb.jpg" alt="" title="Rice With Chiken" /></a>	
			  <a href="images/food6.jpg"><img src="images/food6_thumb.jpg" alt="" title="Breakfast" /></a>
			  <a href="images/food7.jpg"><img src="images/food7_thumb.jpg" alt="" title="Egusi Soup" /></a>		
			  <a href="images/food8.jpg"><img src="images/food8_thumb.jpg" alt="" title="Rice With Chips" /></a>	
			  <a href="images/food9.png"><img src="images/food9_thumb.png" alt="" title="Crab" /></a>
			  <a href="images/food10.jpg"><img src="images/food10_thumb.jpg" alt="" title="Paw Paw Drink" /></a>
			  <a href="images/food11.jpg"><img src="images/food11_thumb.jpg" alt="" title="Breakfast" /></a>		
			  <a href="images/food12.jpg"><img src="images/food12_thumb.jpg" alt="" title="Beep" /></a>	
			  <a href="images/food13.jpg"><img src="images/food13_thumb.jpg" alt="" title="Beep" /></a>	
			  <a href="images/food14.jpg"><img src="images/food14_thumb.jpg" alt="" title="Egg Soup With Plantain" /></a>		
			  <a href="images/food15.jpg"><img src="images/food15_thumb.jpg" alt="" title="Milk Shake" /></a>	
			  <a href="images/food16.jpg"><img src="images/food16_thumb.jpg" alt="" title="Rice With Fish" /></a>
			  <a href="images/food17.jpg"><img src="images/food17_thumb.jpg" alt="" title="Pounded Yam With Egusi Soup" /></a>		
			  <a href="images/food18.jpg"><img src="images/food18_thumb.jpg" alt="" title="Shawarma" /></a>	
			  <a href="images/food19.jpg"><img src="images/food19_thumb.jpg" alt="" title="Doughnut" /></a>		
			  <a href="images/food20.jpg"><img src="images/food20_thumb.jpg" alt="" title="Milk Shake" /></a>
			  
			</div>
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
