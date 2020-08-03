package cours.uahb.repository;

import cours.uahb.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long>
{
    public Employe findByMatricule(String matricule);
    public List<Employe> findByService_Id(Long serviceId);
    public List<Employe> findByService_Libelle(String serviceLibelle);
}
