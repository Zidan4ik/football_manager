package org.example.football_manager.service.imp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.dto.PlayerRequestForAdd;
import org.example.football_manager.dto.PlayerResponseForView;
import org.example.football_manager.entity.Player;
import org.example.football_manager.entity.Team;
import org.example.football_manager.mapper.PlayerMapper;
import org.example.football_manager.repository.PlayerRepository;
import org.example.football_manager.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImp implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper mapper = new PlayerMapper();
    @Override
    public Player save(Player player) {
        if (player.getId() != null) {
            Player playerById = getById(player.getId());
            playerById.setNickname(player.getNickname());
            playerById.setAge(playerById.getAge());
            playerById.setAmount_month(player.getAmount_month());
            playerById.setTeam(player.getTeam());
            return playerRepository.save(playerById);
        }
        return playerRepository.save(player);
    }

    @Override
    public Player save(PlayerRequestForAdd dto) {
        return save(mapper.toEntityForAdd(dto));
    }

    @Override
    public Player getById(Long id) {
        return playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Player with id: " + id + " was not found!"));
    }

    @Override
    public PlayerResponseForView getByIdInResponseView(Long id) {
        return mapper.toDTOForView(getById(id));
    }

    @Override
    public List<Player> getAllByTeam(Team team) {
        return playerRepository.findAllByTeam(team);
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<PlayerResponseForView> getAllInResponseView() {
        return getAll().stream().map(mapper::toDTOForView).toList();
    }

    @Override
    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
}
