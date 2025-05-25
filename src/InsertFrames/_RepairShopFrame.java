package InsertFrames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class _RepairShopFrame {
	private Connection conn;
	private JFrame frame;

	private JTextField RPNameField;
	private JTextField RPAdressField;
	private JTextField RPPhoneField;
	private JTextField MngEmailField;
	private JTextField MngNameField;
	/**
	 * Create the application.
	 */
	public _RepairShopFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.getContentPane().setLayout(null);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel Title = new JLabel("RepairShop Table 삽입");
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(12, 38, 1240, 35);
		frame.getContentPane().add(Title);
		
		JLabel RPNameLabel = new JLabel("정비소 이름");
		RPNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		RPNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RPNameLabel.setBounds(240, 96, 187, 50);
		frame.getContentPane().add(RPNameLabel);
		
		JLabel RPAdressLabel = new JLabel("정비소 주소");
		RPAdressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RPAdressLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		RPAdressLabel.setBounds(240, 156, 187, 50);
		frame.getContentPane().add(RPAdressLabel);
		
		JLabel RPPhoneLabel = new JLabel("정비소 전화번호");
		RPPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RPPhoneLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		RPPhoneLabel.setBounds(240, 216, 187, 50);
		frame.getContentPane().add(RPPhoneLabel);
		
		JLabel RPMngNameLabel = new JLabel("담당자 이름");
		RPMngNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RPMngNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		RPMngNameLabel.setBounds(240, 276, 187, 50);
		frame.getContentPane().add(RPMngNameLabel);
		
		JLabel RpMngEmailLabel = new JLabel("담당자 이메일");
		RpMngEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RpMngEmailLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		RpMngEmailLabel.setBounds(240, 336, 187, 50);
		frame.getContentPane().add(RpMngEmailLabel);
		
		
		RPNameField = new JTextField();
		RPNameField.setBackground(new Color(255, 255, 255));
		RPNameField.setToolTipText("");
		RPNameField.setBounds(439, 106, 439, 40);
		frame.getContentPane().add(RPNameField);
		RPNameField.setColumns(10);
		
		RPAdressField = new JTextField();
		RPAdressField.setToolTipText("00시 00구");
		RPAdressField.setColumns(10);
		RPAdressField.setBounds(439, 166, 439, 40);
		frame.getContentPane().add(RPAdressField);
		
		RPPhoneField = new JTextField();
		RPPhoneField.setToolTipText("");
		RPPhoneField.setColumns(10);
		RPPhoneField.setBounds(439, 226, 439, 40);
		frame.getContentPane().add(RPPhoneField);
		
		MngNameField = new JTextField();
		MngNameField.setToolTipText("");
		MngNameField.setColumns(10);
		MngNameField.setBounds(439, 286, 439, 40);
		frame.getContentPane().add(MngNameField);
		
		MngEmailField = new JTextField();
		MngEmailField.setToolTipText("");
		MngEmailField.setColumns(10);
		MngEmailField.setBounds(439, 346, 439, 40);
		frame.getContentPane().add(MngEmailField);
		
		
		

		JButton SignInBtn = new JButton("Insert!");
		SignInBtn.addActionListener(e -> submit());
		SignInBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		SignInBtn.setBounds(568, 465, 139, 50);
		frame.getContentPane().add(SignInBtn);
		
		JButton CancelBtn = new JButton("Cancel");
		CancelBtn.addActionListener(e -> cancel());
		CancelBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		CancelBtn.setBounds(568, 536, 139, 23);
		frame.getContentPane().add(CancelBtn);
		

	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

    private void submit() {
    	//각 필드의 입력을 받아온다.
    	String rpname = RPNameField.getText().trim(); 
    	String rpaddress = RPAdressField.getText().trim();
    	String rpphone = RPPhoneField.getText().trim();
    	String mngname = MngNameField.getText().trim();
    	String mngemail = MngEmailField.getText().trim();
    	
    	//하나라도 빈 문장이 있는 경우, 회원가입을 금지
    	if(rpname.isEmpty() ||rpaddress.isEmpty() || rpphone.isEmpty() || mngname.isEmpty() ||  mngemail.isEmpty() ) {
    		JOptionPane.showMessageDialog(frame, "필수 정보를 모두 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	//DB 연결 객체 생성
    	conn = DBUtil.getConnection();
    	
    	try {
    		//SQL문을 생성한다.
    		//이때, Auto_increment 설정이 적용되어 있기 때문에, id가 자동으로 부여된다.
    		String sql = "insert into RepairShop(rpname, rpaddress, rpphone, rpmngname, rpmngemail)" +
    				"Values(?, ?, ?, ?, ?)";
    		//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다
    		PreparedStatement psm = conn.prepareStatement(sql);
    		//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
    		psm.setString(1, rpname);
    		psm.setString(2, rpaddress);
    		psm.setString(3, rpphone);
    		psm.setString(4, mngname);
    		psm.setString(5, mngemail);


    		
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
