package pl.karolinahenig.podiazd.rate

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepairShopRateRepository extends CrudRepository<CarRepairShopRate, Long> {
    List<CarRepairShopRate> findAllByCarRepairShopId(long cityId)
}