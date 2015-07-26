import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


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

        Gson gson = new Gson();
        JsonObject controlObj = new JsonObject();

        JQueryControl myControl = getInfo(String crimeType);

        JsonElement control = gson.toJsonTree(myControl);


        try {
            controlObj.addProperty("success", true);
        } catch (Exception e) {
            controlObj.addProperty("success", false);
        }
        
        controlObj.add("crimeData", myControl);
        out.println(controlObj.toString());
        out.close();
    }

    //private Class getInfo()

    private JQueryControl getInfo(String crime) {
        JQueryControl control = new JQueryControl(crime);

        //double rate = control.calculateProbability(crime);

        return control;
    }


}
