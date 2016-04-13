
package com.mycompany.mavenproject2;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pontus
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet {

    ArrayList<Activity> activities = new ArrayList<>();
    int activityID = 0;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String json = new Gson().toJson(activities);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // If POST is sent from "Add activity"
        if (request.getParameter("description") != null) {

            String description = request.getParameter("description");
            String duedate = request.getParameter("duedate");
            String[] checked = request.getParameterValues("done");
            
            activityID++;

            Activity activity = new Activity(description, duedate, checked == null ? "NOT DONE" : "DONE", activityID);

            activities.add(activity);

            //response.sendRedirect(request.getContextPath());

        //  If POST is sent from delete button 
        } else if (request.getParameter("delete") != null) {

            String activityId = request.getParameter("delete");
            int activityToDelete = Integer.parseInt(activityId);

            deleteActivity(activityToDelete);
            
            response.sendRedirect("http://localhost:8080/mavenproject2/");

        //  If POST is sent from edit form    
        } else if (request.getParameter("editDescription") != null) {

            String description = request.getParameter("editDescription");
            String duedate = request.getParameter("editDuedate");
            String[] checked = request.getParameterValues("editDone");
            String id_String = request.getParameter("editID");

            int id = Integer.parseInt(id_String);

            updateActivity(id, description, duedate, checked == null ? "NOT DONE" : "DONE");
            response.sendRedirect("http://localhost:8080/mavenproject2/");
        }
    }

    //  EDIT ACTIVITY
    private void updateActivity(int activityToUppdate, String description, String duedate, String done) {

        for (Activity act : activities) {
            if (act.getId() == (activityToUppdate)) {
                act.setDescription(description);
                act.setDuedate(duedate);
                act.setDone(done);
            }
        }
    }

    //  DELETE ACTIVITY
    private void deleteActivity(int activityToDelete) {

        for (Iterator<Activity> it = activities.iterator(); it.hasNext();) {
            Activity act = it.next();
            if (act.getId() == (activityToDelete)) {
                it.remove();
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>

}
