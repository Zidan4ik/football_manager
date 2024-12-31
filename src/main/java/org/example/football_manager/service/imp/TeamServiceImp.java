package org.example.football_manager.service.imp;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.dto.PlayerRequestForTransfer;
import org.example.football_manager.dto.TeamRequestForAdd;
import org.example.football_manager.dto.TeamResponseForView;
import org.example.football_manager.entity.Player;
import org.example.football_manager.entity.Team;
import org.example.football_manager.exception.InsufficientFundsException;
import org.example.football_manager.mapper.TeamMapper;
import org.example.football_manager.repository.TeamRepository;
import org.example.football_manager.service.PlayerService;
import org.example.football_manager.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImp implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper mapper = new TeamMapper();
    private final PlayerService playerService;

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team save(TeamRequestForAdd dto) {
        return save(mapper.toEntityForAdd(dto));
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<TeamResponseForView> getAllInResponseForView() {
        return getAll().stream().map(mapper::toDTOForView).toList();
    }

    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Team with id: " + id + " was not found!"));
    }

    @Override
    public TeamResponseForView getByIdResponseForView(Long id) {
        return mapper.toDTOForView(getById(id));
    }

    @Override
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void transferPlayer(PlayerRequestForTransfer request) {
        Player player = playerService.getById(request.getPlayerId());
        Team buyingTeam = getById(request.getTeamBuyingId());
        Team sellingTeam = player.getTeam();
        int fullSumma = getFullSumma(player, sellingTeam, buyingTeam);
        decreaseBalance(buyingTeam, fullSumma);
        save(buyingTeam);
        if (sellingTeam != null) { // Якщо немає поточної команди в гравця, то не буде списуватися кошти з команди
            increaseBalance(sellingTeam, fullSumma);
            save(sellingTeam);
        }
        player.setTeam(buyingTeam);
        playerService.save(player);
    }

    private static int getFullSumma(Player player, Team sellingTeam, Team buyingTeam) {
        int priceTransfer = (player.getAmount_month() * 100_000) / player.getAge();
        int commission = sellingTeam != null ? sellingTeam.getCommission() : 0; // Якщо немає поточної команди в гравця, то не буде комісії
        int commissionFromSellingTeam = priceTransfer + commission / 100;
        int fullSumma = priceTransfer + commissionFromSellingTeam;

        if (buyingTeam.getBalance() < fullSumma) {
            throw new InsufficientFundsException("Not enough balance in the buying team to complete the transfer.\nBalance team (" + buyingTeam.getName() + "): " + buyingTeam.getBalance() + ".\nCost transfer: " + fullSumma + ".");
        }
        return fullSumma;
    }

    @Override
    @Transactional
    public void decreaseBalance(Team team, int amountMoney) {
        team.setBalance(team.getBalance() - amountMoney);
        save(team);
    }

    @Override
    @Transactional
    public void increaseBalance(Team team, int amountMoney) {
        team.setBalance(team.getBalance() + amountMoney);
        save(team);
    }
}
