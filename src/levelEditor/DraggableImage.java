package levelEditor;

import hudDisplay.Stat;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.jdom2.Element;

@SuppressWarnings("serial")
public class DraggableImage extends DraggableObject{

	private Map<String, Stat> myStats = new HashMap<String,Stat>();	
		
	String myName = null;
	int myNumber = -1;
	String myClassType = null;
	String myImageFilePath = null;
	String myInfo = null;
	String mySpriteGroup;
	private Image image = null;
	private boolean autoSize = false;
	private Dimension autoSizeDimension = new Dimension(0, 0);
	private Map<String, Object> attributeMap = new HashMap<String, Object>();		
	
	public DraggableImage(){
		super();
	}
	
	public DraggableImage(int num, String name, String info, String path, String className, Image img) {
		super();
		setLayout(null);		
		myNumber = num;
		myName = name;
		myInfo = info;
		myImageFilePath = path;
		myClassType = className;
		image = img;
		
		attributeMap.put("myNumber", myNumber);
		attributeMap.put("myName", myName);
		attributeMap.put("myImageFilePath", myImageFilePath);
		attributeMap.put("myClassType", myClassType);
		attributeMap.put("myInfo", myInfo);
		attributeMap.put("image", image);
		attributeMap.put("mySpriteGroup", mySpriteGroup);
	}
	
	public String toString(){
		return "Number: "+myNumber + " Name: "+myName+" Class: "+myClassType;
	}
	
	public Map<String,Object> getAttributeMap(){
		return attributeMap;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, getWidth(), getHeight());
		if (image != null) {
			setAutoSizeDimension();
			g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		} else {
			g2d.setColor(getBackground());
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
	}

	private Dimension adaptDimension(Dimension source, Dimension dest) {
		int sW = source.width;
		int sH = source.height;
		int dW = dest.width;
		int dH = dest.height;
		double ratio = ((double) sW) / ((double) sH);
		if (sW >= sH) {
			sW = dW;
			sH = (int) (sW / ratio);
		} else {
			sH = dH;
			sW = (int) (sH * ratio);
		}
		return new Dimension(sW, sH);
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w,
			int h) {
		if (infoflags == ALLBITS) {
			repaint();
			setAutoSizeDimension();
			return false;
		}
		return true;
	}

	private void setAutoSizeDimension() {
		if (!autoSize) {
			return;
		}
		if (image != null) {
			if (image.getHeight(null) == 0 || getHeight() == 0) {
				return;
			}
			if ((getWidth() / getHeight()) == (image.getWidth(null) / (image
					.getHeight(null)))) {
				return;
			}
			autoSizeDimension = adaptDimension(
					new Dimension(image.getWidth(null), image.getHeight(null)),
					this.getSize());
			setSize(autoSizeDimension.width, autoSizeDimension.height);
		}
	}

	public Element writeElement() {
		Element sprite= new Element("sprite");
		sprite.addContent(new Element("class").addContent(myClassType));
		sprite.addContent(new Element("image").addContent(myImageFilePath));
		sprite.addContent(new Element("group").addContent(mySpriteGroup));
		sprite.addContent(new Element("x").addContent(getMyX() + ""));
		sprite.addContent(new Element("y").addContent(getMyY()+ ""));		
		if (this.myStats==null ||this.myStats.isEmpty()) {
			return sprite;
		} else {
			for (String key: myStats.keySet()) {
				Element e= new Element(key).addContent(myStats.get(key).toString());
				sprite.addContent(e);
			}
			return sprite;
		}
	}
	
	
	public void grow(int pixels) {
		double ratio = getWidth() / getHeight();
		setSize(getSize().width + pixels,
				(int) (getSize().height + (pixels / ratio)));
	}

	public boolean isAutoSize() {
		return autoSize;
	}

	public void setAutoSize(boolean autoSize) {
		this.autoSize = autoSize;
	}

	public void setImage(String image) {
		setImage(Toolkit.getDefaultToolkit().getImage(image));
	}

	public void setImage(Image image) {
		this.image = image;
		repaint();
		setAutoSizeDimension();
	}
	
	public Image getImage() {
		return image;
	}

	public String getType() {
		return myClassType;
	}

	public int getNumber() {
		return myNumber;
	}

	@Override
	public String getName() {
		return myName;
	}

	public String getInfo() {
		return myInfo;
	}

	public String getPath() {
		return myImageFilePath;
	}

}
