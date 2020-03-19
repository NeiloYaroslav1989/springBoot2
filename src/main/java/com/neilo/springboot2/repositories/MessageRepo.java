package com.neilo.springboot2.repositories;

import org.springframework.data.repository.CrudRepository;
import com.neilo.springboot2.dao.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);

}
