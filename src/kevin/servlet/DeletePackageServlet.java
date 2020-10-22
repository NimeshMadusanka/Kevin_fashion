package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.PackageService;

/**
 * Servlet implementation class DeletePackageServlet
 */
@WebServlet("/DeletePackageServlet")
public class DeletePackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = -1;
		
		if (!request.getParameter("id").equals(""))
			id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("result", PackageService.deletePackage(id));
		request.getRequestDispatcher("package.jsp").forward(request, response);
		
	}

}
