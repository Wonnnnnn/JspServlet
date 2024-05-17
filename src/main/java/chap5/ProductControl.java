package chap5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pcontrol")
public class ProductControl extends HttpServlet {
    ProductService service;

    public ProductControl() {
//        service = new ProductService();
    }

    public void init() throws ServletException {
        service = new ProductService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String view ="";

        if(action == null) {
            getServletContext().getRequestDispatcher("/pcontrol?action=list").forward(req, resp);
        }
        else {
            switch (action) {
                case "list":
                    List<Product> all = service.findAll();
                    view = "productlist.jsp";
                    req.setAttribute("products", all);
                    break;
                case "info":
                    req.getParameter("id");
                    Product p = service.find(req.getParameter("id"));
                    req.setAttribute("products", p);
                    view = "productinfo.jsp";
                    break;
            }
        }

        getServletContext().getRequestDispatcher("/chap5/"+view).forward(req, resp);
    }

}
