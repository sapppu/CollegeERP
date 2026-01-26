import java.awt.*;
import java.awt.event.*;

public class AdapterDemo extends Frame {

	public AdapterDemo() {
		setSize(300, 200);
		setBackground(Color.yellow);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setBackground(Color.red);
			}

			public void mouseReleased(MouseEvent e) {
				setBackground(Color.green);
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new AdapterDemo();
	}
}
