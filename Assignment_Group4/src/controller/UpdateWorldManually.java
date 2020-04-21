package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.WorldGeneralDao;
import model.WorldGeneral;

/**
 * Servlet implementation class UpdateWorldManually
 */
@WebServlet("/worldgeneral/updateworldmanually")
public class UpdateWorldManually extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateWorldManually() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try {
			
			WorldGeneralDao wd = new WorldGeneralDao();
			WorldGeneral worldC = wd .getCurrentWorldGeneral();
			String world = this.gson .toJson(worldC);
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(world);
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
		String date = request.getParameter("date");
		int newConfirmed = Integer.parseInt(request.getParameter("newconfirmed"));
		int totalConfirmed = Integer.parseInt(request.getParameter("totalconfirmed"));
		int newDeaths = Integer.parseInt(request.getParameter("newdeaths"));
		int totalDeaths = Integer.parseInt(request.getParameter("totaldeaths"));
		int newRecovered = Integer.parseInt(request.getParameter("newrecovered"));
		int totalRecovered = Integer.parseInt(request.getParameter("totalrecovered"));
		WorldGeneral w = new WorldGeneral(date, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered);
		WorldGeneralDao wd = new WorldGeneralDao();
		try {
			wd.updateWorldGeneralManually(w);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
