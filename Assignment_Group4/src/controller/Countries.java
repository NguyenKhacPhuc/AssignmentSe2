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

import dao.CountryDao;
import model.Country;

/**
 * Servlet implementation class Countries
 */
@WebServlet("/country")
public class Countries extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Countries() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			CountryDao c = new CountryDao();
			ArrayList<Country> cLst = c.selectAllCountry();
				String country = this.gson .toJson(cLst);
				PrintWriter out = response.getWriter();
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        out.print(country);
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
		 String country = request.getParameter("country");
		 double newConfirmed = Double.parseDouble(request.getParameter("newconfirmed"));
		 double totalConfirmed= Double.parseDouble(request.getParameter("totalconfirmed"));
		 double newDeaths = Double.parseDouble(request.getParameter("newdeaths"));
		 double totalDeaths = Double.parseDouble(request.getParameter("totaldeaths"));
		 double newRecovered = Double.parseDouble(request.getParameter("newrecovered"));
		 double totalRecovered = Double.parseDouble(request.getParameter("totalrecovered"));
		 String  date = request.getParameter("date");
		 String countryCode = request.getParameter("countrycode");
		 Country c = new Country(country, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered, date, countryCode);
		 try {
				CountryDao cD = new CountryDao();
				if(cD.checkExist(c.getCountryCode())){
					cD.updateSpecificCountry(c);
				}
			} catch (IOException | SQLException e) {
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
		String country = request.getParameter("country");
		 double newConfirmed = Double.parseDouble(request.getParameter("newconfirmed"));
		 double totalConfirmed= Double.parseDouble(request.getParameter("totalconfirmed"));
		 double newDeaths = Double.parseDouble(request.getParameter("newdeaths"));
		 double totalDeaths = Double.parseDouble(request.getParameter("totaldeaths"));
		 double newRecovered = Double.parseDouble(request.getParameter("newrecovered"));
		 double totalRecovered = Double.parseDouble(request.getParameter("totalrecovered"));
		 String  date = request.getParameter("date");
		 String countryCode = request.getParameter("countrycode");
		 Country c = new Country(country, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered, date, countryCode);
		 try {
			CountryDao cD = new CountryDao();
			if(!(cD.checkExist(c.getCountryCode()))){
				cD.insertACountry(c);
			}
		} catch (IOException | SQLException e) {
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
		String cD = request.getParameter("countrycode");
		CountryDao coD;
		try {
			coD = new CountryDao();
			coD.deleteCountry(cD);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
