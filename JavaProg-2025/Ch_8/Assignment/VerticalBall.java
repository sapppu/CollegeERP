//import java.io.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.applet.*;
///*<applet code=VerticalBall width=300 height=300>
//</applet>*/
//public class VerticalBall extends Applet
//{
//    // switch for stooping ball
//    boolean done=false;
//    BouncingBallThread thread;
//
//    public void init()
//    {
//        int radius=20;
//        Color color=Color.RED;
//        System.out.println(getHeight());
//        thread=new BouncingBallThread(getWidth()/2-radius/2,getHeight()-radius,radius,color);
//    }
//    public void start()
//    {
//        thread.start();
//    }
//    public void stop()
//    {
//        done=true;
//    }
//    // inner thread
//    public class BouncingBallThread extends Thread
//    {
//        Point top,bottom,current;
//        Color color;
//        int radius;
//        boolean down=true;
//    }
//    public void BouncingBallThread(int xCoor,int height,int radius,Color color )
//    {
//        this.current=new Point(xCoor,0);
//        this.top=new Point(xCoor,0);
//        this.button=new Point(xCoor,height);
//        this.radius=radius;
//        this.color=color;
//    }
//    public void run()
//    {
//        Graphics g=getGraphics();
//        while(!done)
//        {
//            g.setColor(Color.white);
//            g.fillOval(current.x,current.y,radius,radius);
//            if(down)
//            {
//                int t=current.y+5;
//                if(t>bottom.y)
//                    down=false;
//                else
//                    current.y=t;
//            }
//            else
//            {
//                int t=current.y-5;
//                if(t<top.y)
//                {
//                    down=true;
//                }
//                else
//                {
//                    current.y=t;
//                }
//            }
//            g.setColor(color);
//            g.fillOval(current.x,current.y,radius,radius);
//
//            try
//            {
//                Thread.sleep(500);
//            }
//            catch(InterruptedException e){}
//        }
//    }
//}