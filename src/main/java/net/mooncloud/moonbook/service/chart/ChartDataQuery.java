package net.mooncloud.moonbook.service.chart;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.chart.ChartElement;

/**
 * 图表数据查询
 * 
 * @author yangjd
 *
 */
public interface ChartDataQuery
{
	/**
	 * 用户支出Facet查询
	 * 
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
	 */
	public List<ChartElement> userPaymentFacetQuery(Map<String, Object> querys, List<String> fields, List<String> aggregates, String index, String table);
}
