import java.awt.*;
import java.awt.event.*;

public class GB extends Frame implements ActionListener,ItemListener{
Checkbox c1,c2;
Panel t,c,b;
Button[] bt=new Button[9];
Label l;
TextField tf;

GB(){
setLayout(new BorderLayout());
setTitle("Buttons");

t=new Panel();
c=new Panel();
b=new Panel();

c.setLayout(new GridLayout(3,3));

for(int i=0;i<9;i++){
    bt[i]=new Button("Button : "+(i+1));
    bt[i].addActionListener(this);
    c.add(bt[i]);
}
tf=new TextField(30);
l=new Label("chk");
b.add(tf);
b.add(l);

    c1=new Checkbox("CHK 1");
    c2=new Checkbox("CHK 2");
    t.add(c1);
    t.add(c2);
add(t,BorderLayout.NORTH);
add(c,BorderLayout.CENTER);
add(b,BorderLayout.SOUTH);
setSize(400,300);
setVisible(true);
}
    public void actionPerformed(ActionEvent e){
        String act=e.getActionCommand();
        tf.setText("Button"+act);
    }

    public void itemStateChanged(ItemEvent e){
        if(c1.getState()) {l.setText("Button 1 selected");}
        else{l.setText("Button 2 selected");}
    }
public static void main(String[] args) {
        new GB();
}
}
