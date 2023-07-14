package com.newlecture.web.entity;

import java.sql.Date;

public class NoticeView extends Notice {

	private int cntCount;

	public NoticeView() {
		// TODO Auto-generated constructor stub
	}

	public NoticeView(int id, String title, String writerId, Date regdate, String hit, String files, boolean pub,
			int cntCount) {
		super(id, title, writerId, regdate, hit, files, "", pub);
		this.cntCount = cntCount;
	}

	public int getCntCount() {
		return cntCount;
	}

	public void setCntCount(int cntCount) {
		this.cntCount = cntCount;
	}

}
