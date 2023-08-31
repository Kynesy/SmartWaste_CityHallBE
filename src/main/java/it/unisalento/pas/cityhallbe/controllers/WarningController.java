package it.unisalento.pas.cityhallbe.controllers;

import it.unisalento.pas.cityhallbe.domains.Warning;
import it.unisalento.pas.cityhallbe.dto.WarningDTO;
import it.unisalento.pas.cityhallbe.services.IWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/warning")
public class WarningController {
    private final IWarningService warningService;

    @Autowired
    public WarningController(IWarningService warningService) {
        this.warningService = warningService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createWarning(@RequestBody WarningDTO warningDTO){
        Warning warning = fromWarningDTOtoWarning(warningDTO);

        if(warningService.createWarning(warning) == 0){
            return ResponseEntity.ok("{\"message\": \"Warning created\"}");
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{warningId}")
    public ResponseEntity<String> deleteWarning(@PathVariable String warningId){
        if(warningService.deleteWarning(warningId) == 0){
            return ResponseEntity.ok("{\"message\": \"Warning deleted\"}");
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<ArrayList<WarningDTO>> getAllUserWarning(@PathVariable String userId){
        ArrayList<WarningDTO> warningListDTO = new ArrayList<>();
        ArrayList<Warning> warningList = warningService.getAllByUser(userId);

        if(warningList != null){
            for (Warning warn :
                    warningList) {
                WarningDTO warnDTO = fromWarningToWarningDTO(warn);
                warningListDTO.add(warnDTO);
            }
            return ResponseEntity.ok(warningListDTO);
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

    private WarningDTO fromWarningToWarningDTO(Warning warn) {
        WarningDTO warningDTO = new WarningDTO();

        warningDTO.setId(warn.getId());
        warningDTO.setUserId(warn.getUserId());
        warningDTO.setMessage(warn.getMessage());

        return warningDTO;
    }

    private Warning fromWarningDTOtoWarning(WarningDTO warningDTO) {
        Warning warning = new Warning();

        warning.setId(warningDTO.getId());
        warning.setUserId(warningDTO.getUserId());
        warning.setMessage(warningDTO.getMessage());

        return warning;
    }
}
