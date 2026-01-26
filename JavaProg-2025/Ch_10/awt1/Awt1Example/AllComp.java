import java.awt.*;
class   AllComp extends Frame
{
	Label l;
	TextField t;
	Button b;
	Choice cc;
	CheckboxGroup cbg;
	Checkbox  cb,cb1,cb2;	
	List lt;
	Scrollbar sb;
    	MenuBar mbr;
	Menu file,edit,view;
	MenuItem miNew,miOpen,miClose,miCut,miCopy;
	
	AllComp()
	{
		setTitle("ALL Comp Demp programe !");
		setVisible(true);
		setSize(500,500);
		setLayout(new FlowLayout());
		//Add Label
		l=new Label("Enter a No");
		add(l);
		//Add TextField
		t=new TextField(10);
		add(t);
		// add Button
		b=new Button("OKK");
		add(b);
		//Choice;
		cc=new Choice();		
			cc.addItem("Mango");
			cc.addItem("Banana");
			cc.addItem("Pinaple");
			cc.addItem("Orange");
		add(cc);
		//add checkbox and checkboxgroup
   		cbg	 =new CheckboxGroup(); 
			cb=new Checkbox("10 th",cbg,true);
		    cb1=new Checkbox("12th",cbg,false);			
			cb2=new Checkbox("Graduates");   
		add(cb);
		add(cb1);		
		add(cb2);
		//add List
		lt=new List(2,false);
			lt.add("Kayboard");
			lt.add("Monitor");
			lt.add( "Mouse");
			lt.add("Printer");
		add(lt);
		//add scorll bar
		sb=new Scrollbar(Scrollbar.HORIZONTAL,125,20,0,255);
             add(sb);
        MenuBar mbr=new MenuBar();
				Menu file=new Menu("File");
				Menu edit=new Menu("Edit");
		 		Menu view=new Menu("View");
						 	MenuItem miNew=new MenuItem("New");
							MenuItem miOpen=new MenuItem("Open");
							MenuItem miClose=new MenuItem("Close");
		
							MenuItem miCopy=new MenuItem("Copy");
							MenuItem miCut=new MenuItem("Cut");
							file.add(miNew);
							file.add(miOpen);
							file.add(miClose);

							edit.add(miCopy);
							edit.add(miCut);
            mbr.add(file);
			mbr.add(edit);
			mbr.add(view);
			setMenuBar(mbr);

	}
	public static void main(String[] args) 
	{
		AllComp a=new AllComp();
		a.show();
	}
}
