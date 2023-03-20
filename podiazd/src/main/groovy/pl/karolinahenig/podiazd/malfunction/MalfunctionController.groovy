package pl.karolinahenig.podiazd.malfunction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = "api/v1/find-car-repair-shop")
class MalfunctionController {
    @Autowired
    private final CategoryRepository categoryRepository

    @PostMapping(path = "categories")
    Map selectCategories() {
        List<Category> categories = categoryRepository.findAll().collect()
        return ["categories": categories]
    }
}
