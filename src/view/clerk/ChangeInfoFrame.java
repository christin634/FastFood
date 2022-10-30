package view.clerk;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
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

public class ChangeInfoFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImagePanel contentPane;
	private UserService userService = new UserServiceImpl();
	private ClerkService clerkService = new ClerkServiceImpl();
	User user;
	Clerk clerk ;
	private JTextField number;
	private JTextField name;
	private JTextField age;
	private JTextField gender;
	private JTextField phonenumber;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Create the frame.
	 */
	public ChangeInfoFrame(User user) {
		FlatIntelliJLaf.setup();
		this.user=user;
		this.clerk = clerkService.queryByNumber(user.getNumber());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 539, 415);
		contentPane = new ImagePanel(600, 400, ConstUtil.imgPath+"bg.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("工号:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(152, 40, 58, 24);
		contentPane.add(lblNewLabel);
		
		number = new JTextField();
		number.setEditable(false);
		number.setText(user.getNumber());
		number.setBounds(195, 41, 123, 24);
		contentPane.add(number);
		number.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("姓名:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(152, 79, 58, 24);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setEditable(false);
		name.setText(clerk.getName());
		name.setBounds(195, 79, 123, 24);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("年龄:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(152, 116, 58, 24);
		contentPane.add(lblNewLabel_2);
		
		age = new JTextField();
		age.setEditable(false);
		age.setText(String.valueOf(clerk.getAge()));
		age.setBounds(195, 117, 123, 24);
		contentPane.add(age);
		age.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("性别:");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(152, 158, 58, 24);
		contentPane.add(lblNewLabel_3);
		
		gender = new JTextField();
		gender.setEditable(false);
		gender.setText(clerk.getGender());
		gender.setBounds(195, 157, 123, 24);
		contentPane.add(gender);
		gender.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("电话:");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(152, 204, 58, 24);
		contentPane.add(lblNewLabel_4);
		
		phonenumber = new JTextField();
		phonenumber.setBounds(195, 203, 123, 24);
		phonenumber.setText(clerk.getPhonenumber());
		contentPane.add(phonenumber);
		phonenumber.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("登陆用户名:");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(107, 239, 84, 24);
		contentPane.add(lblNewLabel_5);
		
		username = new JTextField();
		username.setBounds(195, 238, 123, 24);
		username.setText(user.getUsername());
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("密码:");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(152, 273, 45, 24);
		contentPane.add(lblNewLabel_6);
		
		password = new JPasswordField();
		password.setBounds(195, 272, 123, 24);
		contentPane.add(password);
		
		JCheckBox cb = new JCheckBox("修改");
		cb.setBounds(324, 274, 109, 23);
		contentPane.add(cb);
		
		JButton btn_confirm = new JButton("确认");
		btn_confirm.setIcon(new ImageIcon(ConstUtil.imgPath+"confirm.png"));
		btn_confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clerk clerk = clerkService.queryByNumber(user.getNumber());
				if(!phonenumber.getText().toString().isEmpty()) {
					clerk.setPhonenumber(phonenumber.getText().toString());
					clerkService.update(clerk);
				}
				if(!username.getText().toString().isEmpty()) {
					user.setUsername(username.getText().toString());
				}
				if(cb.isSelected()&&!password.getText().toString().isEmpty()) {
					user.setPassword(password.getText().toString());
				}
				userService.update(user);
				JOptionPane.showConfirmDialog(contentPane, "修改成功！", "提示",JOptionPane.PLAIN_MESSAGE);
			}
		});
		btn_confirm.setFont(new Font("宋体", Font.PLAIN, 15));
		btn_confirm.setBounds(168, 322, 100, 27);
		contentPane.add(btn_confirm);
		
		JButton cancle = new JButton("取消");
		cancle.setFont(new Font("宋体", Font.PLAIN, 15));
		cancle.setIcon(new ImageIcon(ConstUtil.imgPath+"cancle.png"));
		cancle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		cancle.setBounds(271, 322, 100, 27);
		contentPane.add(cancle);
		setLocationRelativeTo(null);
	}
}
