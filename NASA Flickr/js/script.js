/**
 * Created by alexandrugutu on 9/1/16.
 */

var startButton = document.getElementById('start-button');
startButton.addEventListener('click', function() {

	// var tagParameter = document.getElementById('pictureTags').value;
	// var tag_mode;
	//
	// var radios = document.getElementsByName('tag_mode');
	// for (var i = 0; i < radios.length; i++) {
	// 	if (radios[i].checked) {
	// 		tag_mode = radios[i].value;
	// 	}
	// 	else {
	// 		tag_mode = '';
	// 	}
	// }
	//
	// var isGeotagged = document.getElementById('isGeotagged').value;
	//
	// console.log(tag_mode);

	getPhotos();
});

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

function getPhotos(currentPage) {

	var photoRequest = new XMLHttpRequest();

	if (typeof currentPage !== 'undefind')
		{
			var data = {
				method: 'flickr.people.getPublicPhotos',
				api_key: 'dadeb42528363ca20ca630ea9e800129',
				user_id: '24662369@N07',
				format: 'json',
				nojsoncallback: 1,
				per_page: 25,
				page: currentPage
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
				per_page: 25
			};
		}

	var url = buildURL('https://api.flickr.com/services/rest/', data);

	photoRequest.open('GET', url, true);

	photoRequest.onreadystatechange = function() {
		if (photoRequest.readyState === 4 && photoRequest.status === 200) {
			var photos = JSON.parse(photoRequest.response).photos;
			console.log(JSON.parse(photoRequest.response).photos);
			processInput(photos);
			createButtonHTML(photos.page, photos.pages);
		}
		else if (photoRequest.readyState === 4 && photoRequest.status === 400) {
			console.log("Improper Request");
		}

	};

	photoRequest.send(null);
}

function processInput(photos) {

	// for (var i = 0; i < photos.photo.length; i++)
	for (var index in photos.photo)
		{
			photos.photo[index].size = getSize(photos, index);
		}
}

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

function createHTML(photo) {
	var liItem = document.createElement('li');
	var leftDiv = document.createElement('div');
	var rightDiv = document.createElement('div');
	var clearFixDiv = document.createElement('div');
	var imageSizesDiv = document.createElement('div');

	var previewImage = document.createElement('img');
	var imageTitle = document.createElement('h3');
	var imageTitleLink = document.createElement('a');

	var credits = document.createElement('small');
	var photographer = document.createElement('span');
	var photoSizeList = document.createElement('ul');

	leftDiv.setAttribute('class', 'list-left');
	rightDiv.setAttribute('class', 'list-right');
	previewImage.setAttribute('src', photo.size[1].source);
	clearFixDiv.setAttribute('class', 'clearfix');
	imageSizesDiv.setAttribute('class', 'photo-sizes');
	imageTitleLink.setAttribute('href', photo.size[1].source);

	imageTitleLink.innerHTML = photo.title;

	credits.innerHTML = 'By ';

	photographer.setAttribute('class', 'photographer');
	photographer.innerHTML = 'NASA';

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
	credits.appendChild(photographer);
	imageTitle.appendChild(imageTitleLink);
	imageSizesDiv.appendChild(photoSizeList);

	rightDiv.appendChild(imageTitle);
	rightDiv.appendChild(credits);
	rightDiv.appendChild(imageSizesDiv);

	leftDiv.appendChild(previewImage);

	liItem.appendChild(leftDiv);
	liItem.appendChild(rightDiv);

	document.getElementById('search-results').appendChild(liItem);
	document.getElementById('search-results').appendChild(clearFixDiv);

}

function createButtonHTML(currentPage, totalPages) {

	var previousButton = document.createElement('button');
	var nextButton = document.createElement('button');

	previousButton.setAttribute('data-token', currentPage - 1);
	previousButton.setAttribute('class', 'btn-primary');
	previousButton.innerHTML = 'Prev Page';

	nextButton.setAttribute('data-token', currentPage + 1);
	nextButton.setAttribute('class', 'btn-primary');
	nextButton.innerHTML = 'Next Page';

	var navigationButtons = document.createElement('ul');
	var previousButtonLI = document.createElement('li');
	var nextButtonLI = document.createElement('li');

	previousButtonLI.appendChild(previousButton);
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

	// buttonContainer.appendChild(previousButton);
	// buttonContainer.appendChild(nextButton);

	// document.getElementById('navigationButtons').appendChild(previousButton);
	// document.getElementById('navigationButtons').appendChild(nextButton);

	document.getElementById('navigationButtons').appendChild(navigationButtons);
}

