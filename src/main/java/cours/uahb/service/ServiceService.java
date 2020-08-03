package cours.uahb.service;

import cours.uahb.model.Service;
import cours.uahb.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService implements IService
{
    @Autowired
    private ServiceRepository serviceRepository;
    @Override
    public List<Service> findAll()
    {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<Service> findById(Long id)
    {
        return serviceRepository.findById(id);
    }
}
