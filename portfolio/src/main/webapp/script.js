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
    //list of quotes 
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



/**
 * Fetches comment data from the servers and adds them to the DOM.
 */
function getData() {

    
    fetch('/data').then(response => response.json()).then((data) => {
	//get the element and clear previous information
	const commentListElement = document.getElementById('cList');
	commentListElement.innerHTML = '';

	//parse list of comments and append to DOM 
	data.forEach((comment) => {
	    commentListElement.appendChild(createListElement(
		comment.user + "-----" + comment.message));
	})
    });
    
    				       
}




/**------------------------------------------------create element fucntions------------------------------- */


/** Creates an <li> element containing text. */
function createListElement(text) {
    const liElement = document.createElement('li');
    liElement.innerText = text;
    return liElement;
}
/** creates a <h3> element */
function createHeaderElement(text){
    const h3Element = document.createElement('h3');
    h3Element.innerText = text;
    return h3Element;
}

/** creates a <div> element */
function createDivElement(elem1, elem2){
    const divElem = document.createElement('div');
    divElem.innerHTML = '';
    divElem.appendChild(elem1);
    divElem.appendChild(elem2);
}



