package chap5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calControl")
public class CalcControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int n1 = Integer.parseInt(req.getParameter("n1"));
        int n2 = Integer.parseInt(req.getParameter("n2"));
        String op = req.getParameter("op");
//        String result = req.getParameter("n1")+req.getParameter("op")+req.getParameter("n2");
        Calculator c = new Calculator();
        c.setN1(n1);
        c.setN2(n2);
        c.setOp(op);

        long result = c.calc();

        req.setAttribute("result", result);
        req.setAttribute("resultEL",result);

        getServletContext().getNamedDispatcher("/chap5/calcResult.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
