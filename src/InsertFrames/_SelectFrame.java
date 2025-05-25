package InsertFrames;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class _SelectFrame {
	private Connection conn;
	private JFrame frame;

	private JTable table1;
	private DefaultTableModel tableModel1;
	private JScrollPane scrollPane1;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton EnterBtn;
	/**
	 * Create the application.
	 */
	public _SelectFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		conn = DBUtil.getConnection();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(12, 193, 939, 358);
		frame.getContentPane().add(scrollPane1);
		tableModel1 = new DefaultTableModel();
        table1 = new JTable(tableModel1);
        scrollPane1.setViewportView(table1);
        
        lblNewLabel = new JLabel("Input Select Query");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        lblNewLabel.setBounds(12, 10, 939, 31);
        frame.getContentPane().add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(12, 52, 880, 131);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        EnterBtn = new JButton("<<");
        EnterBtn.addActionListener(e->loadData());
        EnterBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        EnterBtn.setBounds(897, 53, 56, 130);
        frame.getContentPane().add(EnterBtn);
		
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	
    private void loadData() {
    	try {
    		//선택된 테이블의 전체 레코드 조회
    		String sql = textField.getText().trim();
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		ResultSet rs = pstmt.executeQuery();
    		
    		//테이블의 행 수 가져오기
    		ResultSetMetaData meta = rs.getMetaData();
    		int colCnt = meta.getColumnCount();
    		
    		int partIndex = 0;
    		
    		//행 이름을 동적으로 벡터에 저장
			Vector<String> colNames = new Vector<>();
			for (int i = 1; i <= colCnt; i++) {
				String colname = meta.getColumnName(i);
				colNames.add(colname);

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
    		
    		rs.close();
    		pstmt.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
        }
    }
}
