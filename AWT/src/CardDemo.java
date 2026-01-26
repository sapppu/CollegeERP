import java.awt.*;
import java.awt.event.*;

public class CardDemo extends Frame implements ActionListener {

    CardLayout cl;
    Panel cards;
    Button b1, b2;

    public CardDemo() {
        cl = new CardLayout();
        cards = new Panel(cl);

        Panel p1 = new Panel();
        p1.add(new Label("Card 1"));

        Panel p2 = new Panel();
        p2.add(new Label("Card 2"));

        cards.add("c1", p1);
        cards.add("c2", p2);

        add(cards, BorderLayout.CENTER);

        Panel buttons = new Panel();
        b1 = new Button("Show Card 1");
        b2 = new Button("Show Card 2");
        buttons.add(b1); buttons.add(b2);
        add(buttons, BorderLayout.SOUTH);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(300, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) cl.show(cards, "c1");
        if (e.getSource() == b2) cl.show(cards, "c2");
    }

    public static void main(String[] args) {
        new CardDemo();
    }
}
