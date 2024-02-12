package pl.karolinahenig.podiazd.malfunction

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MalfunctionRepository extends CrudRepository<Malfunction, Long> {
    List<Malfunction> findAllByCategoryId(long categoryId)
}