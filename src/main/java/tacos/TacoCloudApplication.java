package tacos;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Bean
    @Profile("!prod")
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepo,
                                        UserRepository userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            ingredientRepo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            ingredientRepo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            ingredientRepo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            ingredientRepo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

            userRepo.save(new TacoUser("aa", passwordEncoder.encode("aaa"),
                    "Test User", "100 Street", "X City", "Y State", "Z Zip", "0024635700"));
        };
    }
}
