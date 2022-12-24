package com.noose.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noose.todo.controller.dto.request.NoteRequest;
import com.noose.todo.domain.note.Note;
import com.noose.todo.exception.TodoException;
import com.noose.todo.service.NoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[RestController] NoteController 테스트")
@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private NoteService noteService;

    @DisplayName("[POST] Note 생성 - 정상 호출")
    @Test
    void createNote() throws Exception {
        NoteRequest request = new NoteRequest("title", "body", List.of("todo1", "todo2"));

        mvc.perform(post("/api/v1/notes")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.title").value("title"))
                .andExpect(jsonPath("$.result.body").value("body"))
                .andExpect(jsonPath("$.result.todos[0].contents").value("todo1"))
                .andExpect(jsonPath("$.result.todos[1].contents").value("todo2"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[GET] Note 조회 - 정상 호출")
    @Test
    void getNotes() throws Exception {
        given(noteService.searchAll()).willReturn(List.of());

        mvc.perform(get("/api/v1/notes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[GET] Note 단건 조회 - 정상 호출")
    @Test
    void getNote() throws Exception {
        given(noteService.searchById(anyLong())).willReturn(Note.of("title", "body"));

        mvc.perform(get("/api/v1/notes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[GET] Note 단건 조회 - 예외")
    @Test
    void getNoteException() throws Exception {
        given(noteService.searchById(anyLong())).willThrow(new TodoException(HttpStatus.NOT_FOUND, "not found"));

        mvc.perform(get("/api/v1/notes/1"))
                .andExpect(status().isNotFound());
    }
}
