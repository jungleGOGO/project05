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
                        active INT NOT NULL DEFAULT 0 ,	-- 거래 상태(거래 완료 여부)
                        regdate DATETIME DEFAULT CURRENT_TIMESTAMP,	-- 등록일
                        addr VARCHAR(200) NOT NULL,
                        bookTitle VARCHAR(255) NOT NULL,
                        bookAuthor VARCHAR(255) NOT NULL,
                        publisher VARCHAR(255) NOT NULL,
                        bookImage VARCHAR(255) NOT NULL,
                        isbn VARCHAR(255) NOT NULL,
                        pubdate VARCHAR(255) NOT NULL,
                        discount VARCHAR(255) NOT NULL
);

CREATE TABLE photos(
                       photo_no int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       market_no INT,
                       saveFolder VARCHAR(300) NOT NULL,
                       originFile VARCHAR(300) NOT NULL,
                       saveFile VARCHAR(300) NOT NULL
);


INSERT INTO request (title, price, content, login_id, addr, bookTitle, bookAuthor, publisher, bookImage, isbn, pubdate, discount)
VALUES
('책 제목 1', 5000, '책 설명 1', 'user1', '주소 1', '도서 1', '저자 1', '출판사 1', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1234567890', '2022-01-01', '10'),
('책 제목 2', 7000, '책 설명 2', 'user2', '주소 2', '도서 2', '저자 2', '출판사 2', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '0987654321', '2022-02-01', '15'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20'),
('책 제목 3', 8000, '책 설명 3', 'user3', '주소 3', '도서 3', '저자 3', '출판사 3', 'https://shopping-phinf.pstatic.net/main_3248051/32480516321.20230927071045.jpg', '1357924680', '2022-03-01', '20')