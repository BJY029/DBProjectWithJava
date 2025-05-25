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

public class _CustomerFrame {
	private Connection conn;
	private JFrame frame;
	
	private JTextField LicenseNumField;
	private JTextField custLogIDField;
	private JTextField custLogPassField;
	private JTextField custAddressField;
	private JTextField custNameField;
	private JTextField custPhoneField;
	private JTextField custEmailField;
	private JTextField custHistoryDateField;
	private JTextField custHistoryCarField;



	/**
	 * Create the application.
	 */
	public _CustomerFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 780);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//frame.getContentPane().setLayout(null);
		
		JLabel Title = new JLabel("Customer Table 삽입");
		Title.setBounds(12, 38, 1240, 35);
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Title);
		
		JLabel LicneseNumLabel = new JLabel("운전 면허증 번호");
		LicneseNumLabel.setBounds(240, 96, 187, 50);
		LicneseNumLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		LicneseNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(LicneseNumLabel);
		
		JLabel custLogIDLabel = new JLabel("고객 아이디");
		custLogIDLabel.setBounds(240, 156, 187, 50);
		custLogIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custLogIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(custLogIDLabel);
		
		JLabel custLogPassLabel = new JLabel("고객 비밀번호");
		custLogPassLabel.setBounds(240, 216, 187, 50);
		custLogPassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custLogPassLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(custLogPassLabel);
		
		JLabel custNameLabel = new JLabel("고객명");
		custNameLabel.setBounds(240, 276, 187, 50);
		custNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(custNameLabel);
		
		JLabel custAddressLabel = new JLabel("고객 주소");
		custAddressLabel.setBounds(240, 336, 187, 50);
		custAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custAddressLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(custAddressLabel);
		
		JLabel custPhoneLabel = new JLabel("고객 전화번호");
		custPhoneLabel.setBounds(240, 396, 187, 50);
		custPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custPhoneLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(custPhoneLabel);
		
		JLabel custEmailLabel = new JLabel("고객 이메일");
		custEmailLabel.setBounds(240, 456, 187, 50);
		custEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custEmailLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(custEmailLabel);
		
		JLabel custHistoryDateLabel = new JLabel("기존 캠핑카 이용 날짜");
		custHistoryDateLabel.setBounds(240, 516, 187, 50);
		custHistoryDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custHistoryDateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(custHistoryDateLabel);
		
		JLabel custHistoryCarLabel = new JLabel("이전에 사용한 캠핑카");
		custHistoryCarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custHistoryCarLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		custHistoryCarLabel.setBounds(240, 576, 187, 50);
		frame.getContentPane().add(custHistoryCarLabel);
		
		
		LicenseNumField = new JTextField();
		LicenseNumField.setBounds(439, 106, 439, 40);
		LicenseNumField.setBackground(new Color(255, 128, 128));
		LicenseNumField.setToolTipText("00-000000");
		frame.getContentPane().add(LicenseNumField);
		LicenseNumField.setColumns(10);
		
		custLogIDField = new JTextField();
		custLogIDField.setBounds(439, 166, 439, 40);
		custLogIDField.setToolTipText("");
		custLogIDField.setColumns(10);
		frame.getContentPane().add(custLogIDField);
		
		custLogPassField = new JTextField();
		custLogPassField.setBounds(439, 226, 439, 40);
		custLogPassField.setToolTipText("");
		custLogPassField.setColumns(10);
		frame.getContentPane().add(custLogPassField);
		
		custNameField = new JTextField();
		custNameField.setBounds(439, 286, 439, 40);
		custNameField.setToolTipText("");
		custNameField.setColumns(10);
		frame.getContentPane().add(custNameField);
		
		custAddressField = new JTextField();
		custAddressField.setBounds(439, 346, 439, 40);
		custAddressField.setToolTipText("");
		custAddressField.setColumns(10);
		frame.getContentPane().add(custAddressField);
		

		custPhoneField = new JTextField();
		custPhoneField.setBounds(439, 406, 439, 40);
		custPhoneField.setToolTipText("000-0000-0000");
		custPhoneField.setColumns(10);
		frame.getContentPane().add(custPhoneField);
		
		custEmailField = new JTextField();
		custEmailField.setBounds(439, 466, 439, 40);
		custEmailField.setToolTipText("");
		custEmailField.setColumns(10);
		frame.getContentPane().add(custEmailField);
		
		custHistoryDateField = new JTextField();
		custHistoryDateField.setBackground(new Color(230, 230, 230));
		custHistoryDateField.setBounds(439, 526, 439, 40);
		custHistoryDateField.setToolTipText("");
		custHistoryDateField.setColumns(10);
		frame.getContentPane().add(custHistoryDateField);
		
		
		custHistoryCarField = new JTextField();
		custHistoryCarField.setBackground(new Color(230, 230, 230));
		custHistoryCarField.setToolTipText("");
		custHistoryCarField.setColumns(10);
		custHistoryCarField.setBounds(439, 577, 439, 40);
		frame.getContentPane().add(custHistoryCarField);
		
		JButton SignInBtn = new JButton("Insert!");
		SignInBtn.setBounds(588, 644, 139, 50);
		SignInBtn.addActionListener(e -> submit());
		SignInBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		frame.getContentPane().add(SignInBtn);
		
		JButton CancelBtn = new JButton("Cancel");
		CancelBtn.addActionListener(e -> cancel());
		CancelBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		CancelBtn.setBounds(588, 697, 139, 23);
		frame.getContentPane().add(CancelBtn);
		
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

    private void submit() {
    	//각 필드의 입력을 받아온다.
    	String LicneseNum = LicenseNumField.getText().trim(); 
    	String custlogid = custLogIDField.getText().trim();
    	String custlogpass = custLogPassField.getText().trim();
    	String custname = custNameField.getText().trim();
    	String custaddress = custAddressField.getText().trim();
    	String custphone = custPhoneField.getText().trim();
    	String custemail = custEmailField.getText().trim();
    	String custhistorydatefield = custHistoryDateField.getText().trim();
    	String custhistorycarfield = custHistoryCarField.getText().trim();
    	
    	//하나라도 빈 문장이 있는 경우, 회원가입을 금지
    	if(LicneseNum.isEmpty() ||custlogid.isEmpty() || custlogpass.isEmpty() || custname.isEmpty() || custphone.isEmpty() || custaddress.isEmpty() || custemail.isEmpty()) {
    		JOptionPane.showMessageDialog(frame, "필수 정보를 모두 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	//DB 연결 객체 생성
    	conn = DBUtil.getConnection();
    	
    	try {
    		//SQL문을 생성한다.
    		//이때, Auto_increment 설정이 적용되어 있기 때문에, id가 자동으로 부여된다.
    		String sql = "insert into Customer(licensenum, custlogid, custlogpassword, custname, custaddress, custphone, custemail, custhistorydate, custhistorycar)" +
    				"Values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    		//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다
    		PreparedStatement psm = conn.prepareStatement(sql);
    		//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
    		psm.setString(1, LicneseNum);
    		psm.setString(2, custlogid);
    		psm.setString(3, custlogpass);
    		psm.setString(4, custname);
    		psm.setString(5, custaddress);
    		psm.setString(6, custphone);
    		psm.setString(7, custemail);
    		if(custhistorydatefield.isEmpty()) custhistorydatefield = null;
    		psm.setString(8, custhistorydatefield);
    		if(custhistorycarfield.isEmpty()) custhistorycarfield = null;
    		psm.setString(9, custhistorycarfield);
    		
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
