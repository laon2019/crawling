package EPLcrawling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EPL_DAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private static EPL_DAO dao;
	private final String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC";
	private final String user = "root";
	private final String pwd = "110625";
	
	private EPL_DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("-------- DriverManager------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void disConnection() {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static EPL_DAO newInstance() {
		if(dao == null) {
			dao = new EPL_DAO();
		} 
		return dao;
	}
	public void EPLInsert(EPL vo) {
		try {
			// 1. DB연결
			getConnection();
			// 2. sql문 작성
			String sql = "insert into EPL values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// 3. mysql로 sql문을 전달 
			pstmt = conn.prepareStatement(sql);
			
			// 4. vo에게 전달 받은 값을 prepareStatement에 할당
			pstmt.setString(1, vo.getClub());
			pstmt.setString(2, vo.getPlayed());
			pstmt.setString(3, vo.getPoints());
			pstmt.setString(4, vo.getWon());
			pstmt.setString(5, vo.getDrawn());
			pstmt.setString(6, vo.getLost());
			pstmt.setString(7, vo.getGf());
			pstmt.setString(8, vo.getGa());
			pstmt.setString(9, vo.getGd());
			
			
			// 5. 전송된 값을 테이블에 적용시킴. 업데이트
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}