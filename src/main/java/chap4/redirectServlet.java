package chap4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redirect")
public class redirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/chap4/redirect.jsp";
        req.setAttribute("shareInfo","공유하려고 하는 정보입니다.");
        resp.sendRedirect(path);
    }

}
