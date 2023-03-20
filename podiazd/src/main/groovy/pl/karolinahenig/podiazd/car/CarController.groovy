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
    private final FuelTypeRepository fuelTypeRepository
    @Autowired
    private final EngineCapacityRepository engineCapacityRepository
    @Autowired
    private final ProductionYearRepository productionYearRepository

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
        String generationValue = params.generationValue
        if (generationValue) {
            List<BodyType> bodyTypes = bodyTypeRepository.findAllByGenerationId(generationValue as long)
            return ["bodyTypes": bodyTypes]
        }
        return [:]
    }
    @PostMapping(path = "fuelTypes")
    Map selectFuelTypes(@RequestBody Map<String, Object> params) {
        String bodyTypeValue = params.bodyTypeValue
        if (bodyTypeValue) {
            List<FuelType> fuelTypes = fuelTypeRepository.findAllByBodyTypeId(bodyTypeValue as long)
            return ["fuelTypes": fuelTypes]
        }
        return [:]
    }
    @PostMapping(path = "engineCapacities")
    Map selectEngineCapacities(@RequestBody Map<String, Object> params) {
        String fuelTypeValue = params.fuelTypeValue
        if (fuelTypeValue) {
            List<EngineCapacity> engineCapacities = engineCapacityRepository.findAllByFuelTypeId(fuelTypeValue as long)
            return ["engineCapacities": engineCapacities]
        }
        return [:]
    }
    @PostMapping(path = "productionYears")
    Map selectProductionYears(@RequestBody Map<String, Object> params) {
        String engineCapacityValue = params.engineCapacityValue
        if (engineCapacityValue) {
            List<ProductionYear> productionYears = productionYearRepository.findAllByEngineCapacityId(engineCapacityValue as long)
            return ["productionYears": productionYears]
        }
        return [:]
    }

}
