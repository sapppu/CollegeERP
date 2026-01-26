import java.awt.*;
import java.awt.event.*;

class ChoiceDemo extends Frame implements ItemListener {
    Choice choice;
    Label label;

    ChoiceDemo() {
        setLayout(new FlowLayout());

        choice = new Choice();
        choice.add("Red");
        choice.add("Green");
        choice.add("Blue");

        label = new Label("Choose a color");

        choice.addItemListener(this);

        add(choice);
        add(label);

        setSize(300, 200);
        setVisible(true);
    }

    public void itemStateChanged(ItemEvent e) {
        label.setText("Selected: " + choice.getSelectedItem());
    }

    public static void main(String[] args) {
        new ChoiceDemo();
    }
}
