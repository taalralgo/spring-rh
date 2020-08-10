package cours.uahb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController
{
    @PreAuthorize("hasAuthority('ROLE_USER') OR hasAuthority('ROLE_ADMIN')")
    @GetMapping("/403")
    public String notFound()
    {
        return "403";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/simple")
    public String simple()
    {
        return "simple";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin()
    {
        return "admin";
    }
}
