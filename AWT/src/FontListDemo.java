import java.awt.*;

public class FontListDemo extends Frame {

    FontListDemo() {
        List fontList = new List(10);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String f : fonts) fontList.add(f);

        add(fontList);

        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FontListDemo();
    }
}
