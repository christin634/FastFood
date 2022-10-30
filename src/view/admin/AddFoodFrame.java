package view.admin;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import bean.Food;
import service.FoodService;
import serviceimpl.FoodServiceImpl;
import util.ConstUtil;
import util.StringUtil;
import view.util.ImagePanel;

public class AddFoodFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImagePanel contentPane;
	private JTextField foodName;
	private JTextField foodPrice;
	private JComboBox<String> brandComb ;
	private JComboBox<String> foodTypeComb ;
	private FoodService foodService = new FoodServiceImpl();

	/**
	 * Create the frame.
	 */
	public AddFoodFrame() {
		setResizable(false);
		FlatIntelliJLaf.setup();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("添加食物");
		contentPane = new ImagePanel(600, 400, ConstUtil.imgPath+"bg.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("名称:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(152, 40, 58, 24);
		contentPane.add(lblNewLabel);
		
		foodName = new JTextField();
		foodName.setBounds(195, 41, 123, 24);
		contentPane.add(foodName);
		foodName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("类别:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(152, 79, 58, 24);
		contentPane.add(lblNewLabel_1);
		
		foodTypeComb = new JComboBox(new String[] {"汉堡","鸡类","小吃","饮料"});
		foodTypeComb.setFocusable(false);
		foodTypeComb.setSelectedIndex(0);
		foodTypeComb.setBounds(195, 79, 123, 24);
		contentPane.add(foodTypeComb);
		
		JLabel lblNewLabel_2 = new JLabel("品牌:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(152, 116, 58, 24);
		contentPane.add(lblNewLabel_2);
		
		brandComb = new JComboBox(new String[] {"KFC","Wallace"});
		brandComb.setFocusable(false);
		brandComb.setSelectedIndex(0);
		brandComb.setBounds(195, 117, 123, 24);
		contentPane.add(brandComb);
		
		JLabel lblNewLabel_3 = new JLabel("价格:");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(152, 158, 58, 24);
		contentPane.add(lblNewLabel_3);
		
		foodPrice = new JTextField(); 
		foodPrice.setBounds(195, 157, 123, 24);
		contentPane.add(foodPrice);
		foodPrice.setColumns(10);
		
		JButton btn_confirm = new JButton("确认");
		btn_confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name=foodName.getText().toString();
				int type = foodTypeComb.getSelectedIndex()+1;
				String brand = brandComb.getSelectedItem().toString();
				if(name.isEmpty()||brand.isBlank()) {
					JOptionPane.showConfirmDialog(contentPane, "请输入相关信息！", "提示",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					if(StringUtil.isFloat(foodPrice.getText().toString())) {
						if(foodService.queryByNameAndBrand(name,brand)==null) {
							Food food = new Food(name, type, brand, Float.parseFloat(foodPrice.getText().toString()));
							foodService.add(food);
							JOptionPane.showConfirmDialog(contentPane, "食物添加成功！", "提示",JOptionPane.PLAIN_MESSAGE);
						}
						else {
							JOptionPane.showConfirmDialog(contentPane, "该食物已存在！", "提示",JOptionPane.PLAIN_MESSAGE);
						}
					}else {
						JOptionPane.showConfirmDialog(contentPane, "输入价格有误！", "提示",JOptionPane.PLAIN_MESSAGE);
					}
						
					
				}
				
			}
		});
		btn_confirm.setFont(new Font("宋体", Font.PLAIN, 15));
		btn_confirm.setBounds(168, 220, 83, 27);
		contentPane.add(btn_confirm);
		
		JButton cancle = new JButton("取消");
		cancle.setFont(new Font("宋体", Font.PLAIN, 15));
		cancle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		cancle.setBounds(250, 220, 83, 27);
		contentPane.add(cancle);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
	}

}
