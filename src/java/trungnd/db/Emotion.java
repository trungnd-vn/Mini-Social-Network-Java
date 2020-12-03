/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "Emotion", catalog = "MiniSocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emotion.findAll", query = "SELECT e FROM Emotion e")
    , @NamedQuery(name = "Emotion.findByEmotionID", query = "SELECT e FROM Emotion e WHERE e.emotionID = :emotionID")
    , @NamedQuery(name = "Emotion.findByEmotionType", query = "SELECT e FROM Emotion e WHERE e.emotionType = :emotionType")
    , @NamedQuery(name = "Emotion.countEmotionID", query = "SELECT COUNT(e.emotionID) FROM Emotion e WHERE e.articleID = :articleID and e.emotionType = :emotionType and e.status = true")})
public class Emotion implements Serializable {

    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    private boolean status;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "emotionID", nullable = false, length = 100)
    private String emotionID;
    @Basic(optional = false)
    @Column(name = "emotionType", nullable = false)
    private boolean emotionType;
    @JoinColumn(name = "accID", referencedColumnName = "accID", nullable = false)
    @ManyToOne(optional = false)
    private Account accID;
    @JoinColumn(name = "articleID", referencedColumnName = "articleID", nullable = false)
    @ManyToOne(optional = false)
    private Article articleID;
//illegal access
    public Emotion() {
    }

    public Emotion(String emotionID) {
        this.emotionID = emotionID;
    }

    public Emotion(String emotionID, boolean emotionType) {
        this.emotionID = emotionID;
        this.emotionType = emotionType;
    }

    public Emotion(boolean status, String emotionID, boolean emotionType, Account accID, Article articleID) {
        this.status = status;
        this.emotionID = emotionID;
        this.emotionType = emotionType;
        this.accID = accID;
        this.articleID = articleID;
    }
     
    public String getEmotionID() {
        return emotionID;
    }

    public void setEmotionID(String emotionID) {
        this.emotionID = emotionID;
    }

    public boolean getEmotionType() {
        return emotionType;
    }

    public void setEmotionType(boolean emotionType) {
        this.emotionType = emotionType;
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
        hash += (emotionID != null ? emotionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emotion)) {
            return false;
        }
        Emotion other = (Emotion) object;
        if ((this.emotionID == null && other.emotionID != null) || (this.emotionID != null && !this.emotionID.equals(other.emotionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.db.Emotion[ emotionID=" + emotionID + " ]";
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
