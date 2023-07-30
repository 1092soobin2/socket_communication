

public class countingDao {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://[host]:[포트]/[db이름]?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private String user = "SEI0249";
	private String password = "[비밀번호]";

	// 1. JDBC driver 로딩
	try {
		Class.forName(driver);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}

	// 2. DB 서버 연결
	Connection connection = DriverManager.getConnection(url, user, password);

	// 3. SQL 실행 통로 형성
	Statement statement = connection.createSTatement();

	// 4. SQL 실행
	String sql = "select * from user";
	ResultSet rs = stmt.executeQuery(sql);

}
