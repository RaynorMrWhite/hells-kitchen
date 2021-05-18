package wssr.smgl.stfu.model;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.Set;

@Entity
@Table(name= "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_user")
    private Long id;
    @Column(name="nutzer")
    private String userName;
    private String password;

    /*@ManyToOne(targetEntity = wssr.smgl.stfu.model.Vorratskammer.class,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinTable(
            name="tbl_vorratskammer",
            joinColumns = {@JoinColumn(name="fk_vorratskammer")},
            inverseJoinColumns={@JoinColumn(name="fk_user")})
    private Set<Vorratskammer> verfuegbarezutat;
    */
    public User(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
