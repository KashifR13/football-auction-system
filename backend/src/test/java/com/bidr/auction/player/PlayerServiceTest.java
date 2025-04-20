package com.bidr.auction.player;

import com.bidr.auction.entity.Player;
import com.bidr.auction.exception.PlayerNotFoundException;
import com.bidr.auction.repository.PlayerRepository;
import com.bidr.auction.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void testGetPlayerById() {
        Player player = new Player();
        player.setId(1L);
        player.setName("John Doe");
        player.setAge(25);
        player.setPosition("Forward");
        player.setProfilePicture("https://example.com/profile.jpg");
        player.setAddressCity("New York");
        player.setNationality("American");
        player.setEmail("johndoe@example.com");
        player.setPhoneNumber("+1234567890");

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        Player result = playerService.getPlayerById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("https://example.com/profile.jpg", result.getProfilePicture());
        assertEquals("New York", result.getAddressCity());
        assertEquals("American", result.getNationality());
        assertEquals("johndoe@example.com", result.getEmail());
        assertEquals("+1234567890", result.getPhoneNumber());
        verify(playerRepository, times(1)).findById(1L);
    }

    @Test
    void testCreatePlayer() {
        Player player = new Player();
        player.setName("Jane Doe");
        player.setAge(22);
        player.setPosition("Midfielder");
        player.setProfilePicture("https://example.com/profile.jpg");
        player.setAddressCity("Los Angeles");
        player.setNationality("Canadian");
        player.setEmail("janedoe@example.com");
        player.setPhoneNumber("+0987654321");

        when(playerRepository.save(player)).thenReturn(player);

        Player result = playerService.createPlayer(player);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        assertEquals("https://example.com/profile.jpg", result.getProfilePicture());
        assertEquals("Los Angeles", result.getAddressCity());
        assertEquals("Canadian", result.getNationality());
        assertEquals("janedoe@example.com", result.getEmail());
        assertEquals("+0987654321", result.getPhoneNumber());
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    void testUpdatePlayer() {
        Player existingPlayer = new Player();
        existingPlayer.setId(1L);
        existingPlayer.setName("John Doe");
        existingPlayer.setAge(25);
        existingPlayer.setPosition("Forward");

        Player updatedPlayer = new Player();
        updatedPlayer.setId(1L);
        updatedPlayer.setName("John Smith");
        updatedPlayer.setAge(26);
        updatedPlayer.setPosition("Midfielder");
        updatedPlayer.setProfilePicture("https://example.com/profile.jpg");
        updatedPlayer.setAddressCity("Los Angeles");
        updatedPlayer.setNationality("Canadian");
        updatedPlayer.setEmail("johnsmith@example.com");
        updatedPlayer.setPhoneNumber("+0987654321");

        when(playerRepository.findById(1L)).thenReturn(Optional.of(existingPlayer));
        when(playerRepository.save(any(Player.class))).thenReturn(updatedPlayer);

        Player result = playerService.updatePlayer(1L, updatedPlayer);

        assertNotNull(result);
        assertEquals("John Smith", result.getName());
        verify(playerRepository, times(1)).findById(1L);
        verify(playerRepository, times(1)).save(updatedPlayer);
    }

    @Test
    void testDeletePlayer() {
        Player player = new Player();
        player.setId(1L);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        doNothing().when(playerRepository).delete(player);

        playerService.deletePlayer(1L);

        verify(playerRepository, times(1)).findById(1L);
        verify(playerRepository, times(1)).delete(player);
    }

    @Test
    void testGetPlayerById_PlayerNotFound() {
        when(playerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(PlayerNotFoundException.class, () -> playerService.getPlayerById(1L));
        assertEquals("Player with ID 1 not found", exception.getMessage());

        verify(playerRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdatePlayer_PlayerNotFound() {
        when(playerRepository.findById(1L)).thenReturn(Optional.empty());

        Player updatedPlayer = new Player();
        updatedPlayer.setName("John Smith");

        Exception exception = assertThrows(PlayerNotFoundException.class, () -> playerService.updatePlayer(1L, updatedPlayer));
        assertEquals("Player with ID 1 not found", exception.getMessage());

        verify(playerRepository, times(1)).findById(1L);
    }

    @Test
    void testDeletePlayer_PlayerNotFound() {
        when(playerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(PlayerNotFoundException.class, () -> playerService.deletePlayer(1L));
        assertEquals("Player with ID 1 not found", exception.getMessage());

        verify(playerRepository, times(1)).findById(1L);
    }

}