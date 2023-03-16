package com.review.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.review.api.entity.Game;
import com.review.api.repository.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.review.api.entity.Review;
import com.review.api.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    @Transactional
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Transactional
    public void updateGame(Long id, Game game) {

        Optional<Game> select = gameRepository.findById(id);
        select.ifPresent((selectGame ->{
            selectGame.setTitle(game.getTitle());
            selectGame.setDeveloper(game.getDeveloper());
            selectGame.setReleaseYear(game.getReleaseYear());

            gameRepository.save(selectGame);
        }));
    }

    @Transactional
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public Game getGame(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게임 정보가 존재하지 않습니다."));
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

}
