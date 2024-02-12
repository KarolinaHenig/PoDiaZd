package pl.karolinahenig.podiazd.malfunction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.karolinahenig.podiazd.car.Modification
import pl.karolinahenig.podiazd.car.ModificationRepository

@RestController
@RequestMapping(path = "api/v1/find-car-repair-shop")
class MalfunctionController {
    @Autowired
    private final CategoryRepository categoryRepository
    @Autowired
    private final MalfunctionRepository malfunctionRepository
    @Autowired
    private final ModificationRepository modificationRepository

    @PostMapping(path = "categories")
    Map selectCategories() {
        List<Category> categories = categoryRepository.findAll().collect()
        return ["categories": categories]
    }

    @PostMapping(path = "malfunctions")
    Map selectMalfunction(@RequestBody Map<String, Object> params) {
        Integer categoryValue = params.category as Integer
        Long modificationValue = params.modificationValue as Long

        List<Malfunction> malfunctions = malfunctionRepository.findAllByCategoryId(categoryValue as long)
        Optional<Modification> modificationOptional = modificationRepository.findById(modificationValue)
        if (modificationOptional.isPresent()) {
            Modification modification = modificationOptional.get()
            malfunctions = malfunctions.findAll {
                if (categoryValue == 1) {
                    return it.engineType == modification.engineType
                } else if (categoryValue == 3) {
                    return it.gearboxType == modification.gearboxType
                            && it.gearboxSubtype == modification.gearboxSubtype
                } else if (categoryValue == 4) {
                    return it.suspension == modification.suspension
                } else {
                    true
                }
            }
        }
        return ["malfunctions": malfunctions]
    }

}
