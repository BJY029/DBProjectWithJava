SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- DB 초기화 및 생성
DROP DATABASE IF EXISTS DBTEST;
CREATE DATABASE DBTEST DEFAULT CHARACTER SET utf8;
USE DBTEST;



-- -----------------------------------------------------
-- Table `DBTEST`.`Company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBTEST`.`Company` (
  `CompanyId` INT NOT NULL auto_increment,
  `CompanyName` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(100) NOT NULL,
  `OfficeNum` VARCHAR(30) NOT NULL,
  `MngName` VARCHAR(45) NULL,
  `MngEmail` VARCHAR(45) NULL,
  PRIMARY KEY (`CompanyId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBTEST`.`Cars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBTEST`.`Cars` (
  `CarID` INT NOT NULL auto_increment,
  `CompanyId` INT NOT NULL,
  `CarName` VARCHAR(45) NOT NULL,
  `CarNum` VARCHAR(45) NULL,
  `CarMaxPsg` INT NULL,
  `CarImgURL` VARCHAR(225) NULL,
  `CarDesc` VARCHAR(200) NULL,
  `CarRentPrice` INT NULL,
  `CarRegDate` DATE NULL,
  PRIMARY KEY (`CarID`),
  INDEX `fk_Cars_Company_idx` (`CompanyId` ASC) VISIBLE,
  UNIQUE INDEX `CarNum_UNIQUE` (`CarNum` ASC) VISIBLE,
  CONSTRAINT `fk_Cars_Company`
    FOREIGN KEY (`CompanyId`)
    REFERENCES `DBTEST`.`Company` (`CompanyId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBTEST`.`Parts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBTEST`.`Parts` (
  `PartID` INT NOT NULL auto_increment,
  `PartName` VARCHAR(45) NULL,
  `PartPrice` INT NULL,
  `PartCnt` INT NULL,
  `PartReceiptDate` DATE NULL,
  `PartCompany` VARCHAR(45) NULL,
  PRIMARY KEY (`PartID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBTEST`.`CarMaintenance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBTEST`.`CarMaintenance` (
  `MtaID` INT NOT NULL auto_increment,
  `CarID` INT NOT NULL,
  `PartID` INT NOT NULL,
  `MtaDate` DATE NULL,
  `MtaTime` INT NULL,
  `MechanicID` INT NULL,
  PRIMARY KEY (`MtaID`),
  INDEX `fk_Cars_has_Parts_Parts1_idx` (`PartID` ASC) VISIBLE,
  INDEX `fk_Cars_has_Parts_Cars1_idx` (`CarID` ASC) VISIBLE,
  CONSTRAINT `fk_Cars_has_Parts_Cars1`
    FOREIGN KEY (`CarID`)
    REFERENCES `DBTEST`.`Cars` (`CarID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cars_has_Parts_Parts1`
    FOREIGN KEY (`PartID`)
    REFERENCES `DBTEST`.`Parts` (`PartID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBTEST`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBTEST`.`employee` (
  `empID` INT NOT NULL auto_increment,
  `empName` VARCHAR(45) NULL,
  `empPhone` VARCHAR(30) NULL,
  `empAddress` VARCHAR(100) NULL,
  `empSalary` INT NULL,
  `empFamilyCnt` INT NULL,
  `empDepartment` VARCHAR(45) NULL,
  `empWork` ENUM('management', 'desk', 'maintenance', 'none') NOT NULL,
  PRIMARY KEY (`empID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `DBTEST`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBTEST`.`customer` (
  `custid` INT NOT NULL auto_increment,
  `LicenseNum` VARCHAR(45) NOT NULL,
  `custlogid` VARCHAR(45) NOT NULL,
  `custlogpassword` VARCHAR(45) NOT NULL,
  `custname` VARCHAR(45) NULL,
  `custaddress` VARCHAR(100) NULL,
  `custphone` VARCHAR(30) NULL,
  `custEmail` VARCHAR(45) NULL,
  `custHistoryDate` DATE NULL,
  `custHistoryCar` VARCHAR(45) NULL,
  PRIMARY KEY (`custid`, `LicenseNum`),
  UNIQUE INDEX `LicenseNum_UNIQUE` (`LicenseNum` ASC) VISIBLE,
  UNIQUE INDEX `custlogid_UNIQUE` (`custlogid` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBTEST`.`RepairShop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBTEST`.`RepairShop` (
  `RPID` INT NOT NULL auto_increment,
  `RPName` VARCHAR(45) NULL,
  `RPAddress` VARCHAR(100) NULL,
  `RPPhone` VARCHAR(30) NULL,
  `RPMngName` VARCHAR(45) NULL,
  `RpMngEmail` VARCHAR(45) NULL,
  PRIMARY KEY (`RPID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBTEST`.`Rent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBTEST`.`Rent` (
  `RentID` INT NOT NULL auto_increment,
  `CompanyId` INT NOT NULL,
  `CarID` INT NOT NULL,
  `LicenseNum` VARCHAR(45) NOT NULL,
  `RentStart` DATE NULL,
  `RentPeriod` INT NULL,
  `RentPrice` INT NULL,
  `PaymentDate` DATE NULL,
  `etcDetails` VARCHAR(100) NULL,
  `etcPrice` INT NULL,
  INDEX `fk_table1_Company1_idx` (`CompanyId` ASC) VISIBLE,
  INDEX `fk_table1_Cars1_idx` (`CarID` ASC) VISIBLE,
  PRIMARY KEY (`RentID`),
  INDEX `fk_Rent_customer1_idx` (`LicenseNum` ASC) VISIBLE,
  CONSTRAINT `fk_table1_Company1`
    FOREIGN KEY (`CompanyId`)
    REFERENCES `DBTEST`.`Company` (`CompanyId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_Cars1`
    FOREIGN KEY (`CarID`)
    REFERENCES `DBTEST`.`Cars` (`CarID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rent_customer1`
    FOREIGN KEY (`LicenseNum`)
    REFERENCES `DBTEST`.`customer` (`LicenseNum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBTEST`.`CustRepair`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBTEST`.`CustRepair` (
  `CustRepairID` INT NOT NULL auto_increment,
  `CarID` INT NOT NULL,
  `RPID` INT NOT NULL,
  `CompanyId` INT NOT NULL,
  `LicenseNum` VARCHAR(45) NOT NULL,
  `RepairDetails` VARCHAR(100) NULL,
  `RepairDate` DATE NULL,
  `RepairPrice` INT NULL,
  `PaymentDate` DATE NULL,
  `etcDetails` VARCHAR(100) NULL,
  PRIMARY KEY (`CustRepairID`),
  INDEX `fk_CustRepair_Cars1_idx` (`CarID` ASC) VISIBLE,
  INDEX `fk_CustRepair_RepairShop1_idx` (`RPID` ASC) VISIBLE,
  INDEX `fk_CustRepair_customer1_idx` (`LicenseNum` ASC) VISIBLE,
  INDEX `fk_CustRepair_Company1_idx` (`CompanyId` ASC) VISIBLE,
  CONSTRAINT `fk_CustRepair_Cars1`
    FOREIGN KEY (`CarID`)
    REFERENCES `DBTEST`.`Cars` (`CarID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CustRepair_RepairShop1`
    FOREIGN KEY (`RPID`)
    REFERENCES `DBTEST`.`RepairShop` (`RPID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CustRepair_customer1`
    FOREIGN KEY (`LicenseNum`)
    REFERENCES `DBTEST`.`customer` (`LicenseNum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CustRepair_Company1`
    FOREIGN KEY (`CompanyId`)
    REFERENCES `DBTEST`.`Company` (`CompanyId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON DBTEST.* TO 'root'@'localhost';

-- User1 권한 설정
CREATE USER IF NOT EXISTS 'user1'@'localhost' IDENTIFIED BY 'user1';
GRANT SELECT, INSERT, UPDATE, DELETE ON DBTEST.Rent TO 'user1'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON DBTEST.Customer TO 'user1'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON DBTEST.CustRepair TO 'user1'@'localhost';
flush privileges;

insert into company values(1, '우리렌트카', '서울시 광진구', '010-1234-5678', '김우리', 'woori@email.com');
insert into company values(2, '국민렌트카', '서울시 동대문구', '010-8765-4321', '김국민', 'kookmin@email.com');
insert into company values(3, '슝슝렌트카', '서울시 강남구', '010-1325-7958', '박수연', 'soo@email.com');
insert into company values(4, '세종렌트카', '세종시', '010-5058-4578', '조세린', 'sejong@email.com');
insert into company values(5, '캠핑모아', '부산시 남구', '010-4758-7528', '이승현', 'lee@email.com');
insert into company values(6, '제주렌트카', '제주시', '010-7242-1140', '한주혁', 'jh123@email.com');
insert into company values(7, '캠프리버', '울산시 남구', '010-2258-5524', '손재무', 'moo@email.com');
insert into company values(8, '노마드웨이', '대전시 유성구', '010-2958-9752', '김민엽', 'yup@email.com');
insert into company values(9, '로드트립', '춘천시', '010-5562-0215', '장성민', 'sung@email.com');
insert into company values(10, '트레일리', '수원시 장안구', '010-5774-9725', '윤서호', 'yoon@email.com');
insert into company values(11, '캠포리아', '진주시', '010-7322-8034', '임성훈', 'lim@email.com');
insert into company values(12, '트레일리', '광주시 서구', '010-5548-9520', '김형주', 'juu@email.com');


INSERT INTO Cars 
(CarID, CompanyID, CarName, CarNum, CarMaxPsg, CarImgURL, CarDesc, CarRentPrice, CarRegDate) 
VALUES
(101, 1, '캠핑스타 300', '12가3456', 4, 'http://img.com/car1.jpg', '소형 가족용 캠핑카', 90000, '2024-01-10'),
(102, 2, '힐링카라반 X', '23나5678', 6, 'http://img.com/car2.jpg', '6인용 중형 캠핑카, 샤워 가능', 120000, '2024-02-05'),
(103, 3, '오로라 V8', '34다6789', 8, 'http://img.com/car3.jpg', '대형 캠핑카, 화장실/침대 포함', 180000, '2023-11-15'),
(104, 4, '나들이 캠핑카 A', '45라1234', 2, 'http://img.com/car4.jpg', '커플용 미니 캠핑카', 70000, '2024-03-01'),
(105, 5, '프렌즈 캠핑카', '56마2345', 5, 'http://img.com/car5.jpg', '친구들과 떠나는 여행용 중형 캠핑카', 110000, '2024-01-28'),
(106, 6, '썬라이즈 C7', '67바6789', 3, 'http://img.com/car6.jpg', '소형 전기 캠핑카', 85000, '2023-12-10'),
(107, 7, '드림카라반 L', '78사9999', 6, 'http://img.com/car7.jpg', '주방 포함 풀옵션 캠핑카', 125000, '2023-09-19'),
(108, 8, '아웃도어 S1', '89아1111', 7, 'http://img.com/car8.jpg', '넓은 공간의 프리미엄 캠핑카', 155000, '2024-04-10'),
(109, 9, '캠핑이랑 C-Lite', '90자2222', 4, 'http://img.com/car9.jpg', '초보자용 경량 캠핑카', 95000, '2024-02-22'),
(110, 10, '로드트립 V1', '01차3333', 6, 'http://img.com/car10.jpg', '고속도로 주행에 최적화된 캠핑카', 115000, '2023-10-03'),
(111, 11, '해피트럭 Camper', '12카4444', 3, 'http://img.com/car11.jpg', '트럭 기반의 실속형 캠핑카', 78000, '2023-12-27'),
(112, 12, '피크닉캠퍼 Z', '13타5555', 5, 'http://img.com/car12.jpg', '온가족을 위한 최적 캠핑카', 102000, '2024-03-17');

INSERT INTO Parts (PartID, PartName, PartPrice, PartCnt, PartReceiptDate, PartCompany) VALUES
(201, '브레이크 패드', 35000, 20, '2024-01-10', '오토코리아'),
(202, '엔진 오일 필터', 12000, 50, '2024-02-05', '한성모터스'),
(203, '에어컨 필터', 18000, 35, '2024-03-12', '코리아필터'),
(204, '타이어 16인치', 95000, 12, '2024-04-20', '한국타이어'),
(205, '배터리 80Ah', 125000, 10, '2024-05-18', '세방전지'),
(206, '와이퍼 블레이드', 8000, 100, '2024-06-01', '와이퍼월드'),
(207, '서스펜션 스트럿', 75000, 8, '2024-07-10', '모빌텍'),
(208, '전조등 LED', 30000, 25, '2024-08-25', '라이트코리아'),
(209, '변속기 오일', 29000, 40, '2024-09-05', '현대모비스'),
(210, '냉각수', 15000, 60, '2024-10-14', '대성화학'),
(211, '차량용 퓨즈', 3000, 200, '2025-02-17', '퓨즈나라'),
(212, '연료 필터', 22000, 30, '2025-04-28', '한일정공');

INSERT INTO CarMaintenance (MtaID, CarID, PartID, MtaDate, MtaTime, MechanicID) VALUES
(1, 101, 201, '2024-01-15', 45, 501),
(2, 102, 202, '2024-02-10', 30, 502),
(3, 103, 203, '2024-04-03', 60, 503),
(4, 104, 204, '2024-06-15', 50, 504),
(5, 105, 205, '2024-08-01', 90, 505),
(6, 106, 206, '2024-10-12', 20, 501),
(7, 107, 207, '2025-01-05', 70, 502),
(8, 108, 208, '2025-02-18', 40, 503),
(9, 109, 209, '2025-03-22', 25, 504),
(10, 110, 210, '2025-04-30', 55, 505),
(11, 111, 211, '2025-05-11', 35, 501),
(12, 112, 212, '2025-05-17', 80, 502);




INSERT INTO Employee (empID, empName, empPhone, empAddress, empSalary, empFamilyCnt, empDepartment, empWork) VALUES
(1001, '김영수', '010-1234-5678', '성남시 분당구', 4000000, 2, '정비팀', 'maintenance'),
(1002, '박지민', '010-2345-6789', '용인시 수지구', 3900000, 1, '정비팀', 'maintenance'),
(1003, '이은지', '010-3456-7890', '서울시 송파구', 4200000, 3, '정비팀', 'maintenance'),
(1004, '정하준', '010-4567-8901', '서울시 강남구', 4100000, 2, '정비팀', 'maintenance'),
(1005, '최민우', '010-5678-9012', '광명시', 4050000, 1, '정비팀', 'maintenance'),
(1006, '한소희', '010-6789-0123', '성남시 수정구', 5000000, 0, '경영지원팀', 'management'),
(1007, '윤채영', '010-7890-1234', '서울시 동작구', 3700000, 2, '고객지원팀', 'desk'),
(1008, '김재훈', '010-8901-2345', '서울시 중랑구', 3600000, 3, '고객지원팀', 'desk'),
(1009, '백현우', '010-9012-3456', '하남시', 4700000, 1, '경영지원팀', 'management'),
(1010, '서지후', '010-0123-4567', '과천시', 3300000, 4, '고객지원팀', 'desk'),
(1011, '조하나', '010-1122-3344', '서울시 관악구', 2900000, 0, '인턴팀', 'none'),
(1012, '남도윤', '010-5566-7788', '안양시 동안구', 3100000, 2, '인턴팀', 'none');



INSERT INTO Customer (custID, LicenseNum, custLogID, custLogPassword, custName, custAddress, custPhone, custEmail, custHistoryDate, custHistoryCar) VALUES
(1001, '12-123456', 'hyunpark01', 'pass1234', '박현우', '서울시 마포구', '010-1234-5678', 'hyunpw@gmail.com', '2024-07-10', '캠핑스타 300'),
(1002, '13-654321', 'leejeong88', 'lee@2023', '이정민', '성남시 분당구', '010-2345-6789', 'ljm@naver.com', '2024-08-02', '오로라 V8'),
(1003, '22-876543', 'kimjin12', 'qwe45678', '김진우', '용인시 수지구', '010-3456-7890', 'jinkim@daum.net', '2024-09-18', '드림카라반 L'),
(1004, '11-345678', 'moonseo01', 'moon@1004', '문서영', '서울시 동작구', '010-4567-8901', 'moonseo@gmail.com', '2024-11-22', '프렌즈 캠핑카'),
(1005, '21-234567', 'jangmi_91', 'jang!pw91', '장미희', '광명시', '010-5678-9012', 'jangmi91@gmail.com', '2025-01-03', '힐링카라반 X'),
(1006, '33-345123', 'chaejung03', 'cj@pw2024', '채정훈', '서울시 강서구', '010-6789-0123', 'chaejh@outlook.com', '2025-02-14', '썬라이즈 C7'),
(1007, '24-654789', 'sujinlee', 'lee88888', '이수진', '과천시', '010-7890-1234', 'sujin@gmail.com', '2025-03-08', '피크닉캠퍼 Z'),
(1008, '14-888888', 'kwonmin', 'pwkwon77', '권민혁', '하남시', '010-8901-2345', 'kwonmin@korea.com', '2025-03-30', '아웃도어 S1'),
(1009, '19-456321', 'seohee22', 'seopw2022', '서희영', '서울시 강남구', '010-9012-3456', 'seohee@naver.com', '2025-04-19', '해피트럭 Camper'),
(1010, '26-998877', 'minjeong77', 'pw@777min', '민정아', '안양시 동안구', '010-0123-4567', 'mj77@hanmail.net', '2025-05-11', '캠핑이랑 C-Lite'),
(1011, '17-111222', 'jiyoonkim', 'passkim99', '김지윤', '서울시 종로구', '010-1122-3344', 'jiyoonk@gmail.com', '2025-05-17', '로드트립 V1'),
(1012, '31-333444', 'songhyun', 'hyun@2025', '송현아', '성남시 수정구', '010-5566-7788', 'songh@outlook.kr', '2025-05-18', '나들이 캠핑카 A');


INSERT INTO Rent (
    RentID, CompanyID, CarID, LicenseNum, RentStart, RentPeriod, RentPrice, PaymentDate, etcDetails, etcPrice
) VALUES
(1, 1, 101, '12-123456', '2025-04-02', 2, 180000, '2025-04-04', '청소비', 20000),
(2, 2, 102, '13-654321', '2025-04-05', 3, 360000, '2025-04-08', NULL, NULL),
(3, 3, 103, '22-876543', '2025-04-09', 1, 180000, '2025-04-10', '야간 출고비', 15000),
(4, 4, 104, '11-345678', '2025-04-12', 4, 280000, '2025-04-16', NULL, NULL),
(5, 5, 105, '21-234567', '2025-04-18', 2, 220000, '2025-04-20', '반환지연료', 10000),
(6, 6, 106, '33-345123', '2025-04-22', 3, 255000, '2025-04-25', '차량세차비', 15000),
(7, 7, 107, '24-654789', '2025-04-26', 1, 125000, '2025-04-27', NULL, NULL),
(8, 8, 108, '14-888888', '2025-05-01', 2, 310000, '2025-05-03', '추가인원 요금', 20000),
(9, 9, 109, '19-456321', '2025-05-04', 1, 155000, '2025-05-05', NULL, NULL),
(10, 10, 110, '26-998877', '2025-05-08', 2, 230000, '2025-05-10', '장거리 운행 추가요금', 25000),
(11, 11, 111, '17-111222', '2025-05-13', 1, 78000, '2025-05-14', NULL, NULL),
(12, 12, 112, '31-333444', '2025-05-16', 2, 306000, '2025-05-18', '내부 정비비', 30000);

INSERT INTO RepairShop (RPID, RPName, RPAddress, RPPhone, RPMngName, RpMngEmail) VALUES
(1, '서울강남캠핑정비소', '서울시 강남구', '02-111-1234', '김영호', 'kimyh@repair.com'),
(2, '부산캠핑카테크', '부산시 해운대구', '051-222-2345', '이은지', 'lee.ej@repair.com'),
(3, '대구오토서비스', '대구시 수성구', '053-333-3456', '정하윤', 'junghy@repair.com'),
(4, '광주캠카정비센터', '광주시 북구', '062-444-4567', '한기훈', 'hanki@repair.com'),
(5, '대전정비마스터', '대전시 유성구', '042-555-5678', '서민재', 'seomj@repair.com'),
(6, '울산차량케어존', '울산시 남구', '052-666-6789', '박도윤', 'parkdy@repair.com'),
(7, '수원캠카정비소', '수원시 장안구', '031-777-7890', '최보람', 'choiboram@repair.com'),
(8, '강릉오토정비', '강릉시', '033-888-8901', '윤지호', 'yoonjh@repair.com'),
(9, '전주차량정비센터', '전주시 완산구', '063-999-9012', '오세진', 'ohsj@repair.com'),
(10, '창원정비플러스', '창원시 성산구', '055-101-1123', '배지현', 'baejh@repair.com'),
(11, '청주캠핑정비소', '청주시 흥덕구', '043-202-2234', '문현우', 'moonhw@repair.com'),
(12, '제주오토서비스', '제주시', '064-303-3345', '황선우', 'hwangsw@repair.com');


INSERT INTO CustRepair (
    CustRepairID, CarID, RPID, CompanyID, LicenseNum, RepairDetails, RepairDate, RepairPrice, PaymentDate, etcDetails
) VALUES
(1, 101, 1, 1, '12-123456', '브레이크 패드 교체', '2025-04-03', 120000, '2025-04-04', '디스크 연마'),
(2, 102, 2, 2, '13-654321', '오일 누유 수리', '2025-04-06', 180000, '2025-04-07', NULL),
(3, 103, 3, 3, '22-876543', '타이어 교체', '2025-04-09', 200000, '2025-04-10', '휠 얼라인먼트 포함'),
(4, 104, 4, 4, '11-345678', '에어컨 필터 정비', '2025-04-13', 60000, '2025-04-14', NULL),
(5, 105, 5, 5, '21-234567', '배터리 교체', '2025-04-19', 150000, '2025-04-20', NULL),
(6, 106, 6, 6, '33-345123', '냉각수 보충 및 라디에이터 점검', '2025-04-23', 70000, '2025-04-24', '서모스탯 점검 포함'),
(7, 107, 7, 7, '24-654789', '엔진 점검 및 오일 교환', '2025-04-26', 95000, '2025-04-27', NULL),
(8, 108, 8, 8, '14-888888', '하체 소음 정비', '2025-05-02', 170000, '2025-05-03', NULL),
(9, 109, 9, 9, '19-456321', '전조등 교체', '2025-05-04', 55000, '2025-05-05', NULL),
(10, 110, 10, 10, '26-998877', '변속기 오일 교환', '2025-05-09', 80000, '2025-05-10', '오일 필터 포함'),
(11, 111, 11, 11, '17-111222', '차량 전체 점검', '2025-05-13', 250000, '2025-05-14', '엔진룸 클리닝'),
(12, 112, 12, 12, '31-333444', '후방카메라 수리', '2025-05-17', 110000, '2025-05-18', NULL);




