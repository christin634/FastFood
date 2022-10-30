package view.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bean.Order;
import service.OrderService;
import serviceimpl.OrderServiceImpl;
import util.ConstUtil;
import view.util.ImagePanel;
import view.util.JTextFieldHintListener;
import view.util.OrderTable;

public class OrderPanel extends ImagePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderTable orderTable;
	List<Order> orders = new ArrayList<>();
	private OrderService orderService = new OrderServiceImpl();
	JPopupMenu popUpMenu ;
	JTextField searchTextField ;
	boolean flag=false;//标记查询
	
	public OrderPanel() {
		super(900,600,ConstUtil.imgPath+"bg.jpg");
		orders=orderService.queryAll();
		
		setFont(new Font("宋体", Font.PLAIN, 15));
		setLayout(null);
		
		createPopupMenu();
		
		JLabel lblNewLabel_1 = new JLabel("日期:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 0, 46, 33);
		add(lblNewLabel_1);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(51, 0, 121, 33);
		searchTextField.addFocusListener(new JTextFieldHintListener(searchTextField, ConstUtil.dateHintText));
		searchTextField.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub
						if(searchTextField.getText().toString().isEmpty()) {
//							orders=orderService.queryAll();
							orderTable.setDtm(orders);
						}
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
		add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton btn_search = new JButton("查询");
		btn_search.setFont(new Font("宋体", Font.PLAIN, 15));
		btn_search.setIcon(new ImageIcon(ConstUtil.imgPath+"search.png"));
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(searchTextField.getText().toString().isEmpty()) {
					JOptionPane.showConfirmDialog(null, "请输入日期.", "提示",JOptionPane.PLAIN_MESSAGE);
				}
				else {
//					int i=0;
					List<Order> searchedOrders = new ArrayList<>();
					for (int i=0;i<orders.size();i++) {
						if(orders.get(i).getDate().compareTo(searchTextField.getText().toString())>=0) {
							searchedOrders.add(orders.get(i));
						}
					}
					if(orders.size()>0) {
//						List<Order> searchedOrders = new ArrayList<>();
//						for(;i<orders.size();i++)
//							searchedOrders.add(orders.get(i));
//						orders=searchedOrders;
						orderTable.setDtm(searchedOrders);
					}
					else {
						JOptionPane.showConfirmDialog(null, "未找到该日之后的订单", "提示",JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		btn_search.setBounds(182, 0, 85, 33);
		add(btn_search);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 828, 502);
		add(scrollPane);
		
		orderTable = new OrderTable(orders);
		scrollPane.setViewportView(orderTable);
		//添加鼠标点击事件
		orderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键
		        if (e.getButton() == MouseEvent.BUTTON3) {
		            //通过点击位置找到点击为表格中的行
		            int focusedRowIndex = orderTable.rowAtPoint(e.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            //将表格所选项设为当前右键点击的行
		            orderTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            //弹出菜单
		            popUpMenu.show(orderTable, e.getX(), e.getY());
		        }
			}
		});
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	}
	private void createPopupMenu() {
        popUpMenu = new JPopupMenu();
        
        JMenuItem delMenItem = new JMenuItem();
        delMenItem.setText("删除");
        delMenItem.setIcon(new ImageIcon(ConstUtil.imgPath+"delete.png"));
        delMenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //该操作需要做的事
            	int confirm = JOptionPane.showConfirmDialog(null, "确认删除?", "提示",JOptionPane.YES_NO_OPTION);
            	if(confirm == JOptionPane.YES_OPTION) {
                	orderService.deleteByNumber(orders.get(orderTable.getSelectedRow()).getNumber());
                	orders=orderService.queryAll();
                	orderTable.setDtm(orders);
            	}
            }
        });
        popUpMenu.add(delMenItem);
    }
}
