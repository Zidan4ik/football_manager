package org.example.football_manager.config;

import lombok.RequiredArgsConstructor;
import org.example.football_manager.entity.Player;
import org.example.football_manager.entity.Team;
import org.example.football_manager.service.PlayerService;
import org.example.football_manager.service.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DatabaseLoader implements CommandLineRunner {
    private final PlayerService playerService;
    private final TeamService teamService;

    @Override
    public void run(String... args) {
        if(teamService.getAll().isEmpty()){
            Team team1 = new Team("Barcelona",6,100_000);
            Team team2 = new Team("Manchester United",0,100_000);
            Team team3 = new Team("Real Madrid",8,100_000);
            teamService.save(team1);
            teamService.save(team2);
            teamService.save(team3);
        }
        if (playerService.getAll().isEmpty()){
            Player player1 = new Player("Messi",18,20,null);
            Player player2 = new Player("Ronaldo",17,17,null);
            Player player3 = new Player("Neymar",19,14,null);
            Player player4 = new Player("Mbappé",20,8,null);

            Team team = teamService.getById(1L);
            Player player5 = new Player("Haaland ",24,-3,team);

            playerService.save(player1);
            playerService.save(player2);
            playerService.save(player3);
            playerService.save(player4);
            playerService.save(player5);
        }
    }
}
