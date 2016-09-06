var express = require('express');
var router = express.Router();
var numeral = require('numeral');
var tokenUses = 0;

/* GET home page. */
// router.get('/', function(req, res, next) {
//     console.log(req.query);
//     if(req.query.hi === 'hello') {
//         console.log('hello world!');
//         res.json({title: 'hello'});
//     }
//     else {
//         console.log('NOPE');
//         res.json({title: 'goodbye'});
//     }
//
//     // next();
// });

router.use(function(req, res, next) {
	// do logging
	console.log(req.method, req.url);
	next(); // make sure we go to the next routes and don't stop here
});

router.get('/', function(req, res, next) {
	res.json({ message: 'hooray! welcome to our api!' });
	next();
});

// Make queries using http://localhost:8080/api/stringStatistics?authenticationID=<authenticationToken>&input=<string input>
router.get('/stringStatistics', function(req, res, next) {
	if (req.query.authenticationID === "K1BG3fm7x712" && tokenUses < 10)
		{
			var output;
			if (req.query.method === "word")
				{
					output = processInputWords(req.query.stringInput);
				}
			else if (req.query.method === "character")
				{
					output = processInputCharacters(req.query.stringInput);
				}
			else
				{
					output = "You need to add the 'method' parameter || word OR character"
				}
			tokenUses++;
			return res.json(output);
		}
	else if (tokenUses >= 10)
		{
			console.log("out of tokens");
			return res.json({message: "Contact me to get more tokens"});
		}
	else
		{
			return res.json({message: "Invalid authentication ID"});
		}
});

module.exports = router;

function processInputWords(stringInput) {
	var words = stringInput.replace(/[^\w\s]/g, "").split(/\s+/);
	var freqMap = {};
	var wordArray = [];
	words.forEach(function(word) {
		if (!freqMap[word]) {
			freqMap[word] = 0;
		}
		freqMap[word] += 1;
	});

	Object.keys(freqMap).sort().forEach(function(word) {
		wordArray.push(new wordObject(word, freqMap[word], numeral(freqMap[word]/words.length).format('0.000%')));
	});

	return new statisticsWordObject(wordArray, words.length);

};

function processInputCharacters(stringInput) {
	var words = stringInput.replace(/[^\w\s]/g, "").split(/\s+/);
	var characterFrequencyMap = {};
	var characterArray = [];
	words.forEach(function(word) {
		word.split('').forEach(function(character) {
			if (!characterFrequencyMap[character]) {
				characterFrequencyMap[character] = 0;
			}
			characterFrequencyMap[character] += 1;
		});
	});

	var sumOfCharacters = 0;

	Object.keys(characterFrequencyMap).forEach(function(character) {
		sumOfCharacters += characterFrequencyMap[character];
	});

	Object.keys(characterFrequencyMap).sort().forEach(function(character) {
		characterArray.push(new characterObject(character, characterFrequencyMap[character], numeral(characterFrequencyMap[character]/sumOfCharacters).format('0.000%')));
	});

	return new statisticsCharacterObject(characterArray, sumOfCharacters);
};

var wordObject = function(word, count, percentage) {
	this.word = word;
	this.count = count;
	this.percentage = percentage;
};

var statisticsWordObject = function(wordArray, totalCount) {
	this.wordArray = wordArray;
	this.totalCount = totalCount;
};

var characterObject = function(character, count, percentage) {
	this.character = character;
	this.count = count;
	this.percentage = percentage;
};

var statisticsCharacterObject = function(characterArray, sumOfCharacter) {
	this.characArray = characterArray;
	this.sumOfCharacter = sumOfCharacter;
}
