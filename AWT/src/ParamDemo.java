import java.awt.*;

class ParamDemo extends Frame {

    ParamDemo(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ParamDemo("Parameter Demo Frame", 400, 300);
    }
}
