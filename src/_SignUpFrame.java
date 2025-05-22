import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class _SignUpFrame {

	private Connection conn;
	private JFrame frame;
	private JTextField NameField;
	private JTextField IDField;
	private JTextField PWField;
	private JTextField AddressField;
	private JTextField PhoneField;
	private JTextField EMailField;
	private JTextField LicenseField;


	/**
	 * Create the application.
	 */
	public _SignUpFrame() {
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
		
		
		JLabel Title = new JLabel("회원 정보를 입력하세요");
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(12, 38, 1240, 35);
		frame.getContentPane().add(Title);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(305, 96, 122, 50);
		frame.getContentPane().add(nameLabel);
		
		JLabel IDLabel = new JLabel("아이디");
		IDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		IDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		IDLabel.setBounds(305, 156, 122, 50);
		frame.getContentPane().add(IDLabel);
		
		JLabel PWLabel = new JLabel("비밀번호");
		PWLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PWLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		PWLabel.setBounds(305, 216, 122, 50);
		frame.getContentPane().add(PWLabel);
		
		JLabel LicenceLabel = new JLabel("면허증 번호");
		LicenceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LicenceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		LicenceLabel.setBounds(305, 276, 122, 50);
		frame.getContentPane().add(LicenceLabel);
		
		JLabel AddressLabel = new JLabel("주소");
		AddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AddressLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		AddressLabel.setBounds(305, 336, 122, 50);
		frame.getContentPane().add(AddressLabel);
		
		JLabel PhoneLabel = new JLabel("전화번호");
		PhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PhoneLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		PhoneLabel.setBounds(305, 396, 122, 50);
		frame.getContentPane().add(PhoneLabel);
		
		JLabel MailLabel = new JLabel("E-MAIL");
		MailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MailLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		MailLabel.setBounds(305, 456, 122, 50);
		frame.getContentPane().add(MailLabel);
		
		NameField = new JTextField();
		NameField.setBounds(439, 106, 439, 40);
		frame.getContentPane().add(NameField);
		NameField.setColumns(10);
		
		IDField = new JTextField();
		IDField.setColumns(10);
		IDField.setBounds(439, 166, 439, 40);
		frame.getContentPane().add(IDField);
		
		PWField = new JTextField();
		PWField.setColumns(10);
		PWField.setBounds(439, 226, 439, 40);
		frame.getContentPane().add(PWField);
		
		LicenseField = new JTextField();
		LicenseField.setToolTipText("##-######");
		LicenseField.setColumns(10);
		LicenseField.setBounds(439, 286, 439, 40);
		frame.getContentPane().add(LicenseField);
		
		AddressField = new JTextField();
		AddressField.setToolTipText("##시 ##구");
		AddressField.setColumns(10);
		AddressField.setBounds(439, 346, 439, 40);
		frame.getContentPane().add(AddressField);
		
		PhoneField = new JTextField();
		PhoneField.setToolTipText("###-####-####");
		PhoneField.setColumns(10);
		PhoneField.setBounds(439, 406, 439, 40);
		frame.getContentPane().add(PhoneField);
		
		EMailField = new JTextField();
		EMailField.setColumns(10);
		EMailField.setBounds(439, 466, 439, 40);
		frame.getContentPane().add(EMailField);
		

		
		JButton SignInBtn = new JButton("Sign In!");
		SignInBtn.addActionListener(e -> submit());
		SignInBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		SignInBtn.setBounds(568, 532, 139, 50);
		frame.getContentPane().add(SignInBtn);
		
		JButton CancelBtn = new JButton("Cancel");
		CancelBtn.addActionListener(e -> cancel());
		CancelBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		CancelBtn.setBounds(568, 598, 139, 23);
		frame.getContentPane().add(CancelBtn);

	}

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
    
    //회원 가입을 위한 함수
    private void submit() {
    	//각 필드의 입력을 받아온다.
    	String name = NameField.getText().trim(); 
    	String id = IDField.getText().trim();
    	String pw = PWField.getText().trim();
    	String license = LicenseField.getText().trim();
    	String address = AddressField.getText().trim();
    	String phone = PhoneField.getText().trim();
    	String email = EMailField.getText().trim();
    	
    	//하나라도 빈 문장이 있는 경우, 회원가입을 금지
    	if(id.isEmpty() || pw.isEmpty() || name.isEmpty() || license.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
    		JOptionPane.showMessageDialog(frame, "필수 정보를 모두 입력하세요.", "회원가입 경고", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	//DB 연결 객체 생성
    	conn = DBUtil.getUserConnection();
    	
    	try {
    		//SQL문을 생성한다.
    		//이때, custid는 Auto_increment 설정이 적용되어 있기 때문에, 자동으로 부여된다.
    		String sql = "insert into customer(custname, custlogid, custlogpassword, licensenum, custaddress, custphone, custemail, custhistorydate)" +
    				"Values(?, ?, ?, ?, ?, ?, ?, ?)";
    		//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다
    		PreparedStatement psm = conn.prepareStatement(sql);
    		//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
    		psm.setString(1, name);
    		psm.setString(2, id);
    		psm.setString(3, pw);
    		psm.setString(4, license);
    		psm.setString(5, address);
    		psm.setString(6, phone);
    		psm.setString(7, email);
    		psm.setDate(8, Date.valueOf(LocalDate.now()));
    		
    		//쿼리를 실행하고 결과를 받아온다.
    		int result = psm.executeUpdate();
    		//성공적으로 최소 1개의 행이 insert 된 경우
    		if(result > 0) {
    			JOptionPane.showMessageDialog(frame, "성공적으로 가입이 완료되었습니다!");
    			frame.dispose();
    		}
    		else {
    			JOptionPane.showMessageDialog(frame, "가입 실패", "가입 오류", JOptionPane.ERROR_MESSAGE);
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
