import java.awt.*;

public class BoundsDemo extends Frame {

    BoundsDemo() {
        setLayout(null);

        Button b = new Button("Button");
        b.setBounds(50, 50, 100, 40);
        add(b);

        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BoundsDemo();
    }
}
