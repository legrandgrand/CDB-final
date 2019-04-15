package webapp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.ComputerDto;
import exception.ComputerValidationException;

import service.ComputerService;

@RestController
@RequestMapping("/computer")
public class ComputerControllerRest {
  private static final Logger logger = LoggerFactory.getLogger(ComputerControllerRest.class);
  
  private ComputerService serviceComputer;

  @Autowired
  public ComputerControllerRest(ComputerService serviceComputer) {
    this.serviceComputer = serviceComputer;
  }
  
  @GetMapping("/{id}")
  public ComputerDto getComputer(@PathVariable long id) {
    return serviceComputer.getFromId(id).get(0);
  }
  
  @GetMapping("/")
  public List<ComputerDto> getAll() {
    return serviceComputer.list();
  }
  
  @PostMapping("/")
  public ResponseEntity<ComputerDto> AddComputer(@RequestBody ComputerDto dto){
    
    try {
      serviceComputer.add(dto);
    } catch (ComputerValidationException invalidComputer) {
      logger.error(invalidComputer.getMessage(), invalidComputer);
      return new ResponseEntity<ComputerDto>(dto, HttpStatus.BAD_REQUEST); 
    }
    
    return new ResponseEntity<ComputerDto>(dto, HttpStatus.OK); 
  }
  
  @PatchMapping("/")
  public ResponseEntity<ComputerDto> updateComputer(@RequestBody ComputerDto dto){
    
    try {
      serviceComputer.update(dto);
    } catch (ComputerValidationException invalidComputer) {
      logger.error(invalidComputer.getMessage(), invalidComputer);
      return new ResponseEntity<ComputerDto>(dto, HttpStatus.BAD_REQUEST); 
    }
    
    return new ResponseEntity<ComputerDto>(dto, HttpStatus.OK); 
  }
  
  @DeleteMapping("/")
  public ResponseEntity<ComputerDto> deleteComputer(@RequestBody ComputerDto dto){
    
    try {
      serviceComputer.delete(dto);
    } catch (ComputerValidationException invalidComputer) {
      logger.error(invalidComputer.getMessage(), invalidComputer);
      return new ResponseEntity<ComputerDto>(dto, HttpStatus.BAD_REQUEST); 
    }
    
    return new ResponseEntity<ComputerDto>(dto, HttpStatus.OK); 
  }

}
