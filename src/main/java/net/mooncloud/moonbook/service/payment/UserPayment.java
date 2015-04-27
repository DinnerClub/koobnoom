package net.mooncloud.moonbook.service.payment;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;

/**
 * 用户支出
 * 
 * @author yangjd
 *
 */
public interface UserPayment
{
	public UserPaymentDetail save(UserPaymentDetail userPaymentDetail);

	public UserPaymentDetail delete(UserPaymentDetail userPaymentDetail);

	public UserPaymentDetail update(UserPaymentDetail userPaymentDetail);

	public List<UserPaymentDetail> search(Map<String, Object> querys);
}
