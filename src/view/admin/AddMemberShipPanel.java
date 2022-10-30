package view.admin;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import service.MemberShipService;
import serviceimpl.MembershipServiceImpl;
import strategy.MemberShip;
import util.ConstUtil;
import view.util.ImagePanel;

public class AddMemberShipPanel extends ImagePanel {

	private JTextField phone;
	private JTextField cardNum;
	private JComboBox<Float> discount ;
	private MemberShipService memberShipService = new MembershipServiceImpl();


	/**
	 * Create the frame.
	 */
	public AddMemberShipPanel() {
		super( 900,600,ConstUtil.imgPath+"bg.jpg");
		setBounds(100, 100, 500, 383);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setFont(new Font("宋体", Font.PLAIN, 15));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("卡  号:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(152, 40, 58, 24);
		add(lblNewLabel);
		
		cardNum = new JTextField();
		cardNum.setBounds(206, 41, 123, 24);
		add(cardNum);
		cardNum.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("手机号:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(152, 79, 58, 24);
		add(lblNewLabel_1);
		
		phone = new JTextField();
		phone.setBounds(206, 74, 123, 24);
		add(phone);
		phone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("折  扣:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(152, 116, 58, 24);
		add(lblNewLabel_2);
		
		Float[] dicountList= {0.9f,0.8f,0.7f,0.6f};
		discount = new JComboBox(dicountList);
		discount.setFocusable(false);
		discount.setSelectedIndex(0);
		discount.setBounds(206, 113, 123, 24);
		add(discount);
		JButton btn_confirm = new JButton("确认");
		btn_confirm.setIcon(new ImageIcon(ConstUtil.imgPath+"confirm.png"));
		btn_confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cardNumber = cardNum.getText().toString();
				String phoneNumber = phone.getText().toString();
				String discountString = discount.getSelectedItem().toString();
				if(cardNumber.isEmpty()||phoneNumber.isBlank()) {
					JOptionPane.showConfirmDialog(null, "请输入相关信息！", "提示",JOptionPane.PLAIN_MESSAGE);
				}
				else if(cardNumber.length()!=8||phoneNumber.length()!=11){
					JOptionPane.showConfirmDialog(null, "卡号为8位，手机号为11位！", "提示",JOptionPane.PLAIN_MESSAGE);
				}else {
					if(memberShipService.queryMemberShipByPhone(phoneNumber)==null&&memberShipService.queryMemberShipByCard(cardNumber)==null) {
						MemberShip memberShip = new MemberShip(phoneNumber, cardNumber, Float.parseFloat(discountString));
						memberShipService.add(memberShip);
						JOptionPane.showConfirmDialog(null, "添加成功！", "提示",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showConfirmDialog(null, "会员已存在！", "提示",JOptionPane.PLAIN_MESSAGE);
				}
				}
				
			}
		});
		btn_confirm.setFont(new Font("宋体", Font.PLAIN, 15));
		btn_confirm.setBounds(162, 167, 120, 30);
		add(btn_confirm);
	}

}
