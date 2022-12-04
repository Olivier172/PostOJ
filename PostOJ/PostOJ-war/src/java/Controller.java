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
 * @author  Olivier en Jorn
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
            dm.addWerknemers("koerier", "Jos");
            dm.addWerknemers("koerier", "Mark");
            dm.addWerknemers("koerier", "Filip");
            dm.addWerknemers("koerier", "Jan");
            dm.addWerknemers("koerier", "Bob");
            dm.addWerknemers("bediende", "Marie");
            dm.addWerknemers("bediende", "Josephine");
            dm.addWerknemers("bediende", "Joeri");
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
            int postcode;
            int gewicht;
            try {
               postcode = Integer.parseInt( request.getParameter("postcode") );
               gewicht =Integer.parseInt(request.getParameter("gewicht"));
            }
            catch(Exception e) {
                //als niet ingevuld, op nul zetten...
                postcode= 0;
                gewicht = 0;
            }
            String gemeente = request.getParameter("gemeente");
            int aid=dm.addAdres(naam, naamennr,postcode, gemeente);
            /*aanmaken van het pakket*/
            String commentaar = request.getParameter("commentaar");
            int pwid = Integer.parseInt(request.getParameter("kourierKeuze")); 
            dm.addPakket(commentaar, gewicht, aid, pwid);
        }
        if(actie.equals("updatepakket")) {
            //hier moet nog code komen om info over een pakket te updaten als de bediendes wijzigingen hebben aangebracht
            int pid = (int) session.getAttribute("pid");
            int wid;
             //als checkbox gecheckt ,dan is de waarde van de parameter wijzigKoerier "on" en dus niet null
             //als de checkbox niet gecheckt dan is de waarde van de parameter wijzigKoerier null en willen we dus onze wid behouden
            if(request.getParameter("wijzigKoerier") != null) {
                wid = Integer.parseInt(request.getParameter("kourierKeuze")); //nieuwe koerier
            }
            else {
                wid = Integer.parseInt(request.getParameter("wid")); //behoud koerier
            }
            int gewicht;
            int postcode;
            try {
               gewicht= Integer.parseInt(request.getParameter("gewicht")); 
               postcode= Integer.parseInt(request.getParameter("postcode")); 
            }
            catch(Exception e) {
                //als niet ingevuld , 0 invullen...
                gewicht=0;
                postcode=0;
            }
            String commentaar = request.getParameter("commentaar");
            String naam = request.getParameter("naam");
            String straatennr = request.getParameter("straatennr");
            String gemeente = request.getParameter("gemeente");
            dm.updatePakket(pid, wid, gewicht, commentaar, naam, straatennr, postcode, gemeente);
        }
        if(actie.equals("updatestatus")) {
            String nieuwestatus = submitValue; //geleverd of probleem
            int pid = (int)session.getAttribute("pid"); //pid terug ophalen uit sessie
            dm.updatePakketStatus(pid, nieuwestatus);
        }
        
        /*Bepalen naar welke pagina moet doorverwezen worden*/
        if(nw.equals("tracking")){
            int pid; 
            try {
                pid = Integer.parseInt(request.getParameter("pid"));
            }
            catch (Exception e){
                pid = 0;//init voor het geval dat het niet goed wordt ingegeven
            }
            session.setAttribute("pid", pid);
            String status = dm.getPakketStatus(pid);
            session.setAttribute("status", status);
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
            RequestDispatcher view = request.getRequestDispatcher("bediendes/bOverzicht.jsp");
            view.forward(request, response);
        }
        else if(nw.equals("register")){
            List koeriers = dm.getKoeriers();
            session.setAttribute("koeriers", koeriers);
            RequestDispatcher view = request.getRequestDispatcher("bediendes/register.jsp");
            view.forward(request, response);
        }
        else if(nw.equals("bDetails")) {
            int pid = Integer.parseInt(submitValue);//pakketnr in de submit knop, met sessie meegeven
            session.setAttribute("pid",pid); 
            //alle info over dit pakket meegeven via de session:
            List<String> pd =  dm.getPakketDetails(pid); //pd=pakket details lijst
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
            
            //koeriers meegeven voor eventueel een andere toe te wijzen aan dit pakket
            List koeriers = dm.getKoeriers();
            session.setAttribute("koeriers",koeriers);
            RequestDispatcher view = request.getRequestDispatcher("bediendes/bDetails.jsp");
            view.forward(request, response); 
        }
        else if(nw.equals("kOverzicht")){
            //AANDACHT Jorn:
            //momenteel is hier hard gecodeerd dat de eerste koerier inlogt en zijn pakketjes wilt zien.
            //hier moet je eigenlijk erachter komen welke koerier is ingelogd en wat zijn wid is zodat 
            //de pakketjes die aan deze koerier zijn toegewezen worden weergegeven in kOverzicht :)
            //misschien is het zo : Authentication auth = SecurityContextHolder.getContext().getAuthentication(); en dan op auth.getPrincipal(), maar ben niet zeker
            int wid = ((Werknemers)dm.getKoeriers().get(0)).getWid(); 
            List pakketjes_koerier = dm.getPakkettenKoerier(wid);
            session.setAttribute("pakketjes_koerier",pakketjes_koerier);
            session.setAttribute("wid",wid);
            RequestDispatcher view = request.getRequestDispatcher("koeriers/kOverzicht.jsp");
            view.forward(request, response);
        }
        else if(nw.equals("kDetails")) {
            int pid = Integer.parseInt(submitValue);//pakketnr in de submit knop, met sessie meegeven
            session.setAttribute("pid",pid); 
            //alle info over dit pakket meegeven via de session:
            List<String> pd =  dm.getPakketDetails(pid); //pd=pakket details lijst
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
            
            RequestDispatcher view = request.getRequestDispatcher("koeriers/kDetails.jsp");
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
