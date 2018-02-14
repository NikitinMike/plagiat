/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreesample;

//import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mike
 */
@Entity
//@Data
//@Table(name = "Articles")
public class Link implements Serializable { 

//    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

//    @OneToMany
//    (
//        cascade = CascadeType.ALL, 
//        orphanRemoval = true,
//        mappedBy="LINK_ID", 
//        fetch=FetchType.EAGER
//    )
//    private List<Article> text = new ArrayList<>();
    
    private String name;

    public Link() {
    }
    
    public Link (String name){
        this.name=name;
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
        if (!(object instanceof Link)) return false;
        Link other = (Link) object;
        if ((this.ID == null && other.ID != null) || (this.ID != null && !this.ID.equals(other.ID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {        
        return "Link["+ID+"] " + name; // + "\n" + text;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
