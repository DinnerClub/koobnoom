package net.mooncloud.moonbook.service.chart.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 任意条件筛选，任意维度组合，对money进行统计查询，使用统计函数
 * 
 * @author yangjd
 *
 */
public class SqlFacetQueryString
{
	public static final String SUM = "SUM"; // 和
	public static final String COUNT = "COUNT";// 项数,始终返回 int 数据类型值
	public static final String AVG = "AVG";// 平均值
	public static final String MAX = "MAX";// 最大值
	public static final String MIN = "MIN";// 最小值
	public static final String COUNT_BIG = "COUNT_BIG";// 项数,始终返回 bigint 数据类型值
	public static final String GROUPING = "GROUPING";//
	public static final String BINARY_CHECKSUM = "BINARY_CHECKSUM";//
	public static final String CHECKSUM_AGG = "CHECKSUM_AGG";// 校验和
	public static final String CHECKSUM = "CHECKSUM";//
	public static final String STDEV = "STDEV";// 所有值的标准偏差
	public static final String STDEVP = "STDEVP";// 总体标准偏差
	public static final String VAR = "VAR";// 所有值的方差
	public static final String VARP = "VARP";// 所有值的总体统计方差

	/**
	 * @param querys
	 *            查询条件组合(AND)
	 * @param fields
	 *            维度组合
	 * @param aggregates
	 *            聚合函数
	 * @param index
	 *            聚合字段
	 * @param table
	 *            表
	 * @param facet
	 * @return
	 */
	public static String facetQueryString(Map<String, Object> querys, List<String> fields, List<String> aggregates, String index, String table, boolean facet)
	{
		// SELECT month, day, SUM(money) AS SUM, COUNT(money) AS COUNT FROM
		// user_payment_detail WHERE year=2015 AND comment="2222" GROUP BY
		// month, day
		if (facet == true)
		{
			StringBuilder fieldSb = new StringBuilder();
			StringBuilder groupSb = new StringBuilder();
			StringBuilder aggregateSb = new StringBuilder();

			// field, group
			if (fields != null && fields.size() > 0)
			{
				fieldSb.append(fields.get(0));
				fields.remove(0);
				for (String field : fields)
				{
					fieldSb.append(", ").append(field);
				}
				if (aggregates != null && aggregates.size() > 0)
				{
					groupSb.append(" GROUP BY ").append(fieldSb);
					aggregateSb.append(", ");
				}
			}

			// aggregate
			if (aggregates != null && aggregates.size() > 0)
			{
				String aggregate = aggregates.get(0);
				aggregateSb.append(aggregate).append("(").append(index).append(")").append(" AS ").append(aggregate);
				for (int i = 1; i < aggregates.size(); i++)
				{
					aggregate = aggregates.get(i);
					aggregateSb.append(", ").append(aggregate).append("(").append(index).append(")").append(" AS ").append(aggregate);
				}
			}

			// query
			StringBuilder querySb = new StringBuilder();
			if (querys != null && querys.size() > 0)
			{
				querySb.append(" WHERE ");
				List<Entry<String, Object>> querysEntryList = new ArrayList<Map.Entry<String, Object>>(querys.entrySet());

				Entry<String, Object> entry = querysEntryList.get(0);
				String key = entry.getKey();
				Object value = entry.getValue();
				if (value instanceof String)
				{
					querySb.append(key).append("=").append("\"").append(value).append("\"");
				}
				else
				{
					querySb.append(key).append("=").append(value);
				}
				for (int i = 1; i < querysEntryList.size(); i++)
				{
					entry = querysEntryList.get(i);
					key = entry.getKey();
					value = entry.getValue();

					querySb.append(" AND ");
					if (value instanceof String)
					{
						querySb.append(key).append("=").append("\"").append(value).append("\"");
					}
					else
					{
						querySb.append(key).append("=").append(value);
					}
				}
			}// query

			StringBuilder sqlSb = new StringBuilder();

			sqlSb.append("SELECT ").append(fieldSb).append(aggregateSb).append(" FROM ").append(table).append(querySb).append(groupSb);
			return sqlSb.toString();
		}
		return null;
	}

	public static void main(String[] args)
	{
		SqlFacetQueryString query = new SqlFacetQueryString();
		Map<String, Object> querys = new HashMap<String, Object>();
		List<String> fields = new ArrayList<String>();
		List<String> aggregates = new ArrayList<String>();
		String index = null;
		String table = null;
		boolean facet = true;

		querys.put("year", 2015);
		querys.put("comment", "2222");
//		fields.add("month");
//		fields.add("day");
		fields.add("*");
//		aggregates.add("SUM");
//		aggregates.add("COUNT");
		index = "money";
		table = "user_payment_detail";
		// SomeStaticUtils.
		String facetQueryString = query.facetQueryString(querys, fields, aggregates, index, table, facet);
		
		System.out.println(facetQueryString);
	}
}
