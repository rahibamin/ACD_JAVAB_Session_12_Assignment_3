package db.connection12;

import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

/**
 * Servlet implementation class addEmployee
 */
@WebServlet(description = "Adding New Employee", urlPatterns = { "/addEmployee" })
public class addEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Connection con = DBConnection.getDBInstance();
		DbUtility.useDB(con, "Employee Management");

		String Emp_Name=request.getParameter("Emp_Name");
		String Emp_Phone=request.getParameter("Emp_Phone");
		String Emp_Email=request.getParameter("Emp_Email");
		String Emp_Type=request.getParameter("Emp_Type");

		response.setContentType("text/html");

		String q = "INSERT INTO `Employee Management`.`Employee` (`Emp_Name`, `Emp_Phone`, `Emp_Email`, `Emp_Type`) values(Emp_Name,Emp_Phone,Emp_Email,Emp_Type);";

		if (DbUtility.executeUpdate(con, q)) {
			response.getWriter().append("<meta http-equiv='refresh' content='3;URL=empList'><p style='color:red;'>Employee saved successfully</p>");
		} 
		else {
			response.getWriter().append("<meta http-equiv='refresh' content='3;URL=EmpInsert.html'><p style='color:red;'>Employee saved unsuccessfully</p>");
		}
		doGet(request, response);

	}
}
