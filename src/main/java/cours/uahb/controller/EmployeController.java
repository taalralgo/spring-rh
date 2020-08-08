package cours.uahb.controller;

import cours.uahb.model.Employe;
import cours.uahb.model.Response;
import cours.uahb.model.Service;
import cours.uahb.repository.EmployeRepository;
import cours.uahb.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Employe employe = new Employe();
        employe.setService(new Service());
        model.addAttribute("employes", employeRepository.findAll());
        model.addAttribute("services", serviceRepository.findAll());
        model.addAttribute("employe", employe);
        return "employe/employe";
    }

    @GetMapping("employe/show/{id}")
    public String show(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Employe> employe = employeRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
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
    @GetMapping("employe/edit/{id}")
    public @ResponseBody Employe edit(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Employe> employe = employeRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (employe.isPresent())
            {
                return employe.get();
            }
            return new Employe();
        }
        catch (Exception e)
        {
            return new Employe();
        }
    }

    @RequestMapping("/employe/add")
    public String add(@ModelAttribute("employe") Employe employe)
    {

        employeRepository.save(employe);
        return "redirect:/employe";
    }

    @GetMapping("employe/remove/{id}")
    public ResponseEntity remove(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Employe> employe = employeRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (employe.isPresent())
            {
                employeRepository.delete(employe.get());
                return ResponseEntity.ok(new Response("success"));
            }
            return ResponseEntity.ok(new Response("error"));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok(new Response("error"));
        }
    }
}
