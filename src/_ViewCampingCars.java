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
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;

public class _ViewCampingCars {
	private Connection conn;
	private JFrame frame;
	private JComboBox<String> comboBox;
	private JComboBox<String> PartsComboBox;
	private JComboBox<String> repairShopComboBox;
	
	private JTable table1;
	private DefaultTableModel tableModel1;
	private JTable table2;
	private DefaultTableModel tableModel2;
	private JTable table3;
	private DefaultTableModel tableModel3;
	private JTable table4;
	private DefaultTableModel tableModel4;
	private JScrollPane scrollPane3;
	private JScrollPane scrollPane4;
	
	private JButton btnParts;
	private JLabel lblNewLabel_1;
	private JButton btnRepairShop;
	


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
        scrollPane3.setBounds(20, 488, 600, 108);
        frame.getContentPane().add(scrollPane3);
        tableModel3 = new DefaultTableModel();
        table3 = new JTable(tableModel3);
        scrollPane3.setViewportView(table3);
        
        scrollPane4 = new JScrollPane();
        scrollPane4.setBounds(652, 491, 600, 102);
        frame.getContentPane().add(scrollPane4);
        tableModel4 = new DefaultTableModel();
        table4 = new JTable(tableModel4);
        scrollPane4.setViewportView(table4);
        
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
        
        JLabel lblNewLabel = new JLabel("부품 상세 내역");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        lblNewLabel.setBounds(20, 398, 600, 33);
        frame.getContentPane().add(lblNewLabel);
        
        
        PartsComboBox = new JComboBox<>();
        PartsComboBox.setBounds(20, 455, 300, 23);
        frame.getContentPane().add(PartsComboBox);
        
        btnParts = new JButton("부품 검색");
        btnParts.setBounds(340, 455, 97, 23);
        frame.getContentPane().add(btnParts);
        
        lblNewLabel_1 = new JLabel("외부 정비소 상세 내역");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(652, 398, 600, 33);
        frame.getContentPane().add(lblNewLabel_1);
        
        repairShopComboBox = new JComboBox<>();
        repairShopComboBox.setBounds(649, 455, 300, 23);
        frame.getContentPane().add(repairShopComboBox);
        
        btnRepairShop = new JButton("정비소 검색");
        btnRepairShop.setBounds(961, 455, 97, 23);
        frame.getContentPane().add(btnRepairShop);
        
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
    		
    		int partIndex = 0;
    		
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
    					partIndex = i;
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
    		
    		Vector<String> Parts = new Vector<>();
    		
    		//각 레코드(행)의 값을 벡터로 담아 전체 데이터 벡터에 추가
    		Vector<Vector<Object>> data = new Vector<>();
    		while(rs.next()) {
    			Vector<Object> row = new Vector<>();
    			for(int i = 1; i <= colCnt; i++) {
    				row.add(rs.getObject(i));
    				if(i == partIndex)
    					Parts.add(rs.getObject(i).toString());
    			}
    			data.add(row);
    		}
    		
    		//DefaultTableModel에 데이터와 속성 이름을 설정
    		//JTable 자동 업데이트
    		tableModel1.setDataVector(data, colNames);
    		
    		loadPartsName(Parts);
    		
