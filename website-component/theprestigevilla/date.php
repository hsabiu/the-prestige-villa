<?php
  $arrival = strtotime("2014-05-21");
  $departure = strtotime("2014-05-20");
  $datediff = abs($arrival - $departure);
  echo floor($datediff/(60*60*24));
?>