import java.applet.Applet;
import java.awt.*;

public class TrafficSignal extends Applet implements Runnable {
    Thread t;
    int count;
    @Override
    public void init() {
        super.init();
        count = 1;
    }

    @Override
    public void paint(Graphics g) {

        if(count == 1){
            g.setColor(Color.RED);
            g.fillOval(100,150,50,50);
            g.setColor(Color.BLACK);
            g.fillOval(100,200,50,50);
            g.fillOval(100,250,50,50);
        }
        else if(count == 2){
            g.setColor(Color.ORANGE);
            g.fillOval(100,200,50,50);
            g.setColor(Color.BLACK);
            g.fillOval(100,150,50,50);
            g.fillOval(100,250,50,50);
        }
        else if(count == 3){
            g.setColor(Color.GREEN);
            g.fillOval(100,250,50,50);
            g.setColor(Color.BLACK);
            g.fillOval(100,150,50,50);
            g.fillOval(100,200,50,50);
        }

    }
    public void start()
    {
        t = new Thread(this,"Signal");
        t.start();
    }

    @Override
    public void run() {
            while(true){
                if(count == 3){
                    count = 1;
                }
                else{
                    count++;
                }
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
    }
}
