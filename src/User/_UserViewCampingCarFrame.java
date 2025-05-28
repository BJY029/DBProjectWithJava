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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextField;

public class _UserViewCampingCarFrame {

	private JFrame frame;
	private Connection conn;
	private int userID;
	
	private JComboBox<String> comboBox;
	private JComboBox<String> RevcomboBox;
	
	private JScrollPane scrollPane;
	
	private JLabel CarNameLabel;
	private JLabel CarDescArea;
	private JLabel MasLabel;
	private JLabel CarMaxPsgLabel;
	private JLabel PriceLabel;
	private JLabel CarRentPriceLabel;
	private JLabel lblNewLabel_1;
	private JLabel CarNumLabel;
	private JLabel RevLabel;
	private JLabel lblNewLabel_2;
	
	private JButton ViewBtn;
	private JLabel lblNewLabel_4;
	private JTextField StartDateTextField;
	private JTextField EndDateTextField;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton RentBtn;

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
		frame.setBounds(100, 100, 1280, 800);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("캠핑카 조회 및 예약");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 1240, 50);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(381, 70, 322, 23);
		frame.getContentPane().add(comboBox);
		

		CarNameLabel = new JLabel("");
		CarNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CarNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		CarNameLabel.setBounds(356, 237, 464, 23);
		CarNameLabel.setOpaque(true);
		CarNameLabel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(CarNameLabel);
		
		CarDescArea = new JLabel("");
		CarDescArea.setBackground(new Color(255, 255, 255));
		CarDescArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		CarDescArea.setHorizontalAlignment(SwingConstants.CENTER);
		CarDescArea.setBounds(356, 116, 464, 111);
		CarDescArea.setOpaque(true);
		CarDescArea.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(CarDescArea);
		
		MasLabel = new JLabel("최대 탑승 인원 수 :");
		MasLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		MasLabel.setHorizontalAlignment(SwingConstants.LEFT);
		MasLabel.setBounds(832, 168, 145, 28);
		MasLabel.setOpaque(true);
		MasLabel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(MasLabel);
		
		CarMaxPsgLabel = new JLabel("");
		CarMaxPsgLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		CarMaxPsgLabel.setHorizontalAlignment(SwingConstants.LEFT);
		CarMaxPsgLabel.setBounds(977, 168, 110, 28);
		frame.getContentPane().add(CarMaxPsgLabel);
		
		PriceLabel = new JLabel("1박당 가격 : ");
		PriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		PriceLabel.setBounds(832, 232, 97, 28);
		PriceLabel.setOpaque(true);
		PriceLabel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(PriceLabel);
		
		CarRentPriceLabel = new JLabel("");
		CarRentPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		CarRentPriceLabel.setBounds(923, 232, 123, 28);
		frame.getContentPane().add(CarRentPriceLabel);
		
		lblNewLabel_1 = new JLabel("차량 번호 :");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(832, 116, 87, 23);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(lblNewLabel_1);
		
		CarNumLabel = new JLabel("");
		CarNumLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		CarNumLabel.setBounds(912, 116, 110, 23);
		frame.getContentPane().add(CarNumLabel);
		
		lblNewLabel_3 = new JLabel("대여 시작일");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(709, 623, 243, 19);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_5 = new JLabel("대여 종료일");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(709, 675, 243, 19);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("yyyy-mm-dd 형식으로 입력해주세요 ");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(664, 581, 336, 38);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_4 = new JLabel("캠핑카를 선택하세요");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(325, 663, 243, 19);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_2= new JLabel("선택한 캠핑카 예약 가능 날짜(30일)");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		lblNewLabel_2.setToolTipText("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(478, 282, 290, 28);
		frame.getContentPane().add(lblNewLabel_2);
		
		RevLabel = new JLabel("예약하기");
		RevLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		RevLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RevLabel.setBounds(0, 549, 1264, 28);
		RevLabel.setOpaque(true);
		RevLabel.setBackground(Color.yellow);
		frame.getContentPane().add(RevLabel);

		

		
		ViewBtn = new JButton("조회하기");
		ViewBtn.addActionListener(e->{
			loadCarData((String) comboBox.getSelectedItem());
			showAvailableData((String) comboBox.getSelectedItem());
					});
		ViewBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		ViewBtn.setBounds(715, 70, 97, 23);
		frame.getContentPane().add(ViewBtn);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 320, 1125, 208);
		frame.getContentPane().add(scrollPane);
		

		
		RevcomboBox = new JComboBox<>();
		RevcomboBox.setBounds(325, 680, 243, 23);
		frame.getContentPane().add(RevcomboBox);
		

		
		StartDateTextField = new JTextField();
		StartDateTextField.setBounds(709, 643, 243, 21);
		frame.getContentPane().add(StartDateTextField);
		StartDateTextField.setColumns(10);
		
		EndDateTextField = new JTextField();
		EndDateTextField.setColumns(10);
		EndDateTextField.setBounds(709, 697, 243, 21);
		frame.getContentPane().add(EndDateTextField);
		
		RentBtn = new JButton("예약 신청하기");
		RentBtn.addActionListener(e->ReservationSystem(RevcomboBox.getSelectedItem().toString()));
		RentBtn.setBounds(582, 728, 117, 23);
		frame.getContentPane().add(RentBtn);
		
	}
	
    private void loadCarsNames() {
    	try {
    		//선택된 테이블의 전체 레코드 조회
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("select carname from cars");
    		
    		while(rs.next()) {
    			comboBox.addItem(rs.getString("carname"));
    			RevcomboBox.addItem(rs.getString("carname"));
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

    
	// 작업중
	private void showAvailableData(String carId) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"select rentstart, DATE_ADD(rentstart, INTERVAL rentperiod DAY) as rentend, rentperiod from rent "
							+ "where carid = ? and DATE_ADD(rentstart, INTERVAL rentperiod DAY) > CURDATE()");
			pstmt.setString(1, carId);
			ResultSet rs = pstmt.executeQuery();

			// 대여 불가능한 날자를 저장하는 set
			Set<LocalDate> unavailableDates = new HashSet<>();
			while (rs.next()) {
				// 대여 시작 날짜와 끝나는 날짜를 받아오고
				LocalDate start = rs.getDate("rentstart").toLocalDate();
				LocalDate end = rs.getDate("rentend").toLocalDate();
				// 해당 날짜들을 set에 대입
				while (!start.isAfter(end)) {
					unavailableDates.add(start);
					start = start.plusDays(1);
				}
			}

			// 가능한 날짜들을 저장할 리스트
			DefaultListModel<String> model = new DefaultListModel<>();
			// 오늘 날짜 받아오기
			LocalDate today = LocalDate.now();
			// 오늘부터 30일 까지 날짜중
			for (int i = 0; i < 30; i++) {
				LocalDate date = today.plusDays(i);
				// 불가능한 날짜 제외 리스트에 추가
				if (!unavailableDates.contains(date)) {
					model.addElement(date.toString());
				}
			}

			JList<String> dataList = new JList<>(model);
			scrollPane.setViewportView(dataList);

			frame.repaint();
			frame.revalidate();

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "날짜 불러오기 실패: " + e.getMessage());
		}
	}
	/*
	 * private String convertToDirectImageURL(String sharedUrl) { if (sharedUrl ==
	 * null || !sharedUrl.contains("/d/")) return null;
	 * 
	 * try { System.out.println("변환전:" + sharedUrl); int start =
	 * sharedUrl.indexOf("/d/") + 3; int end = sharedUrl.indexOf("/", start); String
	 * fileId = sharedUrl.substring(start, end); return
	 * "https://drive.google.com/uc?export=view&id=" + fileId; } catch (Exception e)
	 * { return null; } }
	 */

	
	private void ReservationSystem(String carName) {
		String startDate = StartDateTextField.getText();
		String endDate = EndDateTextField.getText();

		
	
		String companyID = null;
		String carID = null;
		String carPrice = null;
		
		if(startDate.isEmpty() || endDate.isEmpty() ) {
			JOptionPane.showMessageDialog(frame, "모든 요소를 입력하세요", "입력 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			String LicenseNum = getUserLicenseNum();
			if(LicenseNum == null) throw new Exception("면허 정보 갱신 실패");
			
			LocalDate startD = LocalDate.parse(startDate);
			LocalDate endD = LocalDate.parse(endDate);
			String rentPeriod = String.valueOf(ChronoUnit.DAYS.between(startD, endD));
			
			//과거 날짜 입력 시(오늘 이전 날짜)
			if (startD.isBefore(LocalDate.now())) {
			    JOptionPane.showMessageDialog(frame, "대여 시작일은 오늘 이후여야 합니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
			    return;
			}
			
			//종료일이 시작일보다 빠른 경우
			if (endD.isBefore(startD)) {
			    JOptionPane.showMessageDialog(frame, "반납일은 대여 시작일보다 이후여야 합니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
			    return;
			}
			
			if(Integer.parseInt(rentPeriod) == 0) {
				JOptionPane.showMessageDialog(frame, "대여는 하루 이상부터 가능합니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
			    return;
			}
					
					
			
			String sql = "select carid,companyid,carrentprice from cars where carname like ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+carName+"%");
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				companyID =  rs.getString("companyid");
				carID = rs.getString("carid");
				carPrice = String.valueOf((Integer.parseInt(rs.getString("carrentprice")) * Integer.parseInt(rentPeriod)));
			}
			pstmt.close();
			rs.close();
			
			if (isCarAlreadyRented(carID, startD, endD)) {
			    JOptionPane.showMessageDialog(frame, "이미 해당 날짜에 예약이 존재합니다.", "중복 예약", JOptionPane.WARNING_MESSAGE);
			    return;
			}
			
			sql = "insert into rent(companyid, carid, licensenum, rentstart, rentperiod, rentprice, paymentdate)" +
    				"Values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sql);
			pstmt2.setString(1, companyID);
			pstmt2.setString(2, carID);
			pstmt2.setString(3, LicenseNum);
			pstmt2.setDate(4, java.sql.Date.valueOf(startD));
			pstmt2.setString(5, rentPeriod);
			pstmt2.setString(6, carPrice);
			pstmt2.setDate(7, java.sql.Date.valueOf(endD));
			
			int result = pstmt2.executeUpdate();
			if(result > 0) {
    			JOptionPane.showMessageDialog(frame, "성공적으로 예약이 완료되었습니다!");
    			frame.dispose();
    		}
    		else {
    			JOptionPane.showMessageDialog(frame, "예약 실패", "예약 오류", JOptionPane.ERROR_MESSAGE);
    		}
			pstmt2.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "예약 정보 갱신 실패" + e.getMessage());
		} catch (DateTimeParseException ed) { //입력 날짜 형식 오류
			JOptionPane.showMessageDialog(frame, "날짜 형식이 잘못되었습니다. (예: 2025-06-01)", "입력 오류",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, ex);
		}
	}
	
	private String getUserLicenseNum() {
		try {
			String LNum = null;
			
			String sql = "select licensenum from customer where custid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(userID));
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				LNum = rs.getString("licensenum");
			}
			
			pstmt.close();
			rs.close();
			return LNum;
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(frame, "사용자 정보(면허번호) 불러오기 오류" + e.getMessage());
			return null;
		}
	}
	
	private boolean isCarAlreadyRented(String carId, LocalDate startDate, LocalDate endDate) {
	    String sql = "SELECT COUNT(*) FROM rent " +
	                 "WHERE carid = ? " +
	                 "AND ( " +
	                 "  (rentstart <= ? AND DATE_ADD(rentstart, INTERVAL rentperiod DAY) > ?) " +
	                 "  OR " +
	                 "  (rentstart < ? AND DATE_ADD(rentstart, INTERVAL rentperiod DAY) >= ?) " +
	                 "  OR"  +
	                 "   (rentstart >= ? AND DATE_ADD(rentstart, INTERVAL rentperiod DAY) <= ?)" +
	                 ")";
	    
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, carId);
	        pstmt.setDate(2, java.sql.Date.valueOf(startDate));
	        pstmt.setDate(3, java.sql.Date.valueOf(startDate));
	        pstmt.setDate(4, java.sql.Date.valueOf(endDate));
	        pstmt.setDate(5, java.sql.Date.valueOf(endDate));
	        pstmt.setDate(6, java.sql.Date.valueOf(startDate));
	        pstmt.setDate(7, java.sql.Date.valueOf(endDate));

	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0; // true면 예약 불가
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "예약 중복 확인 실패: " + e.getMessage());
	    }
	    return false;
	}

	
	
	public void setVisible(boolean b, int userId) {
		userID = userId;
		frame.setVisible(b);
	}
}
