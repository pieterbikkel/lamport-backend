package nl.han.aim.oosevt.lamport.controllers.area;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.CreateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.UpdateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.services.area.AreaService;
import nl.han.aim.oosevt.lamport.shared.Permission;
import nl.han.aim.oosevt.lamport.shared.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/areas")
@CrossOrigin
public class AreaController {
    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("")
    @Permission(permission = Permissions.GET_AREAS)
    public ResponseEntity<List<AreaResponseDTO>> getAreas() {
        return new ResponseEntity<>(
                areaService.getAreas(),
                HttpStatus.OK
        );
    }

    @GetMapping("")
    @Permission(permission = Permissions.GET_AREAS)
    public ResponseEntity<List<AreaResponseDTO>> getAreas(@RequestParam("search") Optional<String> query) {
        return new ResponseEntity<>(
                query.isPresent() ? areaService.getAreasBySearch(query.get()) : areaService.getAreas(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    @Permission(permission = Permissions.DELETE_AREAS)
    public void deleteArea(@PathVariable("id") int id) {
        areaService.deleteArea(id);
    }

    @PutMapping()
    @Permission(permission = Permissions.UPDATE_AREAS)
    public void updateArea(@RequestBody UpdateAreaRequestDTO updateAreaRequestDTO) {
        areaService.updateArea(updateAreaRequestDTO);
    }

    @PostMapping()
    @Permission(permission = Permissions.CREATE_AREAS)
    public void createArea(@RequestBody CreateAreaRequestDTO createAreaRequestDTO) {
        areaService.createArea(createAreaRequestDTO);
    }
}
