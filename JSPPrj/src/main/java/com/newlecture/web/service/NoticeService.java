package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public class NoticeService {
	public int removeNotAll(int[] ids){ // 넘겨받은 배열을 통해 삭제 -> 몇 개 삭제됐는지 반환
		return 0;
	}
	
	public int pubNoticeAll(int[] ids){
		return 0;
	}
	
	public int insertNotice(Notice notice) {
		return 0;
	}


	public int deleteNotice(int id){
		return 0;
	}
	
	public int updateNotice(Notice notice) {
		return 0;
	}
	
	List<Notice> getNoticeNewestList(){
		return null;
	}
	
	public List<NoticeView> getNoticeList() {

		return getNoticeList("title", "", 1);
	}

	public List<NoticeView> getNoticeList(int page) {

		return getNoticeList("title", "", page);
	}

	public List<NoticeView> getNoticeList(String field, String query, int page) {
		List<NoticeView> list = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/jsp";

		String sql = "SELECT * FROM (" + "    SELECT ROW_NUMBER() OVER(ORDER BY ID DESC) AS ROWNUM, "
				+ "    NOTICE_VIEW.* FROM NOTICE_VIEW WHERE " + field + " LIKE ?) TMP" + "    WHERE ROWNUM BETWEEN ? AND ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, "%" + query + "%");
			st.setInt(2, 1 + (page - 1) * 10);
			st.setInt(3, page * 10);

			ResultSet rs = st.executeQuery(); // 레코드 가져오기

			while (rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				//String content = rs.getString("CONTENT");
				int cntCount=rs.getInt("CNT_COUNT");			
				NoticeView notice = new NoticeView
						(id,
						title,
						writerId, 
						regdate,
						hit, 
						files, 
						//content,
						cntCount);
				list.add(notice);
			}

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int getNoticeCount() {
		return getNoticeCount("title", "");

	}

	public int getNoticeCount(String field, String query) {

		int count = 0;

		String sql = "SELECT COUNT(ID) COUNT FROM(" + "		SELECT	ROW_NUMBER() OVER(ORDER BY ID DESC) AS ROWNUM,"
				+ "    NOTICE.* FROM NOTICE WHERE " + field + " LIKE ?) TMP";
		/* + "	    WHERE ROWNUM BETWEEN 6 AND 10" */

		String url = "jdbc:mysql://localhost:3306/jsp";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, "%" + query + "%");

			ResultSet rs = st.executeQuery(); // 레코드 가져오기
			
			if(rs.next())
				count = rs.getInt("count");

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	public Notice getNotice(int id) {
		Notice notice = null;

		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		String url = "jdbc:mysql://localhost:3306/jsp";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); // 레코드 가져오기

			if (rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				notice = new Notice(nid, title, writerId, regdate, hit, files, content);

			}

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return notice;
	}

	public Notice getNextNotice(int id) {
		Notice notice = null;

		String sql = "SELECT ID FROM NOTICE WHERE ID > ? ORDER BY REGDATE ASC LIMIT 1";
		String url = "jdbc:mysql://localhost:3306/jsp";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); // 레코드 가져오기

			if (rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				notice = new Notice(nid, title, writerId, regdate, hit, files, content);

			}

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return notice;
	}

	public Notice getPrevNotice(int id) {
		Notice notice = null;

		String sql = "SELECT ID FROM NOTICE WHERE ID < ? ORDER BY REGDATE DESC LIMIT 1";
		String url = "jdbc:mysql://localhost:3306/jsp";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); // 레코드 가져오기

			if (rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				notice = new Notice(nid, title, writerId, regdate, hit, files, content);

			}

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return notice;
	}

}
