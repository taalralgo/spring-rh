package cours.uahb.repository;

import cours.uahb.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUtilisateur extends JpaRepository<Utilisateur,Integer>
{
    public Utilisateur findByLogin(String username);
}
