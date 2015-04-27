package net.mooncloud.moonbook.entity.chart;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.mooncloud.moonbook.entity.payment.UserPaymentDetail;

public class ChartElement extends UserPaymentDetail
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double SUM;
	private double COUNT;
	private double AVG;

	public double getSUM()
	{
		return SUM;
	}

	public void setSUM(double sUM)
	{
		SUM = sUM;
	}

	public double getCOUNT()
	{
		return COUNT;
	}

	public void setCOUNT(double cOUNT)
	{
		COUNT = cOUNT;
	}

	public double getAVG()
	{
		return AVG;
	}

	public void setAVG(double aVG)
	{
		AVG = aVG;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		Class<?> clazz = this.getClass();
		sb.append(clazz.getName()).append(" [");
		clazz = UserPaymentDetail.class;
		for (Field field : clazz.getDeclaredFields())
		{
			PropertyDescriptor pd;
			try
			{
				pd = new PropertyDescriptor(field.getName(), clazz);
				Method wM = pd.getReadMethod();// 获得写方法
				sb.append(field.getName()).append("=").append(wM.invoke(this)).append(", ");
			}
			catch (Exception e)
			{
				// e.printStackTrace();
			}
		}
		clazz = this.getClass();
		for (Field field : clazz.getDeclaredFields())
		{
			PropertyDescriptor pd;
			try
			{
				pd = new PropertyDescriptor(field.getName(), clazz);
				Method wM = pd.getReadMethod();// 获得写方法
				sb.append(field.getName()).append("=").append(wM.invoke(this)).append(", ");
			}
			catch (Exception e)
			{
				// e.printStackTrace();
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args)
	{
		ChartElement o = new ChartElement();
		System.out.println(o.toString());
	}
}
