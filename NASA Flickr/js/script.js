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

function getPhotos() {

	var photoRequest = new XMLHttpRequest();

	var data = {
		method: 'flickr.people.getPublicPhotos',
		api_key: 'dadeb42528363ca20ca630ea9e800129',
		user_id: '24662369@N07',
		format: 'json',
		nojsoncallback: 1
	};

	var url = buildURL('https://api.flickr.com/services/rest/', data);

	photoRequest.open('GET', url, true);

	photoRequest.onreadystatechange = function() {
		if (photoRequest.readyState === 4 && photoRequest.status === 200) {
			processInput(JSON.parse(photoRequest.response).photos);
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