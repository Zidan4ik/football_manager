package org.example.football_manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.dto.PlayerRequestForTransfer;
import org.example.football_manager.dto.TeamRequestForAdd;
import org.example.football_manager.dto.TeamResponseForView;
import org.example.football_manager.entity.Team;
import org.example.football_manager.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<List<TeamResponseForView>> getAll() {
        List<TeamResponseForView> teams = teamService.getAllInResponseForView();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<TeamResponseForView> getById(@PathVariable Long id) {
        TeamResponseForView team = teamService.getByIdResponseForView(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping("/team/save")
    public ResponseEntity<?> saveTeam(@RequestBody @Valid TeamRequestForAdd dto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Team team = teamService.save(dto);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @DeleteMapping("/team/{id}/delete")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        teamService.deleteById(id);
        return new ResponseEntity<>("Team with id " + id + " was success deleted", HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferPlayer(@RequestBody @Valid PlayerRequestForTransfer request,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        teamService.transferPlayer(request);
        return new ResponseEntity<>("Player was success transferred at another team!", HttpStatus.OK);
    }
}
