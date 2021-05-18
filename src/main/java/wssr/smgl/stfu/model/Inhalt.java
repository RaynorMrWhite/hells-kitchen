package wssr.smgl.stfu.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="tbl_inhalt")
public class Inhalt {
    @Id
    @GeneratedValue
    @Column(name="pk_inhalt")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_rezept", nullable = false)
    private Rezept rezept;

    @ManyToMany(targetEntity = wssr.smgl.stfu.model.Zutat.class,
            cascade={CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY)
    @JoinTable(
            name="tbl_rezeptinhalt",
            joinColumns={@JoinColumn(name="fk_inhalt")},
            inverseJoinColumns={@JoinColumn(name="fk_zutat")})
    private Set<Zutat> zutaten;

    public Inhalt(){}

    public Rezept getRezept() {
        return rezept;
    }

    public void setRezept(Rezept rezept) {
        this.rezept = rezept;
    }

    public Set<Zutat> getZutat() {
        return zutaten;
    }

    public void setZutat(Set<Zutat> zutat) {
        this.zutaten = zutat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Zutat> getZutaten() {
        return zutaten;
    }

    public void setZutaten(Set<Zutat> zutaten) {
        this.zutaten = zutaten;
    }
}
