package ht.demo.repository;

import ht.demo.entity.LocaleCoinName;
import ht.demo.entity.LocaleCoinNameId;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaleCoinNameRepository extends ListCrudRepository<LocaleCoinName, LocaleCoinNameId> {

    LocaleCoinName findById_Locale(String locale);
}
