CREATE DATABASE estore;

USE estore;

SHOW TABLES;

SELECT * FROM board;

DROP TABLE board;

CREATE TABLE photos (
   photo_no INT AUTO_INCREMENT PRIMARY KEY,      	-- 번호 	
   product_no INT NOT NULL,
	saveFolder VARCHAR(300),	 						-- 상품 id
   photo_file VARCHAR(1000),		 						-- 난수화된 파일 이름
	realname VARCHAR(250),
	FOREIGN KEY(product_no) REFERENCES products(product_no) ON DELETE CASCADE 			 						-- 실제 파일 이름
); 

SELECT * FROM photos;
SELECT * FROM category;

CREATE TABLE category (
    category VARCHAR(50) PRIMARY KEY, 	/* 번호 */
    category_name VARCHAR(50) NOT NULL					/* 카테고리명 */
);

INSERT INTO category VALUES ('A', '국어');
INSERT INTO category VALUES ('B', '수학');
INSERT INTO category VALUES ('C', '영어');


SELECT * FROM products;

DROP TABLE products;

CREATE TABLE products (
   product_no INT AUTO_INCREMENT PRIMARY KEY,		/*상품 번호 */ 	
   title VARCHAR(100) NOT NULL,							/* 제목 */
   price int NOT NULL,										/* 가격 */
   description VARCHAR(5000),							  	/* 설명 */
	user_id INT NOT NULL,								 	/* 작성자 id */
   active varchar(20) NOT NULL,							/* 거래 상태(거래 완료 여부) */
   `condition` varchar(20) NOT NULL,					/* 상품 상태(최상 상 중 하) */
   regdate DATETIME DEFAULT CURRENT_TIMESTAMP,		/*  등록일 */
   addr1 VARCHAR(200),										-- 회원 기본 주소
	addr2 VARCHAR(100),										-- 회원 상세 주소
	postcode VARCHAR(10),	
   category VARCHAR(50)											/* 카테고리 */
);


