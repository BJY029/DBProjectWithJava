import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class _ViewCampingCars {
	private Connection conn;
	private JFrame frame;
	private JComboBox<String> comboBox;
	private JTable table1;
	private DefaultTableModel tableModel1;
	private JTable table2;
	private DefaultTableModel tableModel2;
	private JScrollPane scrollPane3;
	private JScrollPane scrollPane4;

	/**
	 * Create the application.
	 */
	public _ViewCampingCars() {
		initialize();
		loadCarsNames();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		conn = DBUtil.getConnection();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Table 선택창
		comboBox = new JComboBox<>();
		comboBox.setBounds(20, 20, 300, 25);
		frame.getContentPane().add(comboBox);

		// comboBox에서 선택된 캠핑카를 조회하기 위한 버튼
		JButton btnLoad = new JButton("캠핑카 보기");
		btnLoad.setBounds(340, 20, 120, 25);
		frame.getContentPane().add(btnLoad);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(20, 91, 600, 260);
		frame.getContentPane().add(scrollPane1);
		tableModel1 = new DefaultTableModel();
        table1 = new JTable(tableModel1);
        scrollPane1.setViewportView(table1);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(652, 91, 600, 260);
		frame.getContentPane().add(scrollPane2);
		tableModel2 = new DefaultTableModel();
        table2 = new JTable(tableModel2);
        scrollPane2.setViewportView(table2);
        
        scrollPane3 = new JScrollPane();
        scrollPane3.setBounds(20, 397, 600, 260);
        frame.getContentPane().add(scrollPane3);
        
        scrollPane4 = new JScrollPane();
        scrollPane4.setBounds(652, 397, 600, 260);
        frame.getContentPane().add(scrollPane4);
        
        JLabel SPLabel1 = new JLabel("내부 정비 내역");
        SPLabel1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        SPLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        SPLabel1.setBounds(12, 55, 608, 26);
        frame.getContentPane().add(SPLabel1);
        
        JLabel SPLabel2 = new JLabel("외부 정비 내역");
        SPLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        SPLabel2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        SPLabel2.setBounds(652, 55, 600, 26);
        frame.getContentPane().add(SPLabel2);
        
        btnLoad.addActionListener(e -> {
        	loadCarMaintenanceData((String) comboBox.getSelectedItem());
        	loadCustRepairsData((String) comboBox.getSelectedItem());
        	});
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

    private void loadCarsNames() {
    	try {
    		//선택된 테이블의 전체 레코드 조회
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("select carname from cars");
    		
    		while(rs.next()) {
    			comboBox.addItem(rs.getString("carname"));
    		}
    		rs.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "테이블 목록 로드 실패: " + e.getMessage());
        }
    }
    
    private void loadCarMaintenanceData(String Name) {
    	try {
    		//선택된 테이블의 전체 레코드 조회
    		String sql = "SELECT * FROM carmaintenance WHERE carid = " +
    	             "(SELECT carid FROM cars WHERE carname LIKE ?)";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1,  "%"+Name+"%");
    		ResultSet rs = pstmt.executeQuery();
    		
    		//테이블의 행 수 가져오기
    		ResultSetMetaData meta = rs.getMetaData();
    		int colCnt = meta.getColumnCount();
    		
    		//행 이름을 동적으로 벡터에 저장
    		Vector<String> colNames = new Vector<>();
    		for(int i = 1; i <= colCnt; i++) {
    			String colname = meta.getColumnName(i);
    			switch(colname) {
    				case "MtaID":
    					colNames.add("정비ID");
    					break;
    				case "CarID":
    					colNames.add("차량ID");
    					break;
    				case "PartID":
    					colNames.add("부품ID");
    					break;
    				case "MtaDate":
    					colNames.add("정비날짜");
    					break;
    				case "MtaTime":
    					colNames.add("정비시간(분)");
    					break;
    				case "MechanicID":
    					colNames.add("정비사ID");
    					break;
    				default:
    					colNames.add(colname);
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
    
    private void loadCustRepairsData(String Name) {
    	try {
    		//선택된 테이블의 전체 레코드 조회
    		String sql = "SELECT * FROM custrepair WHERE carid = " +
    	             "(SELECT carid FROM cars WHERE carname LIKE ?)";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1,  "%"+Name+"%");
    		ResultSet rs = pstmt.executeQuery();
    		
    		//테이블의 행 수 가져오기
    		ResultSetMetaData meta = rs.getMetaData();
    		int colCnt = meta.getColumnCount();
    		
    		//행 이름을 동적으로 벡터에 저장
    		Vector<String> colNames = new Vector<>();
    		for(int i = 1; i <= colCnt; i++) {
    			String colname = meta.getColumnName(i);
    			switch(colname) {
    				
    				default:
    					colNames.add(colname);
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
    		tableModel2.setDataVector(data, colNames);
    		
    		rs.close();
    		pstmt.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
        }
    }
}
