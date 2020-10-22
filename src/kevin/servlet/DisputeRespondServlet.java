package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.DisputeService;

/**
 * Servlet implementation class DisputeRespondServlet
 */
@WebServlet("/DisputeRespondServlet")
public class DisputeRespondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("ind"));
		String res = request.getParameter("val");
		
		request.setAttribute("result", DisputeService.updateDispute(DisputeService.setDispute(null, null, null, res), id));
		request.getRequestDispatcher("admindispute.jsp").forward(request, response);
		
	}

}
