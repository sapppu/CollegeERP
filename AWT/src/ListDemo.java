import java.awt.*;
import java.awt.event.*;

class ListDemo extends Frame implements ActionListener {
    List list;
    Button b;
    Label output;

    ListDemo() {
        setLayout(new FlowLayout());

        list = new List(5, true);
        list.add("Java");
        list.add("C");
        list.add("C++");
        list.add("Python");
        list.add("JavaScript");

        b = new Button("Show Selected");
        output = new Label("Selected items will appear here");

        b.addActionListener(this);

        add(list);
        add(b);
        add(output);

        setSize(300, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String selected[] = list.getSelectedItems();
        output.setText("Selected: " + String.join(", ", selected));
    }

    public static void main(String[] args) {
        new ListDemo();
    }
}
