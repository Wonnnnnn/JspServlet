package chap7;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginform")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO dao;
    private ServletContext ctx;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new UserDAO();
        ctx = getServletContext();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        String view = null;
        System.out.println("action: " + action);

        if (action == null) {
            ctx.getRequestDispatcher("/loginform").forward(req, resp);
        } else {
            switch (action) {
                case "addUser":
                    view = addUser(req);
                    break;
                case "delUser":
                    view = delUser(req);
                    break;
                case "login":
                    try {
                        view = login(req);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
            if (view.startsWith("redirect:/")) {
                view = view.substring("redirect:/".length());
                resp.sendRedirect(view);
            } else {
                ctx.getRequestDispatcher("/chap7/" + view).forward(req, resp);
            }
        }
    }

    private String delUser(HttpServletRequest req) {
        String id = req.getParameter("id");
        dao.delUser(Integer.parseInt(id));
        return id;
    }

    private String addUser(HttpServletRequest req) {
        String redirectPage = "";
        return redirectPage;
    }

    public String login(HttpServletRequest req) throws Exception {
        String redirectPage = "";
        int uid = Integer.parseInt(req.getParameter("uid"));
        String userid = req.getParameter("id");
        String password = req.getParameter("pw");
        User user = dao.getuser(uid);

        redirectPage = "/chap7/loginFailure.jsp";
        if (user != null) {
            if (user.getUid() == uid && user.getPw().equals(password)) {
                redirectPage = "/chap7/loginSuccess.jsp";
            }
        }
        return redirectPage;
    }
}
