package kr.bit.model;

import java.sql.*;
import java.util.ArrayList;

// JDBC - > myBati, JPA
public class MemberDAO {
	 private Connection conn;
	 private PreparedStatement ps;
	 private ResultSet rs;
	 
	 
	 //데이터베이스 연결객체 생성(Connection)
	 public void getConnect() {
		 String URL="jdbc:mysql://localhost/narp?characterEncoding=UTF-8&serverTimezone=UTC";
		 String user="narp";
		 String password="qwe3776";
		 
		 try {
			 //동적 로딩 (실행시 점에서 객체를 생산하는 방법)
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(URL,user,password); // Driver를 관리하는 
		} catch (Exception e) {
			//ClassNotFoundException - > Exception (모든 클래스의 오류를 다 찾으려면 부담이 된다.-> 부모가 예외를 다 잡도록 한다)
			e.printStackTrace();
		}
	 }
	 
	 public int memberInsert(MemberVO vo) {
		 // 
		 String sql="insert into member(id,pass,name,age,email,phone)"+
				 	" values(?,?,?,?,?,?)";
		 getConnect();
		 int cnt =-1;
		 // SQL 문장을 전송하는 객체 생성
		 try {
			ps=conn.prepareStatement(sql);//미리 컴파일을 시킨다.(속도 빠르게)
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPass());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getAge());
			ps.setString(5, vo.getEmail());
			ps.setString(6, vo.getPhone());
			
			//성공 여부 0,1
			cnt=ps.executeUpdate();//DB에 전송(실행)
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		 return cnt;
	 }
	 
	 //회원전체(VO) 리스트 (ArrayList) 가져오기
	 public ArrayList<MemberVO> memberList() {
		 String sql="select * from member";
		 getConnect();
		 
		 ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		 try {
			 ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			 while(rs.next()) {
				 int num=rs.getInt("num");
				 String id=rs.getString("id");
				 String pass=rs.getString("pass");
				 String name=rs.getString("name");
				 int age=rs.getInt("age");
				 String email=rs.getString("email");
				 String phone=rs.getString("phone");
				 
				 //묶고  - > 담고
				 MemberVO vo = new MemberVO(num, id, pass, name, age, email, phone);
				 list.add(vo);
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
			 }finally {
				 dbClose();
			 }
		 return list;
		 }
	 
	 
	 public int memberDelete(int num) {
		 String sql="delete" + 
		 		" from member" + 
		 		" where num=?";
		 
		 getConnect();
		 int cnt=0;
		 
		 try {
			 ps=conn.prepareStatement(sql);
			 ps.setInt(1, num);
			cnt= ps.executeUpdate(); 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
			  dbClose();
		 }
		 return cnt;
	 }
	 
	 
	 //데이터베이스 연결 끊기
	 public void dbClose() {
		try {
			 if(rs!=null) rs.close();
			 if(ps!=null) ps.close();
			 if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		 }
	}
