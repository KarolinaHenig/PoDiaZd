package pl.karolinahenig.podiazd.car

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = "api/v1/find-car-repair-shop")
class CarController {
    @Autowired
    private final BrandRepository brandRepository
    @Autowired
    private final ModelRepository modelRepository
    @Autowired
    private final GenerationRepository generationRepository
    @Autowired
    private final BodyTypeRepository bodyTypeRepository
    @Autowired
    private final ModificationRepository modificationRepository

    @PostMapping(path = "brands")
    Map selectBrand() {
        List<Brand> brands = brandRepository.findAll().collect()
        return ["brands": brands]
    }

    @PostMapping(path = "models")
    Map selectModels(@RequestBody Map<String, Object> params) {
        String brandValue = params.brandValue
        List<Model> models = modelRepository.findAllByBrandId(brandValue as long)
        return ["models": models]
    }

    @PostMapping(path = "generations")
    Map selectGenerations(@RequestBody Map<String, Object> params) {
        String modelValue = params.modelValue
        if (modelValue) {
            List<Generation> generations = generationRepository.findAllByModelId(modelValue as long)
            return ["generations": generations]
        }
        return [:]
    }

    @PostMapping(path = "bodyTypes")
    Map selectBodyTypes(@RequestBody Map<String, Object> params) {
        Long generationValue = params.generationValue as Long
        Optional<Generation> generation = generationRepository.findById(generationValue)
        if (generation.isPresent()) {
            List<BodyType> bodyTypes = bodyTypeRepository.findAllByGenerations(generation.get())
            return ["bodyTypes": bodyTypes]
        }
        return [:]
    }
    @PostMapping(path = "modifications")
    Map selectModifications(@RequestBody Map<String, Object> params) {
        String generationValue = params.generationValue
        if (generationValue) {
            List<Modification> modifications = modificationRepository.findAllByGenerationId(generationValue as long)
            return ["modifications": modifications]
        }
        return [:]
    }

}
