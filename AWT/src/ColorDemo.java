import java.awt.*;
import java.awt.event.*;

public class ColorDemo extends Frame implements AdjustmentListener {

    Scrollbar r, g, b;
    Panel p;

    ColorDemo() {
        setLayout(new GridLayout(2,1));

        Panel sliders = new Panel(new GridLayout(3,1));
        r = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
        g = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
        b = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);

        sliders.add(r); sliders.add(g); sliders.add(b);

        r.addAdjustmentListener(this);
        g.addAdjustmentListener(this);
        b.addAdjustmentListener(this);

        p = new Panel();
        add(sliders);
        add(p);

        setSize(400, 200);
        setVisible(true);
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        p.setBackground(new Color(r.getValue(), g.getValue(), b.getValue()));
    }

    public static void main(String[] args) {
        new ColorDemo();
    }
}
