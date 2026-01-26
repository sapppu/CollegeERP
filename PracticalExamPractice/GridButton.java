import java.awt.*;
import java.awt.event.*;

public class GridButton extends Frame implements ItemListener,ActionListener{
Checkbox ch1,ch2;
Panel top,center,bottom;
Button[] btns=new Button[9];
Label lbl = new Label("no selection");

TextField tf ;

GridButton(){
setTitle("GRID BUTTONS");
setSize(400,400);

setLayout(new BorderLayout());

top=new Panel();
center=new Panel();
center.setLayout(new GridLayout(3,3));
bottom=new Panel();

ch1=new Checkbox("CheckBox 1");
ch2=new Checkbox("CheckBox 2");
ch1.addItemListener(this);
ch2.addItemListener(this);
top.add(ch1);
top.add(ch2);

for(int i=0;i<9;i++){
btns[i]=new Button("Button"+(i+1));
btns[i].addActionListener(this);
btns[i].setBackground(Color.pink);
btns[i].setForeground(Color.blue);
center.add(btns[i]);
}

tf = new TextField("not selected",50);
bottom.add(tf);
bottom.add(lbl);

add(top,BorderLayout.NORTH);
add(center,BorderLayout.CENTER);
add(bottom,BorderLayout.SOUTH);

setVisible(true);
}

public void actionPerformed(ActionEvent e){
String action= e.getActionCommand();
tf.setText(action+"Clicked");
}

public void itemStateChanged(ItemEvent e){
if(ch1.getState()){
lbl.setText("CheckBox 1 selected");
}
else if(ch2.getState()){
lbl.setText("CheckBox 2 selected");
}
else{
lbl.setText("CheckBox not  selected");
}
}


public static void main(String args[]){
new GridButton();
}
}
