/**
 * Created by alexandrugutu on 8/22/16.
 */

$(document).ready(function() {
	$('.fancybox-media').fancybox({
		openEffect  : 'fade',
		closeEffect : 'none',
		helpers : {
			media : true
		}
	});
});

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
	$('#searchResults').html('');
	$('#navigationButtons').html('');

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

			// jQuery loop
			$.each(data.items, function(i, item) {
			// 	Build list of videos from data return
				var videoOutput = getOutput(item);

				// Display results into our results view
				$('#searchResults').append(videoOutput);
			});

			// Pure Javascript for loop
			// for (i = 0; i < data.items.length - 1; i++)
			// 	{
			// 		var videoOutput = getOutput(data.items[i]);
			//
			// 		$('#searchResults').append(videoOutput);
			// 	}

			var navButtons = getButtons(previousPageToken, nextPageToken);

			$('#navigationButtons').append(navButtons);

			$('.fancybox-media').fancybox({
				openEffect  : 'fade',
				closeEffect : 'none',
				helpers : {
					media : true
				}
			});
		}
	)
}

function nextPage() {
	var pageToken = $('#next-button').data('token');
	var query = $('#next-button').data('query');

	// Clear search bar
	$('#searchResults').html('');
	$('#navigationButtons').html('');

	// Get Form Input
	var query = $('#query').val();

	// GET Request on YouTube API
	$.get(
		'https://www.googleapis.com/youtube/v3/search', {
			part: 'snippet, id',
			q: query,
			pageToken: pageToken,
			type: 'video',
			key: 'AIzaSyBMpNHK_NMeU29WHxo_9r3E6IQB992vgN8'},
		function(data) {
			var nextPageToken = data.nextPageToken;
			var previousPageToken = data.prevPageToken;

			$.each(data.items, function(i, item) {
				// Build list of videos from data return
				var videoOutput = getOutput(item);

				// Display results into our results view
				$('#searchResults').append(videoOutput);
			});

			var navButtons = getButtons(previousPageToken, nextPageToken);

			$('#navigationButtons').append(navButtons);

			$('.fancybox-media').fancybox({
				openEffect  : 'fade',
				closeEffect : 'none',
				helpers : {
					media : true
				}
			});

		}
	)
}

function previousPage() {
	var pageToken = $('#prev-button').data('token');
	var query = $('#prev-button').data('query');

	// Clear search bar
	$('#searchResults').html('');
	$('#navigationButtons').html('');

	// Get Form Input
	var query = $('#query').val();

	// GET Request on YouTube API
	$.get(
		'https://www.googleapis.com/youtube/v3/search', {
			part: 'snippet, id',
			q: query,
			pageToken: pageToken,
			type: 'video',
			key: 'AIzaSyBMpNHK_NMeU29WHxo_9r3E6IQB992vgN8'},
		function(data) {
			var nextPageToken = data.nextPageToken;
			var previousPageToken = data.prevPageToken;

			$.each(data.items, function(i, item) {
				// Build list of videos from data return
				var videoOutput = getOutput(item);

				// Display results into our results view
				$('#searchResults').append(videoOutput);
			});

			var navButtons = getButtons(previousPageToken, nextPageToken);

			$('#navigationButtons').append(navButtons);

			$('.fancybox-media').fancybox({
				openEffect  : 'fade',
				closeEffect : 'none',
				helpers : {
					media : true
				}
			});
		}
	)
}

function getOutput(item) {
	var videoId = item.id.videoId;
	var videoTitle = item.snippet.title;
	var videoDesc = item.snippet.description;
	var videoThumbnail = item.snippet.thumbnails.high.url;
	var channelTitle = item.snippet.channelTitle;
	var videoDate = item.snippet.publishedAt;

	// Build Output String
	var output =
		'<li>' +
			'<div class="list-left">' +
				'<img src="' + videoThumbnail + '">' +
			'</div>' +
			'<div class="list-right">' +
				'<h3><a class="fancybox-media fancybox fancybox.iframe" href="https://www.youtube.com/embed/' + videoId + '">' + videoTitle + '</a></h3>' +
				'<small>By <span class="channelTitle">' + channelTitle + '</span> on ' + videoDate + '</small>' +
				'<p>' + videoDesc + '</p>' +
			'</div>' +
		'</li>' +
		'<div class="clearfix"></div>';

	return output;
}

function getButtons(prevPageTokens, nextPageTokens) {
	if(!prevPageTokens)
		{
			var buttonOutput =
				'<div class="button-container">' +
					'<button id="next-button" class="paging-button" data-token="' + nextPageTokens + '" data-query="' + query + '" onclick="nextPage();">Next Page</button>' +
				'</div>'
		}
	else
		{
			var buttonOutput =
				'<div class="button-container">' +
				'<button id="previous-button" class="paging-button" data-token="' + prevPageTokens + '" data-query="' + query + '" onclick="previousPage();">Previous Page</button>' +
				'<button id="next-button" class="paging-button" data-token="' + nextPageTokens + '" data-query="' + query + '" onclick="nextPage();">Next Page</button>' +
				'</div>'
		}

	return buttonOutput;
}

