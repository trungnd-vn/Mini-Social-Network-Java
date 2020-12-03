/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.db;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "Article", catalog = "MiniSocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a")
    , @NamedQuery(name = "Article.findByArticleID", query = "SELECT a FROM Article a WHERE a.articleID = :articleID")
    , @NamedQuery(name = "Article.findByArticleTitle", query = "SELECT a FROM Article a WHERE a.articleTitle = :articleTitle")
    , @NamedQuery(name = "Article.findByArticleDescription", query = "SELECT a FROM Article a WHERE a.articleDescription = :articleDescription")
    , @NamedQuery(name = "Article.findByArticleDate", query = "SELECT a FROM Article a WHERE a.articleDate = :articleDate")
    , @NamedQuery(name = "Article.findByArticleStatus", query = "SELECT a FROM Article a WHERE a.articleStatus = :articleStatus")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "articleID", nullable = false, length = 10)
    private String articleID;
    @Basic(optional = false)
    @Column(name = "articleTitle", nullable = false, length = 100)
    private String articleTitle;
    @Basic(optional = false)
    @Column(name = "articleDescription", nullable = false, length = 1073741823)
    private String articleDescription;
    @Lob
    @Column(name = "articleImage", length = 2147483647)
    private String articleImage;
    @Basic(optional = false)
    @Column(name = "articleDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date articleDate;
    @Basic(optional = false)
    @Column(name = "articleStatus", nullable = false, length = 20)
    private String articleStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articleID")
    private Collection<Comment> commentCollection;
    @JoinColumn(name = "accID", referencedColumnName = "accID", nullable = false)
    @ManyToOne(optional = false)
    private Account accID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articleID")
    private Collection<Emotion> emotionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articleID")
    private Collection<Notification> notificationCollection;

    public Article() {
    }

    public Article(String articleID) {
        this.articleID = articleID;
    }

    public Article(String articleID, String articleTitle, String articleDescription, Date articleDate, String articleStatus) {
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.articleDescription = articleDescription;
        this.articleDate = articleDate;
        this.articleStatus = articleStatus;
    }

    public Article(String articleID, String articleTitle, String articleDescription, String articleImage, Date articleDate, String articleStatus, Account accID) {
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.articleDescription = articleDescription;
        this.articleImage = articleImage;
        this.articleDate = articleDate;
        this.articleStatus = articleStatus;
        this.accID = accID;
    }
    
    

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Account getAccID() {
        return accID;
    }

    public void setAccID(Account accID) {
        this.accID = accID;
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
        hash += (articleID != null ? articleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.articleID == null && other.articleID != null) || (this.articleID != null && !this.articleID.equals(other.articleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.db.Article[ articleID=" + articleID + " ]";
    }
    
}
