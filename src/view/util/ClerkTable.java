package view.util;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bean.Clerk;
import util.ConstUtil;

public class ClerkTable extends MyTable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Vector<Object>> rowData=new Vector<Vector<Object>>();
    private DefaultTableModel dtm ;
    
    public ClerkTable(List<Clerk> clerks)
    {
      	setDtm(clerks);
		setModel(dtm);
      	this.setRowHeight(40);
      	this.getColumnModel().getColumn(0).setMinWidth(60);
      	this.getColumnModel().getColumn(1).setMinWidth(100);
      	this.getColumnModel().getColumn(2).setMinWidth(100);
      	this.getColumnModel().getColumn(3).setMinWidth(120);
      	this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //单选
		this.setSelectionBackground(new Color(176,196,222));
		this.setSelectionForeground(Color.BLACK);
    }
    
    public void setDtm(List<Clerk> clerks) {
    	rowData.clear();
      	for(int i=0;i<clerks.size();i++) {//设置内容
      		Vector<Object> v = new Vector<>();
      		v.add(clerks.get(i).getNumber());
      		v.add(clerks.get(i).getName());
      		v.add(clerks.get(i).getGender());
      		v.add(clerks.get(i).getAge());
      		v.add(clerks.get(i).getPhonenumber());
      		rowData.add(v);
      	}
      	dtm = new DefaultTableModel(rowData,ConstUtil.clerk_columnNames){
			@Override  
			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		};
		setModel(dtm);
    }
}
