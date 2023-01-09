/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postojclient;

import Beans.DataManagerRemote;
import ViewPackage.View;
import javax.ejb.EJB;

/**
 *
 * @author r0723037
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    @EJB static DataManagerRemote deb;
    public static void main(String[] args) {
        // TODO code application logic here
        new View(deb);
    }
    
}
