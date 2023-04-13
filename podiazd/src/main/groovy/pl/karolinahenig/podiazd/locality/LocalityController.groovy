package pl.karolinahenig.podiazd.locality

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = "api/v1")
class LocalityController {
    @Autowired
    private final VoivodeshipRepository voivodeshipRepository
    @Autowired
    private final CountyRepository countyRepository
    @Autowired
    private final CityRepository cityRepository

    @PostMapping(path = "find-car-repair-shop/voivodeships")
    Map selectVoivodeships() {
        List<Voivodeship> voivodeships = voivodeshipRepository.findAll().collect()
        return ["voivodeships": voivodeships]
    }

    @PostMapping(path = "find-car-repair-shop/cities")
    Map selectCitiesRate(@RequestBody Map<String, Object> params) {
        String voivodeshipValue = params.voivodeshipValue
        List<County> counties = countyRepository.findAllByVoivodeshipId(voivodeshipValue as long)
        List<City> cities = cityRepository.findAllByCountyIn(counties)

        return ["cities": cities]
    }

    @PostMapping(path = "rate-car-repair-shop/voivodeships")
    Map selectVoivodeshipsRate() {
        List<Voivodeship> voivodeships = voivodeshipRepository.findAll().collect()
        return ["voivodeships": voivodeships]
    }

    @PostMapping(path = "rate-car-repair-shop/counties")
    Map selectCounties(@RequestBody Map<String, Object> params) {
        String voivodeshipValue = params.voivodeshipValue
        List<County> counties = countyRepository.findAllByVoivodeshipId(voivodeshipValue as long)

        return ["counties": counties]
    }

    @PostMapping(path = "rate-car-repair-shop/cities")
    Map selectCities(@RequestBody Map<String, Object> params) {
        String countyValue = params.countyValue
        List<City> cities = cityRepository.findAllByCountyId(countyValue as long)

        return ["cities": cities]
    }
}
