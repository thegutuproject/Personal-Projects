$(document).ready(function(){
		$('.databaseView').load('connection.php');
});

$('.addToDatabase').click(function(){
	$.ajax({
		type: 'GET',
		url: 'connection.php',
		data: {
			operation: 'add',
			asin: $('#asin').val(),
			title: $('#title').val(),
			mpn: $('#mpn').val(),
			price: $('#price').val()
		},
		success:function(){
			// successful request; do something with the data
			$('.databaseView').load('connection.php');
		}
	});
});

$('.searchAmazon').click(function(){
	$.ajax({
		type: 'GET',
		url: 'connection.php',
		data: {
			operation: 'search',
			searchASIN: $('#searchASIN').val()
		},
		success:function(data){
			// JSON parsing from PHP -- Unsure why I need to use .asin[] .title[] etc,
			// wanted to do amazonResponse.asin = asin, amazonResponse.title = title, etc...
			var amazonResponse = data;
			$('#asin').val(amazonResponse.asin[0]);
			$('#title').val(amazonResponse.title[0]);
			$('#mpn').val(amazonResponse.mpn[0]);
			$('#price').val(amazonResponse.price[0]);
			$('.databaseView').load('connection.php');
		}
	});
});
