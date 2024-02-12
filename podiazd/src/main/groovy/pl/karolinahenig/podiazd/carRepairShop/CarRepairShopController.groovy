package pl.karolinahenig.podiazd.carRepairShop

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.karolinahenig.podiazd.appuser.AppUser
import pl.karolinahenig.podiazd.locality.CarRepairShop
import pl.karolinahenig.podiazd.locality.CarRepairShopRepository
import pl.karolinahenig.podiazd.rate.CarRepairShopRate
import pl.karolinahenig.podiazd.rate.CarRepairShopRateRepository

import java.security.Principal

@RestController
@RequestMapping(path = "api/v1/car-repair-shop")
class CarRepairShopController {
    @Autowired
    private final CarRepairShopRepository carRepairShopRepository

    @Autowired
    private final CarRepairShopRateRepository carRepairShopRateRepository

    @PostMapping(path = "add")
    void saveCarRepairShop(@RequestBody CarRepairShop request) {
        carRepairShopRepository.save(request)
    }

    @PostMapping(path = "rate")
    void rateCarRepairShop(@RequestBody CarRepairShopRate request, Principal principal) {
        UsernamePasswordAuthenticationToken auth = principal as UsernamePasswordAuthenticationToken
        AppUser appUser = auth.getPrincipal() as AppUser
        request.appUser = appUser
        carRepairShopRateRepository.save(request)
    }

    @PostMapping(path = "select")
    Map selectCarRepairShop(@RequestBody Map<String, Object> params) {
        Integer cityId = params.cityId as Integer
        List<CarRepairShop> carRepairShops = carRepairShopRepository.findAllByCityId(cityId as long)
        return ["carRepairShops": carRepairShops]
    }

}
