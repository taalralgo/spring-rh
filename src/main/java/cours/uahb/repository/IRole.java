package cours.uahb.repository;

import cours.uahb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRole extends JpaRepository<Role,Integer>
{
}
