import java.awt.*;
import java.awt.event.*;

class RadioCheckboxExample extends Frame implements ItemListener {
    Checkbox male, female;
    CheckboxGroup cg;
    Label output;

    RadioCheckboxExample() {
        setLayout(new FlowLayout());

        cg = new CheckboxGroup();
        male = new Checkbox("Male", cg, true);
        female = new Checkbox("Female", cg, false);

        output = new Label("Selected: Male");

        male.addItemListener(this);
        female.addItemListener(this);

        add(male);
        add(female);
        add(output);

        setSize(300, 200);
        setVisible(true);
    }

    public void itemStateChanged(ItemEvent e) {
        output.setText("Selected: " + cg.getSelectedCheckbox().getLabel());
    }

    public static void main(String[] args) {
        new RadioCheckboxExample();
    }
}
