#PythonDraw.py
import turtle
turtle.showturtle
turtle.setup(800,400)
turtle.pencolor("black")
turtle.pensize(1)
turtle.circle(50,360*1.25)
turtle.bk(100)
turtle.circle(50,360*0.5)
turtle.bk(100)
for i in range(2):
    turtle.circle(12.5,180)
    turtle.circle(-12.5,180)
turtle.bk(100)
turtle.circle(-50,360*0.5)
turtle.bk(100)
for i in range(2):
    turtle.circle(-12.5,180)
    turtle.circle(12.5,180)
turtle.right(90)
turtle.bk(100)
turtle.done()
