import java.awt.*;
import java.awt.event.*;

public class ScrollBarDemo extends Frame implements AdjustmentListener {

    Scrollbar sb,sb2;
    Label lbl,lbl2;

    ScrollBarDemo() {
        sb = new Scrollbar(Scrollbar.HORIZONTAL, 0, 10, 0, 110);
        sb2= new Scrollbar(Scrollbar.VERTICAL,0,5,0,50);
        lbl = new Label("Value: 0");
        lbl2 = new Label("Value: 0");

        sb.addAdjustmentListener(this);
        sb2.addAdjustmentListener(this);

        add(sb, BorderLayout.SOUTH);
        add(sb2, BorderLayout.EAST);
        add(lbl, BorderLayout.NORTH);
        add(lbl2, BorderLayout.WEST);

        setSize(400, 150);
        setVisible(true);
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        lbl.setText("Value: " + sb.getValue());
        lbl2.setText("Value: " + sb2.getValue());
    }

    public static void main(String[] args) {
        new ScrollBarDemo();
    }
}
