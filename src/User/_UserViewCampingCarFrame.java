package User;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class _UserViewCampingCarFrame {

	private JFrame frame;
	private Connection conn;
	
	private JComboBox<String> comboBox;
	private JLabel CarNameLabel;
	private JLabel CarDescArea;
	private JLabel MasLabel;
	private JLabel CarMaxPsgLabel;
	private JLabel PriceLabel;
	private JLabel CarRentPriceLabel;
	private JButton ViewBtn;
	private JLabel lblNewLabel_1;
	private JLabel CarNumLabel;


	/**
	 * Create the application.
	 */
	public _UserViewCampingCarFrame() {
		initialize();
		loadCarsNames();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		conn = DBUtil.getUserConnection();

		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("캠핑카 조회 및 예약");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 1240, 50);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(22, 70, 322, 23);
		frame.getContentPane().add(comboBox);
		
		CarNameLabel = new JLabel("");
		CarNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CarNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		CarNameLabel.setBounds(356, 237, 464, 23);
		frame.getContentPane().add(CarNameLabel);
		
		CarDescArea = new JLabel("");
		CarDescArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		CarDescArea.setHorizontalAlignment(SwingConstants.CENTER);
		CarDescArea.setBounds(356, 116, 464, 111);
		frame.getContentPane().add(CarDescArea);
		
		MasLabel = new JLabel("최대 탑승 인원 수 :");
		MasLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		MasLabel.setHorizontalAlignment(SwingConstants.LEFT);
		MasLabel.setBounds(832, 168, 145, 28);
		frame.getContentPane().add(MasLabel);
		
		CarMaxPsgLabel = new JLabel("");
		CarMaxPsgLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		CarMaxPsgLabel.setHorizontalAlignment(SwingConstants.LEFT);
		CarMaxPsgLabel.setBounds(641, 168, 110, 28);
		frame.getContentPane().add(CarMaxPsgLabel);
		
		PriceLabel = new JLabel("1박당 가격 : ");
		PriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		PriceLabel.setBounds(832, 232, 97, 28);
		frame.getContentPane().add(PriceLabel);
		
		CarRentPriceLabel = new JLabel("");
		CarRentPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		CarRentPriceLabel.setBounds(589, 232, 123, 28);
		frame.getContentPane().add(CarRentPriceLabel);
		
		lblNewLabel_1 = new JLabel("차량 번호 :");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(832, 116, 87, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		CarNumLabel = new JLabel("");
		CarNumLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		CarNumLabel.setBounds(912, 116, 110, 23);
		frame.getContentPane().add(CarNumLabel);
		
		ViewBtn = new JButton("조회하기");
		ViewBtn.addActionListener(e->loadCarData((String) comboBox.getSelectedItem()));
		ViewBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		ViewBtn.setBounds(356, 70, 97, 23);
		frame.getContentPane().add(ViewBtn);
		


	}
	
    private void loadCarsNames() {
    	try {
    		//선택된 테이블의 전체 레코드 조회
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("select carname from cars");
    		
    		while(rs.next()) {
    			comboBox.addItem(rs.getString("carname"));
    		}
    		rs.close();
    	}catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "테이블 목록 로드 실패: " + e.getMessage());
        }
    }

    
    private void loadCarData(String name) {
        try {
            String sql = "SELECT * FROM cars WHERE carname LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { // 데이터가 있을 경우
                CarNameLabel.setText(rs.getString("CarName"));
                CarNumLabel.setText(rs.getString("CarNum"));
                CarMaxPsgLabel.setText(String.valueOf(rs.getInt("CarMaxPsg")));
                CarRentPriceLabel.setText(String.valueOf(rs.getInt("CarRentPrice")));
				CarDescArea.setText(rs.getString("CarDesc"));
				
				/*
				// 이미지 URL로부터 이미지 불러오기
				String imageUrl = convertToDirectImageURL(rs.getString("CarImgURL"));
				System.out.println("변환후:" + imageUrl);
				try {
					if (imageUrl == null) {
						ImgLabel.setText("지정된 파일이 없습니다.");
					} else {
						ImgLabel.setText("이미지 불러오는 중..");
						URI uri = URI.create(imageUrl);
						URL url = uri.toURL();
						ImageIcon icon = new ImageIcon(url);
						Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
						ImgLabel.setIcon(new ImageIcon(image));
					}
				} catch (Exception ex) {
					ImgLabel.setText("이미지를 불러올 수 없습니다.");
				}
				*/

			} else {
				JOptionPane.showMessageDialog(frame, "해당 차량 정보를 찾을 수 없습니다.");
			}

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
        }
    }

    
    //작업중
    private void showAvailableData(String carId) {
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(
    				"select rentstart, DATE_ADD(rentstart, INTERVAL rentperiod DAY) as rentend, rentperiod from rent "
    				+ "where carid = ? and DATE_ADD(rentstart, INTERVAL rentperiod DAY) > CURDATE()");
    		pstmt.setString(1,  carId);
    		ResultSet rs = pstmt.executeQuery();
    		
    		
    		
    	}catch(SQLException e) {
    		JOptionPane.showMessageDialog(frame, "날짜 불러오기 실패: " + e.getMessage());
    	}
    }
    /*
	private String convertToDirectImageURL(String sharedUrl) {
		if (sharedUrl == null || !sharedUrl.contains("/d/"))
			return null;

		try {
			System.out.println("변환전:" + sharedUrl);
			int start = sharedUrl.indexOf("/d/") + 3;
			int end = sharedUrl.indexOf("/", start);
			String fileId = sharedUrl.substring(start, end);
			return "https://drive.google.com/uc?export=view&id=" + fileId;
		} catch (Exception e) {
			return null;
		}
	}
	*/

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
