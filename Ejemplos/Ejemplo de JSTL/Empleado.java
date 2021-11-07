package es.carlosgs.introjee.jsp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	private long empno;
	private String ename;
	private String job;
	private long mgr;
	private Date hiredate;
	private BigDecimal sal;
	private BigDecimal comm;
	private long deptno;

	public Empleado() {
	}

}
