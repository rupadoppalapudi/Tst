// when the user mouse hovers on the image,
// this function is used to update the background of the empty frame with respective image and text
function upDate(previewPic){

    //fetching values from html elements
    const img = document.getElementById("image");

       // changes the url for the background image of html element(#image) to the tmp file of the preview image
       img.style.backgroundImage = "url("+previewPic.src+")";

       //Changes the text of html element(#image) to the alt text of the preview image
       img.innerHTML = previewPic.alt;
}

// when the user mouse is not hovered over the image,
// this function is used to change the background image and text back to original
function unDo(){

    //fetching values from html elements
    const img2 = document.getElementById("image");

    // Changes the url for the background image of html element(#image) back to the original image
    img2.style.backgroundImage = "url('')";

    // Changes the text of html element(#image) back to the original text
    img2.innerHTML = "Hover over an image below to display here.";

}
