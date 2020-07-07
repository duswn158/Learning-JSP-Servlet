DROP SEQUENCE BOARDNOSEQ;
DROP SEQUENCE GROUPNOSEQ;
DROP TABLE ANSWERBOARD;

CREATE SEQUENCE BOARDNOSEQ;
CREATE SEQUENCE GROUPNOSEQ;

-- 글 번호, 그룹번호, 그룹순서, 제목탭, 삭제여부(N,Y)
-- 제목, 내용, 작성자, 작성일
CREATE TABLE ANSWERBOARD(
	BOARDNO NUMBER NOT NULL,
	GROUPNO NUMBER NOT NULL,
	GROUPSEQ NUMBER NOT NULL,
	TITLETAB NUMBER NOT NULL,
	DELFLAG VARCHAR2(2) NOT NULL,
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WRITER VARCHAR2(500) NOT NULL,
	REGDATE DATE NOT NULL,
	
	-- ANSWER_PK_BOARDNO 라는 이름의 제약조건으로 BOARDNO를 기본키로 설정
	CONSTRAINT ANSWER_PK_BOARDNO PRIMARY KEY(BOARDNO),
	CONSTRAINT ANSWER_CK_DELFLAG CHECK (DELFLAG IN ('Y', 'N'))	
);

SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, DELFLAG, TITLE, CONTENT, WRITER, REGDATE
FROM ANSWERBOARD;