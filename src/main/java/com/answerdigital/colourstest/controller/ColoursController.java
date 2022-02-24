package com.answerdigital.colourstest.controller;

import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.repository.ColoursRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Colours")
public class ColoursController {

    @Autowired
    private ColoursRepository coloursRepository;

    @GetMapping
    public ResponseEntity<List<Colour>> getColours() {
        return new ResponseEntity(coloursRepository.findAll(), HttpStatus.OK);
    }

    //Optional
    @PostMapping
    public Colour createColour(@RequestBody Colour newColour){
        return coloursRepository.save(newColour);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colour> getSingleColour(@PathVariable("id") long id){
        Optional<Colour> colourSearched = coloursRepository.findById(id);
        if(colourSearched.isPresent()){
            return new ResponseEntity<Colour>(colourSearched.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<Colour>(HttpStatus.NOT_FOUND);
        }
    }
}
