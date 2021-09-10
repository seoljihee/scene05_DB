package ex01.dbcommon;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCommon {
	
	public static Connection con;
	public static void setDBConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jihee","1234");
			System.out.println("연결 되었습니다. : " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
