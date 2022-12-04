/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.List;
import javax.ejb.Remote;

/**
 * @author Olivier Van Lier  
 * Dit is de interface voor de datamanager, alle methodes moeten hier gedeclareerd zijn om die extern te gebruiken.
 */
@Remote
public interface DataManagerRemote {
    public List getWerknemers();
    public List getKoeriers();
    public List getBediendes();    
    public List getAdressen();
    public int getAdresAid(String naam);
    public List getPakketten();
    public List<String> getPakketDetails(int pid);
    public List getPakkettenKoerier(int wid);
    public void addWerknemers(String functie, String wnaam);
    public int addAdres(String naam, String straatennr, Integer postcode, String gemeente);
    public void addPakket(String commentaar,int gewicht, int paid, int pwid);
}
