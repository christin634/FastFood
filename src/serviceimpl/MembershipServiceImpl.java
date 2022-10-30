package serviceimpl;

import java.util.List;

import service.MemberShipService;
import strategy.MemberShip;
import util.JdbcUtil;

public class MembershipServiceImpl implements MemberShipService {

	@Override
	public MemberShip queryMemberShipByPhone(String phoneString) {
		// TODO Auto-generated method stub
		String sqlString = "select * from membership where phone=?";
		return JdbcUtil.query(MemberShip.class, sqlString, phoneString).get(0);
	}

	@Override
	public MemberShip queryMemberShipByCard(String cardString) {
		// TODO Auto-generated method stub
		String sqlString = "select * from membership where cardnum=?";
		List<MemberShip> memberShips = JdbcUtil.query(MemberShip.class, sqlString, cardString);
		if (memberShips==null) {
			return null;
		}
		else
			return memberShips.get(0);
	}

	@Override
	public boolean add(MemberShip memberShip) {
		// TODO Auto-generated method stub
		String sqlString = "insert into membership values(null,?,?,?)";
		return JdbcUtil.executeUpdate(sqlString, memberShip.getPhone(),memberShip.getCardnum(),memberShip.getDiscount());
	}

}
