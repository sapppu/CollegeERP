import java.awt.*;
import java.awt.event.*;

public class SimpleAWTUI extends Frame implements ItemListener, ActionListener {

    Checkbox cb1, cb2;
    Label lbl;
    TextField tf;
    Button[] btns = new Button[9];

    public SimpleAWTUI() {
        setLayout(new BorderLayout());

        // Top Panel - Checkboxes and Label
        Panel top = new Panel();
        top.setLayout(new FlowLayout());

        cb1 = new Checkbox("Checkbox 1");
        cb2 = new Checkbox("Checkbox 2");
        lbl = new Label("Label: None selected");

        top.add(cb1);
        top.add(cb2);


        add(top, BorderLayout.NORTH);

        cb1.addItemListener(this);
        cb2.addItemListener(this);

        // Center Panel - 3x3 Grid for Buttons
        Panel center = new Panel();
        center.setLayout(new GridLayout(3, 3));

        for(int i = 0; i < 9; i++) {
            btns[i] = new Button("Button " + (i + 1));
            btns[i].addActionListener(this);
            center.add(btns[i]);
        }

        add(center, BorderLayout.CENTER);

        // Bottom Panel - TextField
        Panel bottom = new Panel();
        tf = new TextField("No Button Selected", 30);
        bottom.add(tf);
        bottom.add(lbl);
        add(bottom, BorderLayout.SOUTH);

        // Frame settings
        setTitle("AWT Multiple Component Demo");
        setSize(450, 350);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
    }

    public void itemStateChanged(ItemEvent e) {
        if(cb1.getState())
            lbl.setText("Checkbox 1 Selected");
        else if(cb2.getState())
            lbl.setText("Checkbox 2 Selected");
        else
            lbl.setText("Label: None Selected");
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        tf.setText(cmd + " Clicked");
    }

    public static void main(String[] args) {
        new SimpleAWTUI();
    }
}
