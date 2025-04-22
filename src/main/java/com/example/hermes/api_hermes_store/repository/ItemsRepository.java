package com.example.hermes.api_hermes_store.repository;

import com.example.hermes.api_hermes_store.model.Items;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ItemsRepository extends JpaRepository<Items, Long> {

}
