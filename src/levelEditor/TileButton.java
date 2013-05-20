package levelEditor;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public class TileButton extends JToggleButton implements ActionListener,MouseListener {
	Tile myTile;
	GraphicsBank myGraphicsBank;
	TileChooser myTileChooser;
	
	public TileButton(Tile t, GraphicsBank gBank, TileChooser chooser) {
		super();

		myGraphicsBank = gBank;
		myTileChooser = chooser;
		
		Image i2 = new BufferedImage(myGraphicsBank.getBaseTileSize().width,
				myGraphicsBank.getBaseTileSize().height,
				BufferedImage.TYPE_INT_ARGB);

		if (t != null) {
			Image i = t.getImage();
			i2.getGraphics().drawImage(i, 0, 0, 32, 32, null);
			setToolTipText(t.getName());
		}

		setIcon(new ImageIcon(i2));

		setMargin(new Insets(2, 2, 2, 2));
		myTile = t;

		this.addMouseListener(this);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		myTileChooser.setSelectedTile(myTile);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			myTileChooser.showProperties(myTile);
		}
	}

	public Tile getTile() {
		return myTile;
	}

}
