package br.fipppay.Controlers;

import br.fipppay.Model.entities.SystemUser;
import br.fipppay.Model.entities.TokenTypeToReturn;
import br.fipppay.Model.service.SystemUserService;
import br.fipppay.Security.JWTTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("apis/security")
public class AccessRestController
{
    @Autowired
    private SystemUserService systemUserService;

    @PostMapping("/login")
    public ResponseEntity<Object> Login (@RequestBody SystemUser user)
    {
         String token="";
         System.out.println(user.getEmail() +" --- "+ user.getPassword());
         SystemUser db_user = systemUserService.getByEmail(user);

         // IF 'db_user' IS EQUAL TO NULL, IT MEANS THAT THE USER DOES NOT EXIST IN THE DATABASE
         if (db_user != null && db_user.getPassword().equals(user.getPassword()))
         {
             token = JWTTokenProvider.getToken(db_user.getLevel());
             System.out.println(token);
             System.out.println("TOKEN CREATED");
             TokenTypeToReturn TokenWithTheLevel= new TokenTypeToReturn(token, db_user.getLevel());
             return new ResponseEntity<>(TokenWithTheLevel,HttpStatus.OK);
         }
         else
         {
             System.out.println("DB_USER = "+db_user+" / USER NOT VALIDATED");
             return new ResponseEntity<>("ACCESS NOT ALLOWED",HttpStatus.NOT_ACCEPTABLE);
         }
    }

    @Autowired
    HttpServletRequest request;

    @GetMapping("/test-access")
    public ResponseEntity <Object> Test_Access()
    {
        return new ResponseEntity<>(systemUserService.getAll(),HttpStatus.OK);
        //return new ResponseEntity<>("REQUEST ALLOWED BUT WITHOUT A TOKEN",HttpStatus.OK);
        /*String token=request.getHeader("Authorization");
        try
         {
             if (JWTTokenProvider.verifyToken(token))
                 return new ResponseEntity<>("REQUEST ALLOWED",HttpStatus.OK);
             else
                 return new ResponseEntity<>("PROBLEMS WITH THE TOKEN",
                         HttpStatus.NON_AUTHORITATIVE_INFORMATION);
         }catch (Exception e)
         {
            return new ResponseEntity<>("REQUEST ALLOWED BUT WITHOUT A TOKEN",HttpStatus.OK);
         }*/
    }

    // ROUTE TO REGISTER A NEW USER

    @PostMapping("/register-user")
    public ResponseEntity<Object> Register_User (@RequestBody SystemUser user)
    {
        List<SystemUser> Lista = systemUserService.getAll();
        System.out.println("### LISTA DE TODOS OS USUÁRIOS ###");
        for (int i=0; i < Lista.size();i++)
        {
            System.out.println(Lista.get(i).getId());
            System.out.println(Lista.get(i).getName());
            System.out.println(Lista.get(i).getCpf());
            System.out.println(Lista.get(i).getEmail());
            System.out.println(Lista.get(i).getAddress());
            System.out.println(Lista.get(i).getDt_nasc());
            System.out.println(Lista.get(i).getLevel());
            System.out.println(Lista.get(i).getPassword());
        }

        System.out.println("### DADOS DO NOVO USUARIO ###");
        user.setLevel("2");
        SystemUser new_user;
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getCpf());
        System.out.println(user.getEmail());
        System.out.println(user.getAddress());
        System.out.println(user.getDt_nasc());
        System.out.println(user.getPassword());
        System.out.println(user.getLevel());
        new_user = systemUserService.save(user);
        return new ResponseEntity<>(new_user,HttpStatus.OK);
    }

}

