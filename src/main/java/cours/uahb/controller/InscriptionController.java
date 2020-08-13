package cours.uahb.controller;

import cours.uahb.model.Role;
import cours.uahb.model.Utilisateur;
import cours.uahb.repository.IRole;
import cours.uahb.repository.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class InscriptionController
{
    @Autowired
    private IUtilisateur utilisateurRepository;
    @Autowired
    private IRole roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/inscription")
    public String inscription(Model model)
    {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setRole(new Role());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("utilisateur", utilisateur);
        return "user/inscription";
    }

    @GetMapping("/user/liste")
    public String listeUser(Model model)
    {
        String uploadsUrl = "file:///D:\\uploads\\";
        model.addAttribute("utilisateurs", utilisateurRepository.findAll());
        model.addAttribute("uploads", uploadsUrl);
        return "user/liste";
    }

    @PostMapping("/inscription")
    public String save(@ModelAttribute("utilisateur") Utilisateur utilisateur) throws Exception
    {
        byte[] bytes = null;
        Path path = null;

        if(utilisateur.getParts()[0].getName().equals(""))
        {
            utilisateur.setPhoto("noimg.jpg");
        }
        else
        {
            MultipartFile part = utilisateur.getParts()[0];
            bytes = part.getBytes();
            path = Paths.get("D://uploads//" + part.getOriginalFilename() );
            utilisateur.setPhoto(part.getOriginalFilename());
        }
        utilisateur.setPwd(bCryptPasswordEncoder.encode("passer"));
        utilisateur.setChanged(false);
        utilisateur.setLogin(utilisateur.getEmail());
        utilisateurRepository.save(utilisateur);
        if(bytes.length != 0)
        {
            Files.write(path, bytes);
        }
        return "redirect:/user/liste";
    }

    @GetMapping("/image/{imagename}")
    public @ResponseBody byte[] image(@PathVariable(value = "imagename") String imagename) throws Exception
    {
        try
        {
            File file = new File("D://uploads//"+imagename+".jpg");
            String var = "D://uploads//"+imagename+".jpg";
            System.out.println("----------------------------------------");
            System.out.println(var);
            System.out.println("----------------------------------------");
            return Files.readAllBytes(file.toPath());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
