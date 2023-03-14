package com.review.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.review.api.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.review.api.entity.Review;
import com.review.api.service.GameService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameService service;

    //  @PreAuthorize 어노테이션을 이용하여 hasAuthority('ROLE_ADMIN')이라는 표현식을 사용하여 admin 권한을 가진 사용자만 해당 메서드를 호출할 수 있도록 설정

    // 게임 정보 생성 API
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game createdGame = service.createGame(game);
        return ResponseEntity.created(URI.create("/games/" + createdGame.getId())).body(createdGame);
    }

    // 게임 정보 수정 API
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game updatedGame = service.updateGame(id, game);
        return ResponseEntity.ok(updatedGame);
    }

    // 게임 정보 삭제 API
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        service.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    // 게임 정보 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        Game game = service.getGame(id);
        return ResponseEntity.ok(game);
    }

    // 모든 게임 정보 조회 API
    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        List<Game> games = service.getGames();
        return ResponseEntity.ok(games);
    }


}
