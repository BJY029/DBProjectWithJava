import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class _AllTablesFrame {
	
	private Connection conn;
	private JFrame frame;
	private JComboBox<String> comboBox;
	private JTable table;
	private DefaultTableModel tableModel;


	/**
	 * Create the application.
	 */
	public _AllTablesFrame() {
		initialize();
		loadTableNames();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//DB 연결 정보 받아오기
		conn = DBUtil.getConnection();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 740, 480);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Table 선택창
		comboBox = new JComboBox<>();
        comboBox.setBounds(20, 20, 300, 25);
        frame.getContentPane().add(comboBox);
		
        //comboBox에서 선택된 테이블을 조회하기 위한 버튼
        JButton btnLoad = new JButton("테이블 보기");
        btnLoad.setBounds(340, 20, 120, 25);
        frame.getContentPane().add(btnLoad);
        
        //테이블이 표시되는 scrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 60, 640, 380);
        frame.getContentPane().add(scrollPane);
        //테이블 객체 선언
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        //버튼 클릭시 콤보박스에서 선택된 테이블을 기반으로 데이터를 불러온다.
        btnLoad.addActionListener(e -> loadTableData((String) comboBox.getSelectedItem()));
	}
	

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
    
    //모든 테이블 이름을 가져와서 콤보 박스에 추가하는 함수
    private void loadTableNames() {
    	try {
    		//메타 데이터에서 DBTEST 스키마 내 모든 실제 테이블 목록 가져오기
    		DatabaseMetaData meta = conn.getMetaData();
    		ResultSet rs = meta.getTables("DBTEST", null, "%", new String[]{"TABLE"});
    		//결과 집합에서 테이블 이름만 추출하여 콤보 박스에 추가한다.
            while (rs.next()) {
                comboBox.addItem(rs.getString("TABLE_NAME"));
            }
            rs.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "테이블 목록 로드 실패: " + e.getMessage());
        }
    }
    
    //특정 테이블을 표시하는 함수
    private void loadTableData(String tableName) {
    	try {
    		//선택된 테이블의 전체 레코드 조회
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("select * from " + tableName);
    		
    		//테이블의 행 수 가져오기
    		ResultSetMetaData meta = rs.getMetaData();
    		int colCnt = meta.getColumnCount();
    		
    		//행 이름을 동적으로 벡터에 저장
    		Vector<String> colNames = new Vector<>();
    		for(int i = 1; i <= colCnt; i++) {
    			colNames.add(meta.getColumnName(i));
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
    		tableModel.setDataVector(data, colNames);
    		
    		rs.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
        }
    }
}
