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

public class _CarMaintenanceFrame {

	private JFrame frame;

	private JTextField CarIDField;
	private JTextField PartIDField;
	private JTextField MtaDateField;
	private JTextField MechanicIDField;
	private JTextField MtaTimeField;
	
	private Connection conn;
	/**
	 * Create the application.
	 */
	public _CarMaintenanceFrame() {
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
		
		JLabel Title = new JLabel("CarMaintenance Table 삽입");
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(12, 38, 1240, 35);
		frame.getContentPane().add(Title);
		
		JLabel carIdLabel = new JLabel("캠핑카 등록 ID");
		carIdLabel.setToolTipText("유효한 값이어야 합니다!!");
		carIdLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		carIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		carIdLabel.setBounds(240, 96, 187, 50);
		frame.getContentPane().add(carIdLabel);
		
		JLabel PartIDLabel = new JLabel("부품 등록 ID");
		PartIDLabel.setToolTipText("유효한 값이어야 합니다!!");
		PartIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PartIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		PartIDLabel.setBounds(240, 156, 187, 50);
		frame.getContentPane().add(PartIDLabel);
		
		JLabel MtaDateLabel = new JLabel("정비 일자");
		MtaDateLabel.setToolTipText("");
		MtaDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MtaDateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		MtaDateLabel.setBounds(240, 216, 187, 50);
		frame.getContentPane().add(MtaDateLabel);
		
		JLabel MtaTimeLabel = new JLabel("정비에 걸린 시간(분)");
		MtaTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MtaTimeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		MtaTimeLabel.setBounds(240, 276, 187, 50);
		frame.getContentPane().add(MtaTimeLabel);
		
		JLabel MechanicIDLabel = new JLabel("정비 담당자 ID");
		MechanicIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MechanicIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		MechanicIDLabel.setBounds(240, 336, 187, 50);
		frame.getContentPane().add(MechanicIDLabel);
		
		CarIDField = new JTextField();
		CarIDField.setBackground(new Color(255, 128, 128));
		CarIDField.setToolTipText("유효한 값이어야 합니다!!");
		CarIDField.setBounds(439, 106, 439, 40);
		frame.getContentPane().add(CarIDField);
		CarIDField.setColumns(10);
		
		PartIDField = new JTextField();
		PartIDField.setBackground(new Color(255, 128, 128));
		PartIDField.setToolTipText("유효한 값이어야 합니다!!");
		PartIDField.setColumns(10);
		PartIDField.setBounds(439, 166, 439, 40);
		frame.getContentPane().add(PartIDField);
		
		MtaDateField = new JTextField();
		MtaDateField.setToolTipText("0000-00-00");
		MtaDateField.setColumns(10);
		MtaDateField.setBounds(439, 226, 439, 40);
		frame.getContentPane().add(MtaDateField);
		
		MtaTimeField = new JTextField();
		MtaTimeField.setToolTipText("");
		MtaTimeField.setColumns(10);
		MtaTimeField.setBounds(439, 286, 439, 40);
		frame.getContentPane().add(MtaTimeField);
		
		MechanicIDField = new JTextField();
		MechanicIDField.setToolTipText("");
		MechanicIDField.setColumns(10);
		MechanicIDField.setBounds(439, 346, 439, 40);
		frame.getContentPane().add(MechanicIDField);
		
		
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
    	String carId = CarIDField.getText().trim(); 
    	String partid = PartIDField.getText().trim();
    	String mtadate = MtaDateField.getText().trim();
    	String mtatime = MtaTimeField.getText().trim();
    	String mechanicid = MechanicIDField.getText().trim();
    	
    	//하나라도 빈 문장이 있는 경우, 회원가입을 금지
    	if(carId.isEmpty() ||partid.isEmpty() || mtadate.isEmpty() || mtatime.isEmpty() || mechanicid.isEmpty() ) {
    		JOptionPane.showMessageDialog(frame, "필수 정보를 모두 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	//DB 연결 객체 생성
    	conn = DBUtil.getConnection();
    	
    	try {
    		//SQL문을 생성한다.
    		//이때, Auto_increment 설정이 적용되어 있기 때문에, id가 자동으로 부여된다.
    		String sql = "insert into CarMaintenance(carid, partid, mtadate, mtatime, mechanicid)" +
    				"Values(?, ?, ?, ?, ?)";
    		//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다
    		PreparedStatement psm = conn.prepareStatement(sql);
    		//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
    		psm.setString(1, carId);
    		psm.setString(2, partid);
    		psm.setString(3, mtadate);
    		psm.setString(4, mtatime);
    		psm.setString(5, mechanicid);
    		
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
