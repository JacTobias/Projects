//import Paddle from './Paddle.js';
let canvas = document.getElementById("gameScreen")
let context = canvas.getContext('2d')
const gameHeight = 600
const gameWidth = 800
const GAMESTATE = {
    PAUSED: 0,
    RUNNING: 1,
    MENU: 2,
    GAMEOVER: 3
}


function movePaddle(event){
    switch(event.key){
        case 'ArrowLeft':
            game.paddle.moveLeft()
            break;
        case 'ArrowRight':
            game.paddle.moveRight()
            break;
        case 'Escape':
            game.togglePause()
            break;
    }
}

function stopPaddle(event){
    switch(event.key){
        case 'ArrowLeft':
            if(game.paddle.speed < 0){ //travelling left
                game.paddle.stop()
            }
            
            break;
        case 'ArrowRight':
            if(game.paddle.speed > 0 ){
                game.paddle.stop()
            }
            break;
    }   
}




//create game class, holds all objects
class Game{
    constructor(gameWidth, gameHeight){
        this.gameWidth = gameWidth
        this.gameHeight = gameHeight
        this.gameState = GAMESTATE.MENU
        this.ball = new Star(this)
        this.paddle = new Paddle(this)
        this.gameObjects = []
        this.lives = 1
    }
    
    start(){
        if(this.gameState !== GAMESTATE.MENU) return
        
        let bricks = buildLevel(this, level1)
        document.getElementById("start").className = 'hidden'
        document.getElementById("gameScreen").className = 'show'
        document.getElementById("title").className = 'hidden'
        document.getElementById("title2").className = 'show'
        this.gameObjects = [
            this.ball,
            this.paddle,
            ...bricks
        ]
        this.gameState = GAMESTATE.RUNNING
    }

    update(deltaTime){
    if(this.lives == 0){
        this.gameState = GAMESTATE.GAMEOVER
        document.getElementById("gameOver").className = 'show'
        document.getElementById("title2").className = 'hidden'
        document.getElementById("restart").className = 'show'
    }
    
    if(this.gameState === GAMESTATE.PAUSED || this.gameState === GAMESTATE.MENU || this.gameState === GAMESTATE.GAMEOVER){ 
        return}


    this.gameObjects.forEach(object => object.update(deltaTime))
    this.gameObjects = this.gameObjects.filter(object =>!object.markedForDeletion)
    }

    draw(context){
    this.gameObjects.forEach(object => object.draw(context))

    if(this.gameState === GAMESTATE.GAMEOVER){
    context.rect(0,0,this.gameWidth, this.gameHeight)
    context.fillStyle = "rgba(0,0,0,.5)"
    context.fill()

    }

    
    }
    togglePause(){
        if(this.gameState == GAMESTATE.PAUSED){
        this.gameState = GAMESTATE.RUNNING
        }else{
        this.gameState = GAMESTATE.PAUSED
        }

    }
}



function buildLevel(game, level){
    let bricks = []

    level.forEach((row, rowIndex) => {
        row.forEach((brick, brickIndex) => {
            if(brick === 1){
                let position = {
                    x:80 * brickIndex,
                    y:50 + 30* rowIndex
                }
                bricks.push(new Brick(game, position))
            }

        })
    })
    return bricks
}

