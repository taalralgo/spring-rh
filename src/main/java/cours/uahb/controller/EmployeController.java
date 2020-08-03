package cours.uahb.controller;

import cours.uahb.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EmployeController
{
    @Autowired
    private EmployeRepository employeRepository;
    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("employes", employeRepository.findAll());
        return "employe";
    }
}
