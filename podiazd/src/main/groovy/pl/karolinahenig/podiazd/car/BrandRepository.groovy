package pl.karolinahenig.podiazd.car

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository extends CrudRepository<Brand, Long> {
}