package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.OrderService;

/**
 * Servlet implementation class AcceptOrderServlet
 */
@WebServlet("/AcceptOrderServlet")
public class AcceptOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		double cost = Double.parseDouble(request.getParameter("cost"));
		
		if (OrderService.getOrder(id).isAccepted()) {
			 
			 request.setAttribute("result", "Order is Aready Accepted");
			 
		 } else {
			 
			 if (OrderService.updateOrder(OrderService.setOrder("", "", -1, "", false, cost, true, null), id).equals("Order Updated"))
				 request.setAttribute("result", "Order Accepted");
			 
		 }
		 
		 request.getRequestDispatcher("adminorder.jsp").forward(request, response);
		
	}

}