    		rs.close();
    		pstmt.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
        }
    }
    
    private void loadPartsName(Vector<String> parts) {
    	try {
    		PartsComboBox.removeAllItems();
    		for(int i = 0; i < parts.size(); i++) {
    			PartsComboBox.addItem(parts.get(i));
    		}
    		
    		btnParts.addActionListener(e -> loadPartData((String) PartsComboBox.getSelectedItem()));
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(frame, e);
    	}
    }

    
    private void loadPartData(String partId) {
    	try {
    		String sql = "SELECT PartName, PartPrice, PartCnt, PartCompany FROM parts WHERE partId = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, partId);
    		ResultSet rs = pstmt.executeQuery();
    		
    		ResultSetMetaData meta = rs.getMetaData();
    		int colCnt = meta.getColumnCount();
    		
    		Vector<String> colNames = new Vector<>();
    		for (int i = 1; i <= colCnt; i++) {
    		    String colName = meta.getColumnName(i);
    		    if (colName.equals("PartName"))
    		        colNames.add("부품 이름");
    		    else if (colName.equals("PartPrice"))
    		        colNames.add("부품 가격");
    		    else if (colName.equals("PartCnt"))
    		        colNames.add("부품 재고");
    		    else if (colName.equals("PartCompany"))
    		        colNames.add("부품 회사");
    		    else
    		        colNames.add(colName); // 기본적으로 영문 그대로 추가
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
    		tableModel3.setDataVector(data, colNames);
    		
    		rs.close();
    		pstmt.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
        }
    }
    
    
    private void loadCustRepairsData(String Name) {
    	try {
    		//선택된 테이블의 전체 레코드 조회
    		String sql = "SELECT CustRepairID, RPID, CarID, LicenseNum, RepairDetails, RepairDate, RepairPrice, PaymentDate, etcDetails"
    				+ " FROM custrepair WHERE carid = " +
					"(SELECT carid FROM cars WHERE carname LIKE ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + Name + "%");
			ResultSet rs = pstmt.executeQuery();

			// 테이블의 행 수 가져오기
			ResultSetMetaData meta = rs.getMetaData();
			int colCnt = meta.getColumnCount();
			
			int repairIndex = 0;

			// 행 이름을 동적으로 벡터에 저장
			Vector<String> colNames = new Vector<>();
			for (int i = 1; i <= colCnt; i++) {
				String colname = meta.getColumnName(i);
				switch (colname) {
					case "CustRepairID":
						colNames.add("고유정비번호");
						break;
					case "RPID":
						colNames.add("외부정비소ID");
						repairIndex = i;
						break;
					case "CarID":
						colNames.add("차량ID");
						break;
					case "LicenseNum":
						colNames.add("고객면허번호");
						break;
					case "RepairDetails":
						colNames.add("정비내역");
						break;
					case "RepairDate":
						colNames.add("수리날짜");
						break;
					case "RepairPrice":
						colNames.add("수리비용");
						break;
					case "PaymentDate":
						colNames.add("납입기한");
						break;
					case "etcDetails":
						colNames.add("기타정비내역");
						break;
				default:
					break;
				}
			}
    		
			Vector<String> Shops = new Vector<>();
    		//각 레코드(행)의 값을 벡터로 담아 전체 데이터 벡터에 추가
    		Vector<Vector<Object>> data = new Vector<>();
    		while(rs.next()) {
    			Vector<Object> row = new Vector<>();
    			for(int i = 1; i <= colCnt; i++) {
    				row.add(rs.getObject(i));
    				if(repairIndex == i)
    					Shops.add(rs.getObject(i).toString());
    			}
    			data.add(row);
    		}
    		
    		//DefaultTableModel에 데이터와 속성 이름을 설정
    		//JTable 자동 업데이트
    		tableModel2.setDataVector(data, colNames);
    		
    		loadRepairShop(Shops);
    		
    		rs.close();
    		pstmt.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
        }
    }
    
    private void loadRepairShop(Vector<String> shops) {
    	try {
    		repairShopComboBox.removeAllItems();
    		for(int i = 0; i < shops.size(); i++) {
    			repairShopComboBox.addItem(shops.get(i));
    		}
    		
    		btnRepairShop.addActionListener(e -> loadShopData((String) repairShopComboBox.getSelectedItem()));
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(frame, e);
    	}
    }

    
    private void loadShopData(String shopId) {
    	try {
    		String sql = "SELECT RPName, RPAddress, RPPhone, RPMngName, RpMngEmail FROM repairshop WHERE rpid = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, shopId);
    		ResultSet rs = pstmt.executeQuery();
    		
    		ResultSetMetaData meta = rs.getMetaData();
    		int colCnt = meta.getColumnCount();
    		
    		Vector<String> colNames = new Vector<>();
    		for (int i = 1; i <= colCnt; i++) {
    		    String colName = meta.getColumnName(i);
    		    if (colName.equals("RPName"))
    		        colNames.add("정비소 이름");
    		    else if (colName.equals("RPAddress"))
    		        colNames.add("정비소 주소");
    		    else if (colName.equals("RPPhone"))
    		        colNames.add("전화번호");
    		    else if (colName.equals("RPMngName"))
    		        colNames.add("담당자 이름");
    		    else if (colName.equals("RpMngEmail"))
    		        colNames.add("담당자 이메일");
    		    else
    		        colNames.add(colName); // 기본적으로 영문 그대로 추가
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
    		tableModel4.setDataVector(data, colNames);
    		
    		rs.close();
    		pstmt.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
        }
    }
}
