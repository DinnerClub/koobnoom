package net.mooncloud.moonbook.entity.income;

import java.io.Serializable;
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

}
