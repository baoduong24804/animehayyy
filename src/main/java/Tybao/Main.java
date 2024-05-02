package Tybao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.Film;

/**
 * Servlet implementation class Main
 */
@WebServlet("/home")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String link = "https://phim.nguonc.com/api/films/phim-moi-cap-nhat?";// option = 1 (page=[int])
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Object page = request.getParameter("page");
		

		//System.out.println(page);
		List<Film> list = null;
		if(page != null) {
			list = GetAPI.getHomePage(GetAPI.toJsonFromURL(link, 1, "page="+page,"slug=thua-hoan-ky"));
		}else {
			page = "1";
			list = GetAPI.getHomePage(GetAPI.toJsonFromURL(link, 1, "page="+page,"slug=thua-hoan-ky"));
		}

		
		request.setAttribute("list", list);
		request.getRequestDispatcher("\\tybao\\index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
