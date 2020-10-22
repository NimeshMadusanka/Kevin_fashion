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
 * Servlet implementation class EditSupplierServlet
 */
@WebServlet("/EditSupplierServlet")
public class EditSupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name, telephone, address;
		int id;

		if (!request.getParameter("id").equals("")) {
			
			id = Integer.parseInt(request.getParameter("id"));

			name = request.getParameter("name");
			telephone = request.getParameter("telephone");
			address = request.getParameter("address");

			Supplier supplier = SupplierService.addSupplier(name, telephone, address);

			String validationResult = SupplierService.validateSupplierEdit(supplier);

			if (validationResult.equals("Supplier Validated"))
				request.setAttribute("result", SupplierService.updateSupplier(id, supplier));
			else
				request.setAttribute("result", validationResult);

			request.getRequestDispatcher("supplier.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("result", "Please Select a Supplier");
			request.getRequestDispatcher("supplier.jsp").forward(request, response);
			
		}

	}

}
