package com.pet.manager.service;

import com.pet.manager.crontroller.request.PetRQ;
import com.pet.manager.crontroller.response.PetRS;
import com.pet.manager.exception.DuplicatePetException;
import com.pet.manager.exception.ResourceNotFound;
import com.pet.manager.model.Pet;
import com.pet.manager.model.PetType;
import com.pet.manager.repository.PetRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    @Transactional
    public Pet createPet(PetRQ petRQ) {
        try {
            return petRepository.insert(
                    Pet
                    .builder()
                    .firstName(petRQ.getFirstName())
                    .age(petRQ.getAge())
                            .petType(petRQ.getPetType()).build());
        } catch (DuplicateKeyException e) {
            throw new DuplicatePetException();
        }
    }


    public Pet getPetById(String petid) {
        try {
           return petRepository.findById(petid).get();
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound("Can't find pet");
        }
    }

    @Transactional
    public void deletePetById(String id) {
        try {
            petRepository.deleteById(id);
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound("Can't find pet");
        }
    }

    public Pet getPetByName(String petName) {
        try {
           return petRepository.findPetByFirstName(petName).get();
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound("Can't find pet");
        }
    }

    @Transactional
    public Pet updatedPetById(String petId, PetRQ petRQ) {
        try {
            petRepository.findById(petId);
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound("Can't find pet");
        }
        int age = petRQ.getAge();
        String firstName = petRQ.getFirstName();
        if(petRepository.findPetByFirstName(firstName).isPresent()){
            throw new DuplicatePetException("You have to choose a different Name");
        }
        PetType petType = petRQ.getPetType();
        Pet pet = petRepository.findById(petId).get();
        pet.setFirstName(firstName);
        pet.setPetType(petType);
        pet.setAge(age);
        return petRepository.save(pet);
    }

    public List<Pet> getPetsByType(String type) {
        List<String> listTypes = new ArrayList<String>();
        listTypes.add(PetType.CAT.toString());
        listTypes.add(PetType.DOG.toString());
        listTypes.add(PetType.EAGLE.toString());
        if(!listTypes.contains(type)){
            throw new ResourceNotFound("Such Pet Type Does not Exist");
        }
        return petRepository.findPetByPetType(PetType.valueOf(type));
        //List<Pet> allPets = petRepository.findAll();

        //return allPets.stream().filter(x -> x.getPetType().toString() == type).collect(Collectors.toList());
    }

}
