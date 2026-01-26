/* Write a multithreaded java application which will print
	1. All number divisible by 8 from 1 to 100
	2. Even Numbers between 51 To 100
	3. Print "Java is Awesome" 10 times
*/	

class Thread_bivby8 extends Thread
	{
  		  @Override
    		public void run() 
		{
        			for(int i=1;i<100;i++)
        			{
            				if(i % 8 == 0)
           			 	{
                					System.out.printf("\n No is: %d",i);
            				}
        			}
        			System.out.printf("\n Thread 1 finished");
    		}
	}

class Even_number extends Thread
	{
    		@Override
    		public void run() 
		{
        			for(int i =51 ;i< 100; i++)
        			{
            				if(i % 2 == 0)
            				{
                					System.out.printf("\n Even Number is: %d",i);
            				}
        			}
        			System.out.printf("\n Thread 2 finished");
    		}
	}

class print extends Thread
	{
    		String s;
    		Thread t;
    		print(String s)
    		{
			
        				t = new Thread(this,s);
        				t.start();
				this.s = s;
			
    		}

    		@Override
    		public void run() 
		{
        			for(int i=0;i<10;i++)
        			{
            				System.out.printf("\n %s",Thread.currentThread());
        			}
        			System.out.printf("\n Thread 3 finished");
   		 }
	}

public class Multithreadingdivby8 
	{
    		public static void main(String[] args) 
		{
			try
			{
       				Thread_bivby8 t1 = new Thread_bivby8();
        				t1.start();
        				Even_number e = new Even_number();
        				e.start();
        				print p = new print("Java is Awesome");
				//p.sleep(6000);
			}
			catch(InterruptedException e1)
			{
				System.out.printf("Thread is interrupted"+e1);
			}
   		 }
	}
