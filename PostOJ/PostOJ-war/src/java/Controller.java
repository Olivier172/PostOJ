/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Beans.DataManagerRemote;
import EntityBeans.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ejb.*;

/**
 *
 * @author r0723037
 */
@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB private DataManagerRemote dm;
    
    public void init() {
        List werknemers = dm.getWerknemers(); //hebben we al werknemers?
        if(werknemers.isEmpty()) {
            /* Aanmaken van enkele werknemers bij PostOJ*/
            dm.addWerknemers("koerier", "jos");
            dm.addWerknemers("koerier", "mark");
            dm.addWerknemers("koerier", "filip");
            dm.addWerknemers("koerier", "jan");
            dm.addWerknemers("koerier", "bob");
            dm.addWerknemers("bediende", "marie");
            dm.addWerknemers("bediende", "josephine");
            dm.addWerknemers("bediende", "joeri");
            dm.addWerknemers("bediende", "Olivier");
            dm.addWerknemers("bediende", "Jorn");
        }
        List pakketten = dm.getPakketten();//hebben we al op zijn minst 1 test pakketje?
        if(pakketten.isEmpty()) {
            if(dm.getAdresAid("campus Denayer") == -1) { //is het adres van denayer er al?
                int aid=dm.addAdres("campus Denayer", "Jan Pieter de Nayerlaan 5",2860, "Sint-Katelijne-Waver");
            }
            int aid = dm.getAdresAid("campus Denayer");//opvragen van het specifieke adres van campus denayer dat normaal gezien al toegevoegd is
            werknemers = dm.getKoeriers();//koeriers opvragen
            Werknemers w = (Werknemers)werknemers.get(0); //eerste werknemer krijgt het test pakketje naar denayer
            int wid = w.getWid();
            //System.out.println("aid " + aid + " wid " + wid + "\n\n");
            dm.addPakket("test pakketje dat altijd in overzicht aanwezig is", 10,aid, wid);
        }
        
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String submitValue = request.getParameter("forms");
        String actie = request.getParameter("actie"); //moeten we iets doen met de databank?
        String nw = request.getParameter("naarWaar"); //naar welke pagina moeten we
        //als de laatste twee niet zijn ingevuld, geven we ze toch een string waarde
        //zodat de if testen hierna geen nullpointerexception tegenkomen:
        
        if(submitValue == null) {
            submitValue=new String("niet ingevuld door form");
        }
        if(actie == null) {
            actie=new String("niet ingevuld door form, we moeten dus niets doen met de databank");
        }
        if(nw == null) {
            nw=new String("niet ingevuld door form");
        }
        
        System.out.println("naarwaar = " + nw);
        System.out.println("actie = " + actie);
        System.out.println("submitValue = " + submitValue);
        /*checken op een actie dat we moeten doen met de databank*/
        if(actie.equals("nieuwpakket")) { //normaal komen we van register.jsp en is het form goed ingevuld...
                /*aanmaken van het adres*/
                String naam = request.getParameter("naam");
                String naamennr = request.getParameter("adres");
                int postcode = Integer.parseInt( request.getParameter("postcode") );
                String gemeente = request.getParameter("gemeente");
                int aid=dm.addAdres(naam, naamennr,postcode, gemeente);
                /*aanmaken van het pakket*/
                String commentaar = request.getParameter("commentaar");
                int pwid = Integer.parseInt(request.getParameter("kourierKeuze")); 
                int gewicht =Integer.parseInt(request.getParameter("gewicht"));
                dm.addPakket(commentaar, gewicht, aid, pwid);
        }
        
        /*Bepalen naar welke pagina moet doorverwezen worden*/
        if(nw.equals("tracking")){
            HttpSession sessie = request.getSession();
            sessie.setAttribute("packetID", request.getParameter("packetID"));
            RequestDispatcher view = request.getRequestDispatcher("tracking.jsp");
            view.forward(request, response);
        }
        else if(nw.equals("index")){
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        }
        else if(nw.equals("bOverzicht")){
            List pakketjes = dm.getPakketten();
            session.setAttribute("pakketjes", pakketjes);
            RequestDispatcher view = request.getRequestDispatcher("bOverzicht.jsp");
            view.forward(request, response);
        }
        else if(nw.equals("register")){
            List koeriers = dm.getKoeriers();
            session.setAttribute("koeriers", koeriers);
            RequestDispatcher view = request.getRequestDispatcher("register.jsp");
            view.forward(request, response);
        }
        else if(nw.equals("bDetails")) {
            int pid = Integer.parseInt(submitValue);//pakketnr in de submit knop, met sessie meegeven
            session.setAttribute("pid",pid); 
            //alle info over dit pakket meegeven via de session:
            List<String> pd =  dm.getPakketDetails(pid);
            //pakketinfo
            String status = pd.get(0); session.setAttribute("status",status); 
            String datum = pd.get(1); session.setAttribute("datum",datum);
            String tijd = pd.get(2); session.setAttribute("tijd",tijd);
            String gewicht = pd.get(3); session.setAttribute("gewicht",gewicht);
            String commentaar = pd.get(4); session.setAttribute("commentaar",commentaar);
            //adresinfo
            String aid = pd.get(5); session.setAttribute("aid",aid);
            String naam = pd.get(6); session.setAttribute("naam",naam);
            String straatennr = pd.get(7); session.setAttribute("straatennr",straatennr);
            String postcode = pd.get(8); session.setAttribute("postcode",postcode);
            String gemeente = pd.get(9); session.setAttribute("gemeente",gemeente);
            //werknemerinfo (koerier) 
            String wid = pd.get(10); session.setAttribute("wid",wid);
            String wnaam = pd.get(11); session.setAttribute("wnaam",wnaam);
            String wfunctie = pd.get(12); session.setAttribute("wfunctie",wfunctie);
            
            RequestDispatcher view = request.getRequestDispatcher("bDetails.jsp");
            view.forward(request, response); 
        }
        else if(nw.equals("kOverzicht")){
            RequestDispatcher view = request.getRequestDispatcher("kOverzicht.jsp");
            view.forward(request, response);
        }
        
        else if(nw.equals("kDetails")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            session.setAttribute("pid",pid); //pakketnr in de submit knop, met sessie meegeven
            RequestDispatcher view = request.getRequestDispatcher("kDetails.jsp");
            view.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
