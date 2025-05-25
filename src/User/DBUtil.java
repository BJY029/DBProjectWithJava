package User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	//전역 접근을 위해 static으로 관리한다.
	private static Connection conn;
	private static Connection userConn;
	
	//DB 연결 객체를 반환하는 관리자용 DB 연결 시도 함수
	public static Connection getConnection(){
		try {
			//만약 연결된 DB가 없는 경우
			if(conn == null || conn.isClosed()) {
				//DB 연결을 시도한다.
				Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBTEST", "root","1234"); // JDBC 연결
				System.out.println("DB 연결 완료");
			}
		//연결 실패한 경우 null을 반환한다.
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
			return null;
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			return null;
		}
		//연결된 DB 객체를 반환한다.
		return conn;
	}
	
	//DB 연결 객체를 반환하는 사용자용 DB 연결 시도 함수
	public static Connection getUserConnection() {
		try {
			if(userConn == null || userConn.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
				userConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBTEST", "user1","user1"); // JDBC 연결
				System.out.println("User용 DB 연결 완료");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
			return null;
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			return null;
		}
		return userConn;
	}
	
	//DB 연결을 끊는 함수(창이 닫힐 때 호출 될 함수)
	public static void closeConnection() {
		try {
			//만약 현재 어떤 DB가 연결되어 있는 경우
			if(conn != null && !conn.isClosed()) {
				//해당 DB 연결을 끊는다.
				conn.close();
				System.out.println("DB 연결 종료");
			}
			//만약 현재 어떤 User DB가 연결되어 있는 경우
			if(userConn != null && !userConn.isClosed()) {
				//해당 DB 연결을 끊는다.
				userConn.close();
				System.out.println("DB 연결 종료");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
