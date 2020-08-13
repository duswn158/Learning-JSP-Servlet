package com.muldel.dto;

import java.util.Date;

public class MDBoardDto {

	// 컬럼 한줄을 담을것
	private int seq;
	private String writer;
	private String title;
	private String content;
	// sql이 아니라 java.utill로 사용하는 이유
	private Date regdate;
	
	public MDBoardDto(){
		
	}
	
	public MDBoardDto(int seq, String writer, String title, String content, Date regdate){
		
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}
	
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
