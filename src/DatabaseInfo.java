import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Jiakuan on 7/23/15.
 */
public class DatabaseInfo extends HttpServlet {
    public DatabaseInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String crimeType = request.getParameter("crime");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");

        JsonObject controlObj = new JsonObject();

        JsonElement control = getRate(crimeType);

        try {
            controlObj.addProperty("success", true);
        } catch (Exception e) {
            controlObj.addProperty("success", false);
        }
        // TODO:
        // controlObj.add("crime probability", );
        out.println(controlObj.toString());
        out.close();
    }

    private double getRate(String crime) {
        JQueryControl control = new JQueryControl();

        double rate = control.calculateProbability(crime);

        return rate;
    }


}
