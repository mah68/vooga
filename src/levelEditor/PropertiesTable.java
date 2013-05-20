package levelEditor;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PropertiesTable extends JDialog implements ActionListener{

	private JSpinner tileNumber;
	private JTextField userText;
	private JTextField tileName;
	private JLabel tileImg;
	private JButton applyBtn;
	private JButton cancelBtn;
	private JButton deleteBtn;
	private JTextField imageFile;
	private JComboBox classList;
	
	private DraggableImage myDraggableImage;
	
	public PropertiesTable(){
		super();
		this.setSize(300, 400);
		this.setLocationRelativeTo(null);
	}
	
	public void initialize(){
		tileName = new JTextField("", 20);
		imageFile = new JTextField("", 20);
		imageFile.setEditable(false);
		tileNumber = new JSpinner();
		userText = new JTextField("", 20);

		tileImg = new JLabel();
		tileImg.setHorizontalAlignment(SwingConstants.CENTER);
		tileImg.setBorder(new TitledBorder("Image"));

		JPanel cp = (JPanel) this.getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel p2 = new JPanel(new BorderLayout());
		cp.add(p2, BorderLayout.CENTER);
		cp.add(tileImg, BorderLayout.NORTH);

		JPanel btns1 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3, 3, 3, 3);

		c.gridx = 0;
		c.gridy = 0;

		btns1.add(new JLabel("ID"), c);
		c.gridx = 1;
		c.ipadx = 30;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		btns1.add(tileNumber, c);
		c.ipadx = 0;

		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 1;
		btns1.add(classList);
		
		c.gridx = 0;
		c.gridy = 2;
		btns1.add(new JLabel("Name"), c);
		c.gridx = 1;
		btns1.add(tileName, c);

		c.gridx = 0;
		c.gridy = 3;
		btns1.add(new JLabel("User Text"), c);
		c.gridx = 1;
		btns1.add(userText, c);

		p2.add(btns1, BorderLayout.NORTH);
		
		/* The buttons */
		applyBtn = new JButton("Save");
		deleteBtn = new JButton("Delete Tile");
		cancelBtn = new JButton("Cancel");
		applyBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		
		JPanel btns2 = new JPanel(new GridLayout(1, 3));
		btns2.add(deleteBtn);
		btns2.add(applyBtn);
		btns2.add(cancelBtn);

		c.gridwidth = 2;
		c.gridy = 6;
		
		btns1.add(btns2, c);

		this.setSize(300, 500);
		this.setResizable(false);
	}

	public void showProperties(DraggableImage obj) {
		myDraggableImage = obj;
		if (obj != null) {
			System.out.println(obj.getInfo());
			userText.setText(obj.getInfo());
			tileNumber.setValue(new Integer(obj.getNumber()));
			tileName.setText(obj.getName());
			for(int i = 0; i < classList.getItemCount(); i++){
				String temp = (String) classList.getItemAt(i);
				if(temp.equals(obj.getType())){
					classList.setSelectedIndex(i);
				}
			}
			tileImg.setIcon(new ImageIcon(obj.getImage()));

			applyBtn.setEnabled(true);
			deleteBtn.setEnabled(true);
			userText.setEditable(true);
			tileNumber.setEnabled(true);
			tileName.setEditable(true);
		} else {
			userText.setText("");
			tileNumber.setValue(new Integer(0));
			tileName.setText("Null (Erases existing tiles)");
			tileImg.setIcon(null);

			userText.setEditable(false);
			tileNumber.setEnabled(false);
			tileName.setEditable(false);
			applyBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
		}
		this.pack();
		this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
