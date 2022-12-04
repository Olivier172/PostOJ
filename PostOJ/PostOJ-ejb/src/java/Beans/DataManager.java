/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import EntityBeans.*;
import java.io.PrintStream;
import java.math.*;
import java.time.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.String;

import javax.ejb.Stateless;
import javax.validation.*;
/**
 * @author Olivier en Jorn
 * OPGEPAST alle methodes moeten ook gedeclareerd worden in de remote interface!
 * deze is te vinden in PostOJ-JavaLibrary/Source Packages/Beans/DataManagerRemote.java
 * 
 * Opmerking:
 * Bij het toevoegen van een pakket moeten de werknemers en adressen al in de databank zitten
 * is dit niet zo dan moeten deze eerst aangemaakt worden voor dat addPakket() word opgeroepen
 */
@Stateless
public class DataManager implements DataManagerRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext private EntityManager em;
    @Override
    public List getWerknemers(){
        List result = em.createQuery("select w from Werknemers w").getResultList();
        return result;
    }
    
    @Override
    public List getKoeriers(){
        List result = em.createQuery("select w from Werknemers w where w.functie = ?1").setParameter(1, "koerier").getResultList();
        return result;
    }
    
    
    @Override
    public List getBediendes(){
        List result = em.createQuery("select w from Werknemers w where w.functie = ?1").setParameter(1, "bediende").getResultList();
        return result;
    }
    
    @Override
    public List getAdressen(){
        List result = em.createQuery("select a from Adres a").getResultList();
        return result;
    }
    
    //adres id opvragen als je de naam van het adres weet
    @Override
    public int getAdresAid(String naam) {
        List result = em.createQuery("select a from Adres a where a.naam = ?1").setParameter(1, naam).getResultList();
        try{
            Adres a = (Adres) result.get(0); // alleen eerste match nodig
            int aid = a.getAid();
            return aid;     
        }
        catch (Exception e) {
            return -1; //niet gevonden
        } 
    }
    
    //geeft alle pakketten terug
    @Override
    public List getPakketten(){
        List result = em.createQuery("select p from Pakket p").getResultList();
        return result;
    }
    
 
    //geeft alle details van een pakket terug in een list<String> object
    /* Dit bevat in volgorde:
    *   status
    *   datum
    *   tijd
    *   gewicht
    *   commentaar
    *   aid
    *   naam    
    *   straatennr 
    *   postcode
    *   gemeente
    *   wid
    *   wnaam
    *   functie
    */
    //@param pid: de pakket identifier van welk pakket je de info wilt
    @Override
    public List<String> getPakketDetails(int pid) {
        List<String> result= new ArrayList<String>();
        Pakket p = em.find(Pakket.class,pid);
        Adres a= p.getPaid();
        Werknemers w = p.getPwid();
        /*pakket info toevoegen aan lijst*/
        result.add(p.getStatus());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        result.add(df.format(p.getDatum()));
        df = new SimpleDateFormat("HH:mm:ss");
        result.add(df.format(p.getTijd()));
        result.add(p.getGewicht().toString());
        result.add(p.getCommentaar());
        /*adres info toevoegen aan lijst*/
        result.add(a.getAid().toString());
        result.add(a.getNaam());
        result.add(a.getStraatennr());
        result.add(a.getPostcode().toString());
        result.add(a.getGemeente());
        /*werknemer(koerier) infotoevoegen aan lijst*/
        result.add(w.getWid().toString());
        result.add(w.getWnaam());
        result.add(w.getFunctie());
        
        return result;
    }
    
    //geef de pakketten van een specifieke koerier terug in een lijst
    //@param wid: werknemer id van de koerier vanwie je de toegewezen pakketten wilt hebben
    @Override
    public List getPakkettenKoerier(int wid) {
        List result;
        Werknemers w= em.find(Werknemers.class,wid); //vind de juiste werknemer uit de databank en steek dit in een werknemer objectje
        result = em.createQuery("select p from Pakket p where p.pwid = ?1").setParameter(1, w).getResultList();
        return result;
    }
    
    
    /*
    create table Werknemers(
        wid int primary key,    ->Integer wid
        wnaam varchar(40),      ->String wnaam
        functie varchar(40)     ->String functie
    );
    */
    
    @Override
    public String getPakketStatus(int pid) {
        String status;
        try{
            Pakket p = em.find(Pakket.class,pid);
            status = p.getStatus();
        }
        catch (Exception e) {
            status = "pakket niet gevonden";
        }
        return status;
        
    }
       
    @Override
    public void updatePakketStatus(int pid, String status) {
        Pakket p = em.find(Pakket.class,pid);
        if( !(p.getStatus().equals("geleverd")) ){//enkel status wijzigen als het nog niet geleverd was
            p.setStatus(status);
        }        
    }
    
    public void updatePakket(int pid, int wid, int gewicht, String commentaar, String naam, String straatennr, int postcode, String gemeente) {
        Pakket p = em.find(Pakket.class,pid);
        Werknemers w= em.find(Werknemers.class,wid);
        Adres a = p.getPaid();
        /*pakket info updaten*/
        p.setGewicht(gewicht);
        p.setCommentaar(commentaar);
        p.setPwid(w); //nieuwe koerier toewijzen
        /*adres info updaten*/
        a.setNaam(naam);
        a.setStraatennr(straatennr);
        a.setPostcode(postcode);
        a.setGemeente(gemeente);
    }
    
    @Override
    public void addWerknemers(String functie, String wnaam){
        Werknemers w = new Werknemers();
        Integer result = (Integer) em.createQuery("select max(w.wid) from Werknemers w").getSingleResult();
        if(result == null) {
            result = 0;
        }
        w.setWnaam(wnaam);
        w.setWid(result+1);
        w.setFunctie(functie);
        em.persist(w); //opslaan in databank
    }
    
    /*
    create table Adres(
        aid int primary key,    ->Integer aid
        naam varchar(100),       ->String naam
        straatennr varchar(100), ->String straatennr
        postcode int,           ->Integer postcode
        gemeente varchar(100)    ->String gemeente
    );
    */
    @Override
    public int addAdres(String naam, String straatennr, Integer postcode, String gemeente){
        Adres a = new Adres();
        Integer result = (Integer) em.createQuery("select max(a.aid) from Adres a").getSingleResult();
        if(result == null) {
            result = 0;
        }
        a.setAid(new Integer(result+1));
        a.setNaam(naam);
        a.setStraatennr(straatennr);
        a.setPostcode(postcode);
        a.setGemeente(gemeente);
        em.persist(a); //opslaan in databank
        return a.getAid(); //geef adres identifier terug
    }
    
    /*
    create table Pakket(
        pid int primary key,            ->Integer pid
        status varchar(20),             ->String status
        datum date,                     ->Date datum
        tijd time,                      ->Date tijd
        commentaar varchar(500),        ->String Commentaar
        paid int  references Adres,     ->Adres aid
        pwid int  references Werknemers ->Werknemers pwid
    );
    */
    @Override
    public void addPakket(String commentaar, int gewicht,int paid, int pwid){
        Pakket p = new Pakket();
        Integer result = (Integer) em.createQuery("select max(p.pid) from Pakket p").getSingleResult();
        if(result == null) {
            result = 0;
        }
        p.setPid(result+1);
        p.setStatus("transit");
        Date date = new Date(); 
        p.setDatum(date);
        p.setTijd(date);
        p.setCommentaar(commentaar);
        p.setGewicht(gewicht);
        Adres adres= em.find(Adres.class,paid); //vind het juiste adres in de databank en steek dit in een adres objectje
        p.setPaid(adres); //geef adres objectje mee als foreign key verwijzing
        Werknemers werknemer= em.find(Werknemers.class,pwid); //vind de juiste werknemer uit de databank en steek dit in een werknemer objectje
        p.setPwid(werknemer); //geef werknemer objectje mee als foreign key verwijzing
        //System.out.println("commentaar " + p.getCommentaar() + " paid " + p.getPaid() + " pwid " + p.getPwid() + " pid " + p.getPid() + " status " + p.getStatus() + " datum " + p.getDatum() + " tijd " + p.getTijd() );
        em.persist(p); //opslaan in databank
    }
}


   
