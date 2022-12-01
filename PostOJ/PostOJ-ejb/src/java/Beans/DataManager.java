/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import EntityBeans.*;
import java.math.*;
import java.time.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.ejb.Stateless;
/**
 *
 * @author r0723037
 */
@Stateless
public class DataManager implements DataManagerRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext private EntityManager em;
    public List getWerknemers(){
        List result = em.createQuery("select w from Werknemers w").getResultList();
        return result;
    }
    public List getAdres(){
        List result = em.createQuery("select a from Adres a").getResultList();
        return result;
    }
    public List getPakket(){
        List result = em.createQuery("select p from Pakket p").getResultList();
        return result;
    }
    public void addWerknemers(String functie){
        Werknemers w = new Werknemers();
        Integer result = (Integer) em.createQuery("select max(w.wid) from Werknemers w").getSingleResult();
        if(result == null) {
            result = 0;
        }
        w.setWid(result.intValue()+1);
        w.setFunctie(functie);
        em.persist(w);
    }
    
    public void addAdres(String naam, String straatennr, Integer postcode, String gemeente){
        Adres a = new Adres();
        Integer result = (Integer) em.createQuery("select max(a.aid) from Adres a").getSingleResult();
        if(result == null) {
            result = 0;
        }
        a.setAid(result.intValue()+1);
        a.setNaam(naam);
        a.setStraatennr(straatennr);
        a.setPostcode(postcode);
        a.setGemeente(gemeente);
        em.persist(a);
    }
    
    public void addPakket(String commentaar, Adres paid, Werknemers pwid){
        Pakket p = new Pakket();
        Integer result = (Integer) em.createQuery("select max(p.pid) from Pakket p").getSingleResult();
        if(result == null) {
            result = 0;
        }
        p.setPid(result.intValue()+1);
        p.setStatus("transit");
        Date date = new Date(); 
        p.setDatum(date.toString());
        LocalTime time= LocalTime.now();
        p.setTijd(time.toString());
        p.setCommentaar(commentaar);
        p.setPaid(paid);
        p.setPwid(pwid);
        em.persist(p);
    }
    
    /*
    public void makeReservation(int dagen, String datumVan, int knr,int lnaarNr, int lvanNr, int wNr) {
        BigDecimal rnrVorig, rnr;
        Reservaties r= new Reservaties();
        rnrVorig = (BigDecimal)em.createQuery("select max(r.rnr) from Reservaties r").getSingleResult();
        if(rnrVorig == null) {
            rnrVorig = new BigDecimal(0);
        }
        rnr = rnrVorig.add(new BigDecimal(1));
        r.setRnr(rnr);
        r.setDagen(new BigInteger(String.valueOf(dagen)));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        try {
            r.setDatumvan(df.parse(datumVan));
            
           
        }
        catch(Exception e) {
            System.out.println("probleembje bij datums setten van reservatie");
            System.out.println("datumvan is : " + datumVan);
        }
        r.setDatumres(new Date()); //datum van reservatie is vandaag
        r.setKnr( (Klanten) em.find(Klanten.class, new BigDecimal(knr)) );
        r.setLnaarnr( (Locaties) em.find(Locaties.class, new BigDecimal(lnaarNr)) );   
        r.setLvannr( (Locaties) em.find(Locaties.class, new BigDecimal(lvanNr)) );
        r.setWnr( (Wagens) em.find(Wagens.class, new BigDecimal(wNr)) );
        em.persist(r); //reservaties toevoegen aan db
           
    }
    */
}


   
