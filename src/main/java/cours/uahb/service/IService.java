package cours.uahb.service;

import cours.uahb.model.Service;

import java.util.List;
import java.util.Optional;

public interface IService
{
    public List<Service> findAll();
    public Optional<Service> findById(Long id);
}
