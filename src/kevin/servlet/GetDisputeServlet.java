package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kevin.model.Dispute;
import kevin.model.Order;
import kevin.service.DisputeService;
import kevin.service.OrderService;

/**
 * Servlet implementation class GetDisputeServlet
 */
@WebServlet("/GetDisputeServlet")
public class GetDisputeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("disputeId"));
		Dispute dispute = DisputeService.getDispute(id);
		String json = new Gson().toJson(dispute);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

}
