package com.mensal3.mensal3.controllersTest;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mensal3.mensal3.controllers.TagController;
import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.repositories.TagRepository;

@SpringBootTest
public class TagControllerTests {
    
    @Autowired
    TagController tagController;

    @MockBean
    TagRepository tagRepository;

    @BeforeEach
    void setUp() {
        List<TagEntity> listaTags = new ArrayList<>();
    }

    @Test
    @DisplayName("")
    void listarTagsTest() {

    }
}
