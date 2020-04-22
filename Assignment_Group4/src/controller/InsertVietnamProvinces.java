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

import dao.VietNamProvinceDao;
import model.VietNamProvinces;

/**
 * Servlet implementation class VietnamProvinces
 */
@WebServlet("/province/insertAllProvinces")
public class InsertVietnamProvinces extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertVietnamProvinces() {
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
		VietNamProvinceDao vnD = new VietNamProvinceDao();
		vnD.insertProvincesStatistic();
		doGet(request, response);
	}

}
