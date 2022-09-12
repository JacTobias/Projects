//make rounds, where another image gets added, and have a timer 
const cardArray = [
    {
        name: 'Quake',
        img: 'images/daisy.png', //these are objects
    },
    {
        name: 'MsMarvel',
        img: 'images/kamala.png',
    },
    {
        name: 'MrKnight',
        img: 'images/mrknight.png',
    },
    {
        name: 'DrStrange',
        img: 'images/drstrange.png',
    },
    {
        name: 'ScarletWitch',
        img: 'images/wanda.png',
    },
    {
        name: 'CaptainAmerica',
        img: 'images/captainamerica.png',
    },
    {
        name: 'Quake',
        img: 'images/daisy.png', //these are objects
    },
    {
        name: 'MsMarvel',
        img: 'images/kamala.png',
    },
    {
        name: 'MrKnight',
        img: 'images/mrknight.png',
    },
    {
        name: 'DrStrange',
        img: 'images/drstrange.png',
    },
    {
        name: 'ScarletWitch',
        img: 'images/wanda.png',
    },
    {
        name: 'CaptainAmerica',
        img: 'images/captainamerica.png',
    },
]
const levelOne = [
    {
        name: 'Quake',
        img: 'images/daisy.png', //these are objects
    },
    {
        name: 'MsMarvel',
        img: 'images/kamala.png',
    },
    {
        name: 'MrKnight',
        img: 'images/mrknight.png',
    },
    {
        name: 'Yelena',
        img: 'images/yelena.png',
    },
    {
        name: 'Quake',
        img: 'images/daisy.png', //these are objects
    },
    {
        name: 'MsMarvel',
        img: 'images/kamala.png',
    },
    {
        name: 'MrKnight',
        img: 'images/mrknight.png',
    },
    {
        name: 'Yelena',
        img: 'images/yelena.png',
    },
]
const levelTwo = [
    {
        name: 'Quake',
        img: 'images/daisy.png', //these are objects
    },
    {
        name: 'MsMarvel',
        img: 'images/kamala.png',
    },
    {
        name: 'MrKnight',
        img: 'images/mrknight.png',
    },
    {
        name: 'Yelena',
        img: 'images/yelena.png',
    }, 
    {
        name: 'Dr Strange',
        img: 'images/drstrange.png'
    },
    {
        name: 'Quake',
        img: 'images/daisy.png', //these are objects
    },
    {
        name: 'MsMarvel',
        img: 'images/kamala.png',
    },
    {
        name: 'MrKnight',
        img: 'images/mrknight.png',
    },
    {
        name: 'Yelena',
        img: 'images/yelena.png',
    },
    {
        name: 'Dr Strange',
        img: 'images/drstrange.png'
    },
]
const levelThree = [
    {
        name: 'Quake',
        img: 'images/daisy.png', //these are objects
    },
    {
        name: 'MsMarvel',
        img: 'images/kamala.png',
    },
    {
        name: 'MrKnight',
        img: 'images/mrknight.png',
    },
    {
        name: 'Yelena',
        img: 'images/yelena.png',
    },
    {
        name: 'Daredevil',
        img: 'images/daredevil.png'
    },
    {
        name: 'Dr Strange',
        img: 'images/drstrange.png'
    },
    {
        name: 'ScarletWitch',
        img: 'images/wanda.png',
    },
    {
        name: 'Quake',
        img: 'images/daisy.png', //these are objects
    },
    {
        name: 'MsMarvel',
        img: 'images/kamala.png',
    },
    {
        name: 'MrKnight',
        img: 'images/mrknight.png',
    },
    {
        name: 'Yelena',
        img: 'images/yelena.png',
    },
    {
        name: 'Daredevil',
        img: 'images/daredevil.png'
    },
    {
        name: 'Dr Strange',
        img: 'images/drstrange.png'
    },
    {
        name: 'ScarletWitch',
        img: 'images/wanda.png',
    },
]
const levelFour = [
    {
        name: 'Quake',
        img: 'images/daisy.png', //these are objects
    },
    {
        name: 'MsMarvel',
        img: 'images/kamala.png',
    },
    {
        name: 'MrKnight',
        img: 'images/mrknight.png',
    },
    {
        name: 'Yelena',
        img: 'images/yelena.png',
    },
    {
        name: 'Daredevil',
        img: 'images/daredevil.png'
    },
    {
        name: 'DrStrange',
        img: 'images/drstrange.png',
    },
    {
        name: 'ScarletWitch',
        img: 'images/wanda.png',
    },
    {
        name: 'CaptainAmerica',
        img: 'images/sam.png',
    },
    {
        name: 'Moonknight',
        img: 'images/moonknight.png'
    },
    {
        name: 'Quake',
        img: 'images/daisy.png', //these are objects
    },
    {
        name: 'MsMarvel',
        img: 'images/kamala.png',
    },
    {
        name: 'MrKnight',
        img: 'images/mrknight.png',
    },
    {
        name: 'Yelena',
        img: 'images/yelena.png',
    },
    {
        name: 'Daredevil',
        img: 'images/daredevil.png'
    },
    {
        name: 'DrStrange',
        img: 'images/drstrange.png',
    },
    {
        name: 'ScarletWitch',
        img: 'images/wanda.png',
    },
    {
        name: 'CaptainAmerica',
        img: 'images/sam.png',
    },
    {
        name: 'Moonknight',
        img: 'images/moonknight.png'
    },
]

const startscreen = document.querySelector('#startscreen')

function shuffleBoard(deck){
    deck.sort(() => 0.5 - Math.random()) //shuffle array randomly
    console.log(currentLevel)
    console.log(deck)
}
const grid = document.querySelector('#grid') //# means id
let cardsChosen = []
let cardsChosenIds = []
let cardsWon = []
let result = 0
let currentLevel = 1;
const start = document.querySelector("button")
const restart = document.querySelector("button")
const nextLevel = document.querySelector("button")
document.getElementById('result').innerHTML = result

