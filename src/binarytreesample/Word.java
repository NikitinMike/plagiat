/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreesample;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author mike
 */
@Entity
//@Table(catalog = "test", schema = "")
//@NamedQueries({
//    @NamedQuery(name = "Words.findAll", query = "SELECT w FROM Words w")
//    , @NamedQuery(name = "Words.findById", query = "SELECT w FROM Words w WHERE w.id = :id")
//    , @NamedQuery(name = "Words.findByWord", query = "SELECT w FROM Words w WHERE w.word = :word")})
public class Word implements Serializable { 

//    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OrderBy("word")
    private String word;
//    private Long position;    

   
    public Word() {
    }

    public Word(String word,Long pos) {
        this.word=word;
//        position=pos;
    }
    
    public Word(String word) {
        this.word=word;
    }

    public Word(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Word)) {
            return false;
        }
        Word other = (Word) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return word+"[" + id + "]";
    }
    
}
