package com.todolist.Todo_List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.Todo_List.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
