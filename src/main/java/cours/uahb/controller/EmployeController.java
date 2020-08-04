package cours.uahb.controller;

import cours.uahb.model.Employe;
import cours.uahb.repository.EmployeRepository;
import cours.uahb.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
//@RequestMapping("/employe")
public class EmployeController
{
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @GetMapping("/employe")
    public String index(Model model)
    {
        model.addAttribute("employes", employeRepository.findAll());
        model.addAttribute("services", serviceRepository.findAll());
        return "employe/employe";
    }

    @GetMapping("employe/show/{id}")
    public String show(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Employe> employe = employeRepository.findById(Long.parseLong(id));
            model.addAttribute("employe", employe.get());
            if (employe.isPresent())
            {
                model.addAttribute("employe", employe.get());
            }
            return "employe/show";
        }
        catch (Exception e)
        {
            return "redirect:/employe";
        }
    }
}
