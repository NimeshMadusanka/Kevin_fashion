package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.TrackService;

/**
 * Servlet implementation class DeleteTrackServlet
 */
@WebServlet("/DeleteTrackServlet")
public class DeleteTrackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id;
		
		if (!request.getParameter("id").equals("")) {
			
			id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("result", TrackService.deleteTrack(id));
			
		} else {
			
			request.setAttribute("result", "Please Select a Tracker to Delete");
			
		}
		
		request.getRequestDispatcher("track.jsp").forward(request, response);
		
	}

}
