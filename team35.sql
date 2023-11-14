CREATE DATABASE tsherpa;

USE tsherpa;

CREATE TABLE role(
                     role_id INT PRIMARY KEY AUTO_INCREMENT,
                     role VARCHAR(255) DEFAULT NULL
);

INSERT INTO ROLE VALUES(DEFAULT, 'ADMIN');
INSERT INTO ROLE VALUES(DEFAULT, 'EMP');
INSERT INTO ROLE VALUES(DEFAULT, 'TEACHER');
INSERT INTO ROLE VALUES(99, 'USER');

CREATE TABLE user(
                     user_id INT PRIMARY KEY AUTO_INCREMENT,			-- 회원 번호 : 자동증가
                     active INT DEFAULT 1, 									-- 회원 상태 [ 0 : 탈퇴, 1 : 활동중, 2: 활동 정지]
                     login_id VARCHAR(255) NOT NULL,						-- 회원 로그인 아이디
                     user_name VARCHAR(255) NOT NULL,						-- 회원 이름
                     password VARCHAR(300) NOT NULL,						-- 회원 비밀번호
                     email VARCHAR(50) NOT NULL,							-- 회원 이메일
                     tel VARCHAR(20) NOT NULL,								-- 회원 전화번호
                     addr1 VARCHAR(200),										-- 회원 기본 주소
                     addr2 VARCHAR(100),										-- 회원 상세 주소
                     postcode VARCHAR(10),									-- 회원 우편 번호
                     reg_date DATETIME DEFAULT CURRENT_TIMESTAMP(),	-- 회원 가입일
                     birth DATE,													-- 회원 생일
                     pt INT DEFAULT 0,											-- 회원 포인트
                     visited INT DEFAULT 0,									-- 회원 방문 횟수
                     role_id INT NOT NULL DEFAULT 99						-- 회원 권한 등급
);

CREATE VIEW userList AS(
                       SELECT u.user_id AS user_id, u.active AS ACTIVE, u.login_id AS login_id, u.user_name AS user_name, u.password AS PASSWORD, u.role_id AS role_id, r.role AS roleNm
                       FROM user u
                                LEFT JOIN role r ON u.role_id = r.role_id
                           );


CREATE TABLE market(
                       market_no INT AUTO_INCREMENT PRIMARY KEY,	-- 상품 번호
                       title VARCHAR(100) NOT NULL,	-- 제목
                       price int NOT NULL,		-- 가격
                       content VARCHAR(5000) NOT null,	-- 설명
                       login_id VARCHAR(255) NOT null,	-- 작성자 id
                       active int NOT NULL DEFAULT 0,	-- 거래 상태(거래 완료 여부)
                       conditions int NOT NULL,	-- 상품 상태(최상 상 중 하)
                       regdate DATETIME DEFAULT CURRENT_TIMESTAMP	-- 등록일
);

CREATE TABLE request(
                        req_no INT AUTO_INCREMENT PRIMARY KEY,	-- 상품 번호
                        title VARCHAR(100) NOT NULL,	-- 제목
                        price int NOT NULL,		-- 가격
                        content VARCHAR(5000),	-- 설명
                        login_id VARCHAR(255) NOT NULL,	-- 작성자 id
                        active varchar(20) NOT NULL,	-- 거래 상태(거래 완료 여부)
                        regdate DATETIME DEFAULT CURRENT_TIMESTAMP	-- 등록일
);

CREATE TABLE photos(
                       photo_no int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       market_no INT,
                       saveFolder VARCHAR(300) NOT NULL,
                       originFile VARCHAR(300) NOT NULL,
                       saveFile VARCHAR(300) NOT NULL
);