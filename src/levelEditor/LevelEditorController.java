package levelEditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class LevelEditorController {

	private LevelEditorModel myModel;
	private LevelEditorView myView;
	private LevelEditorController myController;
	private Map<String, Object> attributeMap;

	public LevelEditorController(LevelEditorModel model){
		myController = this;
		myModel = model;

		myView = new LevelEditorView(myController, myModel);
		attributeMap = new HashMap<String, Object>();
	}

	public void loadXMLFile(File file) {

	}

	public void writeToXMLFile() {
		Document doc = new Document();
		Element root=new Element("root").setText("This is the root");
		doc.addContent(root);
		Element background= new Element("background").setText(myModel.getBackground());
		root.addContent(background);

		Element sprites = new Element("sprites");
		root.addContent(sprites);
		
		for (int i=0; i<myModel.getAllDraggableImages().size(); i++){
			Element sprite = myModel.getAllDraggableImages().get(i).writeElement();
			sprites.addContent(sprite);
		}
	    try {
		      XMLOutputter outputter = new XMLOutputter();
		      Format oFormat = Format.getPrettyFormat();
		      outputter.setFormat(oFormat);
		      FileOutputStream FileOS = new  FileOutputStream("level1.xml");
		      OutputStreamWriter OSWriter = new  OutputStreamWriter(FileOS);
		      outputter.output(doc, OSWriter);
		      OSWriter.close();
		      FileOS.close();
		    }
		    catch (IOException e) {
		      System.err.println(e);
		    }
	}

	public void createNewLevel(File file) {

	}

	public void addDraggableImage(String filePath) {
		myView.addDraggableImagetoView(filePath);
	}


	public void setBackground(String filePath) {
		myModel.setBackground(filePath);
		updateView();
	}

	public void updateView(){
		myView.updateView(myModel);
	}

	public void addDraggableImagetoController(int x, int y, Tile currentTile) {
		myModel.addDraggableImagetoModel(x,y, currentTile);
		myView.addDraggableImagetoView(x, y, convertTiletoDraggableIamge(currentTile));
	}

	public DraggableImage convertTiletoDraggableIamge(Tile t){
		DraggableImage img = new DraggableImage(t.number, t.name, t.info, t.path, t.className, t.image);
		return img;
	}

}
