package util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
 
public class ImageRender implements TableCellRenderer
{
 
	  @SuppressWarnings("unchecked")
	  @Override
	  public Component getTableCellRendererComponent(
	          JTable table, Object value, boolean isSelected, 
	          boolean hasFocus, int rowIndex, int columnIndex) {
	    
	    if( value instanceof Image ){
	    	JLabel jLabel = new JLabel();
	    	jLabel.setLayout(new BorderLayout());//设置布局
	    	jLabel.setIcon(new ImageIcon((Image)value));//给jlable设置图片
	      return jLabel;
	    }
	    
	    else if( value instanceof File ) {
	      try {
	        return new JLabel(new ImageIcon(ImageIO.read((File)value)));
	      } catch(IOException ex) {
	        throw new RuntimeException(ex.getMessage(), ex);
	      }
	    }
	    
	    else {
	      String val = String.valueOf(value);
	      try {
	        return new JLabel(new ImageIcon(ImageIO.read(new File(val))));
	      } catch(IOException ex) {
	        throw new RuntimeException(ex.getMessage(), ex);
	      }
	    }
	  }
	}