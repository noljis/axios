package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.domain.DeptDTO;
import model.domain.EmpDTO;
import model.domain.EmpOneDTO;
import util.DBUtil;

public class CompanyDAO {
		
	//직원 한명 정보
	public static EmpOneDTO getEmp(int empno) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		EmpOneDTO data = null;
		try {
			con = DBUtil.getConnection();
		
			pstmt = con.prepareStatement("select empno, ename, NVL(job,'미정') job, mgr, NVL(TO_CHAR(hiredate, 'YYYY/MM/DD'), '9999/12/31') hiredate, sal, comm, e.deptno, dname, loc from emp e, dept d where e.deptno=d.deptno and empno=?");
			pstmt.setInt(1, empno);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				data = new EmpOneDTO(rset.getInt(1), rset.getString(2), rset.getString(3),
									rset.getInt(4), rset.getString(5), rset.getDouble(6), 
									rset.getDouble(7), rset.getInt(8), rset.getString(9), 
									rset.getString(10));
				
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return data;
	}
	//해보고 싶은것: Like 연산자 사용해서 부분일치에 체크하면 해당 직원들 정보가 나오는 것
	public static ArrayList<EmpOneDTO> getLikeEmp(int no) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<EmpOneDTO> datas = null;
		try {
			con = DBUtil.getConnection();
		
			pstmt = con.prepareStatement("select empno, ename, NVL(job,'미정') job, mgr, NVL(TO_CHAR(hiredate, 'YYYY/MM/DD'), '9999/12/31') hiredate, sal, comm, e.deptno, dname, loc from emp e, dept d where e.deptno=d.deptno and empno like ?");
			pstmt.setString(1, "%"+no+"%");
			rset = pstmt.executeQuery();
			datas = new ArrayList<>();
			while(rset.next()) {
				datas.add(new EmpOneDTO(rset.getInt(1), rset.getString(2), rset.getString(3),
									rset.getInt(4), rset.getString(5), rset.getDouble(6), 
									rset.getDouble(7), rset.getInt(8), rset.getString(9), 
									rset.getString(10)));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return datas;
	}
	
	//모든 직원들 정보
	public static ArrayList<EmpDTO> getEmpAll() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<EmpDTO> datas = null;
		try {
			con = DBUtil.getConnection();
			//**날짜 null : 문자열로 바꾸고 NVL 함수 써줌
			pstmt = con.prepareStatement("select empno, ename, NVL(job,'미정') job, mgr, NVL(TO_CHAR(hiredate, 'YYYY/MM/DD'), '9999/12/31') hiredate, sal, comm, deptno from emp");
			rset = pstmt.executeQuery();
			
			datas = new ArrayList<EmpDTO>();

			while(rset.next()) {
				datas.add(new EmpDTO(rset.getInt(1), rset.getString(2), rset.getString(3),
						rset.getInt(4), rset.getString(5), rset.getDouble(6), 
						rset.getDouble(7), rset.getInt(8)));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return datas;
	}
	
	//모든 부서 정보
	public static ArrayList<DeptDTO> getDeptAll() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<DeptDTO> datas = null;
		try {
			con = DBUtil.getConnection();
		
			pstmt = con.prepareStatement("select * from dept");
			rset = pstmt.executeQuery();
			
			datas = new ArrayList<DeptDTO>();
			
			while(rset.next()) {
				datas.add(new DeptDTO(rset.getInt(1), rset.getString(2), rset.getString(3)));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return datas;
	}
	
}
