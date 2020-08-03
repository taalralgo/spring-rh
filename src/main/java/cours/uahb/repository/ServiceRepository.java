package cours.uahb.repository;

import cours.uahb.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long>
{
    public Service findByLibelle(String libelle);
}
