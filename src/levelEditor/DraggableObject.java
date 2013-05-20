package levelEditor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class DraggableObject extends JComponent {

	private boolean draggable = true;
	private boolean snapToGrid = true;
	protected Point anchorPoint;

	protected boolean overbearing = false;
	final DraggableObject handle = this;

	private int xCoordinate = 0;
	private int yCoordinate = 0;

	protected DraggableObject myObject;

	public DraggableObject() {
		addMouseMotionListener();
		addMouseListener();
		setOpaque(true);
		setBackground(new Color(240, 240, 240));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}

	private void removeMouseMotionListener() {
		for (MouseMotionListener listener : this.getMouseMotionListeners()) {
			removeMouseMotionListener(listener);
		}
		setCursor(Cursor.getDefaultCursor());
	}

	public boolean isDraggable() {
		return draggable;
	}

	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
		if (draggable) {
			addMouseMotionListener();
			addMouseListener();
		} else {
			removeMouseMotionListener();
		}

	}

	public boolean isOverbearing() {
		return overbearing;
	}

	public void setOverbearing(boolean overbearing) {
		this.overbearing = overbearing;
	}

	public void setCorrdinates(int x, int y) {
		xCoordinate = x;
		yCoordinate = y;
	}

	public int getMyX() {
		return xCoordinate;
	}

	public int getMyY() {
		return yCoordinate;
	}

	private void addMouseMotionListener() {
		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				anchorPoint = e.getPoint();
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int anchorX = anchorPoint.x;
				int anchorY = anchorPoint.y;

				Point parentOnScreen = getParent().getLocationOnScreen();
				Point mouseOnScreen = e.getLocationOnScreen();
				Point position = new Point(mouseOnScreen.x - parentOnScreen.x
						- anchorX, mouseOnScreen.y - parentOnScreen.y - anchorY);
				setLocation(position);
			}

		});
	}

	private void addMouseListener() {
		addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {
				int anchorX = anchorPoint.x;
				int anchorY = anchorPoint.y;

				Point parentOnScreen = getParent().getLocationOnScreen();
				Point mouseOnScreen = e.getLocationOnScreen();

				int newX = (int) (32 * (Math.round((mouseOnScreen.x
						- parentOnScreen.x - anchorX) / 32.0)));
				int newY = (int) (32 * (Math.round((mouseOnScreen.y
						- parentOnScreen.y - anchorY) / 32.0)));

				Point position = new Point();

				if (snapToGrid) {
					position = new Point(newX, newY);
					setCorrdinates(position.x, position.y);
				} else {
					position = new Point(mouseOnScreen.x - parentOnScreen.x
							- anchorX, mouseOnScreen.y - parentOnScreen.y
							- anchorY);
					setCorrdinates(position.x, position.y);
				}

				setLocation(position);
			}
		});
	}

}
