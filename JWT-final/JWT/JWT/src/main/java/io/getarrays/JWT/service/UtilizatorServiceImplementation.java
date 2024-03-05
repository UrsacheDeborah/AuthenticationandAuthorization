package io.getarrays.JWT.service;

import io.getarrays.JWT.domain.Rol;
import io.getarrays.JWT.domain.Utilizator;
import io.getarrays.JWT.repozitor.RolRepo;
import io.getarrays.JWT.repozitor.UtilizatorRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UtilizatorServiceImplementation implements UtilizatorService, UserDetailsService {
    private final UtilizatorRepo userRepo;
    private final RolRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilizator user = userRepo.findByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else{
            log.info("User found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoluri().forEach(rol -> {
            authorities.add(new SimpleGrantedAuthority(rol.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getParola(),authorities);
    }
    @Override
    public Utilizator saveUser(Utilizator user) {
        log.info("Se salveaza utilizator {} nou in baza de date", user.getName());
        user.setParola(passwordEncoder.encode(user.getParola()));
        return userRepo.save(user);
    }

    @Override
    public Rol saveRole(Rol role) {
        log.info("Se salveaza rol {} nou in baza de date", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void adaugaRol(String userName, String roleName) {
        log.info("Se adauga rol nou {} pentru utilizatorul {}", roleName, userName);
        Utilizator utilizator = userRepo.findByUsername(userName);
        Rol role = roleRepo.findByName(roleName);
        utilizator.getRoluri().add(role);

    }

    @Override
    public Utilizator getUser(String userName) {
        log.info("Se returneaza numele utilizatorului {}", userName);
        return userRepo.findByUsername(userName);
    }

    @Override
    public List<Utilizator> getUsers() {
        log.info("Se returneaza numele tuturor utilizatorilor");
        return userRepo.findAll();
    }


}
