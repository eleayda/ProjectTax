package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Developer;
import model.TaxExpert;
import model.TaxObject;

/**
 * Servlet implementation class CalcTax
 */
@WebServlet(urlPatterns = { "/CalcTax" }, initParams = {

		// Schablonbelopp utdelning i fåmansföretag TAX2015
		@WebInitParam(name = "divident_max", value = "150150"),
		// Skatt på utdelningen i fåmansföretag TAX2015
		@WebInitParam(name = "tax_divident", value = "0.2"),
		//  Arbetsgivareavgift under 26 år
		@WebInitParam(name = "tax_employer_min", value = "0.1549"),
		// Arbetsgivareavgift över 26 år
		@WebInitParam(name = "tax_employer_max", value = "0.3142"),
		// Bolagsskatt TAX2015
		@WebInitParam(name = "tax_company", value = "0.22"),
		// Kommunalskatt
		@WebInitParam(name = "tax_local", value = "0.30"),
		// Statlig skatt under översta skicktgräns
		@WebInitParam(name = "tax_state_low", value = "0.20"),
		// Statlig skatt over översta skickgräns
		@WebInitParam(name = "tax_state_up", value = "0.25"),
		// nedre skickgräns TAX2015
		@WebInitParam(name = "limit_low", value = "420800"),
		// övre skicktgräns TAX2015
		@WebInitParam(name = "limit_up", value = "602600") })
public class CalcTax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//-----------get params------------------------------<
		String salory = request.getParameter("salory"); // lön
		String saloryPer = request.getParameter("saloryPer"); // lönetyp

		String favor = request.getParameter("favor"); // förmån
		String favorPer = request.getParameter("favorPer"); // förmån period

		String price = request.getParameter("price"); // timpis för tjänst

		String office = request.getParameter("office"); // lokalhyra
		String officePer = request.getParameter("officePer"); // betalperiod

		String prog = request.getParameter("prog");// programvaror
		String progPer = request.getParameter("progPer");// period

		String car = request.getParameter("car");// bil
		String carPer = request.getParameter("carPer");// period

		String extra = request.getParameter("extra"); // övriga kostnader
		String extraPer = request.getParameter("extraPer");// period

		String age = request.getParameter("age"); // ålder

		
//-----send params to model classes----------------<
		TaxExpert taxExpert=new TaxExpert();
		setInitParam(taxExpert);
		
		TaxObject taxObject=new TaxObject();
		
		taxObject.addIncWorker(salory,saloryPer);
		taxObject.addIncWorker(favor, favorPer);
		
		taxObject.addIncCompany(price);
	
		taxObject.addCostCompany(office, officePer);
		taxObject.addCostCompany(prog, progPer);
		taxObject.addCostCompany(car,carPer);
		taxObject.addCostCompany(extra, extraPer);
		
		
		

		
//------calculate netto incomes--------------------
		int nettoIncomeCompany=(int) taxExpert.calcNettoIncomeCompany(taxObject, age);
		int nettoIncomeWorker=(int) taxExpert.calcNettoIncomeWorker(taxObject);

	

//--------session and cookies options--------------	
		
		request.getSession().setMaxInactiveInterval(60*60*24*7);
		Cookie cookie =new Cookie("age",age);
		response.addCookie(cookie);
		cookie.setMaxAge(request.getSession().getMaxInactiveInterval());
		
//---------dispatch----------------------
		
		ServletContext servletContext=(ServletContext) this.getServletConfig().getServletContext();
		synchronized(servletContext){
			
			Developer developer=(Developer)servletContext.getAttribute("developer");
			request.setAttribute("developer", developer);
			request.getSession().setAttribute("developer", developer);
		}
		
		
		request.setAttribute("nettoIncCompany", nettoIncomeCompany);
		request.setAttribute("nettoIncWorker", nettoIncomeWorker);
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("ViewFile.jsp");
		requestDispatcher.forward(request, response);


	}
/**
 * This method gets servlet context init param and set them as instans variabels at
 * @param  TaxExpert object
 */

	public void setInitParam(TaxExpert te) {

		double tax_employer_min = Double.parseDouble(this.getServletConfig()
				.getInitParameter("tax_employer_min"));
		te.setTaxEmployer_min(tax_employer_min);
		
		double tax_divident = Double.parseDouble(this.getServletConfig()
				.getInitParameter("tax_divident"));
		te.setTax_divident(tax_divident);
		
		double divident_max = Double.parseDouble(this.getServletConfig()
				.getInitParameter("divident_max"));
		te.setDivident_max(divident_max);
		
		double tax_employer_max = Double.parseDouble(this.getServletConfig()
				.getInitParameter("tax_employer_max"));
		te.setTax_employer_max(tax_employer_max);
		
		double tax_company = Double.parseDouble(this.getServletConfig()
				.getInitParameter("tax_company"));
		te.setTax_company(tax_company);
		
		double tax_local = Double.parseDouble(this.getServletConfig()
				.getInitParameter("tax_local"));
		te.setTax_local(tax_local);
		
		double tax_state_low = Double.parseDouble(this.getServletConfig()
				.getInitParameter("tax_state_low"));
		te.setTax_state_low(tax_state_low);
		
		double tax_state_up = Double.parseDouble(this.getServletConfig()
				.getInitParameter("tax_state_up"));
		te.setTax_state_up(tax_state_up);
		
		double limit_low = Double.parseDouble(this.getServletConfig()
				.getInitParameter("limit_low"));
		te.setLimit_low(limit_low);
		
		double limit_up = Double.parseDouble(this.getServletConfig()
				.getInitParameter("limit_up"));
		te.setLimit_up(limit_up);
	}

}
