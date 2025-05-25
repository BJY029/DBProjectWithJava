package InsertFrames;

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
import java.awt.Color;

public class _CarsFrame {

	private Connection conn;
	private JFrame frame;
	
	private JTextField CompanyIDField;
	private JTextField nameField;
	private JTextField CarNumField;
	private JTextField CarImgURLField;
	private JTextField CarMaxPsgField;
	private JTextField CarDescField;
	private JTextField CarRentPriceField;
	private JTextField CarRegDateField;


	/**
	 * Create the application.
	 */
	public _CarsFrame() {
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
		JLabel Title = new JLabel("Cars Table 삽입");
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(12, 38, 1240, 35);
		frame.getContentPane().add(Title);
		
		JLabel nameIdLabel = new JLabel("캠핑카 대여 회사 ID");
		nameIdLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		nameIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameIdLabel.setBounds(240, 96, 187, 50);
		frame.getContentPane().add(nameIdLabel);
		
		JLabel CarNameLabel = new JLabel("캠핑카 이름");
		CarNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CarNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		CarNameLabel.setBounds(240, 156, 187, 50);
		frame.getContentPane().add(CarNameLabel);
		
		JLabel CarNumLabel = new JLabel("차량 번호");
		CarNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CarNumLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		CarNumLabel.setBounds(240, 216, 187, 50);
		frame.getContentPane().add(CarNumLabel);
		
		JLabel CarMaxPsgLabel = new JLabel("최대 탑승 인원");
		CarMaxPsgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CarMaxPsgLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		CarMaxPsgLabel.setBounds(240, 276, 187, 50);
		frame.getContentPane().add(CarMaxPsgLabel);
		
		JLabel CarImgLabel = new JLabel("Image URL");
		CarImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CarImgLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		CarImgLabel.setBounds(240, 336, 187, 50);
		frame.getContentPane().add(CarImgLabel);
		
		JLabel CarDescLabel = new JLabel("캠핑카 설명");
		CarDescLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CarDescLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		CarDescLabel.setBounds(240, 396, 187, 50);
		frame.getContentPane().add(CarDescLabel);
		
		JLabel CarRentPriceLabel = new JLabel("대여 가격");
		CarRentPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CarRentPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		CarRentPriceLabel.setBounds(240, 456, 187, 50);
		frame.getContentPane().add(CarRentPriceLabel);
		
		JLabel CarRegDateLabel = new JLabel("캠핑카 등록 일자");
		CarRegDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CarRegDateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		CarRegDateLabel.setBounds(240, 516, 187, 50);
		frame.getContentPane().add(CarRegDateLabel);
		
		CompanyIDField = new JTextField();
		CompanyIDField.setBackground(new Color(255, 128, 128));
		CompanyIDField.setToolTipText("유효한 값이어야 합니다!!");
		CompanyIDField.setBounds(439, 106, 439, 40);
		frame.getContentPane().add(CompanyIDField);
		CompanyIDField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setToolTipText("");
		nameField.setColumns(10);
		nameField.setBounds(439, 166, 439, 40);
		frame.getContentPane().add(nameField);
		
		CarNumField = new JTextField();
		CarNumField.setToolTipText("00가0000");
		CarNumField.setColumns(10);
		CarNumField.setBounds(439, 226, 439, 40);
		frame.getContentPane().add(CarNumField);
		
		CarMaxPsgField = new JTextField();
		CarMaxPsgField.setToolTipText("");
		CarMaxPsgField.setColumns(10);
		CarMaxPsgField.setBounds(439, 286, 439, 40);
		frame.getContentPane().add(CarMaxPsgField);
		
		CarImgURLField = new JTextField();
		CarImgURLField.setToolTipText("");
		CarImgURLField.setColumns(10);
		CarImgURLField.setBounds(439, 346, 439, 40);
		frame.getContentPane().add(CarImgURLField);
		

		CarDescField = new JTextField();
		CarDescField.setToolTipText("");
		CarDescField.setColumns(10);
		CarDescField.setBounds(439, 406, 439, 40);
		frame.getContentPane().add(CarDescField);
		
		CarRentPriceField = new JTextField();
		CarRentPriceField.setToolTipText("");
		CarRentPriceField.setColumns(10);
		CarRentPriceField.setBounds(439, 466, 439, 40);
		frame.getContentPane().add(CarRentPriceField);
		
		CarRegDateField = new JTextField();
		CarRegDateField.setToolTipText("0000-00-00(년-월-일)");
		CarRegDateField.setColumns(10);
		CarRegDateField.setBounds(439, 526, 439, 40);
		frame.getContentPane().add(CarRegDateField);
		
		
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
    	String companyId = CompanyIDField.getText().trim(); 
    	String name = nameField.getText().trim();
    	String num = CarNumField.getText().trim();
    	String maxpsg = CarMaxPsgField.getText().trim();
    	String url = CarImgURLField.getText().trim();
    	String desc = CarDescField.getText().trim();
    	String rentprice = CarRentPriceField.getText().trim();
    	String regdate = CarRegDateField.getText().trim();
    	
    	//하나라도 빈 문장이 있는 경우, 회원가입을 금지
    	if(companyId.isEmpty() ||name.isEmpty() || num.isEmpty() || maxpsg.isEmpty() || desc.isEmpty() || url.isEmpty() || rentprice.isEmpty() || regdate.isEmpty()) {
    		JOptionPane.showMessageDialog(frame, "필수 정보를 모두 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	//DB 연결 객체 생성
    	conn = DBUtil.getConnection();
    	
    	try {
    		//SQL문을 생성한다.
    		//이때, Auto_increment 설정이 적용되어 있기 때문에, id가 자동으로 부여된다.
    		String sql = "insert into cars(companyid, carname, carnum, carmaxpsg, carimgurl, cardesc, carrentprice, carregdate)" +
    				"Values(?, ?, ?, ?, ?, ?, ?, ?)";
    		//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다
    		PreparedStatement psm = conn.prepareStatement(sql);
    		//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
    		psm.setString(1, companyId);
    		psm.setString(2, name);
    		psm.setString(3, num);
    		psm.setString(4, maxpsg);
    		psm.setString(5, url);
    		psm.setString(6, desc);
    		psm.setString(7, rentprice);
    		psm.setString(8, regdate);
    		
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
