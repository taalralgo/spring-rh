package cours.uahb.controller;

import cours.uahb.model.Role;
import cours.uahb.model.Utilisateur;
import cours.uahb.repository.IRole;
import cours.uahb.repository.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

    private Utilisateur connectedUser;

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

    @GetMapping("/user/changepassword")
    public String changepassword(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        connectedUser = utilisateurRepository.findByLogin(user.getUsername());
        model.addAttribute("utilisateur", connectedUser);
        return "user/changepassword";
    }

    @PostMapping("/user/changepassword")
    public String resetpassword(@RequestParam(name = "oldpassword") String oldpassword,
                                @RequestParam(name = "password") String password,
                                @RequestParam(name = "confirmation") String confirmation,
                                Model model)
    {
        String error = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        connectedUser = utilisateurRepository.findByLogin(user.getUsername());
        if(password.length() < 7) {
            error+= "Le mot de passe doit faire au moins 7 caracteres.";
            model.addAttribute("erreur",error);
            model.addAttribute("utilisateur", connectedUser);
            return "user/changepassword";
        }
//        if(!connectedUser.getPwd().equals(bCryptPasswordEncoder.encode(oldpassword))) {
//            error+= "L'ancien mot de passe est incorrect.";
//            model.addAttribute("erreur",error);
//            model.addAttribute("utilisateur", connectedUser);
//            return "user/changepassword";
//        }
        if(password.equals(oldpassword)) {
            error+= "L'ancien mot de passe et le nouveau mot de passe doit etre different.";
            model.addAttribute("erreur",error);
            model.addAttribute("utilisateur", connectedUser);
            return "user/changepassword";
        }
        if(!password.equals(confirmation)) {
            error+= "La confirmation ne correspond pas au mot de passe.";
            model.addAttribute("erreur",error);
            model.addAttribute("utilisateur", connectedUser);
            return "user/changepassword";
        }

        connectedUser.setPwd(bCryptPasswordEncoder.encode(password));
        connectedUser.setChanged(true);
        utilisateurRepository.save(connectedUser);
        if(connectedUser.getRole().getLibRole().equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/simple";

    }
}
