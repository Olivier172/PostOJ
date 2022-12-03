/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityBeans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ovanl
 */
@Entity
@Table(name = "WERKNEMERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Werknemers.findAll", query = "SELECT w FROM Werknemers w")
    , @NamedQuery(name = "Werknemers.findByWid", query = "SELECT w FROM Werknemers w WHERE w.wid = :wid")
    , @NamedQuery(name = "Werknemers.findByWnaam", query = "SELECT w FROM Werknemers w WHERE w.wnaam = :wnaam")
    , @NamedQuery(name = "Werknemers.findByFunctie", query = "SELECT w FROM Werknemers w WHERE w.functie = :functie")})
public class Werknemers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "WID")
    private Integer wid;
    @Size(max = 40)
    @Column(name = "WNAAM")
    private String wnaam;
    @Size(max = 40)
    @Column(name = "FUNCTIE")
    private String functie;
    @OneToMany(mappedBy = "pwid")
    private Collection<Pakket> pakketCollection;

    public Werknemers() {
    }

    public Werknemers(Integer wid) {
        this.wid = wid;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public String getWnaam() {
        return wnaam;
    }

    public void setWnaam(String wnaam) {
        this.wnaam = wnaam;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    @XmlTransient
    public Collection<Pakket> getPakketCollection() {
        return pakketCollection;
    }

    public void setPakketCollection(Collection<Pakket> pakketCollection) {
        this.pakketCollection = pakketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Werknemers)) {
            return false;
        }
        Werknemers other = (Werknemers) object;
        if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityBeans.Werknemers[ wid=" + wid + " ]";
    }
    
}
