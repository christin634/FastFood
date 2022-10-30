package view.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Vector;

import javax.swing.CellEditor;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import bean.Food;
import util.ConstUtil;

public class FoodTable extends MyTable    //实现自定义类
{
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
//    private String brand;
//    private List<Food> foods=new ArrayList<>();
    //ConstUtil.food_columnNames
    //	序号，名称，单价
//    private Vector<String> columnNames= new Vector<>();
    private Vector<Vector<Object>> rowData=new Vector<Vector<Object>>();
    private DefaultTableModel dtm ;
    
    public FoodTable(List<Food> foods)
    {
      	setDtm(foods);
		setModel(dtm);
      	this.setRowHeight(50);
      	this.getColumnModel().getColumn(0).setMinWidth(60);
      	this.getColumnModel().getColumn(1).setMinWidth(400);
      	this.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //单选
		this.setSelectionBackground(new Color(176,196,222));
		this.setSelectionForeground(Color.BLACK);
    }
    
    public void setDtm(List<Food> foods) {
    	rowData.clear();
      	for(int i=0;i<foods.size();i++) {//设置内容
      		Vector<Object> v = new Vector<>();
      		v.add(i+1);
      		v.add(foods.get(i).getName());
      		v.add(foods.get(i).getPrice());
      		rowData.add(v);
      	}
      	dtm = new DefaultTableModel(rowData,ConstUtil.food_columnNames){
			@Override  
			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		};
		setModel(dtm);
    }
    
}