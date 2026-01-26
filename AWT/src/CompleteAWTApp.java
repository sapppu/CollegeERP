import java.awt.*;
import java.awt.event.*;

public class CompleteAWTApp extends Frame implements ActionListener, ItemListener, MouseListener, KeyListener {

    // Components
    Label lName, lGender, lCity, lSkills, lOutput;
    TextField tfName;
    Checkbox male, female;
    Choice cityChoice;
    List skillsList;
    Button submitBtn, clearBtn;
    TextArea outputArea;

    public CompleteAWTApp() {

        // Frame Layout
        setLayout(new FlowLayout());

        // --- NAME ---
        lName = new Label("Enter Name:");
        tfName = new TextField(20);

        add(lName);
        add(tfName);

        // --- GENDER ---
        lGender = new Label("Gender:");
        CheckboxGroup cg = new CheckboxGroup();
        male = new Checkbox("Male", cg, true);
        female = new Checkbox("Female", cg, false);

        add(lGender);
        add(male);
        add(female);

        male.addItemListener(this);
        female.addItemListener(this);

        // --- CITY CHOICE ---
        lCity = new Label("City:");
        cityChoice = new Choice();
        cityChoice.add("Mumbai");
        cityChoice.add("Pune");
        cityChoice.add("Delhi");
        cityChoice.add("Bangalore");

        cityChoice.addItemListener(this);

        add(lCity);
        add(cityChoice);

        // --- SKILLS LIST ---
        lSkills = new Label("Skills:");
        skillsList = new List(4, true);
        skillsList.add("Java");
        skillsList.add("Python");
        skillsList.add("C++");
        skillsList.add("SQL");

        add(lSkills);
        add(skillsList);

        // --- BUTTONS ---
        submitBtn = new Button("Submit");
        clearBtn = new Button("Clear");

        submitBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        add(submitBtn);
        add(clearBtn);

        // --- OUTPUT AREA ---
        outputArea = new TextArea(5, 40);
        add(outputArea);

        // --- MENU BAR ---
        MenuBar mb = new MenuBar();
        Menu file = new Menu("File");
        MenuItem resetItem = new MenuItem("Reset");
        MenuItem exitItem = new MenuItem("Exit");
        file.add(resetItem);
        file.add(exitItem);
        mb.add(file);
        setMenuBar(mb);

        resetItem.addActionListener(this);
        exitItem.addActionListener(this);

        // --- MOUSE & KEY LISTENERS ---
        addMouseListener(this);
        tfName.addKeyListener(this);

        // Frame Settings
        setTitle("Complete AWT GUI Application");
        setSize(500, 500);
        setVisible(true);

        // Close Window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }

    // -------- ACTION HANDLING --------
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("Submit")) {
            String name = tfName.getText();
            String gender = male.getState() ? "Male" : "Female";
            String city = cityChoice.getSelectedItem();
            String skills = String.join(", ", skillsList.getSelectedItems());

            outputArea.setText(
                    "===== USER DETAILS =====\n" +
                            "Name: " + name + "\n" +
                            "Gender: " + gender + "\n" +
                            "City: " + city + "\n" +
                            "Skills: " + skills + "\n"
            );
        }

        if (cmd.equals("Clear") || cmd.equals("Reset")) {
            tfName.setText("");
            cityChoice.select(0);
            skillsList.deselect(0);
            outputArea.setText("");
        }

        if (cmd.equals("Exit")) {
            System.exit(0);
        }
    }

    // -------- ITEM HANDLING --------
    public void itemStateChanged(ItemEvent e) {
        // Can add additional reactions if required
    }

    // -------- MOUSE EVENTS --------
    public void mouseClicked(MouseEvent e) {
        outputArea.append("\nMouse Clicked at (" + e.getX() + "," + e.getY() + ")");
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    // -------- KEY EVENTS --------
    public void keyTyped(KeyEvent e) {
        outputArea.append("\nTyped: " + e.getKeyChar());
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    // -------- MAIN --------
    public static void main(String[] args) {
        new CompleteAWTApp();
    }
}

