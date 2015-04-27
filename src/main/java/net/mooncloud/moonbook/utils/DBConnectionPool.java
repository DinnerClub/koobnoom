package net.mooncloud.moonbook.utils;
/**
 * 管理类DBConnectionManager支持对一个或多个由属性文件定义的数据库连接池的访问。
 * 客户程序可以调用getInstance()方法访问本类的唯一实例。
 * 装载和注册JDBC驱动程序。
 * 根据在属性文件中定义的属性创建连接池对象。
 * 实现连接池名字与其实例之间的映射。
 * 跟踪客户程序对连接池的引用，保证在最后一个客户程序结束时安全地关闭所有连接池。
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

public class DBConnectionPool
{
	// private int checkedOut;
	private Vector freeConnections = new Vector();

	private int maxConn;

	private String password;

	private String URL;

	private String user;

	/**
	 * 创建新的连接池
	 * 
	 * @param name
	 *            连接池名字
	 * @param URL
	 *            数据库的JDBC URL
	 * @param user
	 *            数据库帐号,或 null
	 * @param password
	 *            密码,或 null
	 * @param maxConn
	 *            此连接池允许建立的最大连接数,0表示没有限制
	 */
	public DBConnectionPool(final String URL, final String user, final String password,
			final int maxConn)
	{
		this.URL = URL;
		this.user = user;
		this.password = password;
		this.maxConn = maxConn;
	}

	/**
	 * 将不再使用的连接返回给连接池
	 * 
	 * @param con
	 *            客户程序释放的连接
	 */
	public synchronized void freeConnection(Connection con)
	{
		// 将指定连接加入到向量末尾
		try
		{
			if (con.isClosed())
			{
				System.out.println("before freeConnection con is closed");
			}
			freeConnections.addElement(con);
			Connection contest = (Connection) freeConnections.lastElement();
			if (contest.isClosed())
			{
				System.out.println("after freeConnection contest is closed");
			}
			notifyAll();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}

	/**
	 * 从连接池获得一个可用连接.如没有空闲的连接且当前连接数小于最大连接 数限制,则创建新连接.如原来登记为可用的连接不再有效,则从向量删除之, 然后递归调用自己以尝试新的可用连接.
	 */
	public synchronized Connection getConnection()
	{
		Connection con = null;
		if (freeConnections.size() > 0)
		{
			// 获取向量中第一个可用连接
			con = (Connection) freeConnections.firstElement();
			freeConnections.removeElementAt(0);
			try
			{
				if (con.isClosed())
				{
					System.out.println("从连接池删除一个无效连接");
					// 递归调用自己,尝试再次获取可用连接
					con = getConnection();
				}
			}
			catch (SQLException e)
			{
				System.out.println("从连接池删除一个无效连接出错");
				// 递归调用自己,尝试再次获取可用连接
				con = getConnection();
			}
			if (freeConnections.size() > maxConn)
			{
				System.out.println(" 删除一个溢出连接 ");
				releaseOne();
			}
		}

		else if ((maxConn == 0) || (freeConnections.size() < maxConn))
		{
			con = newConnection();
		}
		return con;
	}

	public synchronized Connection returnConnection()
	{
		Connection con = null;
		// 如果闲置小于最大连接,返回一个新连接
		if (freeConnections.size() < maxConn)
		{
			con = newConnection();
		}
		// 如果闲置大于最大连接，返回一个可用的旧连接
		else if (freeConnections.size() >= maxConn)
		{

			con = (Connection) freeConnections.firstElement();
			// System.out.println(" [a 连接池可用连接数 ] : " + "[ " + freeConnections.size() + " ]");
			freeConnections.removeElementAt(0);
			// System.out.println(this.URL);
			// System.out.println(" [b 连接池可用连接数 ] : " + "[ " + freeConnections.size() + " ]");
			try
			{
				if (con.isClosed())
				{
					System.out.println("从连接池删除一个无效连接");
					returnConnection();
				}
			}
			catch (SQLException e)
			{
				System.out.println("从连接池删除一个无效连接出错");
				returnConnection();
			}
		}
		return con;
	}

	/**
	 * 从连接池获取可用连接.可以指定客户程序能够等待的最长时间 参见前一个getConnection()方法.
	 * 
	 * @param timeout
	 *            以毫秒计的等待时间限制
	 */
	public synchronized Connection getConnection(long timeout)
	{
		long startTime = new Date().getTime();
		Connection con;
		while ((con = getConnection()) == null)
		{
			try
			{
				wait(timeout);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if ((new Date().getTime() - startTime) >= timeout)
			{
				// wait()返回的原因是超时
				return null;
			}
		}
		return con;
	}

	/**
	 * 关闭所有连接
	 */
	public synchronized void release()
	{
		Enumeration allConnections = freeConnections.elements();
		while (allConnections.hasMoreElements())
		{
			Connection con = (Connection) allConnections.nextElement();
			try
			{
				con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		freeConnections.removeAllElements();
	}

	/**
	 * 关闭一个连接
	 */
	public synchronized void releaseOne()
	{
		if (freeConnections.firstElement() != null)
		{
			Connection con = (Connection) freeConnections.firstElement();
			try
			{
				con.close();
				System.out.println("关闭连接池中的一个连接");
			}
			catch (SQLException e)
			{

				System.out.println("无法关闭连接池中的一个连接");
			}
		}
		else
		{
			System.out
					.println("releaseOne() bug.......................................................");

		}
	}

	/**
	 * 创建新的连接
	 */
	private Connection newConnection()
	{
		Connection con = null;
		try
		{
			if (user == null)
			{
				con = DriverManager.getConnection(URL);
			}
			else
			{
				con = DriverManager.getConnection(this.URL + "?user=" + this.user + "&password="
						+ this.password + "&useUnicode=true" + "&characterEncoding=utf8"
						+ "&autoReconnect=true" + "&zeroDateTimeBehavior=convertToNull");

			}

		}
		catch (SQLException e)
		{
			return null;
		}
		return con;
	}
}
