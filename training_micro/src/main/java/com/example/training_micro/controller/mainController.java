package com.example.training_micro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.training_micro.model.mainModel;
import com.example.training_micro.repository.mainRepo;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class mainController {
    
    @Autowired
    mainRepo mainRepo;

    @GetMapping("/models")
    public ResponseEntity<List<mainModel>> getAllModel(@RequestParam(required = false) String nama) {
    try {
      List<mainModel> models = new ArrayList<mainModel>();

      if (nama == null)
        mainRepo.findAll().forEach(models::add);
      else
        mainRepo.findByName(nama).forEach(models::add);

      if (models.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(models, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/models/{id}")
  public ResponseEntity<mainModel> getTutorialById(@PathVariable("id") long id) {
    Optional<mainModel> modelData = mainRepo.findById(id);

    if (modelData.isPresent()) {
      return new ResponseEntity<>(modelData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/models")
  public ResponseEntity<mainModel> createTutorial(@RequestBody mainModel model) {
    try {
      mainModel _model = mainRepo
          .save(new mainModel(model.getName(), model.getAddress(), model.getAge()));
      return new ResponseEntity<>(_model, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/models/{id}")
  public ResponseEntity<mainModel> updateTutorial(@PathVariable("id") long id, @RequestBody mainModel model) {
    Optional<mainModel> modelData = mainRepo.findById(id);

    if (modelData.isPresent()) {
      mainModel _model = modelData.get();
      _model.setName(model.getName());
      _model.setAddress(model.getAddress());
      _model.setAge(model.getAge());
      return new ResponseEntity<>(mainRepo.save(_model), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/models/{id}")
  public ResponseEntity<HttpStatus> deleteModel(@PathVariable("id") long id) {
    try {
      mainRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/models")
  public ResponseEntity<HttpStatus> deleteAllModels() {
    try {
      mainRepo.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/models/age")
  public ResponseEntity<List<mainModel>> findByAge(String age) {
    try {
      List<mainModel> models = mainRepo.findByAge(age);

      if (models.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(models, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

