package kevin.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kevin.model.User;
import kevin.service.DisputeService;

/**
 * Servlet implementation class DisputeServlet
 */
@WebServlet("/DisputeServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class DisputeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "disputes";
	private static final String IMG_PATH = "disputes/";
	private static final String FILE_SERVER = "C:\\Users\\zenj8\\eclipse-workspace\\KEVIN\\WebContent\\disputes";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		File backupSaveDir = new File(FILE_SERVER);
		if (!backupSaveDir.exists()) {
			backupSaveDir.mkdir();
		}

		Part filePart = request.getPart("image");
		String fileName = extractFileName(filePart);
		fileName = new File(fileName).getName();
		filePart.write(savePath + File.separator + fileName);
		filePart.write(FILE_SERVER + File.separator + fileName);

		String description = request.getParameter("description");
		String imagePath = IMG_PATH + fileName;

		request.setAttribute("result",
				DisputeService.saveDispute(DisputeService.setDispute(user.getEmail(), description, imagePath, null)));
		request.getRequestDispatcher("dispute.jsp").forward(request, response);

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
