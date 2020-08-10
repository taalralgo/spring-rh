package cours.uahb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner
{

    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    BCryptPasswordEncoder encoder;
    @Override
    public void run(String... args) throws Exception
    {
        System.out.println(encoder.encode("passer"));
    }
}
