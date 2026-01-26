import java.awt.*;
import java.awt.event.*;

class RegistrationForm extends Frame implements ActionListener {

    TextField name, email;
    Checkbox male, female;
    Choice city;
    Button submit;
    Label output;

    RegistrationForm() {
        setLayout(new GridLayout(7, 2));

        add(new Label("Name:"));
        name = new TextField();
        add(name);

        add(new Label("Email:"));
        email = new TextField();
        add(email);

        add(new Label("Gender:"));
        CheckboxGroup cg = new CheckboxGroup();
        male = new Checkbox("Male", cg, true);
        female = new Checkbox("Female", cg, false);
        add(male); add(female);

        add(new Label("City:"));
        city = new Choice();
        city.add("Mumbai"); city.add("Pune"); city.add("Delhi");
        add(city);

        submit = new Button("Submit");
        submit.addActionListener(this);
        add(submit);

        output = new Label("");
        add(output);

        setSize(400, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        output.setText("Registered: " + name.getText());
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}
