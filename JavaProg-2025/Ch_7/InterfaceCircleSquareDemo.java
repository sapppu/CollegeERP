/*
	Program illustrate Interface
	
	Ex. shows the method getArea() , which declared in the interface shape2d ,
	is implemented in two different ways in the classes Circle and Square

*/


import java.io.*;
interface Shape2d
{
	double getArea();
}
class Shape1
{
	void display(String s)
	{
		System.out.println("Name of the shape is : :" + s);
	}
}
class Circle extends Shape1 implements Shape2d
{
	int radius;
	Circle(int radius)
	{
		this.radius = radius ;
	}

	public double getArea()
	{
		return Math.PI * radius * radius ;
	}
} 

class Square extends Shape1 implements Shape2d
{
	int side;
	Square(int side)
	{
		this.side = side ;
	}

	public double getArea()
	{
		return side * side;
	}
} 
class InterfaceCircleSquareDemo
{
	public static void main(String[] args) 
	{
		Circle c = new Circle (10);
		c.display("Circle");
		System.out.println("The area of the circle is  :" + c.getArea());

		Square s = new Square (10);
		s.display("Square");
		System.out.println("The area of the Square is  :" + s.getArea());
	}
}