function startGame(){
    document.getElementById('score').className='show'
    document.getElementById('result').className='show'
    document.getElementById('marvelf').className = 'hidden'
    document.getElementById('marvela').className= 'show'
    document.getElementById('grid').className= 'show'
    document.getElementById('start').className = 'hidden'
    createBoardLevelOne()
}

function goToNextLevel(){
    grid.innerHTML = ""
    if(currentLevel == 2){
        document.getElementById("nextLevel").className = 'hidden'
        createBoardLevelTwo()
    }
    if(currentLevel == 3){
        document.getElementById("nextLevel").className = 'hidden'
        createBoardLevelThree()
    }
    if(currentLevel == 4){
        document.getElementById("nextLevel").className = 'hidden'
        createBoardLevelFour()
    }
}


function createBoardLevelOne(){
    shuffleBoard(levelOne)
    for(let i = 0; i < levelOne.length; i++){
        const card = document.createElement('img')
        card.setAttribute('src', 'images/marvel.png')
        card.setAttribute('data-id', i)
        card.addEventListener('click', flipCard)
        grid.appendChild(card)
    }
}
function createBoardLevelTwo(){
    shuffleBoard(levelTwo)
    for(let i = 0; i < levelTwo.length; i++){
        const card = document.createElement('img')
        card.setAttribute('src', 'images/marvel.png')
        card.setAttribute('data-id', i)
        card.addEventListener('click', flipCard)
        grid.appendChild(card)
    }
}

function createBoardLevelThree(){
    shuffleBoard(levelThree)
    for(let i = 0; i < levelThree.length; i++){
        const card = document.createElement('img')
        card.setAttribute('src', 'images/marvel.png')
        card.setAttribute('data-id', i)
        card.addEventListener('click', flipCard)
        grid.appendChild(card)
    }
}

function createBoardLevelFour(){
    shuffleBoard(levelFour)
    for(let i = 0; i < levelFour.length; i++){
        const card = document.createElement('img')
        card.setAttribute('src', 'images/marvel.png')
        card.setAttribute('data-id', i)
        card.addEventListener('click', flipCard)
        grid.appendChild(card)
    }
}

function checkMatch(){
    const cards = document.querySelectorAll('img')
    const optionOne = cardsChosenIds[0]
    const optionTwo = cardsChosenIds[1]
    //for bigger project : document.querySelectorAll('#grid img')
    if(optionOne == optionTwo){ //clicked same card
        alert('clicked on same card')
    }
    if(cardsChosen[0] == cardsChosen[1] && optionOne != optionTwo){
        alert('They match : 10 points')
        result = result + 10
        document.getElementById("result").innerHTML = result
        cardsWon.push(cardsChosen)       
        cards[optionOne].setAttribute('src', 'images/white.png')
        cards[optionTwo].setAttribute('src', 'images/white.png')
        cards[optionOne].removeEventListener('click', flipCard)
        cards[optionTwo].removeEventListener('click', flipCard)
        
    }else {
        cards[optionOne].setAttribute('src','images/marvel.png')
        cards[optionTwo].setAttribute('src', 'images/marvel.png')
        alert('Dont Match')
    }
    cardsChosen = []
    cardsChosenIds = [] 
    
    if(currentLevel == 1 && cardsWon.length == levelOne.length/2){
        currentLevel = 2
        cardsWon = []
        document.getElementById("nextLevel").className = 'show' 
    }
    if(currentLevel == 2 && cardsWon.length == levelTwo.length/2){
        currentLevel = 3
        cardsWon = []
        document.getElementById("nextLevel").className = 'show'
    }
    if(currentLevel == 3 && cardsWon.length == levelThree.length/2){
        currentLevel = 4
        cardsWon = []
        document.getElementById("nextLevel").className = 'show'
    }
    if(currentLevel == 4 && cardsWon.length == levelFour.length/2){
        document.getElementById('restart').className = 'show'
        alert('You Won! Press Restart button to replay')
        cardsWon = []
    } 
}
function flipCard(){
    if(currentLevel == 1){
        const cardId = this.getAttribute('data-id')
        cardsChosen.push(levelOne[cardId].name)
        cardsChosenIds.push(cardId)
        this.setAttribute('src', levelOne[cardId].img)
        if(cardsChosen.length === 2){
            setTimeout(checkMatch, 500)
        }
    }
    if(currentLevel == 2){
        const cardId = this.getAttribute('data-id')
        cardsChosen.push(levelTwo[cardId].name)
        cardsChosenIds.push(cardId)
        this.setAttribute('src', levelTwo[cardId].img)
        if(cardsChosen.length === 2){
            setTimeout(checkMatch, 500)
        }
    }
    if(currentLevel == 3){
        const cardId = this.getAttribute('data-id')
        cardsChosen.push(levelThree[cardId].name)
        cardsChosenIds.push(cardId)
        this.setAttribute('src', levelThree[cardId].img)
        if(cardsChosen.length === 2){
            setTimeout(checkMatch, 500)
        }
    }
    if(currentLevel == 4){
        const cardId = this.getAttribute('data-id')
        cardsChosen.push(levelFour[cardId].name)
        cardsChosenIds.push(cardId)
        this.setAttribute('src', levelFour[cardId].img)
        if(cardsChosen.length === 2){
            setTimeout(checkMatch, 500)
        }
    }
}

function replay(){
    document.getElementById('restart').className = 'hidden'
    document.getElementById('start').className = 'show'
    grid.innerHTML = ""
    document.getElementById("result").innerHTML = 0
    location.reload(true)
}

