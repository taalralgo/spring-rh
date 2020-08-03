package cours.uahb.service;

import cours.uahb.model.Employe;

import java.util.List;

public interface IEmploye
{
    public Employe findById(Long id);
    public List<Employe> findAll();
}
