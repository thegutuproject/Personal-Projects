/**
 * Created by alexandrugutu on 9/1/16.
 */

var startButton = document.getElementById('start-button');

// Failed attempt at implementing filters... :(

startButton.addEventListener('click', function() {
//
// 	var tagParameter = document.getElementById('pictureTags').value;
// 	var tag_mode;
//
// 	var radios = document.getElementsByName('tag_mode');
// 	for (var i = 0; i < radios.length; i++) {
// 		if (radios[i].checked) {
// 			tag_mode = radios[i].value;
// 		}
// 		else {
// 			tag_mode = '';
// 		}
// 	}
//
// 	var isGeotagged = document.getElementById('isGeotagged').value;
//
// 	var extraParameters = tagParameter
//
	getPhotos();
});

// Function dedicated to encoding URL parameters to pass to Flickr

function buildURL(url, data) {
	var queryString = "";
	for (var parameter in data) {
		var value = data[parameter];
		queryString += encodeURIComponent(parameter) + "=" + encodeURIComponent(value) + "&";
	}
	if (queryString.length > 0) {
		queryString = queryString.substring(0, queryString.length-1); //chop off last "&"
		url = url + "?" + queryString;
	}

	return url;
}

// Initial call to get photos from Flickr

function getPhotos(currentPage) {

	var photoRequest = new XMLHttpRequest();

	if (typeof currentPage !== 'undefined')
		{
			var data = {
				method: 'flickr.people.getPublicPhotos',
				api_key: 'dadeb42528363ca20ca630ea9e800129',
				user_id: '24662369@N07',
				format: 'json',
				nojsoncallback: 1,
				per_page: 25,
				page: currentPage,
				extras: 'owner_name, date_taken, description'
			};
		}
	else
		{
			var data = {
				method: 'flickr.people.getPublicPhotos',
				api_key: 'dadeb42528363ca20ca630ea9e800129',
				user_id: '24662369@N07',
				format: 'json',
				nojsoncallback: 1,
				per_page: 25,
				extras: 'owner_name, date_taken, description'
			};
		}

	var url = buildURL('https://api.flickr.com/services/rest/', data);

	photoRequest.open('GET', url, true);

	photoRequest.onreadystatechange = function() {
		if (photoRequest.readyState === 4 && photoRequest.status === 200) {
			var photos = JSON.parse(photoRequest.response).photos;
			processInput(photos);
			createButtonHTML(photos.page, photos.pages);
		}
		else if (photoRequest.readyState === 4 && photoRequest.status === 400) {
			console.log("Improper Request");
		}

	};

	photoRequest.send(null);
}

// Loops through all images and makes an AJAX call to get sizes.

function processInput(photos) {

	// for (var i = 0; i < photos.photo.length; i++)
	for (var index in photos.photo)
		{
			photos.photo[index].size = getSize(photos, index);
		}
}

// Appends the size files to the photo JSON object.

function getSize(photos, index) {

	var photoSizeRequest = new XMLHttpRequest();

	var data = {
		method: 'flickr.photos.getSizes',
		api_key: 'dadeb42528363ca20ca630ea9e800129',
		photo_id: photos.photo[index].id,
		format: 'json',
		nojsoncallback: 1
	};

	var url = buildURL('https://api.flickr.com/services/rest/', data);

	photoSizeRequest.open('GET', url, true);

	photoSizeRequest.onreadystatechange = function() {
		if (photoSizeRequest.readyState === 4 && photoSizeRequest.status === 200) {
			photos.photo[index].size = JSON.parse(photoSizeRequest.responseText).sizes.size;
			createHTML(photos.photo[index]);
		}
		else if (photoSizeRequest.readyState === 4 && photoSizeRequest.status === 400) {
			console.log("Improper Request");
		}

	};

	photoSizeRequest.send(null);
}

// Function to create HTML objects. This needs major re-organizing, but I haven't figured out an efficient pattern.

