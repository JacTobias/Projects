const grid = document.querySelector('#grid')

const character = document.createElement('div')
const food = document.createElement('div')
let currentPosition = [40, 350]
let foodPosition = [0,0]
let length = 1

makeCharacter()
makeFood()
function makeCharacter(){
    character.classList.add('character')
    character.style.left = 40 + 'px';
    character.style.bottom = 400 + 'px'
    grid.appendChild(character)
    drawCharacter(character)
}

let snakeTail = []

function makeFood(){
    food.classList.add('food')
    foodPosition[0] = Math.floor(Math.random()* 780)
    foodPosition[1] = Math.floor(Math.random()* 580)
    console.log(foodPosition[0], foodPosition[1])
    grid.appendChild(food)
    drawFood()
}

function drawFood(){
    food.style.left = foodPosition[0] + 'px'
    food.style.bottom = foodPosition[1] + 'px'
}

document.addEventListener('keydown', moveCharacter)

function drawCharacter(){
    console.log('move')
    character.style.left = currentPosition[0] + 'px'
    character.style.bottom = currentPosition[1] + 'px'
    console.log(currentPosition[0] ,currentPosition[1])
}

function moveCharacter(e){
    switch(e.key){
        case 'ArrowLeft':
            if(currentPosition[0] == 0){
                break;
            }
            if(currentPosition[0] > 0){
                console.log('left')
            currentPosition[0] -= 10
            drawCharacter()
            break;
            }
            
        case 'ArrowRight':
            if(currentPosition[0] == 780){
                break;
            }
            if(currentPosition[0] < 780){
                console.log('right')
                currentPosition[0] += 10
                drawCharacter()
                break; 
            }
        case 'ArrowUp':
            if(currentPosition[1] == 580){
                break;
            }
            if(currentPosition[1] < 580){
                console.log('up')
                currentPosition[1] += 10
                drawCharacter()
                break;
            }
        case 'ArrowDown':
            if(currentPosition[1] == 0){
                break;
            }
            if(currentPosition[1] > 0){
                console.log('down')
                currentPosition[1] -= 10
                drawCharacter()
                break;
            }
    }
    foodEaten()
}

function foodEaten(){
    minFoodLeft = foodPosition[0] - 10
    minFoodBottom = foodPosition[1] - 10
    maxFoodLeft = foodPosition[0] + 10
    maxFoodBottom = foodPosition[1] + 10
    if(currentPosition[0] > minFoodLeft 
        && currentPosition[0] < maxFoodLeft
        && currentPosition[1] > minFoodBottom
        && currentPosition[1] < maxFoodBottom){
            length++
            grid.removeChild(food)
            makeFood()
        }
}