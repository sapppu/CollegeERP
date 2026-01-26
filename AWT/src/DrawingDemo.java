import java.awt.*;

class DrawingDemo extends Frame {

    public void paint(Graphics g) {
        g.drawLine(50, 50, 200, 50);
        g.drawRect(50, 80, 150, 80);
        g.drawOval(50, 180, 100, 100);
    }

    DrawingDemo() {
        setSize(300, 350);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawingDemo();
    }
}
