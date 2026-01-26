import java.awt.*;
import java.awt.event.*;

public class Panel1 extends Frame{
Panel1(){
setTitle("Swapnaj Panel");
setSize(200,300);

setLayout(new BorderLayout());
Panel topP=new Panel();
Panel leftP=new Panel();
Panel rightP=new Panel();
Panel bottomP=new Panel();

topP.setBackground(Color.gray);
bottomP.setBackground(Color.gray);
leftP.setBackground(Color.red);
rightP.setBackground(Color.green);
topP.setLayout(new FlowLayout());

leftP.setLayout(new FlowLayout());
rightP.setLayout(new FlowLayout());

add(topP,BorderLayout.NORTH);
add(bottomP,BorderLayout.SOUTH);
add(leftP,BorderLayout.WEST);
add(rightP,BorderLayout.EAST);

topP.add(new Label("Top Panel"));
bottomP.add(new Label("Bottom Panel"));
leftP.add(new Label("LeftPanel"));
rightP.add(new Label("Right Panel"));

addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent e){
dispose();
}
});
setVisible(true);
}
public static void main(String[] args){
new Panel1();
}
}
