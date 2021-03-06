package com.medievalgrosbill.database.cards;

import com.medievalgrosbill.database.base.BaseCRUDRepository;
import com.medievalgrosbill.models.cards.Card;
import com.medievalgrosbill.models.cards.Equipment;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends BaseCRUDRepository<Card> {	
    
	List<Card> findByNameContains(String name);
    List<Card> findByDtype(String name);
}
