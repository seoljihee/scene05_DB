package ex01.dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ex01.dbcommon.DBCommon;
import ex01.dto.MemDTO;

public class DBServiceImpl implements DBService{
	//DB연결 객체 생성
	
	PreparedStatement ps;
	ResultSet rs;
	
	

	@Override
	public int insertMember(MemDTO dto) {
		System.out.println("디비에 회원가입 합니다.");
		String sql = "insert into fx_test values(?,?,?)";
		int result = 0;
		try {
		ps = DBCommon.con.prepareStatement(sql);
		ps.setString(1, dto.getId());
		ps.setString(2, dto.getPwd());
		ps.setString(3, dto.getName());
		//select 를 제외한 나머지 명령어는 executeUpdate를 사용한다.
		//executeUpdate는 return값으로 성공1, 실패0 또는 exception을 발생
		result = ps.executeUpdate();  //return으로 돌려주는 값이 int형태이다.
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MemDTO loginCheck(String userId) {
		System.out.println("로그인 체크합니다.");
		String sql = "select * from fx_test where id=?";
		
		MemDTO dto = null;
		try {
			ps = DBCommon.con.prepareStatement(sql);
			ps.setString(1, userId);
			//select 는 executeQuery로 처리한다.
			//성공시 데이터를 가져온다, 실패시 예외처리로 이동한다.
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto = new MemDTO();
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

}
