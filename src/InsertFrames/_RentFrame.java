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

public class _RentFrame {
	private Connection conn;
	private JFrame frame;

	private JTextField CompanyIDField;
	private JTextField CarIDField;
	private JTextField LicenseNumField;
	private JTextField RentPeriodField;
	private JTextField RentStartField;
	private JTextField RentPriceField;
	private JTextField PaymentDateField;
	private JTextField etcDetailsField;
	private JTextField etcPriceField;
	/**
	 * Create the application.
	 */
	public _RentFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 780);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Title = new JLabel("Rent Table 삽입");
		Title.setBounds(12, 38, 1240, 35);
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Title);
		
		JLabel CompanyIDLabel = new JLabel("캠핑카 대여 회사 ID");
		CompanyIDLabel.setBounds(240, 96, 187, 50);
		CompanyIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		CompanyIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(CompanyIDLabel);
		
		JLabel CarIDLabel = new JLabel("캠핑카 등록 ID");
		CarIDLabel.setBounds(240, 156, 187, 50);
		CarIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CarIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(CarIDLabel);
		
		JLabel LicenseNumLabel = new JLabel("운전면허증 번호");
		LicenseNumLabel.setBounds(240, 216, 187, 50);
		LicenseNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LicenseNumLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(LicenseNumLabel);
		
		JLabel RentStartLabel = new JLabel("캠핑카 대여 시작일");
		RentStartLabel.setBounds(240, 276, 187, 50);
		RentStartLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RentStartLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(RentStartLabel);
		
		JLabel RentPeriodLabel = new JLabel("캠핑카 대여 기간(일)");
		RentPeriodLabel.setBounds(240, 336, 187, 50);
		RentPeriodLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RentPeriodLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(RentPeriodLabel);
		
		JLabel RentPriceLabel = new JLabel("청구요금");
		RentPriceLabel.setBounds(240, 396, 187, 50);
		RentPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RentPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(RentPriceLabel);
		
		JLabel PaymentDateLabel = new JLabel("납입 기한");
		PaymentDateLabel.setBounds(240, 456, 187, 50);
		PaymentDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PaymentDateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(PaymentDateLabel);
		
		JLabel etcDetailsLabel = new JLabel("기타 청구 내역");
		etcDetailsLabel.setBounds(240, 516, 187, 50);
		etcDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		etcDetailsLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(etcDetailsLabel);
		
		JLabel etcPriceLabel = new JLabel("기타 청구 요금");
		etcPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		etcPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		etcPriceLabel.setBounds(240, 576, 187, 50);
		frame.getContentPane().add(etcPriceLabel);
		
		
		CompanyIDField = new JTextField();
		CompanyIDField.setBounds(439, 106, 439, 40);
		CompanyIDField.setBackground(new Color(255, 128, 128));
		CompanyIDField.setToolTipText("유효한 값이어야 합니다!!");
		frame.getContentPane().add(CompanyIDField);
		CompanyIDField.setColumns(10);
		
		CarIDField = new JTextField();
		CarIDField.setBackground(new Color(255, 128, 128));
		CarIDField.setBounds(439, 166, 439, 40);
		CarIDField.setToolTipText("유효한 값이어야 합니다!!");
		CarIDField.setColumns(10);
		frame.getContentPane().add(CarIDField);
		
		LicenseNumField = new JTextField();
		LicenseNumField.setBackground(new Color(255, 128, 128));
		LicenseNumField.setBounds(439, 226, 439, 40);
		LicenseNumField.setToolTipText("유효한 값이어야 합니다!!");
		LicenseNumField.setColumns(10);
		frame.getContentPane().add(LicenseNumField);
		
		RentStartField = new JTextField();
		RentStartField.setBounds(439, 286, 439, 40);
		RentStartField.setToolTipText("0000-00-00");
		RentStartField.setColumns(10);
		frame.getContentPane().add(RentStartField);
		
		RentPeriodField = new JTextField();
		RentPeriodField.setBounds(439, 346, 439, 40);
		RentPeriodField.setToolTipText("");
		RentPeriodField.setColumns(10);
		frame.getContentPane().add(RentPeriodField);
		

		RentPriceField = new JTextField();
		RentPriceField.setBounds(439, 406, 439, 40);
		RentPriceField.setToolTipText("");
		RentPriceField.setColumns(10);
		frame.getContentPane().add(RentPriceField);
		
		PaymentDateField = new JTextField();
		PaymentDateField.setBounds(439, 466, 439, 40);
		PaymentDateField.setToolTipText("0000-00-00");
		PaymentDateField.setColumns(10);
		frame.getContentPane().add(PaymentDateField);
		
		etcDetailsField = new JTextField();
		etcDetailsField.setBackground(new Color(230, 230, 230));
		etcDetailsField.setBounds(439, 526, 439, 40);
		etcDetailsField.setToolTipText("");
		etcDetailsField.setColumns(10);
		frame.getContentPane().add(etcDetailsField);
		
		
		etcPriceField = new JTextField();
		etcPriceField.setBackground(new Color(230, 230, 230));
		etcPriceField.setToolTipText("");
		etcPriceField.setColumns(10);
		etcPriceField.setBounds(439, 577, 439, 40);
		frame.getContentPane().add(etcPriceField);
		
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
    	String companyid = CompanyIDField.getText().trim(); 
    	String carid = CarIDField.getText().trim();
    	String licensenum = LicenseNumField.getText().trim();
    	String rentstart = RentStartField.getText().trim();
    	String rentperiod = RentPeriodField.getText().trim();
    	String rentprice = RentPriceField.getText().trim();
    	String paymentdate = PaymentDateField.getText().trim();
    	String etcdetails = etcDetailsField.getText().trim();
    	String etcprice = etcPriceField.getText().trim();
    	
    	//하나라도 빈 문장이 있는 경우, 회원가입을 금지
    	if(companyid.isEmpty() ||carid.isEmpty() || licensenum.isEmpty() || rentstart.isEmpty() || rentprice.isEmpty() || rentperiod.isEmpty() || paymentdate.isEmpty()) {
    		JOptionPane.showMessageDialog(frame, "필수 정보를 모두 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	//DB 연결 객체 생성
    	conn = DBUtil.getConnection();
    	
    	try {
    		//SQL문을 생성한다.
    		//이때, Auto_increment 설정이 적용되어 있기 때문에, id가 자동으로 부여된다.
    		String sql = "insert into Rent(companyid, carid, licensenum, rentstart, rentperiod, rentprice, paymentdate, etcdetails, etcprice)" +
    				"Values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    		//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다
    		PreparedStatement psm = conn.prepareStatement(sql);
    		//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
    		psm.setString(1, companyid);
    		psm.setString(2, carid);
    		psm.setString(3, licensenum);
    		psm.setString(4, rentstart);
    		psm.setString(5, rentperiod);
    		psm.setString(6, rentprice);
    		psm.setString(7, paymentdate);
    		if(etcdetails.isEmpty()) etcdetails = null;
    		psm.setString(8, etcdetails);
    		if(etcprice.isEmpty()) etcprice = null;
    		psm.setString(9, etcprice);
    		
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
