package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.OrderService;

/**
 * Servlet implementation class EditOrderServlet
 */
@WebServlet("/EditOrderServlet")
public class EditOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		if (OrderService.getOrder(id).isAccepted()) {
			 
			 request.setAttribute("result", "Cannot Update Order. It\'s Alredy Accepted. Please Contact the Owner.");
			 
		 } else {
			 
			 request.setAttribute("result", OrderService.updateOrder(OrderService.setOrder("", "", quantity, "", true, -1, true, null), id));
			 
		 }
		 
		 request.getRequestDispatcher("order.jsp").forward(request, response);
		
	}

}
