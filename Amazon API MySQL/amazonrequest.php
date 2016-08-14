<?php

require ("credentials.php");

$method = "GET";
$host = "webservices.amazon.com";
$uri = "/onca/xml";

$param['AWSAccessKeyId']		= public_key;
$param['AssociateTag']			= associate_key;
$param['Operation']				= 'ItemLookup';
$param['ItemId']				= '0679722769';
$param['ResponseGroup']			= 'ItemAttributes,Offers,Images,Reviews';
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

if ($xml_response === False)
{
	return False;
}
else
{
	/* parse XML */
	$parsed_xml = @simplexml_load_string($xml_response);
	print_r($parsed_xml);
//	return ($parsed_xml === False) ? False : $parsed_xml;
}

?>
