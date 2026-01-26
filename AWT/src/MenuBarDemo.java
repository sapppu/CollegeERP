import java.awt.*;
import java.awt.event.*;

class MenuBarDemo extends Frame implements ActionListener {
    Label msg;

    MenuBarDemo() {
        msg = new Label("Choose a menu item");
        add(msg);

        MenuBar mb = new MenuBar();
        Menu file = new Menu("File");

        MenuItem newItem = new MenuItem("New");
        MenuItem exitItem = new MenuItem("Exit");

        newItem.addActionListener(this);
        exitItem.addActionListener(this);

        file.add(newItem);
        file.add(exitItem);

        mb.add(file);
        setMenuBar(mb);

        setLayout(new FlowLayout());
        setSize(300, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Exit"))
            System.exit(0);
        msg.setText("Selected: " + e.getActionCommand());
    }

    public static void main(String[] args) {
        new MenuBarDemo();
    }
}
