package User;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class _UserCheckRentFrame {
	private Connection conn;
	private JFrame frame;
	private int userID;
	
	private JTable table1;
	private DefaultTableModel tableModel1;


	/**
	 * Create the application.
	 */
	public _UserCheckRentFrame() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		conn = DBUtil.getUserConnection();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("예약내역 확인 및 변경");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 1240, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 53, 1230, 89);
		frame.getContentPane().add(scrollPane);
		
		tableModel1 = new DefaultTableModel();
        table1 = new JTable(tableModel1);
        scrollPane.setViewportView(table1);


	}

	
	   private void loadUserRentData() {
	    	try {
	    		String userLicense = getUserLicenseNum();
	    		//선택된 테이블의 전체 레코드 조회
	    		String sql = "SELECT rentstart, DATE_ADD(rentstart, INTERVAL rentperiod DAY) as rentend, rentperiod, rentprice, paymentdate, etcdetails, etcprice"
	    				+ " FROM rent WHERE licenseNum like ?";
	
	    		PreparedStatement pstmt = conn.prepareStatement(sql);
	    		pstmt.setString(1, "%"+userLicense+"%");
	    		ResultSet rs = pstmt.executeQuery();
	    		
	    		//테이블의 행 수 가져오기
	    		ResultSetMetaData meta = rs.getMetaData();
	    		int colCnt = meta.getColumnCount();
	    		
	    		
	    		//행 이름을 동적으로 벡터에 저장
	    		Vector<String> colNames = new Vector<>();
	    		for(int i = 1; i <= colCnt; i++) {
	    			String colname = meta.getColumnName(i);
	    			switch(colname) {
	    				case "RentStart":
	    					colNames.add("대여 시작일");
	    					break;
	    				case "rentend":
	    					colNames.add("반납일");
	    					break;
	    				case "RentPeriod":
	    					colNames.add("대여 기간(일)");
	    					break;
	    				case "RentPrice":
	    					colNames.add("대여 가격");
	    					break;
	    				case "PaymentDate":
	    					colNames.add("납입 기한");
	    					break;
	    				case "etcDetails":
	    					colNames.add("기타 내역");
	    					break;
	    				case "etcPrice":
	    					colNames.add("추가 비용");
	    					break;
	    				default:
	    					break;
	    			}
	    		}
	    		  		
	    		//각 레코드(행)의 값을 벡터로 담아 전체 데이터 벡터에 추가
	    		Vector<Vector<Object>> data = new Vector<>();
	    		while(rs.next()) {
	    			Vector<Object> row = new Vector<>();
	    			for(int i = 1; i <= colCnt; i++) {
	    				row.add(rs.getObject(i));
	    			}
	    			data.add(row);
	    		}
	    		
	    		//DefaultTableModel에 데이터와 속성 이름을 설정
	    		//JTable 자동 업데이트
	    		tableModel1.setDataVector(data, colNames);

	    		
	    		rs.close();
	    		pstmt.close();
	    	}catch (SQLException e) {
	            JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
	        }
	    }
	   
		private String getUserLicenseNum() {
			try {
				String LNum = null;
				
				String sql = "select licensenum from customer where custid = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, String.valueOf(userID));
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					LNum = rs.getString("licensenum");
				}
				
				pstmt.close();
				rs.close();
				return LNum;
				
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(frame, "사용자 정보(면허번호) 불러오기 오류" + e.getMessage());
				return null;
			}
		}
	
	public void setVisible(boolean b, int userId) {
		userID = userId;
		loadUserRentData();
		frame.setVisible(b);
	}
	
	
}
