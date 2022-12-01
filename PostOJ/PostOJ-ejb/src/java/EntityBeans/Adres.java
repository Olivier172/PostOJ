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
 * @author MM
 */
@Entity
@Table(name = "ADRES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adres.findAll", query = "SELECT a FROM Adres a")
    , @NamedQuery(name = "Adres.findByAid", query = "SELECT a FROM Adres a WHERE a.aid = :aid")
    , @NamedQuery(name = "Adres.findByNaam", query = "SELECT a FROM Adres a WHERE a.naam = :naam")
    , @NamedQuery(name = "Adres.findByStraatennr", query = "SELECT a FROM Adres a WHERE a.straatennr = :straatennr")
    , @NamedQuery(name = "Adres.findByPostcode", query = "SELECT a FROM Adres a WHERE a.postcode = :postcode")
    , @NamedQuery(name = "Adres.findByGemeente", query = "SELECT a FROM Adres a WHERE a.gemeente = :gemeente")})
public class Adres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AID")
    private Integer aid;
    @Size(max = 20)
    @Column(name = "NAAM")
    private String naam;
    @Size(max = 20)
    @Column(name = "STRAATENNR")
    private String straatennr;
    @Column(name = "POSTCODE")
    private Integer postcode;
    @Size(max = 20)
    @Column(name = "GEMEENTE")
    private String gemeente;
    @OneToMany(mappedBy = "paid")
    private Collection<Pakket> pakketCollection;

    public Adres() {
    }

    public Adres(Integer aid) {
        this.aid = aid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getStraatennr() {
        return straatennr;
    }

    public void setStraatennr(String straatennr) {
        this.straatennr = straatennr;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
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
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adres)) {
            return false;
        }
        Adres other = (Adres) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityBeans.Adres[ aid=" + aid + " ]";
    }
    
}
