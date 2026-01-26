import java.awt.*;
import java.awt.event.*;

public class RadioDemo extends Frame implements ItemListener {

    CheckboxGroup group;
    Checkbox male, female;
    Label lbl;

    RadioDemo() {
        setLayout(new FlowLayout());

        group = new CheckboxGroup();
        male = new Checkbox("Male", group, false);
        female = new Checkbox("Female", group, false);
        lbl = new Label("Select gender");

        add(male); add(female); add(lbl);

        male.addItemListener(this);
        female.addItemListener(this);

        setSize(300, 150);
        setVisible(true);
    }

    public void itemStateChanged(ItemEvent e) {
        lbl.setText("Selected: " + group.getSelectedCheckbox().getLabel());
    }

    public static void main(String[] args) {
        new RadioDemo();
    }
}
