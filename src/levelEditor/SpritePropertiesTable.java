package levelEditor;

import java.awt.Dimension;
import java.util.Map;


import javax.swing.JTable;
import javax.swing.event.TableModelListener;

@SuppressWarnings("serial")
public class SpritePropertiesTable extends JTable{

	static Object[][] temp = { {"X Velocity","", }, {"Y Velocity", " ", }};
	private SpritePropertiesTable myTable;
	
	public SpritePropertiesTable() {
		super(temp, setColumnNames());
		this.setPreferredSize(new Dimension(50,50));
		myTable = this;
	}

	public SpritePropertiesTable(Object[][] data, String[] col){
		super(data, col);
		this.setPreferredSize(new Dimension(50,50));
		myTable = this;
	}
	
	
	private static Object[][] setData(Map<String, Object> map) {
		Object[][] data = new Object[map.keySet().size()][3];
		for(int i = 0;i < map.keySet().size(); i++){
			data[i][0] = ((Map<String, Object>) map.keySet()).get(i);
			data[i][1] = map.get(((Map<String, Object>) map.keySet()).get(i));
		}
		return data;
	}

	
	
	private static String[] setColumnNames() {
		String[] columnNames = { "Sprite Properties", "Sprite Values", };
		return columnNames;
	}

	private void readDraggableImageProperties(DraggableImage image){
		Map<String, Object> attributeMap = image.getAttributeMap();
		Object[][] data = setData(attributeMap);
		SpritePropertiesTable newTable = new SpritePropertiesTable(data, setColumnNames());
		myTable = newTable;
	}
	
	public SpritePropertiesTable getSpritePropertiesTable(){
		return myTable;
	}
	
}
