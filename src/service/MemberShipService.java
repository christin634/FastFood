package service;

import strategy.MemberShip;

public interface MemberShipService {

	public MemberShip queryMemberShipByPhone(String phoneString);
	public MemberShip queryMemberShipByCard(String cardString);
	public boolean add(MemberShip memberShip);
}
