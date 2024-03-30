package pl.karolinahenig.podiazd.locality

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepairShopRepository extends CrudRepository<CarRepairShop, Long> {
    List<CarRepairShop> findAllByCityId(long cityId)
    List<CarRepairShop> findAll()
}