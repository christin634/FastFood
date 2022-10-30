package view.util;

import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import bean.Food;
import util.ConstUtil;

public class MyTable extends JTable    //实现自定义类
{
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MyTable(){
      	
    }
    
    /**
     * @Override
     */
    public JTableHeader getTableHeader()
    {
        JTableHeader tableHeader = super.getTableHeader();
        tableHeader.setReorderingAllowed(false);   //设置表格列不可重排
        DefaultTableCellRenderer hr =(DefaultTableCellRenderer)tableHeader.getDefaultRenderer();  //获得表格头的单元格对象
        hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);  //列名居中
        return tableHeader;
        
    }
    /**
     * @Override
     */
    public TableCellRenderer getDefaultRenderer(Class<?>columnClass)
    {
        DefaultTableCellRenderer cr =(DefaultTableCellRenderer)super.getDefaultRenderer(columnClass);
        cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);  //单元格内容居中
        return cr;
    }
    /**
     * @Override
     */
    public boolean isCellEditable(int row,int column)
    {
        return false;   //单元格不可修改
    }
    
}