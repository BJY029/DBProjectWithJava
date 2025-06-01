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

public class _PartsFrame {
	private Connection conn;
	private JFrame frame;
	
	private JTextField PartNameField;
	private JTextField PartPriceField;
	private JTextField PartCntField;
	private JTextField PartCompanyField;
	private JTextField PartReceiptDateField;


	/**
	 * Create the application.
	 */
	public _PartsFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.getContentPane().setLayout(null);
		JLabel Title = new JLabel("Parts Table 삽입");
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(12, 38, 1240, 35);
		frame.getContentPane().add(Title);
		
		JLabel PartNameLabel = new JLabel("부품 이름");
		PartNameLabel.setToolTipText("");
		PartNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		PartNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PartNameLabel.setBounds(240, 96, 187, 50);
		frame.getContentPane().add(PartNameLabel);
		
		JLabel PartPriceLabel = new JLabel("부품 단가");
		PartPriceLabel.setToolTipText("");
		PartPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PartPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		PartPriceLabel.setBounds(240, 156, 187, 50);
		frame.getContentPane().add(PartPriceLabel);
		
		JLabel PartCntLabel = new JLabel("부품 재고 수량");
		PartCntLabel.setToolTipText("");
		PartCntLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PartCntLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		PartCntLabel.setBounds(240, 216, 187, 50);
		frame.getContentPane().add(PartCntLabel);
		
		JLabel PartReceiptDateLabel = new JLabel("입고된 날짜");
		PartReceiptDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PartReceiptDateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		PartReceiptDateLabel.setBounds(240, 276, 187, 50);
		frame.getContentPane().add(PartReceiptDateLabel);
		
		JLabel PartCompanyLabel = new JLabel("공급 회사 이름");
		PartCompanyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PartCompanyLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		PartCompanyLabel.setBounds(240, 336, 187, 50);
		frame.getContentPane().add(PartCompanyLabel);
		
		PartNameField = new JTextField();
		PartNameField.setBackground(new Color(255, 255, 255));
		PartNameField.setToolTipText("");
		PartNameField.setBounds(439, 106, 439, 40);
		frame.getContentPane().add(PartNameField);
		PartNameField.setColumns(10);
		
		PartPriceField = new JTextField();
		PartPriceField.setBackground(new Color(255, 255, 255));
		PartPriceField.setToolTipText("");
		PartPriceField.setColumns(10);
		PartPriceField.setBounds(439, 166, 439, 40);
		frame.getContentPane().add(PartPriceField);
		
		PartCntField = new JTextField();
		PartCntField.setToolTipText("");
		PartCntField.setColumns(10);
		PartCntField.setBounds(439, 226, 439, 40);
		frame.getContentPane().add(PartCntField);
		
		PartReceiptDateField = new JTextField();
		PartReceiptDateField.setToolTipText("0000-00-00");
		PartReceiptDateField.setColumns(10);
		PartReceiptDateField.setBounds(439, 286, 439, 40);
		frame.getContentPane().add(PartReceiptDateField);
		
		PartCompanyField = new JTextField();
		PartCompanyField.setToolTipText("");
		PartCompanyField.setColumns(10);
		PartCompanyField.setBounds(439, 346, 439, 40);
		frame.getContentPane().add(PartCompanyField);
		
		
		JButton SignInBtn = new JButton("Insert!");
		SignInBtn.addActionListener(e -> submit());
		SignInBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		SignInBtn.setBounds(568, 532, 139, 50);
		frame.getContentPane().add(SignInBtn);
		
		JButton CancelBtn = new JButton("Cancel");
		CancelBtn.addActionListener(e -> cancel());
		CancelBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		CancelBtn.setBounds(568, 592, 139, 23);
		frame.getContentPane().add(CancelBtn);
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
    private void submit() {
    	//각 필드의 입력을 받아온다.
    	String partName = PartNameField.getText().trim(); 
    	String partprice = PartPriceField.getText().trim();
    	String partcnt = PartCntField.getText().trim();
    	String partdate = PartReceiptDateField.getText().trim();
    	String partcompany = PartCompanyField.getText().trim();
    	
    	//하나라도 빈 문장이 있는 경우, 회원가입을 금지
    	if(partName.isEmpty() ||partprice.isEmpty() || partcnt.isEmpty() || partdate.isEmpty() || partcompany.isEmpty() ) {
    		JOptionPane.showMessageDialog(frame, "필수 정보를 모두 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	//DB 연결 객체 생성
    	conn = DBUtil.getConnection();
    	
    	try {
    		//SQL문을 생성한다.
    		//이때, Auto_increment 설정이 적용되어 있기 때문에, id가 자동으로 부여된다.
    		String sql = "insert into Parts(partname, partprice, partcnt, partreceiptdate, partcompany)" +
    				"Values(?, ?, ?, ?, ?)";
    		//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다
    		PreparedStatement psm = conn.prepareStatement(sql);
    		//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
    		psm.setString(1, partName);
    		psm.setString(2, partprice);
    		psm.setString(3, partcnt);
    		psm.setString(4, partdate);
    		psm.setString(5, partcompany);
    		
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
