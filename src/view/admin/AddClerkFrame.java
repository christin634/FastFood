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

import bean.Clerk;
import bean.User;
import service.ClerkService;
import service.UserService;
import serviceimpl.ClerkServiceImpl;
import serviceimpl.UserServiceImpl;
import util.ConstUtil;
import view.util.ImagePanel;

public class AddClerkFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImagePanel contentPane;
	private JTextField numberTF;
	private JTextField nameTF;
	private JTextField ageTF;
	private JTextField phonenumberTF;
	private JComboBox<String> genderComb ;
	private UserService userService = new UserServiceImpl();
	private ClerkService clerkService = new ClerkServiceImpl();

	/**
	 * Create the frame.
	 */
	public AddClerkFrame() {
		setResizable(false);
		FlatIntelliJLaf.setup();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 420);
		setTitle("添加员工");
		contentPane = new ImagePanel(600, 400, ConstUtil.imgPath+"bg.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblNewLabel = new JLabel("工号:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(152, 40, 58, 24);
		contentPane.add(lblNewLabel);
		
		numberTF = new JTextField();
		numberTF.setBounds(195, 41, 123, 24);
		contentPane.add(numberTF);
		numberTF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("姓名:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(152, 79, 58, 24);
		contentPane.add(lblNewLabel_1);
		
		nameTF = new JTextField();
		nameTF.setBounds(195, 79, 123, 24);
		contentPane.add(nameTF);
		nameTF.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("年龄:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(152, 116, 58, 24);
		contentPane.add(lblNewLabel_2);
		
		ageTF = new JTextField();
		ageTF.setBounds(195, 117, 123, 24);
		contentPane.add(ageTF);
		ageTF.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("性别:");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(152, 158, 58, 24);
		contentPane.add(lblNewLabel_3);
		
		genderComb = new JComboBox(new String[] {"男","女"});
		genderComb.setFocusable(false);
		genderComb.setSelectedIndex(0);
		genderComb.setBounds(195, 157, 123, 24);
		contentPane.add(genderComb);
		
		JLabel lblNewLabel_4 = new JLabel("电话:");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(152, 204, 58, 24);
		contentPane.add(lblNewLabel_4);
		
		phonenumberTF = new JTextField();
		phonenumberTF.setBounds(195, 203, 123, 24);
		contentPane.add(phonenumberTF);
		phonenumberTF.setColumns(10);
		
		JButton btn_confirm = new JButton("确认");
		btn_confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String number = numberTF.getText().toString();
				String name = nameTF.getText().toString();
				String age = ageTF.getText().toString();
				String gender = genderComb.getSelectedItem().toString();
				String phone = phonenumberTF.getText().toString();
				if(number.isEmpty()||name.isBlank()||age.isBlank()||gender.isBlank()||phone.isBlank()) {
					JOptionPane.showConfirmDialog(contentPane, "请输入相关信息！", "提示",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					if(userService.queryByNumber(number)==null) {
						Clerk clerk = new Clerk(number,  name, Integer.parseInt( age),  gender,  phone);
						User user = new User(number, name, "111111", 2);
						clerkService.add(clerk);
						userService.add(user);
						JOptionPane.showConfirmDialog(contentPane, "添加成功！", "提示",JOptionPane.PLAIN_MESSAGE);
					}
					else {
						JOptionPane.showConfirmDialog(contentPane, "工号已存在！", "提示",JOptionPane.PLAIN_MESSAGE);
					}
				}
				
			}
		});
		btn_confirm.setFont(new Font("宋体", Font.PLAIN, 15));
		btn_confirm.setBounds(168, 250, 83, 30);
		contentPane.add(btn_confirm);
		
		JButton cancle = new JButton("取消");
		cancle.setFont(new Font("宋体", Font.PLAIN, 15));
		cancle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		cancle.setBounds(271, 250, 83, 30);
		contentPane.add(cancle);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
