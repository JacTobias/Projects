const computerchoiceDisplay = document.getElementById('computer-choice')
const userchoiceDisplay = document.getElementById('user-choice')
const resultDisplay = document.getElementById('result')
const possibleChoices = document.querySelectorAll('button')

let userChoice
let computerChoice
let result

possibleChoices.forEach(possibleChoice => possibleChoice.addEventListener('click', (e) => {
    userChoice = e.target.id
    userchoiceDisplay.innerHTML = userChoice
    generateComputerChoice()
    getResult()
}))

function generateComputerChoice(){
    const randomNumber = Math.floor(Math.random() * possibleChoices.length) + 1
    
    if(randomNumber == 1){
        computerChoice = 'rock'
    }
    if(randomNumber == 2 ){
        computerChoice = 'scissors'
    }
    if(randomNumber == 3){
        computerChoice = 'paper'
    }

    computerchoiceDisplay.innerHTML = computerChoice
}

function getResult() {
    if(computerChoice === userChoice){
        result = 'tie'
    }
    if(computerChoice === 'rock' && userChoice === 'paper'){
        result = 'won'
    }
    if(computerChoice === 'rock' && userChoice === 'scissors'){
        result = 'lost'
    }
    if(computerChoice === 'paper' && userChoice === 'rock'){
        result = 'lost'
    }
    if(computerChoice === 'paper' && userChoice === 'scissors'){
        result = 'won'
    }
    if(computerChoice === 'scissors' && userChoice === 'rock'){
        result = 'won'
    }
    if(computerChoice === 'scissors' && userChoice === 'paper'){
        result = 'lost'
    }
    resultDisplay.innerHTML = result
}