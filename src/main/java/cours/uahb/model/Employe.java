package cours.uahb.model;

import javax.persistence.*;

@Entity
public class Employe
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10)
    private String matricule;
    @Column(length = 255)
    private String nomComplet;
    @Column(length = 50)
    private String poste;
    private int salaire;
    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    public Employe()
    {
        super();
    }

    public Employe(String nomComplet, String poste, int salaire, Service service)
    {
        this.nomComplet = nomComplet;
        this.poste = poste;
        this.salaire = salaire;
        this.service = service;
    }

    public Long getId()
    {
        return id;
    }

    public String getMatricule()
    {
        return matricule;
    }

    public void setMatricule(String matricule)
    {
        this.matricule = matricule;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNomComplet()
    {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet)
    {
        this.nomComplet = nomComplet;
    }

    public String getPoste()
    {
        return poste;
    }

    public void setPoste(String poste)
    {
        this.poste = poste;
    }

    public int getSalaire()
    {
        return salaire;
    }

    public void setSalaire(int salaire)
    {
        this.salaire = salaire;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }
}

