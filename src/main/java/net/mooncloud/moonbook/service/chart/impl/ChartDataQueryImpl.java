package net.mooncloud.moonbook.service.chart.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.chart.ChartElement;
import net.mooncloud.moonbook.service.chart.ChartDataQuery;
import net.mooncloud.moonbook.utils.DBConf;

public class ChartDataQueryImpl implements ChartDataQuery
{

	static Connection connection = null;
	static Statement statement = null;

	public List<ChartElement> userPaymentFacetQuery(Map<String, Object> querys, List<String> fields, List<String> aggregates, String index, String table)
	{
		try
		{
			connection = DBConf.getConnection();
			statement = connection.createStatement();
			connection.setAutoCommit(false);

			// statement.addBatch(SqlFacetQueryString.facetQueryString(querys,
			// fields, aggregates, index, table, true));

			String facetQueryString = SqlFacetQueryString.facetQueryString(querys, fields, aggregates, index, table, true);
			ResultSet resultSet = statement.executeQuery(facetQueryString);
			while(resultSet.next())
			{
//				resultSet.get
			}
			statement.executeBatch();
			connection.commit();
			statement.clearBatch();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
