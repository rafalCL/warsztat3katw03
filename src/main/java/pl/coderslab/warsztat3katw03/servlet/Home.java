package pl.coderslab.warsztat3katw03.servlet;

import pl.coderslab.warsztat3katw03.dao.UserDao;
import pl.coderslab.warsztat3katw03.db.DbUtil;
import pl.coderslab.warsztat3katw03.model.Exercise;
import pl.coderslab.warsztat3katw03.model.Solution;
import pl.coderslab.warsztat3katw03.model.SolutionToDisplay;
import pl.coderslab.warsztat3katw03.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numSol = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        List<Solution> solutions = Solution.findRecent(numSol);
        List<SolutionToDisplay> solutionList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for(Solution sol : solutions){
            Exercise ex = Exercise.findById(sol.getExercisesId());
            String title = ex.getTitle();
            User u = User.findById(sol.getUsersId());
            String authorName = u.getUsername();
            String dateStr = formatter.format(sol.getUpdated());

            solutionList.add(new SolutionToDisplay(title, authorName, dateStr));
        }
        req.setAttribute("solutionList", solutionList);
        getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp")
                .forward(req, resp);
    }
}
