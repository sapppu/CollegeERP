/* Program illustrate Interface demo */


import java.io.*;
interface Shape2d
{
	double getArea();
}
class Shape1
{
	void display()
	{
		System.out.println("Name of the shape is Circle :");
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
class InterfaceDemo2
{
	public static void main(String[] args) 
	{
		Circle c = new Circle (10);
		c.display();
		System.out.println("The area of the circle is  :" + c.getArea());
	}
}
