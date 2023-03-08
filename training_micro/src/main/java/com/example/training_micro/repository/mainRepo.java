package com.example.training_micro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.training_micro.model.mainModel;

public interface mainRepo extends JpaRepository<mainModel, Long> {
    List<mainModel> findByName(String name);
    List<mainModel> findByAge(String age);
}
