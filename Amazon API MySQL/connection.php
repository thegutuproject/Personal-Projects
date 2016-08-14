<?php
    require ("credentials.php");

	global $connection;
	$connection = mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD);

	if (!$connection) {
		die('Could not connect: ' . mysqli_error($con));
	}

	mysqli_select_db($connection,DB_DATABASE);

	if (isset($_GET['operation']))
	{
		$operation = trim($_GET['operation']);
	}
//	else
//	{
		$sql="SELECT * FROM items";
		$result = mysqli_query($connection, $sql);

		echo "<!DOCTYPE html>";
		echo "<html>";
		echo "<head>";
		echo "<style>";
		echo "table {";
		echo "width: 100%;";
		echo "border-collapse: collapse;";
		echo "}";
		echo "table, td, th {";
		echo "border: 1px solid black;";
		echo "padding: 5px;";
		echo "}";
		echo "th {text-align: left;}";
		echo "</style>";
		echo "</head>";
		echo "<body>";
		echo "<table>";
		echo "<tr>";
		echo "<th>ASIN</th>";
		echo "<th>Title</th>";
		echo "<th>MPN</th>";
		echo "<th>Price</th>";
		echo "</tr>";

		while($row = mysqli_fetch_array($result)) {
			echo "<td>" . $row['ASIN'] . "</td>";
			echo "<td>" . $row['Title'] . "</td>";
			echo "<td>" . $row['MPN'] . "</td>";
			echo "<td>" . $row['Price'] . "</td>";
			echo "</tr>";
		}
		echo "</table>";

		echo "</body>";
		echo "</html>";
//	}

	if ($operation == "add")
	{
		$asin = trim($_GET['asin']);
		$title = trim($_GET['title']);
		$mpn = trim($_GET['mpn']);
		$price = trim($_GET['price']);

		$sql = "INSERT INTO items (ASIN, Title, MPN, Price) VALUES ('" . $asin . "', '" . $title . "', '" . $mpn . "', " . $price . ");";

		$result = 	mysqli_query($connection, $sql);

		mysqli_close($connection);
	}


	if ($operation == "search")
	{
		$method = "GET";
		$host = "webservices.amazon.com";
		$uri = "/onca/xml";

		$param['AWSAccessKeyId']		= public_key;
		$param['AssociateTag']			= associate_key;
		$param['Operation']				= 'ItemLookup';
		$param['ItemId']				= $_GET['searchASIN'];
		$param['ResponseGroup']			= 'ItemAttributes';
		$param["Timestamp"]             = gmdate("Y-m-d\TH:i:s\Z");
		$param['Version']				= '2013-08-01';
		$param['Service']				= 'AWSECommerceService';

		ksort($param);

		$parameter_url = array();

		foreach ($param as $key => $value){

			$key = str_replace("%7E", "~", rawurlencode($key));
			$value = str_replace("%7E", "~", rawurlencode($value));

			$parameter_url[] = $key . "=" . $value;
		}

		$parameter_url = implode('&', $parameter_url);

		$string_to_sign = $method . "\n" . $host . "\n" . $uri . "\n" . $parameter_url;

		$signature = base64_encode(hash_hmac("sha256", $string_to_sign, private_key, True));
		$signature = str_replace("%7E", "~", rawurlencode($signature));

		$link = "http://" . $host . $uri . "?" . $parameter_url . "&Signature=" . $signature;

		$xml_response = file_get_contents($link);

		if ($xml_response !== False)
		{
			/* parse XML */
			$parsed_xml = @simplexml_load_string($xml_response);

			$asin =  $parsed_xml -> Items[0] -> Item[0] -> ASIN;
			$title =  $parsed_xml -> Items[0] -> Item[0] -> ItemAttributes[0] -> Title;
			$mpn =  $parsed_xml -> Items[0] -> Item[0] -> ItemAttributes[0] -> MPN;
			$price =  $parsed_xml -> Items[0] -> Item[0] -> ItemAttributes[0] -> ListPrice[0] -> FormattedPrice;

			$result =  $asin . ';' . $title . ';' . $mpn . ';' . $price;
		}

		echo trim($result);
	}
?>
