package cours.uahb.service;

import cours.uahb.model.Employe;
import cours.uahb.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService implements IEmploye
{
    @Autowired
    private EmployeRepository employeRepository;
    @Override
    public Employe findById(Long id)
    {
        return null;
    }

    @Override
    public List<Employe> findAll()
    {
        return null;
    }
}
