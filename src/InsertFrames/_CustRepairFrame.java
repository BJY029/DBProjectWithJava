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

public class _CustRepairFrame {
	private Connection conn;
	private JFrame frame;

	private JTextField CarIDField;
	private JTextField RPIDField;
	private JTextField LicenseNumField;
	private JTextField RepairDetailsField;
	private JTextField CompanyIDField;
	private JTextField RepairDateField;
	private JTextField RepairPriceField;
	private JTextField PaymentDateField;
	private JTextField etcDetailsField;

	/**
	 * Create the application.
	 */
	public _CustRepairFrame() {
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
		
		JLabel Title = new JLabel("CustRepair Table 삽입");
		Title.setBounds(12, 38, 1240, 35);
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Title);
		
		JLabel CarIDLabel = new JLabel("캠핑카 등록 ID");
		CarIDLabel.setBounds(240, 96, 187, 50);
		CarIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		CarIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(CarIDLabel);
		
		JLabel RPIDLabel = new JLabel("캠핑카 정비소 ID");
		RPIDLabel.setBounds(240, 156, 187, 50);
		RPIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RPIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(RPIDLabel);
		
		JLabel LicenseNumLabel = new JLabel("운전면허증 번호");
		LicenseNumLabel.setBounds(240, 216, 187, 50);
		LicenseNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LicenseNumLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(LicenseNumLabel);
		
		JLabel CompanyIDLabel = new JLabel("캠핑카 대여 회사 ID");
		CompanyIDLabel.setBounds(240, 276, 187, 50);
		CompanyIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CompanyIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(CompanyIDLabel);
		
		JLabel repairDetailsLabel = new JLabel("정비 내역");
		repairDetailsLabel.setBounds(240, 336, 187, 50);
		repairDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		repairDetailsLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(repairDetailsLabel);
		
		JLabel RepairDateLabel = new JLabel("수리 날짜");
		RepairDateLabel.setBounds(240, 396, 187, 50);
		RepairDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RepairDateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(RepairDateLabel);
		
		JLabel RepairPriceLabel = new JLabel("수리 비용");
		RepairPriceLabel.setBounds(240, 456, 187, 50);
		RepairPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RepairPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(RepairPriceLabel);
		
		JLabel PaymentDateLabel = new JLabel("납입 기한");
		PaymentDateLabel.setBounds(240, 516, 187, 50);
		PaymentDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PaymentDateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		frame.getContentPane().add(PaymentDateLabel);
		
		JLabel etcDetailsLabel = new JLabel("기타 정비 내역");
		etcDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		etcDetailsLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		etcDetailsLabel.setBounds(240, 576, 187, 50);
		frame.getContentPane().add(etcDetailsLabel);
		
		
		CarIDField = new JTextField();
		CarIDField.setBounds(439, 106, 439, 40);
		CarIDField.setBackground(new Color(255, 128, 128));
		CarIDField.setToolTipText("유효한 값이어야 합니다!!");
		frame.getContentPane().add(CarIDField);
		CarIDField.setColumns(10);
		
		RPIDField = new JTextField();
		RPIDField.setBackground(new Color(255, 128, 128));
		RPIDField.setBounds(439, 166, 439, 40);
		RPIDField.setToolTipText("유효한 값이어야 합니다!!");
		RPIDField.setColumns(10);
		frame.getContentPane().add(RPIDField);
		
		LicenseNumField = new JTextField();
		LicenseNumField.setBackground(new Color(255, 128, 128));
		LicenseNumField.setBounds(439, 226, 439, 40);
		LicenseNumField.setToolTipText("유효한 값이어야 합니다!!");
		LicenseNumField.setColumns(10);
		frame.getContentPane().add(LicenseNumField);
		
		CompanyIDField = new JTextField();
		CompanyIDField.setBackground(new Color(255, 128, 128));
		CompanyIDField.setBounds(439, 286, 439, 40);
		CompanyIDField.setToolTipText("유효한 값이어야 합니다!!");
		CompanyIDField.setColumns(10);
		frame.getContentPane().add(CompanyIDField);
		
		RepairDetailsField = new JTextField();
		RepairDetailsField.setBounds(439, 346, 439, 40);
		RepairDetailsField.setToolTipText("");
		RepairDetailsField.setColumns(10);
		frame.getContentPane().add(RepairDetailsField);
		

		RepairDateField = new JTextField();
		RepairDateField.setBounds(439, 406, 439, 40);
		RepairDateField.setToolTipText("0000-00-00");
		RepairDateField.setColumns(10);
		frame.getContentPane().add(RepairDateField);
		
		RepairPriceField = new JTextField();
		RepairPriceField.setBounds(439, 466, 439, 40);
		RepairPriceField.setToolTipText("0000-00-00");
		RepairPriceField.setColumns(10);
		frame.getContentPane().add(RepairPriceField);
		
		PaymentDateField = new JTextField();
		PaymentDateField.setBackground(new Color(255, 255, 255));
		PaymentDateField.setBounds(439, 526, 439, 40);
		PaymentDateField.setToolTipText("0000-00-00");
		PaymentDateField.setColumns(10);
		frame.getContentPane().add(PaymentDateField);
		
		
		etcDetailsField = new JTextField();
		etcDetailsField.setBackground(new Color(230, 230, 230));
		etcDetailsField.setToolTipText("");
		etcDetailsField.setColumns(10);
		etcDetailsField.setBounds(439, 577, 439, 40);
		frame.getContentPane().add(etcDetailsField);
		
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
    	String carid = CarIDField.getText().trim(); 
    	String rpid = RPIDField.getText().trim();
    	String licensenum = LicenseNumField.getText().trim();
    	String companyid = CompanyIDField.getText().trim();
    	String repairdetails = RepairDetailsField.getText().trim();
    	String repairdate = RepairDateField.getText().trim();
    	String repairprice = RepairPriceField.getText().trim();
    	String paymentdate = PaymentDateField.getText().trim();
    	String etcdetails = etcDetailsField.getText().trim();
    	
    	//하나라도 빈 문장이 있는 경우, 회원가입을 금지
    	if(carid.isEmpty() ||rpid.isEmpty() || licensenum.isEmpty() || companyid.isEmpty() || repairdate.isEmpty() || repairdetails.isEmpty() || repairprice.isEmpty() || paymentdate.isEmpty()) {
    		JOptionPane.showMessageDialog(frame, "필수 정보를 모두 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	//DB 연결 객체 생성
    	conn = DBUtil.getConnection();
    	
    	try {
    		//SQL문을 생성한다.
    		//이때, Auto_increment 설정이 적용되어 있기 때문에, id가 자동으로 부여된다.
    		String sql = "insert into CustRepair(carid, rpid, companyid, licensenum, repairdetails, repairdate, repairprice, paymentdate, etcdetails)" +
    				"Values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    		//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다
    		PreparedStatement psm = conn.prepareStatement(sql);
    		//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
    		psm.setString(1, carid);
    		psm.setString(2, rpid);
    		psm.setString(3, companyid);
    		psm.setString(4, licensenum);
    		psm.setString(5, repairdetails);
    		psm.setString(6, repairdate);
    		psm.setString(7, repairprice);
    		psm.setString(8, paymentdate);
    		if(etcdetails.isEmpty()) etcdetails = null;
    		psm.setString(9, etcdetails);
    		
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
