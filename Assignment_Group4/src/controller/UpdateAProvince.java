package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.VietNamProvinceDao;
import model.VietNamProvinces;

/**
 * Servlet implementation class UpdateAProvince
 */
@WebServlet("/province/updateaprovince")
public class UpdateAProvince extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAProvince() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VietNamProvinceDao vnD = new VietNamProvinceDao();
		ArrayList<VietNamProvinces> vnpLst;
		try {
			vnpLst = vnD.selectAllProvinces();
			String vnPStatistic = this.gson  .toJson(vnpLst);
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(vnPStatistic);
	        out.flush(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LocalDateTime now = LocalDateTime.now();
		String date = now.toString();
		String name = request.getParameter("name");
		double confirmed = Double.parseDouble(request.getParameter("confirmed"));
		double underTreatment = Double.parseDouble(request.getParameter("undertreatment"));
		double recovered = Double.parseDouble(request.getParameter("recovered"));
		double deaths = Double.parseDouble(request.getParameter("deaths"));
		VietNamProvinceDao vnd = new VietNamProvinceDao();
		VietNamProvinces vnDao = new VietNamProvinces(name, confirmed, underTreatment, recovered, deaths, date);
		try {
			vnd.updateAProvince(vnDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
