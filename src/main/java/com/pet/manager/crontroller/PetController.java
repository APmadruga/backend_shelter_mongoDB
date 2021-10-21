package com.pet.manager.crontroller;
import com.pet.manager.crontroller.request.PetRQ;
import com.pet.manager.model.Feed;
import com.pet.manager.model.Pet;
import com.pet.manager.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/pet/{id}")
    public Pet getPetById(@PathVariable(value="id") String petId){
        return petService.getPetById(petId);
    }

    @GetMapping("/pet")
    public Pet getPetByName(@RequestParam String petName){
        return petService.getPetByName(petName);
    }

    @PutMapping("/pet-update/{id}")
    public ResponseEntity updatePetById(@PathVariable(value = "id") String petId, @RequestBody PetRQ petRQ){
        petService.updatedPetById(petId, petRQ);
        return ResponseEntity.created(URI.create("/pet/"+ petId)).body("Updated");
    }

    @GetMapping("/pets")
    public List<Pet> getAllPets(){
        return petService.getPets();
    }

    @GetMapping("/pet/{id}/feeds")
    public List<Feed> getFeedsFromPet(@PathVariable(value = "id") String petId){
        return petService.getFeedsById(petId);
    }

    @GetMapping("/pets/{type}")
    public List<Pet> getPetsByType(@PathVariable(value = "type") String type) {
        return petService.getPetsByType(type);
    }

    @PostMapping("/pet")
    public ResponseEntity<Pet> createPet(@RequestBody PetRQ petRQ){
        return ResponseEntity.ok(petService.createPet(petRQ));
    }


    @DeleteMapping("/pet-delete/{id}")
    public ResponseEntity deletePetById(@PathVariable(value = "id")  String id){
        petService.deletePetById(id);
        return ResponseEntity.created(URI.create("/pet")).body("Deleted");
    }
}
