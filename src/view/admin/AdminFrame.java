package view.admin;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;

import com.formdev.flatlaf.FlatIntelliJLaf;

import bean.User;
import util.ConstUtil;
import view.util.ImagePanel;
import view.util.MyTabbedPaneUI;

public class AdminFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImagePanel contentPane;
	private ImagePanel foodSp;
	private ImagePanel orderSp ;
	private ImagePanel clerkSp;
	private ImagePanel addMemberShipPanel;
	private ImagePanel changePwdPanel;
	User user;


	/**
	 * Create the frame.
	 */
	public AdminFrame(User user) {
		this.user=user;
		setResizable(false);
		FlatIntelliJLaf.setup();
		setTitle("管理员");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new ImagePanel(907,600,ConstUtil.imgPath+"bg.jpg");
		contentPane.setBorder(null);

		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
		);
		
		foodSp = new FoodPanel();
		tabbedPane.addTab("食物", null, foodSp, null);
		orderSp = new OrderPanel();
		tabbedPane.addTab("订单", null, orderSp, null);
		clerkSp = new ClerkPanel();
		tabbedPane.addTab("员工", null, clerkSp, null);
		addMemberShipPanel = new AddMemberShipPanel();
		tabbedPane.addTab("添加会员", null, addMemberShipPanel, null);
		changePwdPanel = new ChangePwdPanel(user);
		tabbedPane.addTab("修改密码", null, changePwdPanel, null);
		tabbedPane.setUI(new MyTabbedPaneUI());
		
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}
}
