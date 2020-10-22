package kevin.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kevin.service.ItemService;

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/EditItemServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class EditItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "uploaded";
	private static final String IMG_PATH = "uploaded/";
	private static final String FILE_SERVER = "C:\\Users\\zenj8\\eclipse-workspace\\KEVIN\\WebContent\\uploaded";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;

		Part filePart = request.getPart("image");
		String fileName = extractFileName(filePart);
		String imagePath = "", name = "", description = "", category = null, color = null, size = null;
		int id = 0, quantity = -1;
		double price = -1;
		

		if (!fileName.equals("")) {
			
			fileName = new File(fileName).getName();
			filePart.write(savePath + File.separator + fileName);
			filePart.write(FILE_SERVER + File.separator + fileName);
			imagePath = IMG_PATH + fileName;
			
		}
		
		if (!request.getParameter("id").equals(""))
			id = Integer.parseInt(request.getParameter("id"));
		if (!request.getParameter("quantity").equals(""))
			quantity = Integer.parseInt(request.getParameter("quantity"));
		if (!request.getParameter("price").equals(""))
			price = Double.parseDouble(request.getParameter("price"));
		if (!request.getParameter("name").equals(""))
			name = request.getParameter("name");
		if (!request.getParameter("description").equals(""))
			description = request.getParameter("description");
		if (request.getParameter("category") != null && !request.getParameter("category").equals(""));
			category = request.getParameter("category");
		if (request.getParameter("color") != null && !request.getParameter("color").equals(""))
			color = request.getParameter("color");
		if (request.getParameter("size") != null && !request.getParameter("size").equals(""))
			size = request.getParameter("size");

		request.setAttribute("result", ItemService.updateItem(ItemService.setItem(id, name, price, category, color, size, quantity, description, imagePath)));
		request.getRequestDispatcher("item.jsp").forward(request, response);

	}

	private String extractFileName(Part filePart) {

		String contentDisp = filePart.getHeader("content-disposition");
		String[] items = contentDisp.split(";");

		for (String s : items) {

			if (s.trim().startsWith("filename"))
				return s.substring(s.indexOf("=") + 2, s.length() - 1);

		}

		return "";

	}

}
