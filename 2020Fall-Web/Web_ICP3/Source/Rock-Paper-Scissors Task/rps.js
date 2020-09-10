let userscore = 0;  //creating two variables user score and computer score
let computerscore = 0;

// get values from the HTML file
const userscore_span = document.getElementById("user");
const computerscore_span = document.getElementById("computer");
const result_p = document.querySelector(".result > p");
const rock_div = document.getElementById("r");
const paper_div = document.getElementById("p");
const scissor_div = document.getElementById("s");
const result_img = document.getElementById("tst");

// creates a function for computer choices
function getcomputerchoice() {
    const choices = ['r', 'p', 's'];
    // Math.floor is used for whole numbers Math.random is used to show random numbers between 0 and 1
    const randomnumber = Math.floor(Math.random() * 3);
    return choices[randomnumber]; // r = 0 , p =  1 ,
}

// convert letters to words
function convertword(letter) {
    if (letter === "r") return "Rock";
    else if (letter === "p") return "Paper";
    else return "Scissors"
}

// depend on user and computer choices decide win, lose or draw
function game(userchoice) {
    const computerchoice = getcomputerchoice();

    // switch case used to identify the win lose or  draw
    switch (userchoice + computerchoice) {
        case "rs":                        // winning cases
        case "pr":
        case "sp":
            win(userchoice, computerchoice);
            console.log("user wins");     // shows data in the console
            break;
        case "rp":                        // losing cases
        case "ps":
        case "sr":
            lose(userchoice, computerchoice);
            console.log("user loses");
            break;
        case "rr":                        // draw cases
        case "pp":
        case "ss":
            draw(userchoice, computerchoice);
            console.log("Draw");
            break;
    }
}

// win lose and draw functions
function win(userchoice, computerchoice) {
    userscore++;
    userscore_span.innerHTML = userscore;   // innerHTML overwrites the obtained html data
    computerscore_span.innerHTML = computerscore;
    result_p.innerHTML = `USER: ${convertword(userchoice)}  - COMPUTER: ${convertword(computerchoice)}`; // `` are used so there is no need for "" and + function
    result_img.src = "https://image.freepik.com/free-vector/you-win-sign-pop-art-style_175838-498.jpg"; // changes the src attribute //YOU WIN!
}

function lose(userchoice, computerchoice) {
    computerscore++;
    userscore_span.innerHTML = userscore;
    computerscore_span.innerHTML = computerscore;
    result_p.innerHTML = `USER: ${convertword(userchoice)}  -  COMPUTER: ${convertword(computerchoice)} `;  // $ is used for the converting function
    result_img.src = "https://image.shutterstock.com/image-vector/you-lose-comic-speech-bubble-600w-449380606.jpg"; // YOU LOSE
}

function draw(userchoice, computerchoice) {
    userscore_span.innerHTML = userscore;
    computerscore_span.innerHTML = computerscore;
    result_p.innerHTML = `USER: ${convertword(userchoice)}  ties  COMPUTER: ${convertword(computerchoice)}  `;
    result_img.src = "https://cdn.shopify.com/s/files/1/1155/3572/articles/AAEC5F28-FBB9-4ACF-A781-10C286647D0D_636x.jpeg?v=1506833593"; // It's a draw!
}

function main() {
    rock_div.addEventListener('click', function () {
        console.log("hey clicked rock");
        game("r");
    })
    paper_div.addEventListener('click', function () {
        console.log("hey clicked paper");
        game("p");
    })
    scissor_div.addEventListener('click', function () {
        console.log("hey clicked scissor");
        game("s");
    })

}

main();