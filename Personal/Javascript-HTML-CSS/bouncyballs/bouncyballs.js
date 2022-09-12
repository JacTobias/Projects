let canvas = document.getElementById('screen')
let context = canvas.getContext('2d')
const screenWidth = 1520
const screenHeight = 720
let lastTime = 0

class Ball{
    constructor(screenWidth, screenHeight, position, speed, ballColor){
        this.size = 35
        this.screenWidth = screenWidth
        this.screenHeight = screenHeight
        this.speedx = speed.x
        this.speedy = speed.y
        this.positionx = position.x
        this.positiony = position.y
        this.ballColor = ballColor
    }
    draw(context){
        if(this.ballColor == 1){ //red
        context.drawImage((document.getElementById("1")), this.positionx, this.positiony, this.size, this.size)
        }
        if(this.ballColor == 2){//orange
            context.drawImage((document.getElementById("2")), this.positionx, this.positiony, this.size, this.size)
        }
        if(this.ballColor == 3){//yellow
            context.drawImage((document.getElementById("3")), this.positionx, this.positiony, this.size, this.size)
        }
        if(this.ballColor == 4){//green
            context.drawImage((document.getElementById("4")), this.positionx, this.positiony, this.size, this.size)
        }
        if(this.ballColor == 5){//blue
            context.drawImage((document.getElementById("5")), this.positionx, this.positiony, this.size, this.size)
        }
        if(this.ballColor == 6){//purple
            context.drawImage((document.getElementById("6")), this.positionx, this.positiony, this.size, this.size)
        }
        if(this.ballColor == 7){//pink
            context.drawImage((document.getElementById("7")), this.positionx, this.positiony, this.size, this.size)
        }
    }
    update(deltaTime){
        this.positionx += this.speedx
        this.positiony += this.speedy

        if(this.positionx < 0 || this.positionx > this.screenWidth - this.size){
            this.speedx = -this.speedx
        }
        if(this.positiony < 0 || this.positiony > this.screenHeight - this.size){
            this.speedy = -this.speedy
        }
    }
}

function startGame(){
    document.getElementById('screen').className = 'show'
    document.getElementById('start').className = 'hidden'
    document.getElementById('before').className = 'hidden'
    makeBalls()
    requestAnimationFrame(loop)
    loop()
}

let count = 0
let ballsArray = []
let currentx = 1
let currenty = 1

function makeBalls(){ 
    for(let i = 0; i < 200; i++){
        let ballColor = Math.floor(Math.random() * 8)
        let randomxposition = Math.floor(Math.random() * (screenWidth - 100))
        let randomyposition = Math.floor(Math.random() * (screenHeight- 100))
        let randomspeedx = Math.floor(Math.random() * 7) + 1
        let randomspeedy = Math.floor(Math.random() * 5) + 1
        let ball = new Ball(screenWidth, screenHeight, {x:randomxposition, y:randomyposition}, {x:randomspeedx*currentx, y:randomspeedy*currenty}, ballColor)
        ballsArray.push(ball)
        if(count%2 == 0){
            currenty = -1
        }
        currentx = -1
    }
}

function loop(timeStamp){
    let deltaTime = timeStamp - lastTime
    lastTime = timeStamp
    context.clearRect(0,0,1520,720)
    requestAnimationFrame(loop)
    ballsArray.forEach(ball => ball.draw(context))
    ballsArray.forEach(ball => ball.update(deltaTime))
}
