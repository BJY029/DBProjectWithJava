package InsertFrames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class _EmployeeFrame {
	private Connection conn;
	private JFrame frame;

	private JTextField empNameField;
	private JTextField empPhoneField;
	private JTextField empAddressField;
	private JTextField empFamilyCntField;
	private JTextField empSalaryField;
	private JTextField empDepartmentField;
	private JComboBox comboBox;
	/**
	 * Create the application.
	 */
	public _EmployeeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Title = new JLabel("Employee Table 삽입");
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(12, 38, 1240, 35);
		frame.getContentPane().add(Title);
		
		JLabel empNameLabel = new JLabel("직원 이름");
		empNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		empNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empNameLabel.setBounds(240, 96, 187, 50);
		frame.getContentPane().add(empNameLabel);
		
		JLabel empPhoneLabel = new JLabel("직원 전화번호");
		empPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empPhoneLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		empPhoneLabel.setBounds(240, 156, 187, 50);
		frame.getContentPane().add(empPhoneLabel);
		
		JLabel empAddressLabel = new JLabel("직원 집 주소");
		empAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empAddressLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		empAddressLabel.setBounds(240, 216, 187, 50);
		frame.getContentPane().add(empAddressLabel);
		
		JLabel empSalaryLabel = new JLabel("직원 월 급여");
		empSalaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empSalaryLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		empSalaryLabel.setBounds(240, 276, 187, 50);
		frame.getContentPane().add(empSalaryLabel);
		
		JLabel empFamilyCntLabel = new JLabel("직원 부양 가족 수");
		empFamilyCntLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empFamilyCntLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		empFamilyCntLabel.setBounds(240, 336, 187, 50);
		frame.getContentPane().add(empFamilyCntLabel);
		
		JLabel empDepartmentLabel = new JLabel("직원 부서명");
		empDepartmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empDepartmentLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		empDepartmentLabel.setBounds(240, 396, 187, 50);
		frame.getContentPane().add(empDepartmentLabel);
		
		JLabel empWorkLabel = new JLabel("직원 담당 업무");
		empWorkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empWorkLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		empWorkLabel.setBounds(240, 456, 187, 50);
		frame.getContentPane().add(empWorkLabel);
		
		
		empNameField = new JTextField();
		empNameField.setBackground(new Color(255, 255, 255));
		empNameField.setToolTipText("");
		empNameField.setBounds(439, 106, 439, 40);
		frame.getContentPane().add(empNameField);
		empNameField.setColumns(10);
		
		empPhoneField = new JTextField();
		empPhoneField.setToolTipText("000-0000-0000");
		empPhoneField.setColumns(10);
		empPhoneField.setBounds(439, 166, 439, 40);
		frame.getContentPane().add(empPhoneField);
		
		empAddressField = new JTextField();
		empAddressField.setToolTipText("00시 00구");
		empAddressField.setColumns(10);
		empAddressField.setBounds(439, 226, 439, 40);
		frame.getContentPane().add(empAddressField);
		
		empSalaryField = new JTextField();
		empSalaryField.setToolTipText("");
		empSalaryField.setColumns(10);
		empSalaryField.setBounds(439, 286, 439, 40);
		frame.getContentPane().add(empSalaryField);
		
		empFamilyCntField = new JTextField();
		empFamilyCntField.setToolTipText("");
		empFamilyCntField.setColumns(10);
		empFamilyCntField.setBounds(439, 346, 439, 40);
		frame.getContentPane().add(empFamilyCntField);
		

		empDepartmentField = new JTextField();
		empDepartmentField.setToolTipText("");
		empDepartmentField.setColumns(10);
		empDepartmentField.setBounds(439, 406, 439, 40);
		frame.getContentPane().add(empDepartmentField);
		
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"none", "management", "desk", "maintenance"}));
		comboBox.setBounds(439, 474, 286, 23);
		frame.getContentPane().add(comboBox);
		

		JButton SignInBtn = new JButton("Insert!");
		SignInBtn.addActionListener(e -> submit());
		SignInBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		SignInBtn.setBounds(568, 584, 139, 50);
		frame.getContentPane().add(SignInBtn);
		
		JButton CancelBtn = new JButton("Cancel");
		CancelBtn.addActionListener(e -> cancel());
		CancelBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		CancelBtn.setBounds(568, 644, 139, 23);
		frame.getContentPane().add(CancelBtn);
		

	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

    private void submit() {
    	//각 필드의 입력을 받아온다.
    	String empName = empNameField.getText().trim(); 
    	String empPhone = empPhoneField.getText().trim();
    	String empAddress = empAddressField.getText().trim();
    	String empSalary = empSalaryField.getText().trim();
    	String empFamily = empFamilyCntField.getText().trim();
    	String empDepartment = empDepartmentField.getText().trim();
    	String empWork = (String) comboBox.getSelectedItem();
    	
    	//하나라도 빈 문장이 있는 경우, 회원가입을 금지
    	if(empName.isEmpty() ||empPhone.isEmpty() || empAddress.isEmpty() || empSalary.isEmpty() || empDepartment.isEmpty() || empFamily.isEmpty() ) {
    		JOptionPane.showMessageDialog(frame, "필수 정보를 모두 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	//DB 연결 객체 생성
    	conn = DBUtil.getConnection();
    	
    	try {
    		//SQL문을 생성한다.
    		//이때, Auto_increment 설정이 적용되어 있기 때문에, id가 자동으로 부여된다.
    		String sql = "insert into Employee(empName, empphone, empaddress, empsalary, empfamilycnt, empdepartment, empwork)" +
    				"Values(?, ?, ?, ?, ?, ?, ?)";
    		//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다
    		PreparedStatement psm = conn.prepareStatement(sql);
    		//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
    		psm.setString(1, empName);
    		psm.setString(2, empPhone);
    		psm.setString(3, empAddress);
    		psm.setString(4, empSalary);
    		psm.setString(5, empFamily);
    		psm.setString(6, empDepartment);
    		psm.setString(7, empWork);

    		
    		//쿼리를 실행하고 결과를 받아온다.
    		int result = psm.executeUpdate();
    		//성공적으로 최소 1개의 행이 insert 된 경우
    		if(result > 0) {
    			JOptionPane.showMessageDialog(frame, "성공적으로 삽입이 완료되었습니다!");
    			frame.dispose();
    		}
    		else {
    			JOptionPane.showMessageDialog(frame, "삽입 실패", "오류", JOptionPane.ERROR_MESSAGE);
    		}
    	}catch(SQLException ex) {
    		ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "DB 오류: " + ex.getMessage());
    	}
    }
    
    //cancel 버튼을 누르면 그냥 창을 닫는다.
    private void cancel() {
    	frame.dispose();
    }

}
