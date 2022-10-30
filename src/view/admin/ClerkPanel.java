package view.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bean.Clerk;
import service.ClerkService;
import service.UserService;
import serviceimpl.ClerkServiceImpl;
import serviceimpl.UserServiceImpl;
import util.ConstUtil;
import view.util.ClerkTable;
import view.util.ImagePanel;
import view.util.TableCellListener;

public class ClerkPanel extends ImagePanel {

	private static final long serialVersionUID = 1L;
	private ClerkTable clerkTable;
	List<Clerk> clerks = new ArrayList<>();
	private ClerkService clerkService = new ClerkServiceImpl();
	private UserService userService = new UserServiceImpl();
	JPopupMenu popUpMenu ;
	JTextField searchTextField ;
	boolean flag=false;//标记查询
	
	public ClerkPanel() {
		super(900,600,ConstUtil.imgPath+"bg.jpg");
		clerks=clerkService.queryAll();
		
		createPopupMenu();
		setFont(new Font("宋体", Font.PLAIN, 15));
		setLayout(null);
		JLabel lblNewLabel_1 = new JLabel("姓名:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(8, 0, 50, 35);
		add(lblNewLabel_1);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(49, 0, 93, 35);
		searchTextField.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub
						if(searchTextField.getText().toString().isEmpty()) {
//							clerks=clerkService.queryAll();
							clerkTable.setDtm(clerks);
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
					JOptionPane.showConfirmDialog(null, "请输入姓名.", "提示",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					List<Clerk> searchedclerks = new ArrayList<>();
					for (int i=0;i<clerks.size();i++) {
						//支持模糊搜索,结果可能有多个
						if(clerks.get(i).getName().contains(searchTextField.getText().toString())) {
							searchedclerks.add(clerks.get(i));
						}
					}
					if(searchedclerks.size()>0) {
//						List<Clerk> searchedclerks = new ArrayList<>();
//						searchedclerks.add(clerks.get(i));
//						clerks=searchedclerks;
						clerkTable.setDtm(searchedclerks);
					}
					else {
						JOptionPane.showConfirmDialog(null, "未找到该员工", "提示",JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		btn_search.setBounds(152, 0, 85, 35);
		add(btn_search);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 45, 830, 499);
		add(scrollPane);
		
		clerkTable = new ClerkTable(clerks) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				if(column==0) {
					return false;
				}
				return true;
			}
		};
		
		//添加鼠标点击事件
		clerkTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键
		        if (e.getButton() == MouseEvent.BUTTON3) {
		            //通过点击位置找到点击为表格中的行
		            int focusedRowIndex = clerkTable.rowAtPoint(e.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            //将表格所选项设为当前右键点击的行
		            clerkTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            //弹出菜单
		            popUpMenu.show(clerkTable, e.getX(), e.getY());
		        }
			}
		});
		
		scrollPane.setViewportView(clerkTable);
		
		Action action = new AbstractAction(){//单元格改变监听
            public void actionPerformed(ActionEvent e){
                TableCellListener tcl = (TableCellListener)e.getSource();
                //获取改变的单元格行列
                int row=tcl.getRow(),col=tcl.getColumn();
                Object value = tcl.getNewValue();
                switch(col) {
	                case 1:clerks.get(row).setName((String)value);break;
	                case 2:clerks.get(row).setGender((String)value);break;
	                case 3:clerks.get(row).setAge((int)value);break;
	                case 4:clerks.get(row).setPhonenumber((String)value);break;
	                default:break;
                }
                clerkService.update(clerks.get(row));
                clerkTable.setDtm(clerks);
            }
        };
        TableCellListener tcl = new TableCellListener(clerkTable, action);
		JButton btn_add = new JButton("新增");
		btn_add.setFont(new Font("宋体", Font.PLAIN, 15));
		btn_add.setBounds(234, 0, 85, 35);
		btn_add.setIcon(new ImageIcon(ConstUtil.imgPath+"add.png"));
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddClerkFrame().setVisible(true);
			}
		});
		add(btn_add);
		
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
                	clerkService.deleteByNumber(clerks.get(clerkTable.getSelectedRow()).getNumber());
                	userService.deleteByNumber(clerks.get(clerkTable.getSelectedRow()).getNumber());
                	clerks=clerkService.queryAll();
                	clerkTable.setDtm(clerks);
            	}
            }
        });
        JMenuItem resetMenItem = new JMenuItem();
        resetMenItem.setText("重置密码");
        resetMenItem.setIcon(new ImageIcon(ConstUtil.imgPath+"reset.png"));
        resetMenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //该操作需要做的事
            	boolean flag=userService.update(clerks.get(clerkTable.getSelectedRow()).getNumber(), "111111");
            	if(flag) {
            		JOptionPane.showConfirmDialog(null, "工号"+clerks.get(clerkTable.getSelectedRow()).getNumber()+"密码重置为:111111", "提示",JOptionPane.YES_NO_OPTION);
            	}
            }
           
        });
        popUpMenu.add(delMenItem);
        popUpMenu.add(resetMenItem);
    }
}
