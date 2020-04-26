package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.WorldGeneralDao;
import model.WorldGeneral;

/**
 * Servlet implementation class World
 */
@WebServlet("/world")
public class World extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public World() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			WorldGeneralDao wG = new WorldGeneralDao();
			WorldGeneral w = wG.getCurrentWorldGeneral();
			String world = this.gson  .toJson(w);
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(world);
	        out.flush();
		} catch (IOException | SQLException e) {
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

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LocalDateTime now = LocalDateTime.now();
		String date = now.toString();
		int newConfirmed = Integer.parseInt(request.getParameter("newconfirmed"));
		int totalConfirmed = Integer.parseInt(request.getParameter("totalconfirmed"));
		int newDeaths = Integer.parseInt(request.getParameter("newdeaths"));
		int totalDeaths = Integer.parseInt(request.getParameter("totaldeaths"));
		int newRecovered = Integer.parseInt(request.getParameter("newrecovered"));
		int totalRecovered = Integer.parseInt(request.getParameter("totalrecovered"));
		WorldGeneralDao wD = new WorldGeneralDao();
		try {
			wD.insertWorldGeneralManually(date, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