function createHTML(photo) {

	// All "containers" so to speak
	var liItem = document.createElement('li');
	var leftDiv = document.createElement('div');
	var rightDiv = document.createElement('div');
	var clearFixDiv = document.createElement('div');
	var imageSizesDiv = document.createElement('div');
	var descriptionDiv = document.createElement('div');

	var previewImage = document.createElement('img');
	var imageTitle = document.createElement('h3');
	var imageTitleLink = document.createElement('a');
	var descriptionSpan = document.createElement('span');

	var credits = document.createElement('small');
	var photographer = document.createElement('span');
	var photoSizeList = document.createElement('ul');
	var dateTaken = document.createElement('span');
	var strongerDate = document.createElement('strong');


	// Mess, needs restructuring
	// section to add properties to the various containers
	leftDiv.setAttribute('class', 'list-left');
	rightDiv.setAttribute('class', 'list-right');
	previewImage.setAttribute('src', photo.size[1].source);
	clearFixDiv.setAttribute('class', 'clearfix');
	imageSizesDiv.setAttribute('class', 'photo-sizes');
	imageTitleLink.setAttribute('href', photo.size[1].source);
	descriptionDiv.setAttribute('id', 'photo-description');
	dateTaken.innerHTML = ' On ';
	strongerDate.innerHTML = photo.datetaken;

	imageTitleLink.innerHTML = photo.title;
	credits.innerHTML = 'By ';

	photographer.setAttribute('class', 'photographer');
	photographer.innerHTML = photo.ownername;

	descriptionSpan.innerHTML = photo.description._content;

	photoSizeList.setAttribute('id', 'photoSizes quad');

	for (var i = 0; i < photo.size.length; i++) {
		var imageSizeLinkList = document.createElement('li');
		var imageSizeLink = document.createElement('a');
		imageSizeLink.setAttribute('href', photo.size[i].source);
		imageSizeLink.innerHTML = photo.size[i].label;

		imageSizeLinkList.appendChild(imageSizeLink);
		photoSizeList.appendChild(imageSizeLinkList);
	}

	// Construction Begins
	dateTaken.appendChild(strongerDate);
	credits.appendChild(photographer);
	credits.appendChild(dateTaken);
	imageTitle.appendChild(imageTitleLink);
	imageSizesDiv.appendChild(photoSizeList);
	descriptionDiv.appendChild(descriptionSpan);

	rightDiv.appendChild(imageTitle);
	rightDiv.appendChild(credits);
	rightDiv.appendChild(descriptionDiv);
	rightDiv.appendChild(imageSizesDiv);

	leftDiv.appendChild(previewImage);

	liItem.appendChild(leftDiv);
	liItem.appendChild(rightDiv);

	document.getElementById('search-results').appendChild(liItem);
	document.getElementById('search-results').appendChild(clearFixDiv);

}

// Very similar to above, but this one only handles page buttons on the bottom of the page

function createButtonHTML(currentPage, totalPages) {

	var previousButton = document.createElement('button');
	var nextButton = document.createElement('button');
	var navigationButtons = document.createElement('ul');
	var previousButtonLI = document.createElement('li');
	var nextButtonLI = document.createElement('li');

	previousButton.setAttribute('data-token', currentPage - 1);
	previousButton.setAttribute('class', 'btn-primary');
	previousButton.innerHTML = 'Prev Page';
	previousButtonLI.appendChild(previousButton);


	nextButton.setAttribute('data-token', currentPage + 1);
	nextButton.setAttribute('class', 'btn-primary');
	nextButton.innerHTML = 'Next Page';
	nextButtonLI.appendChild(nextButton);


	navigationButtons.appendChild(previousButtonLI);
	navigationButtons.appendChild(nextButtonLI);
	navigationButtons.setAttribute('class','navigation-buttons');

	previousButton.addEventListener('click', function() {

		document.getElementById('search-results').innerHTML = '';
		document.getElementById('navigationButtons').innerHTML = '';

		if (currentPage - 1 < 1)
			{
				getPhotos(currentPage);
			}
		else
			{
				getPhotos(currentPage - 1);
			}
	});

	nextButton.addEventListener('click', function() {

		document.getElementById('search-results').innerHTML = '';
		document.getElementById('navigationButtons').innerHTML = '';

		if (currentPage + 1 > totalPages)
			{
				getPhotos(currentPage);
			}
		else
			{
				getPhotos(currentPage + 1);
			}
	});

	document.getElementById('navigationButtons').appendChild(navigationButtons);
}

