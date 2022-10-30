package view.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.User;
import service.UserService;
import serviceimpl.UserServiceImpl;
import util.ConstUtil;
import view.util.ImagePanel;

public class ChangePwdPanel extends ImagePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField raw;
	private JPasswordField newPwd;
	private JPasswordField confirmPwd;
	private UserService userService = new UserServiceImpl();
	User user;

	/**
	 * Create the frame.
	 */
	public ChangePwdPanel(User user) {
		super( 900,600,ConstUtil.imgPath+"bg.jpg");
		this.user=user;
		setBounds(100, 100, 586, 400);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setFont(new Font("宋体", Font.PLAIN, 15));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("原密码:");
		lblNewLabel.setBounds(165, 29, 58, 31);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		
		raw = new JTextField();
		raw.setBounds(222, 27, 128, 31);
		raw.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("新密码:");
		lblNewLabel_1.setBounds(165, 72, 60, 28);
		lblNewLabel_1.setFont(new Font("新宋体", Font.PLAIN, 15));
		add(lblNewLabel);
		add(raw);
		add(lblNewLabel_1);
		
		newPwd = new JPasswordField();
		newPwd.setBounds(222, 72, 128, 31);
		add(newPwd);
		
		JLabel lblNewLabel_2 = new JLabel("确认新密码:");
		lblNewLabel_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(143, 113, 88, 31);
		add(lblNewLabel_2);
		
		confirmPwd = new JPasswordField();
		confirmPwd.setBounds(222, 114, 128, 31);
		add(confirmPwd);
		
		JButton confirm = new JButton("确认");
		confirm.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		confirm.setBounds(220, 170, 120, 30);
		confirm.setIcon(new ImageIcon(ConstUtil.imgPath+"confirm.png"));
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(raw.getText().toString().isEmpty()||newPwd.getText().toString().isEmpty()||confirmPwd.getText().toString().isEmpty()) {
					JOptionPane.showConfirmDialog(null, "请输入相关信息.", "提示",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					if(!raw.getText().toString().equals(user.getPassword())) {
						JOptionPane.showConfirmDialog(null, "原密码输入错误.", "提示",JOptionPane.PLAIN_MESSAGE);
					}
					else if(newPwd.getText().toString().equals(raw.getText().toString())) {
						JOptionPane.showConfirmDialog(null, "新旧密码相同.", "提示",JOptionPane.PLAIN_MESSAGE);
					}
					else if(!newPwd.getText().toString().equals(confirmPwd.getText().toString())) {
						JOptionPane.showConfirmDialog(null, "两次密码不一致.", "提示",JOptionPane.PLAIN_MESSAGE);
					}else {
						userService.update(user.getNumber(), newPwd.getText().toString());
						JOptionPane.showConfirmDialog(null, "密码修改成功.", "提示",JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		add(confirm);

	}
}
