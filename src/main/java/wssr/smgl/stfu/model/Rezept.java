package wssr.smgl.stfu.model;

import javax.persistence.*;

@Entity
@Table(name="tbl_rezept")
public class Rezept {
    @Id
    @GeneratedValue
    @Column(name="pk_rezept")
    private Long id;
    private String rezeptName;
    private int anzahlZutaten;

    public Rezept(){    }

    public Rezept(String rezeptName, int anzahlZutaten){
        this.rezeptName = rezeptName;
        this.anzahlZutaten=anzahlZutaten;
    }

    public int getAnzahlZutaten() {
        return anzahlZutaten;
    }

    public void setAnzahlZutaten(int anzahlZutaten) {
        this.anzahlZutaten = anzahlZutaten;
    }

    public Integer[] getAnzahlZutatenArray() {
        Integer[] result = new Integer[anzahlZutaten];
        for (int i = 0; i < anzahlZutaten; i++) {
            result[i] = i;
        }
        return result;
    }

    public String getRezeptName() { return rezeptName; }

    public void setRezeptName(String rezeptName) {this.rezeptName = rezeptName;}

    public Long getId(){
        return id;
    }

    public void setId(Long id){ this.id = id; }

}
