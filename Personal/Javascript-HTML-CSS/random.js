let canvas = document.getElementById("gameScreen")
let context = canvas.getContext('2d')

//context.clearRect(0,0,800,600)

context.fillStyle='#f00'
context.fillRect(220, 20, 100, 100) //corrdinates (x, y), width, height
//what was previously there is stil there, need to clear

context.fillStyle='#00f'
context.fillRect(120,120, 100, 100)