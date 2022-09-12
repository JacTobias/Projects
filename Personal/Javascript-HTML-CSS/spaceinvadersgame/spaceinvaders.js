
const grid = document.querySelector('.grid')


for(let i = 0; i < 225; i++){
    const square = document.createElement('div')
    grid.appendChild(square)
}

const squares = Array.from(document.querySelectorAll('.grid div')) //creates an array
let currentShootIn = 202
const width = 15
let direction = 1
let invadersid
let goingRight = true
const alienInvaders = [
    0,1,2,3,4,5,6,7,8,9,
    15,16,17,18,19,20,21,22,23,24,
    30,31,32,33,34,35,36,37,38,39
]

function draw(){
    for(let i = 0; i < alienInvaders.length;i++){
        squares[alienInvaders[i]].classList.add('invader')
    }
}
draw()
function remove(){
    for(let i = 0; i < alienInvaders.length;i++){
        squares[alienInvaders[i]].classList.remove('invader')
    }
}

squares[currentShootIn].classList.add('shooter')

function moveShooter(e){
    squares[currentShootIn].classList.remove('shooter')
    switch(e.key){
        case 'ArrowLeft':
            if(currentShootIn % width !== 0) currentShootIn -=1
            break
        case 'ArrowRight':
            if(currentShootIn % width < width -1) currentShootIn +=1
            break
    }
    squares[currentShootIn].classList.add('shooter')
}

document.addEventListener('keydown', moveShooter)

function moveInvaders(){
    const leftEdge = alienInvaders[0] % width === 0
    const rightEdge = alienInvaders[alienInvaders.length -1]% width === width -1
    remove()

    if(rightEdge && goingRight){
        for(let i = 0; i < alienInvaders.length; i++){
            alienInvaders[i] += width +1
            direction = -1
            goingRight = false;
        }
    }
    if(leftEdge && !goingRight){
        for(let i = 0; i < alienInvaders.length; i++){
            alienInvaders[i] +=width -1
            direction = 1
            goingRight = true
        }
    }

    for (let i = 0; i < alienInvaders.length; i++){
        alienInvaders[i] += direction
    }
    draw()

    if(squares[currentShootIn].classList.contains('invader', 'shooter')){
        clearInterval(invadersid)
    }

}

invadersid = setInterval(moveInvaders, 500)