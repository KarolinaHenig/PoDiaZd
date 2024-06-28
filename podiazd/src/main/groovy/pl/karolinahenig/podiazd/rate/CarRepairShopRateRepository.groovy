package pl.karolinahenig.podiazd.rate

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepairShopRateRepository extends CrudRepository<CarRepairShopRate, Long> {
    List<CarRepairShopRate> findAllByCarRepairShopId(long cityId)

    List<CarRepairShopRate> findAllByAppUserId(long appUserId)

    @Query(
            value = """
                        select crs.id, (select  avg(rate) from car_repair_shop_rate where car_repair_shop_id = crs.id) as average,
                        (select  avg(rate) from car_repair_shop_rate where car_repair_shop_id = crs.id and brand_id = ?1) as averageBrand,
                        (select  avg(rate) from car_repair_shop_rate where car_repair_shop_id = crs.id and malfunction_id =?2) averageMalfunction,
                        case when crs.city_id = ?3 then 1
                        when crs.city_id != ?3 and crs.county_id = ?4 then 2
                        when crs.city_id != ?3 and crs.county_id != ?4 and crs.voivodeship_id = ?5 then 3
                        else 4
                        end as display_order
                        from car_repair_shop  as crs
                        order by display_order 
                        limit 20
                        """,
            nativeQuery = true
    )
    List<Map<String, Object>> findBestsRates(long brandId, long malfunctionId, long cityId, long countyId, long voivodeshipId)

}