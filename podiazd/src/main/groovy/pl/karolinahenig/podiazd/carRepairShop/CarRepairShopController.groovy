package pl.karolinahenig.podiazd.carRepairShop

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.karolinahenig.podiazd.appuser.AppUser
import pl.karolinahenig.podiazd.locality.*
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

    @Autowired
    private final CityRepository cityRepository

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

    @PostMapping(path = "displayCarRepairShop")
    Map displayCarRepairShop(@RequestBody Map<String, Object> params, Principal principal) {
        UsernamePasswordAuthenticationToken auth = principal as UsernamePasswordAuthenticationToken
        AppUser appUser = auth.getPrincipal() as AppUser
        params.appUser = appUser

        List<CarRepairShop> carRepairShopList = carRepairShopRepository.findAll()
        for (CarRepairShop carRepairShop : carRepairShopList) {
            List<CarRepairShopRate> carRepairShopRateList = carRepairShopRateRepository.findAllByCarRepairShopId(carRepairShop.id)

            carRepairShop.averageRate = avgRate(carRepairShopRateList*.rate) ?: "Brak ocen"

            List<CarRepairShopRate> carRepairShopRateBrandList = carRepairShopRateList.findAll { it.brand.id == params.brandId }
            carRepairShop.averageRateCar = avgRate(carRepairShopRateBrandList*.rate) ?: "Brak ocen"

            List<CarRepairShopRate> carRepairShopRateMalfunctionList = carRepairShopRateList.findAll { it.malfunction.id == params.malfunctionId }
            carRepairShop.averageRateMalfunction = avgRate(carRepairShopRateMalfunctionList*.rate) ?: "Brak ocen"

            carRepairShopRepository.save(carRepairShop)
        }
        List<CarRepairShop> carRepairShops = carRepairShopRepository.findAll()
        City city = cityRepository.findById(params.cityId as Long).get()
        County county = city.county
        Voivodeship voivodeship = county.voivodeship

        List<CarRepairShop> carRepairShopListCity = carRepairShops.findAll { it.city.id == params.cityId }
        List<CarRepairShop> carRepairShopListCounty = carRepairShops.findAll { it.city.county.id == county.id && it.city.id != params.cityId }
        List<CarRepairShop> carRepairShopListVoivodeship = carRepairShops.findAll { it.voivodeship.id == voivodeship.id && it.city.county.id != county.id }
        List<CarRepairShop> carRepairShopListInCountry = carRepairShops.findAll { it.voivodeship.id != voivodeship.id }
        List<CarRepairShop> carRepairShopSortedList = []
        carRepairShopSortedList.addAll(carRepairShopListCity)
        carRepairShopSortedList.addAll(carRepairShopListCounty)
        carRepairShopSortedList.addAll(carRepairShopListVoivodeship)
        carRepairShopSortedList.addAll(carRepairShopListInCountry)
        return ["carRepairShops": carRepairShopSortedList]
    }

    @PostMapping(path = "opinions")
    Map displayOpinions(@RequestBody Map<String, Object> params, Principal principal) {
        UsernamePasswordAuthenticationToken auth = principal as UsernamePasswordAuthenticationToken
        AppUser appUser = auth.getPrincipal() as AppUser
        params.appUser = appUser

        List<CarRepairShopRate> carRepairShopRateList = carRepairShopRateRepository.findAllByCarRepairShopId(params.carRepairShopId as Integer)

        List<CarRepairShopRate> carRepairShopRateListMalfunction = carRepairShopRateList.findAll { it.malfunction.id == params.malfunctionId }
        List<CarRepairShopRate> carRepairShopRateListModel = carRepairShopRateList.findAll { it.model.id == params.modelId && it.malfunction.id != params.malfunctionId }
        List<CarRepairShopRate> carRepairShopRateListBrand = carRepairShopRateList.findAll { it.brand.id == params.brandId && it.malfunction.id != params.malfunctionId && it.model.id != params.modelId }
        List<CarRepairShopRate> carRepairShopRateListDifferent = carRepairShopRateList.findAll { it.brand.id != params.brandId && it.malfunction.id != params.malfunctionId && it.model.id != params.modelId }
        List<CarRepairShopRate> carRepairShopRateSortedList = []
        carRepairShopRateSortedList.addAll(carRepairShopRateListMalfunction)
        carRepairShopRateSortedList.addAll(carRepairShopRateListModel)
        carRepairShopRateSortedList.addAll(carRepairShopRateListBrand)
        carRepairShopRateSortedList.addAll(carRepairShopRateListDifferent)
        return ["opinions": carRepairShopRateSortedList]
    }

    @PostMapping(path = "malfunction-history")
    Map displayMalfunctionHistory(Principal principal) {
        UsernamePasswordAuthenticationToken auth = principal as UsernamePasswordAuthenticationToken
        AppUser appUser = auth.getPrincipal() as AppUser

        List<CarRepairShopRate> carRepairShopRateList = carRepairShopRateRepository.findAllByAppUserId(appUser.id)
        return ["carRepairShopRateList": carRepairShopRateList]
    }

    private static Double avgRate(List<Double> carRepairShopRates) {
        Integer carRepairShopRateSize = carRepairShopRates.size()
        Double sumRates = 0
        for (Double rate : carRepairShopRates) {
            sumRates += rate
        }
        if (carRepairShopRateSize == 0) {
            return 0
        }
        return sumRates / carRepairShopRateSize
    }

}
