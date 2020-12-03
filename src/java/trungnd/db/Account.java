/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "Account", catalog = "MiniSocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByAccID", query = "SELECT a FROM Account a WHERE a.accID = :accID")
    , @NamedQuery(name = "Account.findByAccPassword", query = "SELECT a FROM Account a WHERE a.accPassword = :accPassword")
    , @NamedQuery(name = "Account.findByAccName", query = "SELECT a FROM Account a WHERE a.accName = :accName")
    , @NamedQuery(name = "Account.findByRoleName", query = "SELECT a FROM Account a WHERE a.roleName = :roleName")
    , @NamedQuery(name = "Account.findByAccStatus", query = "SELECT a FROM Account a WHERE a.accStatus = :accStatus")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "accID", nullable = false, length = 200)
    private String accID;
    @Basic(optional = false)
    @Column(name = "accPassword", nullable = false, length = 100)
    private String accPassword;
    @Basic(optional = false)
    @Column(name = "accName", nullable = false, length = 100)
    private String accName;
    @Basic(optional = false)
    @Column(name = "roleName", nullable = false, length = 20)
    private String roleName;
    @Basic(optional = false)
    @Column(name = "accStatus", nullable = false, length = 20)
    private String accStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accID")
    private Collection<Comment> commentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accID")
    private Collection<Article> articleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accID")
    private Collection<Emotion> emotionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accID")
    private Collection<Notification> notificationCollection;

    public Account() {
    }

    public Account(String accID) {
        this.accID = accID;
    }

    public Account(String accID, String accPassword, String accName, String roleName, String accStatus) {
        this.accID = accID;
        this.accPassword = accPassword;
        this.accName = accName;
        this.roleName = roleName;
        this.accStatus = accStatus;
    }

    public String getAccID() {
        return accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }

    public String getAccPassword() {
        return accPassword;
    }

    public void setAccPassword(String accPassword) {
        this.accPassword = accPassword;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
    }

    @XmlTransient
    public Collection<Emotion> getEmotionCollection() {
        return emotionCollection;
    }

    public void setEmotionCollection(Collection<Emotion> emotionCollection) {
        this.emotionCollection = emotionCollection;
    }

    @XmlTransient
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accID != null ? accID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accID == null && other.accID != null) || (this.accID != null && !this.accID.equals(other.accID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.db.Account[ accID=" + accID + " ]";
    }
    

}
