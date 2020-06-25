// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random quote to the page.
 */
function randomQuote() {
    // List of quotes 
    const quotes =[ 'Be yourself; everyone else is already taken   - Oscar Wilde',
		       'Very little is needed to make a happy life;  it is all within yourself, in your way of thinking.  - Marcus Aurelius',
		       'I know this defies the law of gravity, but I never studied law!  - Bugs Bunny',
		       "I don't know where I'm going from here, but I promise it won't be boring.  - David Bowie"
		     ];

    // Pick a random quote.
    const quote = greetings[Math.floor(Math.random() * greetings.length)];

    // Add it to the page.
    const quoteCont = document.getElementById('quote-container');
    quoteCont.innerText = quote;
}



var map;
function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
	center: { lat: 0 , lng: 0 },
	zoom: 2
    });

    myLatLng1 = {lat: -45.87, lng: 170.50};
    var marker = new google.maps.Marker({
	position: myLatLng1,
	map: map,
	title: 'Dunedin!'
    });

    myLatLng2 = {lat: 5.6, lng: -0.185};
    var marker = new google.maps.Marker({
	position: myLatLng2,
	map: map,
	title: 'Accra!'
    });

    myLatLng3 = {lat: 40.80, lng: -73.96};
    var marker = new google.maps.Marker({
	position: myLatLng3,
	map: map,
	title: 'New York!'
    });

    myLatLng4 = {lat: 38.63, lng: -77.36};
    var marker = new google.maps.Marker({
	position: myLatLng4,
	map: map,
	title: 'Virginia!'
    });
    
}



/**
 * Fetches comment data from the servers and adds them to the DOM.
 */
function getData() {

    
    fetch('/data').then(response => response.json()).then((data) => {
	// Get the element and clear previous information
	const commentListElement = document.getElementById('cList');
	commentListElement.innerHTML = '';

	// Parse list of comments and append to DOM 
	data.forEach((comment) => {
	    commentListElement.appendChild(createListElement(
		comment.user + "-----" + comment.message));
	})
    });
}




/**------------------------------------------------Create element functions------------------------------- */


/** Creates an <li> element containing text. */
function createListElement(text) {
    const liElement = document.createElement('li');
    liElement.innerText = text;
    return liElement;
}
/** Creates a <h3> element */
function createHeaderElement(text){
    const h3Element = document.createElement('h3');
    h3Element.innerText = text;
    return h3Element;
}

/** Creates a <div> element */
function createDivElement(elem1, elem2){
    const divElem = document.createElement('div');
    divElem.innerHTML = '';
    divElem.appendChild(elem1);
    divElem.appendChild(elem2);
}



