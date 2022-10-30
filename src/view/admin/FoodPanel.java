package view.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bean.Food;
import service.FoodService;
import serviceimpl.FoodServiceImpl;
import util.ConstUtil;
import util.StringUtil;
import view.util.FoodTable;
import view.util.ImagePanel;
import view.util.TableCellListener;

public class FoodPanel extends ImagePanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FoodTable foodTable;
	List<Food> foods = new ArrayList<>();
	private FoodService foodService = new FoodServiceImpl();
	JPopupMenu popUpMenu ;
	JComboBox<String> brandComb;
	JTextField searchTextField ;
	boolean flag=false;//标记查询

	/**
	 * Create the panel.
	 */
	public FoodPanel() {
		super(900,600,ConstUtil.imgPath+"bg.jpg");
		
		brandComb = new JComboBox(new String[] {"KFC","WALLACE"});
		brandComb.setSelectedIndex(0);
		brandComb.setBounds(49, 0, 107, 36);
		brandComb.setFocusable(false);
		brandComb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					foods = foodService.queryAllFood(brandComb.getSelectedItem().toString());
					foodTable.setDtm(foods);
				}
			}
		});
		
		foods = foodService.queryAllFood(brandComb.getSelectedItem().toString());
	
		setFont(new Font("宋体", Font.PLAIN, 15));
		JLabel lblNewLabel = new JLabel("品牌:");
		lblNewLabel.setBounds(10, 0, 49, 36);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 818, 500);
		setLayout(null);
		add(lblNewLabel);
		add(brandComb);
		add(scrollPane);
		
		createPopupMenu();
		foodTable = new FoodTable(foods) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				if(column==2) {//价格可编辑
					return true;
				}
				return false;
			}
		};
		foodTable.setRowHeight(40);
		//添加鼠标点击事件
		foodTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键
		        if (e.getButton() == MouseEvent.BUTTON3) {
		            //通过点击位置找到点击为表格中的行
		            int focusedRowIndex = foodTable.rowAtPoint(e.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            //将表格所选项设为当前右键点击的行
		            foodTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            //弹出菜单
		            popUpMenu.show(foodTable, e.getX(), e.getY());
		        }
			}
		});
		scrollPane.setViewportView(foodTable);
		
		JLabel lblNewLabel2 = new JLabel("名称:");
		lblNewLabel2.setBounds(166, 0, 49, 36);
		lblNewLabel2.setFont(new Font("宋体", Font.PLAIN, 15));
		add(lblNewLabel2);
		
		JTextField searchTextField = new JTextField();
		searchTextField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(searchTextField.getText().toString().isEmpty()) {
					foods=foodService.queryAllFood(brandComb.getSelectedItem().toString());
					foodTable.setDtm(foods);
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
		searchTextField.setBounds(203, 0, 121, 33);
		add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton search = new JButton("查询");
		search.setFont(new Font("宋体", Font.PLAIN, 15));
		search.setBounds(328, 0, 97, 36);
		search.setIcon(new ImageIcon(ConstUtil.imgPath+"search.png"));
		search.setFocusPainted(false);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(searchTextField.getText().toString().isEmpty()) {
					JOptionPane.showConfirmDialog(null, "请输入食物名称.", "提示",JOptionPane.PLAIN_MESSAGE);
				}
				else {
//					int i=0;
					List<Food> searchedFoods = new ArrayList<>();
					for (int i=0;i<foods.size();i++) {
						if(foods.get(i).getName().contains(searchTextField.getText().toString())) {
							searchedFoods.add(foods.get(i));;
						}
					}
					if(foods.size()>0) {
//						List<Food> searchedFoods = new ArrayList<>();
//						searchedFoods.add(foods.get(i));
						foods=searchedFoods;
						foodTable.setDtm(foods);
					}
					else {
						JOptionPane.showConfirmDialog(null, "未找到"+searchTextField.getText().toString(), "提示",JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		add(search);
		
		JButton btn_add = new JButton("新增");
		btn_add.setFont(new Font("宋体", Font.PLAIN, 15));
		btn_add.setBounds(430, 0, 97, 36);
		btn_add.setIcon(new ImageIcon(ConstUtil.imgPath+"add.png"));
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddFoodFrame().setVisible(true);
			}
		});
		btn_add.setFocusPainted(false);
		add(btn_add);
		
		Action action = new AbstractAction(){//单元格改变监听
            public void actionPerformed(ActionEvent e){
                TableCellListener tcl = (TableCellListener)e.getSource();
                //获取改变的单元格行列
                int row=tcl.getRow(),col=tcl.getColumn();
                Food food = foods.get(row);
                if(col==2) {
                	if(StringUtil.isFloat(tcl.getNewValue().toString())) {
                		food.setPrice((float)tcl.getNewValue());
                        foodService.update(food);
                        foodTable.setDtm(foods);
                	}
                }else {
                	foodTable.setDtm(foods);
                }
                
            }
        };
        TableCellListener tcl = new TableCellListener(foodTable, action);
		
	}
	private void createPopupMenu() {
        popUpMenu = new JPopupMenu();
        ImageIcon icon = new ImageIcon(ConstUtil.imgPath+"delete.png");
        JMenuItem delMenItem = new JMenuItem();
        delMenItem.setText("删除");
        delMenItem.setIcon(icon);
        delMenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //该操作需要做的事
            	int confirm = JOptionPane.showConfirmDialog(null, "确认删除"+foods.get(foodTable.getSelectedRow()).getName()+"?", "提示",JOptionPane.YES_NO_OPTION);
            	if(confirm == JOptionPane.YES_OPTION) {
                	foodService.delete(foods.get(foodTable.getSelectedRow()));
                	foods=foodService.queryAllFood(brandComb.getSelectedItem().toString());
                	foodTable.setDtm(foods);
            	}
            }
        });
        popUpMenu.add(delMenItem);
    }
}
