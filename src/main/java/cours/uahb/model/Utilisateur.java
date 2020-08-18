

package cours.uahb.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Utilisateur implements UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login; // nomPartenaire+ninea
    private String pwd; // mot de passe par defaut passer
    private String nomComplet;
    private String email;
    private String photo;
    private boolean changed;
    @Transient
    private MultipartFile[] parts;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;


    // Upload files.
    @Transient
    private MultipartFile[] fileDatas;

    public MultipartFile[] getFileDatas() {
        return fileDatas;
    }

    public void setFileDatas(MultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }

    public Utilisateur() {
    }

    public Utilisateur(String login, String pwd, String nomComplet, String email, String photo) {
        this.login = login;
        this.pwd = pwd;
        this.nomComplet = nomComplet;
        this.email = email;
        this.photo = photo;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public MultipartFile[] getParts()
    {
        return parts;
    }

    public void setParts(MultipartFile[] parts)
    {
        this.parts = parts;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List authorities = new ArrayList();

        authorities.add(new SimpleGrantedAuthority(role.getLibRole()));

        return authorities;
    }

    @Override
    public String getPassword()
    {
        return pwd;
    }

    @Override
    public String getUsername()
    {
        return login;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}