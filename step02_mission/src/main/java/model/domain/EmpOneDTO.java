package model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// empno, ename, job, mgr, hiredate, sal, comm, e.deptno, dname, loc
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmpOneDTO {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private double sal;
	private double comm;
	private int deptno;
	private String dname;
	private String loc;	
}
