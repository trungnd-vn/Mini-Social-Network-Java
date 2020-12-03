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
@Table(name = "Comment", catalog = "MiniSocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
    , @NamedQuery(name = "Comment.findByCmtID", query = "SELECT c FROM Comment c WHERE c.cmtID = :cmtID")
    , @NamedQuery(name = "Comment.findByCommentContent", query = "SELECT c FROM Comment c WHERE c.commentContent = :commentContent")
    , @NamedQuery(name = "Comment.findByStatus", query = "SELECT c FROM Comment c WHERE c.status = :status")
    , @NamedQuery(name = "Comment.findByArticleID&Status", query = "SELECT c FROM Comment c WHERE c.status = :status and c.articleID = :articleID ORDER BY c.cmtDate ASC")
    })
public class Comment implements Serializable {

    @Basic(optional = false)
    @Column(name = "cmtDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cmtDate;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cmtID", nullable = false, length = 10)
    private String cmtID;
    @Basic(optional = false)
    @Column(name = "commentContent", nullable = false, length = 1073741823)
    private String commentContent;
    @JoinColumn(name = "accID", referencedColumnName = "accID", nullable = false)
    @ManyToOne(optional = false)
    private Account accID;
    @JoinColumn(name = "articleID", referencedColumnName = "articleID", nullable = false)
    @ManyToOne(optional = false)
    private Article articleID;
    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    private boolean status;
    
    public Comment() {
    }

    public Comment(String cmtID) {
        this.cmtID = cmtID;
    }

   public Comment(String cmtID, String commentContent, boolean status) {
        this.cmtID = cmtID;
        this.commentContent = commentContent;
        this.status = status;
    }

    public Comment(String commentContent, Account accID, Article articleID, boolean status) {
        this.commentContent = commentContent;
        this.accID = accID;
        this.articleID = articleID;
        this.status = status;
    }

    public Comment(String cmtID, String commentContent, Account accID, Article articleID, boolean status) {
        this.cmtID = cmtID;
        this.commentContent = commentContent;
        this.accID = accID;
        this.articleID = articleID;
        this.status = status;
    }

    public Comment(Date cmtDate, String cmtID, String commentContent, Account accID, Article articleID, boolean status) {
        this.cmtDate = cmtDate;
        this.cmtID = cmtID;
        this.commentContent = commentContent;
        this.accID = accID;
        this.articleID = articleID;
        this.status = status;
    }
    
    
    

    public String getCmtID() {
        return cmtID;
    }

    public void setCmtID(String cmtID) {
        this.cmtID = cmtID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        hash += (cmtID != null ? cmtID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.cmtID == null && other.cmtID != null) || (this.cmtID != null && !this.cmtID.equals(other.cmtID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.db.Comment[ cmtID=" + cmtID + " ]";
    }

    public Date getCmtDate() {
        return cmtDate;
    }

    public void setCmtDate(Date cmtDate) {
        this.cmtDate = cmtDate;
    }
    
}
