package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.CompanyDAO;
import model.domain.DeptDTO;
import model.domain.EmpDTO;
import model.domain.EmpOneDTO;

@WebServlet("/company")
public class CompanyController extends HttpServlet {
	
	//4개의 요청
	//모든 직원 정보 검색, 모든 부서 정보 검색, 한 직원의 정보 검색
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getParameter("command");
		try {
			if(command.equals("emps")) {//모든 직원 정보 검색 처리
				allEmp(request, response);
			}else if(command.equals("depts")) {//모든 부서 정보 검색 처리
				allDept(request, response);
			}else if(command.equals("empOne")) { //직원 한명의 정보 검색 처리 
				oneEmp(request, response);
			}else if(command.equals("empLike")) { //부분검색
				oneLikeEmp(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	//모든 직원 정보 검색 
	public void allEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String jsp = "step08_error.jsp";
		try {
			ArrayList<EmpDTO> elist = CompanyDAO.getEmpAll();
			JSONObject emp = null;
			JSONArray emps = new JSONArray();
			for(int i = 0; i < elist.size(); i++) {
				emp = new JSONObject();
				emp.put("사원번호", elist.get(i).getEmpno()); 
				emp.put("사원이름", elist.get(i).getEname()); 
				emp.put("직무", elist.get(i).getJob()); 
				emp.put("mgr", elist.get(i).getMgr()); 
				emp.put("입사일", elist.get(i).getHiredate()); 
				emp.put("급여", elist.get(i).getSal()); 
				emp.put("커미션", elist.get(i).getComm()); 
				emp.put("부서번호", elist.get(i).getDeptno()); 
				//System.out.println(emp);
				emps.put(emp);
			}
			request.setAttribute("data", emps);
			jsp = "step08_res.jsp";
		}catch(SQLException s) {
			s.printStackTrace();
			request.setAttribute("errorMsg", "내부적인 오류로 리스트를 불러오지 못했습니다.");
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	//모든 부서 정보 검색
	public void allDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String jsp = "step08_error.jsp";
		try {
			ArrayList<DeptDTO> dlist = CompanyDAO.getDeptAll();
			JSONObject dept = null; //얘를 JSON으로 하지말고 일반배열로?
			JSONArray depts = new JSONArray();
			for(int i = 0; i < dlist.size(); i++) {
				dept = new JSONObject();
				dept.put("부서번호", dlist.get(i).getDeptno()); 
				dept.put("부서명", dlist.get(i).getDname());
				dept.put("부서위치", dlist.get(i).getLoc());
				depts.put(dept);
			}
//			System.out.println(depts);
			request.setAttribute("data", depts);
			jsp = "step08_res.jsp";
		}catch(SQLException s) {
			s.printStackTrace();
			request.setAttribute("errorMsg", "내부적인 오류로 리스트를 불러오지 못했습니다.");
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}
	
	//직원 한명 검색
	public void oneEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String jsp = "step08_error.jsp";
		try {
			String no = request.getParameter("no");
			System.out.println(no);
			EmpOneDTO empone = CompanyDAO.getEmp(Integer.parseInt(no));
			JSONObject emp = new JSONObject();
			emp.put("사원번호", empone.getEmpno()); 
			emp.put("사원명", empone.getEname()); 
			emp.put("직무", empone.getJob()); 
			emp.put("mgr", empone.getMgr()); 
			emp.put("입사일", empone.getHiredate()); 
			emp.put("급여", empone.getSal()); 
			emp.put("커미션", empone.getComm()); 
			emp.put("부서번호", empone.getDeptno()); 
			emp.put("부서명", empone.getDname()); 
			emp.put("부서위치", empone.getLoc()); 
			System.out.println(emp);
			//한명 검색..... JSONArray안쓰니까 대괄호
			request.setAttribute("data", "["+emp+"]");
			jsp = "step08_res.jsp";
		}catch(SQLException s) {
			s.printStackTrace();
			request.setAttribute("errorMsg", "내부적인 오류로 검색하지 못했습니다.");
		}catch(NumberFormatException ne) {
			ne.printStackTrace();
			//예외처리를..화면을 넘겨야 할까? 
			request.setAttribute("errorMsg", "숫자를 입력하세요.");
		}catch(NullPointerException ne) {
			ne.printStackTrace();
			request.setAttribute("errorMsg", "검색된 회원이 없습니다.");
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}
	
	//직원번호 부분일치 검색
	public void oneLikeEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String jsp = "step08_error.jsp";
		try {
			String no = request.getParameter("no");
			ArrayList<EmpOneDTO> empone = CompanyDAO.getLikeEmp(Integer.parseInt(no));
			//배열이 비어있으면 예외던지기
			if(empone.isEmpty()) { 
				throw new NullPointerException();
			}
			JSONObject emp = null;
			JSONArray emps = new JSONArray();
			for(int i = 0; i < empone.size(); i++) {
				emp = new JSONObject();
				emp.put("사원번호", empone.get(i).getEmpno()); 
				emp.put("사원명", empone.get(i).getEname()); 
				emp.put("직무", empone.get(i).getJob()); 
				emp.put("mgr", empone.get(i).getMgr()); 
				emp.put("입사일", empone.get(i).getHiredate()); 
				emp.put("급여", empone.get(i).getSal()); 
				emp.put("커미션", empone.get(i).getComm()); 
				emp.put("부서번호", empone.get(i).getDeptno()); 
				emp.put("부서명", empone.get(i).getDname()); 
				emp.put("부서위치", empone.get(i).getLoc()); 
				emps.put(emp);
			}
			request.setAttribute("data", emps);
			jsp = "step08_res.jsp";
		}catch(SQLException s) {
			request.setAttribute("errorMsg", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
		}catch(NumberFormatException ne) {
			ne.printStackTrace();
			request.setAttribute("errorMsg", "숫자를 입력하세요.");
		}catch(NullPointerException ne) { //JSONArray때문에 object로 넘어감..
			ne.printStackTrace();
			request.setAttribute("errorMsg", "검색된 회원이 없습니다.");
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}
	
}
