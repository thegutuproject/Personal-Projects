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
			var response = data.split(";");
			$('#asin').val(response[0].trim());
			$('#title').val(response[1].trim());
			$('#mpn').val(response[2].trim());
			$('#price').val(response[3].trim());
			$('.databaseView').load('connection.php');
		}
	});
});
