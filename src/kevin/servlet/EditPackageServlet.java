package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.PackageService;

/**
 * Servlet implementation class EditPackageServlet
 */
@WebServlet("/EditPackageServlet")
public class EditPackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = -1;
		String name;
		double discountPercentage = -1, price = -1;
		
		if (!request.getParameter("id").equals(""))
			id = Integer.parseInt(request.getParameter("id"));
		if (!request.getParameter("percentage").equals(""))
			discountPercentage = Double.parseDouble(request.getParameter("percentage"));
		name = request.getParameter("name");
		if (!request.getParameter("price").equals(""))
			price = Double.parseDouble(request.getParameter("price"));
		
		kevin.model.Package pack = PackageService.setPackage(name, discountPercentage, price);
		String validationResult = PackageService.validateEditPackage(pack);
		
		if (validationResult.equals("Package Validated"))
			request.setAttribute("result", PackageService.updatePackage(id, pack));
		else
			request.setAttribute("result", validationResult);
		
		request.getRequestDispatcher("package.jsp").forward(request, response);
		
	}

}
