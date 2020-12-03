/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.db;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "Notification", catalog = "MiniSocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    , @NamedQuery(name = "Notification.findByNotifyID", query = "SELECT n FROM Notification n WHERE n.notifyID = :notifyID")
    , @NamedQuery(name = "Notification.findByNotifiDate", query = "SELECT n FROM Notification n WHERE n.notifiDate = :notifiDate")
    , @NamedQuery(name = "Notification.findByNotifyDescription", query = "SELECT n FROM Notification n WHERE n.notifyDescription = :notifyDescription")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "notifyID", nullable = false)
    private Integer notifyID;
    @Basic(optional = false)
    @Column(name = "notifiDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date notifiDate;
    @Basic(optional = false)
    @Column(name = "notifyDescription", nullable = false, length = 1073741823)
    private String notifyDescription;
    @JoinColumn(name = "accID", referencedColumnName = "accID", nullable = false)
    @ManyToOne(optional = false)
    private Account accID;
    @JoinColumn(name = "articleID", referencedColumnName = "articleID", nullable = false)
    @ManyToOne(optional = false)
    private Article articleID;

    public Notification() {
    }

    public Notification(Integer notifyID) {
        this.notifyID = notifyID;
    }

    public Notification(Integer notifyID, Date notifiDate, String notifyDescription) {
        this.notifyID = notifyID;
        this.notifiDate = notifiDate;
        this.notifyDescription = notifyDescription;
    }

    public Integer getNotifyID() {
        return notifyID;
    }

    public void setNotifyID(Integer notifyID) {
        this.notifyID = notifyID;
    }

    public Date getNotifiDate() {
        return notifiDate;
    }

    public void setNotifiDate(Date notifiDate) {
        this.notifiDate = notifiDate;
    }

    public String getNotifyDescription() {
        return notifyDescription;
    }

    public void setNotifyDescription(String notifyDescription) {
        this.notifyDescription = notifyDescription;
    }

    public Account getAccID() {
        return accID;
    }

    public void setAccID(Account accID) {
        this.accID = accID;
    }

    public Article getArticleID() {
        return articleID;
    }

    public void setArticleID(Article articleID) {
        this.articleID = articleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notifyID != null ? notifyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notifyID == null && other.notifyID != null) || (this.notifyID != null && !this.notifyID.equals(other.notifyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.db.Notification[ notifyID=" + notifyID + " ]";
    }
    
}
