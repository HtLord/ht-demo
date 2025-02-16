package ht.demo.repository;

import ht.demo.entity.LocaleCoinName;
import ht.demo.entity.LocaleCoinNameId;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocaleCoinNameRepository extends ListCrudRepository<LocaleCoinName, LocaleCoinNameId> {

    List<LocaleCoinName> findById_LocaleIs(String locale);
}
