package cours.uahb.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Service
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String libelle;

    @OneToMany(mappedBy = "service")
    private List<Employe> employes;

    public Service()
    {
        super();
    }

    public Service(String libelle)
    {
        this.libelle = libelle;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return libelle;
    }
}
