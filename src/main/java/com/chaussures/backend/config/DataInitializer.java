package com.chaussures.backend.config;

import com.chaussures.backend.entities.AppUser;
import com.chaussures.backend.entities.Chaussure;
import com.chaussures.backend.entities.ModelDeChaussure;
import com.chaussures.backend.repos.AppUserRepository;
import com.chaussures.backend.repos.ChaussureRepository;
import com.chaussures.backend.repos.ModelDeChaussureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository userRepository;
    private final ModelDeChaussureRepository modelRepository;
    private final ChaussureRepository chaussureRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AppUserRepository userRepository,
                           ModelDeChaussureRepository modelRepository,
                           ChaussureRepository chaussureRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.chaussureRepository = chaussureRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.count() == 0) {
            Set<String> adminRoles = new LinkedHashSet<>();
            adminRoles.add("ROLE_ADMIN");
            Set<String> userRoles = new LinkedHashSet<>();
            userRoles.add("ROLE_USER");

            userRepository.save(new AppUser("admin", passwordEncoder.encode("123"), adminRoles));
            userRepository.save(new AppUser("imene", passwordEncoder.encode("123"), userRoles));
        }

        if (modelRepository.count() == 0) {
            List<ModelDeChaussure> modeles = new ArrayList<>();
            for (String nom : List.of("Sneakers", "Baskets", "Sandales", "Bottes", "Mocassins", "Talons")) {
                modeles.add(modelRepository.save(new ModelDeChaussure(nom)));
            }
        }

        if (chaussureRepository.count() == 0) {
            chaussureRepository.save(new Chaussure("Air Max", 320.0, 42, modele("Sneakers")));
            chaussureRepository.save(new Chaussure("Stan Smith", 250.0, 41, modele("Baskets")));
            chaussureRepository.save(new Chaussure("Timberland", 480.0, 44, modele("Bottes")));
        }
    }

    private ModelDeChaussure modele(String nom) {
        return modelRepository.findByNomModelIgnoreCase(nom)
                .orElseThrow(() -> new IllegalStateException("Modèle manquant : " + nom));
    }
}
