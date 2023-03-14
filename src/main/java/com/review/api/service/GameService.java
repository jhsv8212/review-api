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
    public Game updateGame(Long id, Game game) {
        // toBuilder 사용하여 업데이트 항목만 적용
        Game existingGame = getGame(id);
        existingGame.toBuilder()
                .title(game.getTitle())
                .developer(game.getDeveloper())
                .releaseYear(game.getReleaseYear())
                .build();
        return gameRepository.save(existingGame);
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
