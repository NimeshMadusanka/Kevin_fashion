package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.model.Supplier;
import kevin.service.SupplierService;

/**
 * Servlet implementation class DeleteSupplierServlet
 */
@WebServlet("/DeleteSupplierServlet")
public class DeleteSupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!request.getParameter("id").equals("")) {

			int id = Integer.parseInt(request.getParameter("id"));

			request.setAttribute("result", SupplierService.deleteSupplier(id));
			request.getRequestDispatcher("supplier.jsp").forward(request, response);

		} else {

			request.setAttribute("result", "Please select a Supplier");
			request.getRequestDispatcher("supplier.jsp").forward(request, response);

		}

	}

}
