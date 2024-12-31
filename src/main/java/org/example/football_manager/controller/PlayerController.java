package org.example.football_manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.dto.PlayerRequestForAdd;
import org.example.football_manager.dto.PlayerResponseForView;
import org.example.football_manager.entity.Player;
import org.example.football_manager.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping("/player/save")
    public ResponseEntity<?> savePlayer(@RequestBody @Valid PlayerRequestForAdd dto,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Player player = playerService.save(dto);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerResponseForView>> getAll() {
        List<PlayerResponseForView> players = playerService.getAllInResponseView();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<PlayerResponseForView> getPlayer(@PathVariable Long id) {
        PlayerResponseForView byIdInResponseView = playerService.getByIdInResponseView(id);
        return new ResponseEntity<>(byIdInResponseView, HttpStatus.OK);
    }

    @DeleteMapping("/player/{id}/delete")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id) {
        playerService.deleteById(id);
        return new ResponseEntity<>("Player with id " + id + " was success deleted", HttpStatus.OK);
    }
}
