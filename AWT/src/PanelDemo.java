import java.awt.*;
import java.awt.event.*;

public class PanelDemo extends Frame {

    public PanelDemo() {
        setTitle("Panel Demo");
        setSize(400, 300);
        setLayout(new BorderLayout());

        Panel topPanel = new Panel();
        topPanel.setBackground(Color.lightGray);
        topPanel.setLayout(new FlowLayout());
        topPanel.add(new Label("Top Panel"));
        topPanel.add(new Button("OK"));

        Panel centerPanel = new Panel();
        centerPanel.setBackground(Color.white);
        centerPanel.setLayout(new GridLayout(2, 2));
        centerPanel.add(new Button("1"));
        centerPanel.add(new Button("2"));
        centerPanel.add(new Button("3"));
        centerPanel.add(new Button("4"));

        Panel bottomPanel = new Panel();
        bottomPanel.setBackground(Color.gray);
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(new Label("Bottom Panel"));

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new PanelDemo();
    }
}