const level1 = [
    [1, 0, 0, 1, 0, 1, 0, 0, 0, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
]


class Brick{
    constructor(game, position){
        this.width = 80
        this.height = 30
        this.brick = document.getElementById("spaceship")
        this.position = position
        this.game = game
        this.markedForDeletion = false;
    }
    update(){
        if(collisionDetection(this.game.ball, this)){
            this.markedForDeletion = true;
            this.game.ball.speed.y = -this.game.ball.speed.y
            if(game.ball.speed.x > 0){//x positive
                if(game.ball.speed.y > 0){
                    game.ball.ball = document.getElementById("stardown")
                }else{
                game.ball.ball = document.getElementById("starright") }
            }else{ //x negative
                if(game.ball.speed.y > 0){
                    game.ball.ball = document.getElementById("starleft")
                }else{
                game.ball.ball = document.getElementById("starup") }
            }
        }
    }
    draw(){
        context.drawImage(this.brick, this.position.x, this.position.y, this.width, this.height)
    }
}

function collisionDetection(ball, gameObject){
    let bottomofBall = ball.position.y + ball.size
    let topofBall = ball.position.y
    let topofgameObject = gameObject.position.y
    let leftofgameObject = gameObject.position.x
    let rightofgameObject = gameObject.position.x + gameObject.width
    let bottomofgameObject = gameObject.position.y + gameObject.height
    if(bottomofBall >= topofgameObject
        && topofBall <= bottomofgameObject 
        && ball.position.x >=leftofgameObject 
        && ball.position.x + ball.size <= rightofgameObject)
        { 
            return true
    }
    if(ball.position.x > gameWidth - ball.size){ //right wall
        if(ball.speed.y > 0){//positive
            ball.ball = document.getElementById('starleft')
        }
        if(ball.speed.y < 0){//negative
            ball.ball = document.getElementById('starup')
        }
    }

    if(ball.position.x < 0){ //left wall
        if(ball.speed.y > 0){//positive
            ball.ball = document.getElementById('stardown')
        }
        if(ball.speed.y < 0){
            ball.ball = document.getElementById('starright')
        }
    }

    if(ball.position.y - ball.size> gameHeight ){ //bottom wall
        if(ball.speed.x > 0){//positive
            ball.ball = document.getElementById('starright')
        }
        if(ball.speed.x < 0){
            ball.ball = document.getElementById('starup')
        }
    }

    if(ball.position.y < 0){//top wall
        if(ball.speed.x > 0){ //positive
            ball.ball = document.getElementById('stardown')
        }
        if(ball.speed.x < 0){
            ball.ball = document.getElementById('starleft')
        }
    }
}


class Star{
    constructor(game){
        this.size = 25;
        this.ball = document.getElementById('starright')
        this.speed = {x:4, y:-2}
        this.position = {x:400, y:400}
        this.gameWidth = game.gameWidth
        this.gameHeight = game.gameHeight
        this.game = game
    }
    draw(context){
        context.drawImage(this.ball, this.position.x, this.position.y, this.size, this.size)
    }
    update(deltaTime){
        this.position.x += this.speed.x
        this.position.y += this.speed.y
        if(this.position.x > this.gameWidth - this.size || this.position.x < 0){
            this.speed.x = -this.speed.x
        }
        if(this.position.y < 0){
            this.speed.y = -this.speed.y
        }
        if(this.position.y + this.size> this.gameHeight){
            this.game.lives--
        }
        if(collisionDetection(this, this.game.paddle)){
            if(this.speed.x > 0){//positive
                this.ball = document.getElementById("starright")
            }else{ //negative
                this.ball = document.getElementById("starup")
            }
            this.speed.y = -this.speed.y;
            this.position.y = this.game.paddle.position.y - this.size
        }
    }
    
}

class Paddle{
    constructor(game){
        this.gameWidth = game.gameWidth
        this.gameHeight = game.gameHeight
        this.width = 150
        this.height = 20
        this.maxSpeed = 5
        this.speed = 0
        this.game = game
        this.position = { //creates an object
            x: this.gameWidth / 2 - this.width / 2,
            y: this.gameHeight - this.height - 10,
        }
    }

    draw(context) {
    context.fillRect (this.position.x, this.position.y, this.width, this.height);
    context.fillStyle = 'rgba(255,255,255,.7)'
    }
    update(deltaTime){
        this.position.x += this.speed
        if(this.position.x < 0){
            this.position.x = 0
        }
        if(this.position.x + this.width > this.gameWidth){
            this.position.x = this.gameWidth - this.width
        }
    }
    moveLeft(){
        this.speed = -this.maxSpeed
    }
    moveRight(){
        this.speed = this.maxSpeed
    }
    stop(){
        this.speed = 0
    }
}

let game = new Game(gameWidth, gameHeight)
game.gameState = GAMESTATE.MENU
let lastTime = 0;

document.addEventListener('keydown', movePaddle)
document.addEventListener('keyup', stopPaddle)

function gameLoop(timeStamp) {
    let deltaTime = timeStamp - lastTime;
    lastTime = timeStamp
    context.clearRect(0,0,gameWidth,gameHeight)
    game.update(deltaTime)
    game.draw(context)
    requestAnimationFrame(gameLoop)//calls gameLoop again with next timeStamp
    
}
requestAnimationFrame(gameLoop)

function restartGame(){
    location.reload(true)
}


//context.clearRect(0,0,800,600)

//context.fillStyle='#f00'
//context.fillRect(220, 20, 100, 100) //corrdinates (x, y), width, height
//what was previously there is stil there, need to clear

