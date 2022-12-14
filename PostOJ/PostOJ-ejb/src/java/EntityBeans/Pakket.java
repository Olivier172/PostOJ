/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ovanl
 */
@Entity
@Table(name = "PAKKET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pakket.findAll", query = "SELECT p FROM Pakket p")
    , @NamedQuery(name = "Pakket.findByPid", query = "SELECT p FROM Pakket p WHERE p.pid = :pid")
    , @NamedQuery(name = "Pakket.findByStatus", query = "SELECT p FROM Pakket p WHERE p.status = :status")
    , @NamedQuery(name = "Pakket.findByDatum", query = "SELECT p FROM Pakket p WHERE p.datum = :datum")
    , @NamedQuery(name = "Pakket.findByTijd", query = "SELECT p FROM Pakket p WHERE p.tijd = :tijd")
    , @NamedQuery(name = "Pakket.findByGewicht", query = "SELECT p FROM Pakket p WHERE p.gewicht = :gewicht")
    , @NamedQuery(name = "Pakket.findByCommentaar", query = "SELECT p FROM Pakket p WHERE p.commentaar = :commentaar")})
public class Pakket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PID")
    private Integer pid;
    @Size(max = 20)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DATUM")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @Column(name = "TIJD")
    @Temporal(TemporalType.TIME)
    private Date tijd;
    @Column(name = "GEWICHT")
    private Integer gewicht;
    @Size(max = 500)
    @Column(name = "COMMENTAAR")
    private String commentaar;
    @JoinColumn(name = "PAID", referencedColumnName = "AID")
    @ManyToOne
    private Adres paid;
    @JoinColumn(name = "PWID", referencedColumnName = "WID")
    @ManyToOne
    private Werknemers pwid;

    public Pakket() {
    }

    public Pakket(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getTijd() {
        return tijd;
    }

    public void setTijd(Date tijd) {
        this.tijd = tijd;
    }

    public Integer getGewicht() {
        return gewicht;
    }

    public void setGewicht(Integer gewicht) {
        this.gewicht = gewicht;
    }

    public String getCommentaar() {
        return commentaar;
    }

    public void setCommentaar(String commentaar) {
        this.commentaar = commentaar;
    }

    public Adres getPaid() {
        return paid;
    }

    public void setPaid(Adres paid) {
        this.paid = paid;
    }

    public Werknemers getPwid() {
        return pwid;
    }

    public void setPwid(Werknemers pwid) {
        this.pwid = pwid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pakket)) {
            return false;
        }
        Pakket other = (Pakket) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityBeans.Pakket[ pid=" + pid + " ]";
    }
    
}
