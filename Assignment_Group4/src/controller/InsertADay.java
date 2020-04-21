package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.EachDayDao;
import model.EachDay;

/**
 * Servlet implementation class InsertADay
 */
@WebServlet("/eachday/insertavnday")
public class InsertADay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertADay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EachDayDao e = new EachDayDao();
		ArrayList<EachDay> elst;
		try {
			elst = e.getAllDays();
			String days = this.gson  .toJson(elst);
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(days);
	        out.flush(); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String year = request.getParameter("year");
		String day = request.getParameter("day");
		String month = request.getParameter("month");
		String date = String.join("/", year,month,day);
		date = date.concat("T00:00:00Z");
		double cases = Double.parseDouble(request.getParameter("cases"));
		double recovered = Double.parseDouble(request.getParameter("recovered"));
		double deaths = Double.parseDouble(request.getParameter("deaths"));
		
		EachDay e = new EachDay(date, cases, recovered, deaths);
		EachDayDao eaD = new EachDayDao();
		try {
			eaD.insertAday(e);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		doGet(request, response);
	}

}
