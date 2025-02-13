package ht.demo.repository;

import ht.demo.entity.Coin;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends ListCrudRepository<Coin, String> {
}
