package view.util;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bean.Order;
import util.ConstUtil;

public class OrderTable extends MyTable {

	private Vector<Vector<Object>> rowData=new Vector<Vector<Object>>();
    private DefaultTableModel dtm ;
    
    public OrderTable(List<Order> orders)
    {
      	setDtm(orders);
		setModel(dtm);
      	this.setRowHeight(40);
      	this.getColumnModel().getColumn(0).setMinWidth(120);
      	this.getColumnModel().getColumn(1).setMinWidth(140);
      	this.getColumnModel().getColumn(2).setMinWidth(460);
      	this.getColumnModel().getColumn(3).setMinWidth(100);
      	this.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //单选
		this.setSelectionBackground(new Color(176,196,222));
		this.setSelectionForeground(Color.BLACK);
    }
    
    public void setDtm(List<Order> orders) {
    	rowData.clear();
      	for(int i=0;i<orders.size();i++) {//设置内容
      		Vector<Object> v = new Vector<>();
      		v.add(orders.get(i).getNumber());
      		v.add(orders.get(i).getDate());
      		v.add(orders.get(i).getContent());
      		v.add(orders.get(i).getPrice());
      		rowData.add(v);
      	}
      	dtm = new DefaultTableModel(rowData,ConstUtil.meal_order_columnNames){
			@Override  
			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		};
		setModel(dtm);
    }
}
