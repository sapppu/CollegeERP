import java.awt.*;
import java.awt.event.*;

public class CalculatorAWT extends Frame implements ActionListener
{
    TextField t1, t2, t3;
    Button b1, b2, b3, b4;

    CalculatorAWT()
    {
    //    super("Simple AWT Calculator");
        setLayout(new FlowLayout());

        t1 = new TextField(20);
        t2 = new TextField(20);
        t3 = new TextField(20);

        b1 = new Button("+");
        b2 = new Button("-");
        b3 = new Button("*");
        b4 = new Button("/");

        add(t1);
        add(t2);
        add(t3);
        add(b1);
        add(b2);
        add(b3);
        add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(300, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        try {
            double a = Double.parseDouble(t1.getText());
            double b = Double.parseDouble(t2.getText());
            double c = 0;

            if (e.getSource() == b1) {
                c = a + b;
            }
            else if (e.getSource() == b2) {
                c = a - b;
            }
            else if (e.getSource() == b3) {
                c = a * b;
            }
            else if (e.getSource() == b4) {
                if (b == 0) {
                    t3.setText("Cannot divide by 0");
                    return;
                }
                c = a / b;
            }

            t3.setText("" + c);
        }
        catch (NumberFormatException ex) {
            t3.setText("Invalid Input");
        }
    }

    public static void main(String args[])
    {
        new CalculatorAWT();
    }
}

