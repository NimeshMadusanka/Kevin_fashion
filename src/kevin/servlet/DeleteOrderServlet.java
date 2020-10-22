package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.service.OrderService;

/**
 * Servlet implementation class DeleteOrderServlet
 */
@WebServlet("/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String url = "order.jsp";

		if (!request.getParameter("id").equals("") || request.getParameter("id") != null) {

			int id = Integer.parseInt(request.getParameter("id"));

			if (session.getAttribute("admin") != null) {

				url = "adminorder.jsp";
				request.setAttribute("result", OrderService.deleteOrder(id));

			} else {

				if (OrderService.getOrder(id).isAccepted()) {

					request.setAttribute("result",
							"Cannot Delete Order. It is Alredy Accepted. Please Contact the Owner.");

				} else {

					request.setAttribute("result", OrderService.deleteOrder(id));

				}

			}

		} else {

			request.setAttribute("result", "Please Select An Order");

		}

		request.getRequestDispatcher(url).forward(request, response);

	}

}
