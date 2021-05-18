package wssr.smgl.stfu.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="tbl_zutat")
public class Zutat {

    @Id
    @GeneratedValue
    @Column(name="pk_zutat")
    private Long id;
    private String name;
    private int mengeingramm;

    public Zutat(){
    }

    public Zutat(String name, int mengeingramm){
        this.name = name;
        this.mengeingramm = mengeingramm;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Zutat other = (Zutat) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    public Zutat(String name){
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMengeingramm() {
        return mengeingramm;
    }

    public void setMengeingramm(int mengeingramm) {
        this.mengeingramm = mengeingramm;
    }

}
