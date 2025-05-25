package InsertFrames;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class _CompanyFrame {

	private JFrame frame;
	private Connection conn;
	
	private JTextField NameField;
	private JTextField AddressField;
	private JTextField PhoneField;
	private JTextField EmailField;
	private JTextField MngNameField;

	/**
	 * Create the application.
	 */
	public _CompanyFrame() {
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
		
		JLabel Title = new JLabel("Company Table 삽입");
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(12, 38, 1240, 35);
		frame.getContentPane().add(Title);
		
		JLabel nameLabel = new JLabel("회사이름");
		nameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(305, 96, 122, 50);
		frame.getContentPane().add(nameLabel);
		
		JLabel AddressLabel = new JLabel("회사주소");
		AddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AddressLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		AddressLabel.setBounds(305, 156, 122, 50);
		frame.getContentPane().add(AddressLabel);
		
		JLabel PhoneLabel = new JLabel("회사 전화번호");
		PhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PhoneLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		PhoneLabel.setBounds(305, 216, 122, 50);
		frame.getContentPane().add(PhoneLabel);
		
		JLabel MngNameLabel = new JLabel("담당자 이름");
		MngNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MngNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		MngNameLabel.setBounds(305, 276, 122, 50);
		frame.getContentPane().add(MngNameLabel);
		
		JLabel EmailLabel = new JLabel("담당자 이메일");
		EmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EmailLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		EmailLabel.setBounds(305, 336, 122, 50);
		frame.getContentPane().add(EmailLabel);
		
		NameField = new JTextField();
		NameField.setBounds(439, 106, 439, 40);
		frame.getContentPane().add(NameField);
		NameField.setColumns(10);
		
		AddressField = new JTextField();
		AddressField.setToolTipText("##시 ##구");
		AddressField.setColumns(10);
		AddressField.setBounds(439, 166, 439, 40);
		frame.getContentPane().add(AddressField);
		
		PhoneField = new JTextField();
		PhoneField.setToolTipText("###-####-####");
		PhoneField.setColumns(10);
		PhoneField.setBounds(439, 226, 439, 40);
		frame.getContentPane().add(PhoneField);
		
		MngNameField = new JTextField();
		MngNameField.setToolTipText("##-######");
		MngNameField.setColumns(10);
		MngNameField.setBounds(439, 286, 439, 40);
		frame.getContentPane().add(MngNameField);
		
		EmailField = new JTextField();
		EmailField.setToolTipText("");
		EmailField.setColumns(10);
		EmailField.setBounds(439, 346, 439, 40);
		frame.getContentPane().add(EmailField);
		

		
		JButton SignInBtn = new JButton("Insert!");
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
	
    //삽입을 위한 함수
    private void submit() {
    	//각 필드의 입력을 받아온다.
    	String name = NameField.getText().trim(); 
    	String address = AddressField.getText().trim();
    	String phone = PhoneField.getText().trim();
    	String mngname = MngNameField.getText().trim();
    	String email = EmailField.getText().trim();
    	
    	//하나라도 빈 문장이 있는 경우, 회원가입을 금지
    	if( name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty() || mngname.isEmpty()) {
    		JOptionPane.showMessageDialog(frame, "필수 정보를 모두 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	//DB 연결 객체 생성
    	conn = DBUtil.getConnection();
    	
    	try {
    		//SQL문을 생성한다.
    		//이때, Auto_increment 설정이 적용되어 있기 때문에, id가 자동으로 부여된다.
    		String sql = "insert into company(companyname, address, officenum, mngname, mngemail)" +
    				"Values(?, ?, ?, ?, ?)";
    		//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다
    		PreparedStatement psm = conn.prepareStatement(sql);
    		//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
    		psm.setString(1, name);
    		psm.setString(2, address);
    		psm.setString(3, phone);
    		psm.setString(4, mngname);
    		psm.setString(5, email);
    		
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
