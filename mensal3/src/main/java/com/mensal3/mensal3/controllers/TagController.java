package com.mensal3.mensal3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.services.TagService;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
	private TagService tagService;
	
	@PostMapping("/registrarTag")
	public ResponseEntity<TagEntity> registrarTag(@Validated @RequestBody TagEntity tagEntity) {
		try {
			return ResponseEntity.ok(tagService.registrarTag(tagEntity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping("/listarTags")
	public ResponseEntity<List<TagEntity>> listAllTag() {
		try {
			return ResponseEntity.ok(tagService.listAllTag());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@DeleteMapping("/deletarTag/{idTag}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long idTag) {
		try {
	        tagService.deleteTag(idTag);
	        return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PutMapping("/alterarTag/{idTag}")
	public ResponseEntity<TagEntity> alterarTag(@PathVariable Long idTag, @RequestBody TagEntity novaTagEntity) {
		try {
			TagEntity tagAlterada = tagService.alterarTag(idTag, novaTagEntity);
			return ResponseEntity.ok(tagAlterada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}