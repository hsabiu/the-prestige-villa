<!DOCTYPE html>
<html>
  <head>
     <meta charset="utf-8">
     <meta http-equiv="X-UA-Compatible" content="IE=edge">
     <meta name="viewport" content="width=device-width, initial-scale=1">
     <meta name="description" content="">
     <meta name="author" content="">
	
     <title></title>
	 
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
 
  <div class="container" style="font-family: Candara, Helvetica, sans-serif;">
	<div class="row">
      <div class="col-md-8" style="border: 1px solid #c0c0c0; border-radius: 20px; color: #720D0D; padding: 10px; background: #F4F1EF; text-align: justify; margin-up: 20px; margin-bottom: 100px; margin-left: 200px; margin-right: 200px;">
	  <h1 style="color: #FF0000; text-align: justify;">Sorry! No room found</h1>
	   <p>Sorry, there is no available room of the type you chooose, but you can click the "Check Availability" button at the top of the reservation page to check for available rooms of other types. Also you can contact our hotel directly using the mobile numbers provided at the left side of each page to check for room availability due to last minute cancelation of reservation. Thank You.</p>
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