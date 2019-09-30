package hu.flowacademy.feri2.Model;


import javax.persistence.*;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String picture;

    @Column
    private String sub;

    @Column
    private String givenName;

    @Column
    private String familyName;

    @Column
    private boolean emailVerified;

    @Column
    private String locale;

    public User() {
    }

    public User(String name, String email, String picture, String sub, String givenName, String familyName, boolean emailVerified, String locale) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.sub = sub;
        this.givenName = givenName;
        this.familyName = familyName;
        this.emailVerified = emailVerified;
        this.locale = locale;
    }

    public Long getId() {
        return id;
    }
}
