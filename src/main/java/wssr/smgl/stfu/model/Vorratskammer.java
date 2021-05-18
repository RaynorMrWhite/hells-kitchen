package wssr.smgl.stfu.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_vorratskammer")
public class Vorratskammer {

    @Id
    @GeneratedValue
    @Column(name="pk_vorratskammer")
    private Long id;
    private String zutatname;
    private int mengeingramm;

    public Vorratskammer(){    }

    public Vorratskammer(String zutatname, int mengeingramm){
        this.zutatname = zutatname;
        this.mengeingramm = mengeingramm;

    }

    public Long getId() {
        return id;
    }

    public String getZutatname() {
        return zutatname;
    }

    public int getMengeingramm() {
        return mengeingramm;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setZutatname(String zutatname) {
        this.zutatname = zutatname;
    }

    public void setMengeingramm(int mengeingramm) {
        this.mengeingramm = mengeingramm;
    }

}

