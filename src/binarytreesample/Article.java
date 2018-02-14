package binarytreesample;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;

@Entity
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

//    @OrderBy("LINK_ID")
    Long LINK_ID;
    
//    @OrderBy("WORD_ID")
    Long WORD_ID;
    
    Long position;
    
//    @ManyToOne //(fetch=FetchType.LAZY)
//    @JoinColumn(name="LINK_ID")
//    private Link link;
  
    public Article() {
    }

    public Article(Long link,Long word,Long pos) {
        LINK_ID=link;
        WORD_ID=word;
        position=pos;        
    }
    
    public Long getId() {
        return ID;
    }

    public void setId(Long id) {
        this.ID = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ID != null ? ID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) return false;
        Article other = (Article) object;
        if ((this.ID == null && other.ID != null) || (this.ID != null && !this.ID.equals(other.ID))) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LWP["+ID+"]{"+LINK_ID+","+WORD_ID+","+position+"}";
    }
    
}
