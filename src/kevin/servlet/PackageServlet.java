package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.PackageService;

/**
 * Servlet implementation class PackageServlet
 */
@WebServlet("/PackageServlet")
public class PackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name;
		double discountPercentage, price;
		
		discountPercentage = Double.parseDouble(request.getParameter("percentage"));
		name = request.getParameter("name");
		price = Double.parseDouble(request.getParameter("price"));
		
		kevin.model.Package pack = PackageService.setPackage(name, discountPercentage, price);
		String validationResult = PackageService.validatePackage(pack);
		
		if (validationResult.equals("Package Validated"))
			request.setAttribute("result", PackageService.savePackage(pack));
		else
			request.setAttribute("result", validationResult);
		
		request.getRequestDispatcher("package.jsp").forward(request, response);
		
	}

}
