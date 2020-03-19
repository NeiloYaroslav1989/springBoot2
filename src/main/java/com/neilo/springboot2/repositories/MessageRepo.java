package com.neilo.springboot2.repositories;

import org.springframework.data.repository.CrudRepository;
import com.neilo.springboot2.dao.Message;

public interface MessageRepo extends CrudRepository<Message, Integer> {
}
