package com.pet.manager.crontroller;


import com.pet.manager.crontroller.request.PetRQ;
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

    @PutMapping("/pet/{id}")
    public ResponseEntity updatePetById(@PathVariable(value = "id") String petId, @RequestBody PetRQ petRQ){
        petService.updatedPetById(petId, petRQ);
        return ResponseEntity.created(URI.create("/pet/"+ petId)).body("Updated");
    }

    @GetMapping("/pets")
    public List<Pet> getAllPets(){
        return petService.getPets();
    }

    @GetMapping("/pets/{type}")
    public List<Pet> getPetsByType(@PathVariable(value = "type") String type) {
        // -> Not unique index on type attribute which is not unique
        // -> Create A unique index on the Name field
        // -> CommandLineRunnerBean
        // -> Map the exception of having multiple names to a 409(CONFLICT statusCode)

        return petService.getPetsByType(type);
    /*   try {
           insert
       } catch(IndexViolationException e){
           throw new DuplicatedPetException() -> Exception handler to 409
       }*/
    }

    @PostMapping("/pet")
    public ResponseEntity<Pet> createPet(@RequestBody PetRQ petRQ){
        return ResponseEntity.ok(petService.createPet(petRQ));
    }


    @DeleteMapping("/pet/{id}")
    public ResponseEntity deletePetById(@PathVariable(value = "id")  String id){
        petService.deletePetById(id);
        return ResponseEntity.created(URI.create("/pet")).body("Deleted");
    }
}
