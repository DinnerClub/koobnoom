package net.mooncloud.moonbook.entity.income;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 用户收入账目详情列表
 * 
 * @author yangjd
 *
 */
public class UserIncomeDetail implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long detailid; // bigint(20) NOT NULL COMMENT '账目id',
	private long userid; // bigint(20) DEFAULT NULL COMMENT '用户id',
	private int year; // int(2) DEFAULT NULL COMMENT '年',
	private int month; // int(1) DEFAULT NULL COMMENT '月',
	private int day; // int(1) DEFAULT NULL COMMENT '日',
	private int hour; // int(1) DEFAULT NULL COMMENT '时',
	private int minute; // int(1) DEFAULT NULL COMMENT '分',
	private int second; // int(1) DEFAULT NULL COMMENT '秒',
	private Date time; // timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT
						// '支出时间',
	private double latitude; // double DEFAULT NULL COMMENT '纬度坐标',
	private double longitude; // double DEFAULT NULL COMMENT '经度坐标',
	private String address; // varchar(255) DEFAULT NULL COMMENT '地址',
	private int pid; // int(2) DEFAULT NULL COMMENT '用途类目',
	private int cid; // int(2) DEFAULT NULL,
	private int mid; // int(2) DEFAULT NULL COMMENT '收入方式',
	private int sid; // int(2) DEFAULT NULL,
	private double money; // decimal(12,2) DEFAULT NULL COMMENT '收入金额',
	private String comment; // varchar(200) DEFAULT NULL COMMENT '备注',
	private double curlat; // double DEFAULT NULL COMMENT '当前纬度',
	private double curlon; // double DEFAULT NULL COMMENT '当前经度',
	private int syn; // int(2) DEFAULT '0' COMMENT '同步标识',
	private Date created; // timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT
							// '创建时间',
	private Date updated; // timestamp NULL DEFAULT '0000-00-00 00:00:00'
							// COMMENT
							// '更新时间',
	private int status; // int(2) DEFAULT '0' COMMENT '状态',

	public long getDetailid()
	{
		return detailid;
	}

	public void setDetailid(long detailid)
	{
		this.detailid = detailid;
	}

	public long getUserid()
	{
		return userid;
	}

	public void setUserid(long userid)
	{
		this.userid = userid;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getMonth()
	{
		return month;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public int getDay()
	{
		return day;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public int getHour()
	{
		return hour;
	}

	public void setHour(int hour)
	{
		this.hour = hour;
	}

	public int getMinute()
	{
		return minute;
	}

	public void setMinute(int minute)
	{
		this.minute = minute;
	}

	public int getSecond()
	{
		return second;
	}

	public void setSecond(int second)
	{
		this.second = second;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public int getPid()
	{
		return pid;
	}

	public void setPid(int pid)
	{
		this.pid = pid;
	}

	public int getCid()
	{
		return cid;
	}

	public void setCid(int cid)
	{
		this.cid = cid;
	}

	public int getMid()
	{
		return mid;
	}

	public void setMid(int mid)
	{
		this.mid = mid;
	}

	public int getSid()
	{
		return sid;
	}

	public void setSid(int sid)
	{
		this.sid = sid;
	}

	public double getMoney()
	{
		return money;
	}

	public void setMoney(double money)
	{
		this.money = money;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public double getCurlat()
	{
		return curlat;
	}

	public void setCurlat(double curlat)
	{
		this.curlat = curlat;
	}

	public double getCurlon()
	{
		return curlon;
	}

	public void setCurlon(double curlon)
	{
		this.curlon = curlon;
	}

	public int getSyn()
	{
		return syn;
	}

	public void setSyn(int syn)
	{
		this.syn = syn;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		Class<?> clazz = this.getClass();
		sb.append(clazz.getName()).append(" [");
		for (Field field : this.getClass().getDeclaredFields())
		{
			PropertyDescriptor pd;
			try
			{
				pd = new PropertyDescriptor(field.getName(), this.getClass());
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
}
