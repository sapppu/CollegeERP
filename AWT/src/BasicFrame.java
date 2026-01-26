import java.awt.*;

class BasicFrame extends Frame {
    BasicFrame() {
        setTitle("Basic AWT Frame");
        setSize(300, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new BasicFrame();
    }
}
