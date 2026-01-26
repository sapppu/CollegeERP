import java.applet.Applet;
import java.awt.*;


/* <applet code = "GridBagDemo" width=200 height=200>
   </applet>
*/
public class GridBagDemo extends Applet
{
	Button b1,b2,b3,b4,b5,b6;
	GridBagLayout gb1;
	GridBagConstraints gbc;
	public void init()
	{
		gb1 = new GridBagLayout();
		setLayout(gb1);
		gbc = new GridBagConstraints();

		b1 = new Button("FirstButton");
		gbc.fill = GridBagConstraints.BOTH; // to fill the entire display area
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 1;
		gbc.weightx = 1.00;
		gb1.setConstraints (b1,gbc);
		add(b1);

		b2 = new Button("SecondButton");
		gbc.gridwidth = GridBagConstraints.REMAINDER; // To fill remainder display area
		gb1.setConstraints (b2,gbc);
		add(b2);

		b3 = new Button("ThirdButton");
		gbc.weightx = 0.0;	// To specify horizontal stretch of the component . Default value  = 0
		gbc.weighty = 1.0;	// To specify Vertical stretch of the component . Default value  = 0
		gbc.gridheight = 2;	// To specify no of rows in the display area for add the component
		gbc.gridwidth = 1;	// To specify no of Columns in the display area for add the component
		gb1.setConstraints (b3,gbc);
		add(b3);

		b4 = new Button("FourthButton");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gb1.setConstraints (b4,gbc);
		add(b4);

		b5 = new Button("FifthButton");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gb1.setConstraints (b5,gbc);
		add(b5);

		b6 = new Button("SixButton");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gb1.setConstraints (b6,gbc);
		add(b6);

	}

}








