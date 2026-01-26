import java.awt.*;

class GridLayoutDemo extends Frame {
    GridLayoutDemo() {
        setLayout(new GridLayout(3, 3,10,10));

        for (int i = 1; i <= 9; i++)
            add(new Button("Button " + i));

        setSize(300, 300);
        setVisible(true);

    }

    public static void main(String[] args) {
        new GridLayoutDemo();
    }
}

