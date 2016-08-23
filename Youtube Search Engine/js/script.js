/**
 * Created by alexandrugutu on 8/22/16.
 */

$(function() {
	var searchField = $('#query');
	var icon = $('#search-btn');

	$(searchField).on('focus', function() {
		$(this).animate({
			width: '100%'
		}, 400);
		$(icon).animate({
			right: '10px'
		}, 400);
	});

	// Blur Event Handler

	$(searchField).on('blur', function() {
		if(searchField.val() == '')
			{
				$(searchField).animate({
					width: '45%'
				}, 400, function(){});
				$(icon).animate({
					right: '360px'
				}, 400, function(){});
			}
	});

	$('#search-form').submit(function(e) {
		e.preventDefault();
	});
});

function search() {
	// Clear search bar
	$('#results').html('');
	$('#buttons').html('');

	// Get Form Input
	var query = $('#query').val();

	// GET Request on YouTube API
	$.get(
		'https://www.googleapis.com/youtube/v3/search', {
			part: 'snippet, id',
			q: query,
			type: 'video',
			key: 'AIzaSyBMpNHK_NMeU29WHxo_9r3E6IQB992vgN8'},
		function(data) {
			var nextPageToken = data.nextPageToken;
			var previousPageToken = data.prevPageToken;

			console.log(data);

		}
	)
}