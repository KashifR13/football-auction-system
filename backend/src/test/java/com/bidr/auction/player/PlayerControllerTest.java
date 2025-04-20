package com.bidr.auction.player;

import com.bidr.auction.controller.PlayerController;
import com.bidr.auction.entity.Player;
import com.bidr.auction.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    private MockMvc mockMvc;

    @Test
    void testGetAllPlayers() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();

        List<Player> players = Arrays.asList(
                new Player(1L, "John Doe", 25, "Forward", "https://example.com/profile1.jpg", "New York", "American", "johndoe@example.com", "+1234567890"),
                new Player(2L, "Jane Smith", 22, "Midfielder", "https://example.com/profile2.jpg", "Los Angeles", "Canadian", "janesmith@example.com", "+0987654321")
        );

        when(playerService.getAllPlayers()).thenReturn(players);

        mockMvc.perform(get("/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].profilePicture").value("https://example.com/profile1.jpg"))
                .andExpect(jsonPath("$[0].addressCity").value("New York"))
                .andExpect(jsonPath("$[0].nationality").value("American"))
                .andExpect(jsonPath("$[0].email").value("johndoe@example.com"))
                .andExpect(jsonPath("$[0].phoneNumber").value("+1234567890"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].profilePicture").value("https://example.com/profile2.jpg"))
                .andExpect(jsonPath("$[1].addressCity").value("Los Angeles"))
                .andExpect(jsonPath("$[1].nationality").value("Canadian"))
                .andExpect(jsonPath("$[1].email").value("janesmith@example.com"))
                .andExpect(jsonPath("$[1].phoneNumber").value("+0987654321"));

        verify(playerService, times(1)).getAllPlayers();
    }

    @Test
    void testGetPlayerById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();

        Player player = new Player(1L, "John Doe", 25, "Forward", "https://example.com/profile.jpg", "New York", "American", "johndoe@example.com", "+1234567890");

        when(playerService.getPlayerById(1L)).thenReturn(player);

        mockMvc.perform(get("/players/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.profilePicture").value("https://example.com/profile.jpg"))
                .andExpect(jsonPath("$.addressCity").value("New York"))
                .andExpect(jsonPath("$.nationality").value("American"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("+1234567890"));

        verify(playerService, times(1)).getPlayerById(1L);
    }

    @Test
    void testCreatePlayer() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();

        Player player = new Player();
        player.setName("John Doe");
        player.setAge(25);
        player.setPosition("Forward");
        player.setProfilePicture("https://example.com/profile.jpg");
        player.setAddressCity("New York");
        player.setNationality("American");
        player.setEmail("johndoe@example.com");
        player.setPhoneNumber("+1234567890");

        when(playerService.createPlayer(any(Player.class))).thenReturn(player);

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"age\":25,\"position\":\"Forward\",\"profilePicture\":\"https://example.com/profile.jpg\",\"addressCity\":\"New York\",\"nationality\":\"American\",\"email\":\"johndoe@example.com\",\"phoneNumber\":\"+1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.profilePicture").value("https://example.com/profile.jpg"))
                .andExpect(jsonPath("$.addressCity").value("New York"))
                .andExpect(jsonPath("$.nationality").value("American"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("+1234567890"));

        verify(playerService, times(1)).createPlayer(any(Player.class));
    }

    @Test
    void testUpdatePlayer() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();

        Player updatedPlayer = new Player(1L, "John Smith", 26, "Midfielder", "https://example.com/profile.jpg", "Los Angeles", "Canadian", "johnsmith@example.com", "+0987654321");

        when(playerService.updatePlayer(eq(1L), any(Player.class))).thenReturn(updatedPlayer);

        mockMvc.perform(put("/players/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Smith\",\"age\":26,\"position\":\"Midfielder\",\"profilePicture\":\"https://example.com/profile.jpg\",\"addressCity\":\"Los Angeles\",\"nationality\":\"Canadian\",\"email\":\"johnsmith@example.com\",\"phoneNumber\":\"+0987654321\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Smith"))
                .andExpect(jsonPath("$.addressCity").value("Los Angeles"))
                .andExpect(jsonPath("$.nationality").value("Canadian"))
                .andExpect(jsonPath("$.email").value("johnsmith@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("+0987654321"));

        verify(playerService, times(1)).updatePlayer(eq(1L), any(Player.class));
    }

    @Test
    void testDeletePlayer() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();

        doNothing().when(playerService).deletePlayer(1L);

        mockMvc.perform(delete("/players/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(playerService, times(1)).deletePlayer(1L);
    }

}