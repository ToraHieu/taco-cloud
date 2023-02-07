package tacos.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.IngredientUDT;
import tacos.TacoUDRUtils;
import tacos.data.IngredientRepository;


@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

    private IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id) {
        return TacoUDRUtils.toIngredientUDT(ingredientRepository.findById(id).orElse(null));
    }
}
