const grid = document.querySelector('#grid')
const blockWidth = 100
const blockHeight = 20
const boardWidth = 1000
const boardHeight = 600
const ballDiameter = 20
const scoreDisplay = document.querySelector('#score')
let xDirection = 4
let yDirection = 4
let score = 0

//add levels
const userStart = [470, 10]
let currentPosition = userStart
const ballStart = [510, 30]
let currentBall = ballStart
let timerId

class Block {
    constructor(xAxis, yAxis){
        this.bottomleft = [xAxis, yAxis]
        this.bottomright = [xAxis + blockWidth, yAxis]
        this.topleft = [xAxis, yAxis + blockHeight]
        this.topright = [xAxis + blockWidth, yAxis + blockHeight]
        
    }
}
const ball = document.createElement('div')
const user = document.createElement('div')
const blocks = [
    new Block(10, 570),
    new Block(120, 570),
    new Block(230, 570),
    new Block(340, 570),
    new Block(450, 570),
    new Block(560, 570),
    new Block(670, 570),
    new Block(780, 570),
    new Block(890, 570),
    new Block(10, 540),
    new Block(120, 540),
    new Block(230, 540),
    new Block(340, 540),
    new Block(450, 540),
    new Block(560, 540),
    new Block(670, 540),
    new Block(780, 540),
    new Block(890, 540),
    new Block(10, 510),
    new Block(120, 510),
    new Block(230, 510),
    new Block(340, 510),
    new Block(450, 510),
    new Block(560, 510),
    new Block(670, 510),
    new Block(780, 510),
    new Block(890, 510),
    new Block(10, 480),
    new Block(120, 480),
    new Block(230, 480),
    new Block(340, 480),
    new Block(450, 480),
    new Block(560, 480),
    new Block(670, 480),
    new Block(780, 480),
    new Block(890, 480),
    new Block(10, 450),
    new Block(120, 450),
    new Block(230, 450),
    new Block(340, 450),
    new Block(450, 450),
    new Block(560, 450),
    new Block(670, 450),
    new Block(780, 450),
    new Block(890, 450),
]

function addBlocks(){
    for (let i = 0; i < blocks.length; i++){
    const block = document.createElement('div')
    block.classList.add('block')
    block.style.left = blocks[i].bottomleft[0] + 'px'
    block.style.bottom = blocks[i].bottomleft[1] + 'px'
    grid.appendChild(block)
}
}
function startGame(){
    document.getElementById('grid').className = 'show'
    document.getElementById('breakoutf').className = 'hidden'
    document.getElementById('breakouta').className = 'show'
    addBlocks()
    document.getElementById('start').className = 'hidden'
    makeUser()
    makeBall()
    timerId = setInterval(moveBall, 30)
}
function replay(){
    document.getElementById('restart').className = 'hidden'
    document.getElementById('start').className = 'show'
    document.getElementById("result").innerHTML = 0
    location.reload(true)
}

function makeUser(){
    user.classList.add('user')
    drawUser(user)
    grid.appendChild(user)
}

function makeBall(){
    ball.classList.add('ball')
    drawBall()
    grid.appendChild(ball)
}


function drawUser(){
    user.style.left = currentPosition[0] + 'px'
    user.style.bottom = currentPosition[1] + 'px'
}

//move user
function moveUser(e){
    switch(e.key){
        case 'ArrowLeft':
            if(currentPosition[0] > 10){
            currentPosition[0] -= 15
            drawUser()
            break;
            }
        case 'ArrowRight':
            if(currentPosition[0] < 890){
                currentPosition[0] += 15
                drawUser()
                break;
            }
    }
}
document.addEventListener('keydown', moveUser)


function drawBall(){
    ball.style.left = currentBall[0] + 'px'
    ball.style.bottom = currentBall[1] + 'px'
}


function moveBall(){
    currentBall[0] += xDirection
    currentBall[1] += yDirection
    drawBall()
    collisionsBall()
}
 //how often ball updates

function collisionsBall(){
    //block collisions
    for (let i = 0; i < blocks.length; i++){
        if(
            (currentBall[0] > blocks[i].bottomleft[0] 
            && currentBall[0] < blocks[i].bottomright[0] 
            && (currentBall[1] + ballDiameter) > blocks[i].bottomleft[1] 
            && currentBall[1] < blocks[i].topleft[1]))
            {
                //within a block
                const allBlocks = Array.from(document.querySelectorAll('.block'))
                allBlocks[i].classList.remove('block')
                blocks.splice(i, 1)
                changeDirection()
                score++
                //scoreDisplay.innerHTML = score
            }
    
    }
    //wall collisions
    if (currentBall[0] >= (boardWidth - ballDiameter) 
    || currentBall[1] >= (boardHeight - ballDiameter) 
    || currentBall[0] <= 0){
        //off grid
        changeDirection()
    }
    //user collisions
    if(currentBall[0] > currentPosition[0] 
        && currentBall[0] < currentPosition[0] + blockWidth 
        && currentBall[1] > currentPosition[1] 
        && currentBall[1] < currentPosition[1] + blockHeight){
            changeDirection()
    }
    if(currentBall[1] <= 0 ){
        clearInterval(timerId)
        document.getElementById('restart').className = 'show'
    }
    if(blocks.length == 0){
        grid.innerHTML = ''
        document.getElementById('grid').className = 'hidden'
        clearInterval(timerId)
        document.removeEventListener('keydown', moveUser)
        document.getElementById('restart').className = 'show'
    }
}

function changeDirection(){
    if(xDirection == 4 && yDirection == 4){
        yDirection = -4
        return
    }
    if(xDirection == 4 && yDirection == -4){
        xDirection = -4
        return
    }
    if(xDirection == -4 && yDirection == -4){
        yDirection = 4
        return
    }
    if(xDirection == -4 && yDirection == 4){
        xDirection = 4
        return
    }
}