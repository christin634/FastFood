package view;

import bean.User;
import com.formdev.flatlaf.FlatIntelliJLaf;
import service.UserService;
import serviceimpl.UserServiceImpl;
import util.ConstUtil;
import view.admin.AdminFrame;
import view.clerk.ClerkFrame;
import view.util.ImagePanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImagePanel contentPane;
	private UserService userService = new UserServiceImpl();
	private int type = 2;	//用户类型
	private JTextField usernameField;
	private JPasswordField passwodField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		FlatIntelliJLaf.setup();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		setTitle("登陆");
		
		// 图片面板
		contentPane = new ImagePanel(400,300,ConstUtil.imgPath+"bg.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        // 布局方式需设置后更改
        contentPane.setLayout(null);
		
		UIManager.put( "Button.arc", 999 );
		
		ImageIcon usrenameIcon = new ImageIcon(ConstUtil.imgPath+"username.png");
		usernameField = new JTextField() {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				usrenameIcon.paintIcon(usernameField,g,5,5);
			}
		};
		usernameField.setMargin(new Insets(2, 20, 2, 2));
		usernameField.setFont(new Font("宋体", Font.PLAIN, 15));
		usernameField.setBounds(109, 69, 190, 28);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		ImageIcon passwordIcon = new ImageIcon(ConstUtil.imgPath+"password.png");
		passwodField = new JPasswordField() {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				passwordIcon.paintIcon(passwodField,g,5,5);
			}
		};
		passwodField.setMargin(new Insets(2, 20, 2, 2));
		passwodField.setFont(new Font("宋体", Font.PLAIN, 15));
		passwodField.setBounds(109, 108, 190, 29);
		contentPane.add(passwodField);
		
		ButtonGroup btnGroup = new ButtonGroup();
		JRadioButton admin = new JRadioButton("管理员");
		admin.setFont(new Font("宋体", Font.PLAIN, 15));
		admin.setFocusPainted(false);
		admin.setFocusable(false);
		admin.setBounds(109, 149, 102, 32);
		admin.addActionListener(listener);
		JRadioButton common = new JRadioButton("员工");
		common.setFont(new Font("宋体", Font.PLAIN, 15));
		common.setFocusable(false);
		common.setSelected(true);
		common.setBounds(213, 148, 86, 34);
		common.addActionListener(listener);
		btnGroup.add(admin);
		btnGroup.add(common);
		contentPane.add(admin);
		contentPane.add(common);
		
		JButton loginButton = new JButton("登陆");
		loginButton.setIcon(new ImageIcon(ConstUtil.imgPath+"login.png"));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		loginButton.setFont(new Font("宋体", Font.PLAIN, 15));
		loginButton.setFocusPainted(false);
		loginButton.setBounds(91, 199, 97, 32);
		contentPane.add(loginButton);
		
		JButton exitButton = new JButton("退出");
		exitButton.setIcon(new ImageIcon(ConstUtil.imgPath+"exit.png"));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("宋体", Font.PLAIN, 15));
		exitButton.setFocusPainted(false);
		exitButton.setBounds(213, 199, 97, 32);
		contentPane.add(exitButton);
		
		JLabel lblNewLabel_3 = new JLabel("点餐收银系统");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 30));
		lblNewLabel_3.setBounds(51, 10, 299, 49);
		contentPane.add(lblNewLabel_3);
		//居中显示
		setLocationRelativeTo(null);
	}
	//单选框选项改变时定义监听器
    ActionListener listener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            switch (e.getActionCommand()) {
            case "管理员":
                type=1;
                break;
            case "员工":
                type=2;
                break;
            default:
                break;
            }
        }
    };//listener
    
    //登陆
    public void login() {
    	String username = usernameField.getText().toString();
		String password = passwodField.getText().toString();
		User user = userService.queryUser(username, password, type);
		if(user!=null) {
			dispose();
			switch(type) {
				case 1:
					new AdminFrame(user).setVisible(true);
					break;
				case 2:
					new ClerkFrame(user).setVisible(true);
					break;
				default:break;
			}
		}else {
			JOptionPane.showMessageDialog(null, "用户名或密码错误！","提示",JOptionPane.WARNING_MESSAGE);
		}
    }
}
