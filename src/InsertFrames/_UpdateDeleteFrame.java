package InsertFrames;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.SQLException;

public class _UpdateDeleteFrame {

	private JFrame frame;
	private Connection conn;
	private JTextField SetField;
	private JTextField WhereField;
	private JTextField textField;
	
	private JComboBox<String> UpdateComboBox;
	private JComboBox<String> DeleteComboBox;
	/**
	 * Create the application.
	 */
	public _UpdateDeleteFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		conn = DBUtil.getConnection();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 500);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Table");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 36, 182, 40);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUpdate = new JLabel("UPDATE");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblUpdate.setBounds(12, 102, 171, 40);
		frame.getContentPane().add(lblUpdate);
		
		JLabel lblSet = new JLabel("SET");
		lblSet.setHorizontalAlignment(SwingConstants.CENTER);
		lblSet.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblSet.setBounds(12, 163, 171, 40);
		frame.getContentPane().add(lblSet);

		JLabel lblWhere = new JLabel("WHERE");
		lblWhere.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhere.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblWhere.setBounds(11, 299, 171, 40);
		frame.getContentPane().add(lblWhere);
		
		JLabel lblDeleteTable = new JLabel("Delete Table");
		lblDeleteTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteTable.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblDeleteTable.setBounds(492, 36, 182, 40);
		frame.getContentPane().add(lblDeleteTable);
		
		JLabel lblDeleteFrom = new JLabel("DELETE FROM");
		lblDeleteFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteFrom.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblDeleteFrom.setBounds(540, 102, 171, 40);
		frame.getContentPane().add(lblDeleteFrom);
		
		JLabel lblWhere_1 = new JLabel("WHERE");
		lblWhere_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhere_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblWhere_1.setBounds(540, 163, 171, 40);
		frame.getContentPane().add(lblWhere_1);
		
		
		UpdateComboBox = new JComboBox<>();
		UpdateComboBox.setBounds(187, 102, 274, 40);
		frame.getContentPane().add(UpdateComboBox);
		
		SetField = new JTextField();
		SetField.setBounds(187, 163, 274, 111);
		frame.getContentPane().add(SetField);
		SetField.setColumns(10);
		
		WhereField = new JTextField();
		WhereField.setColumns(10);
		WhereField.setBounds(187, 299, 274, 111);
		frame.getContentPane().add(WhereField);
		

		
		
		DeleteComboBox = new JComboBox<>();
		DeleteComboBox.setBounds(713, 102, 274, 40);
		frame.getContentPane().add(DeleteComboBox);
 
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(713, 163, 274, 111);
		frame.getContentPane().add(textField);
		
		JButton UpdateBtn = new JButton("Update!");
		UpdateBtn.addActionListener(e->UpdateAction());
		UpdateBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		UpdateBtn.setBounds(197, 420, 113, 31);
		frame.getContentPane().add(UpdateBtn);
		
		JButton DeleteBtn = new JButton("Delete!");
		DeleteBtn.addActionListener(e->DeleteAction());
		DeleteBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		DeleteBtn.setBounds(731, 313, 113, 31);
		frame.getContentPane().add(DeleteBtn);
		
		
		loadTableNames();
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
                UpdateComboBox.addItem(rs.getString("TABLE_NAME"));
                DeleteComboBox.addItem(rs.getString("TABLE_NAME"));
            }
            rs.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "테이블 목록 로드 실패: " + e.getMessage());
        }
    }

    private void UpdateAction() {
        String tableName = (String) UpdateComboBox.getSelectedItem();
        String setString = SetField.getText().trim();
        String whereString = WhereField.getText().trim();

        if (setString.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "SET 내용을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String sql = "UPDATE " + tableName + " SET " + setString;
            if (!whereString.isEmpty()) {
                sql += " WHERE " + whereString;
            }

            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);

            if (result > 0) {
                JOptionPane.showMessageDialog(frame, "성공적으로 수정이 완료되었습니다!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "수정 실패", "오류", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "DB 오류: " + ex.getMessage());
        }
    }
    
    private void DeleteAction() {
        String tableName = (String) DeleteComboBox.getSelectedItem();
        String whereClause = textField.getText().trim();

        if (tableName == null || tableName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "테이블을 선택하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (whereClause.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(frame, 
                "WHERE 절이 없습니다. 전체 데이터를 삭제하시겠습니까?", 
                "경고", 
                JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try {
            String sql = "DELETE FROM " + tableName;
            if (!whereClause.isEmpty()) {
                sql += " WHERE " + whereClause;
            }

            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);

            if (result > 0) {
                JOptionPane.showMessageDialog(frame, "성공적으로 삭제가 완료되었습니다!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "삭제 실패", "오류", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "DB 오류: " + ex.getMessage());
        }
    }


}
