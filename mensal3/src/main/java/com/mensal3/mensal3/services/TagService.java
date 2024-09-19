package com.mensal3.mensal3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

	public TagEntity registrarTag(TagEntity tagEntity) {
		return tagRepository.save(tagEntity);
	}
	
	public List<TagEntity> listAllTag() {
		return tagRepository.findAll();	
	}

	// public TagEntity findById(Long idTag) {
	// 	return tagRepository.findById(idTag).get();
	// }
	
	public void deleteTag(Long idTag) throws Exception {
		TagEntity tagEntity = tagRepository.findById(idTag)
			.orElseThrow(() -> new Exception("Tag não encontrada"));
		
		tagRepository.delete(tagEntity);
	}
	
	public TagEntity alterarTag(Long idTag, TagEntity novaTagEntity) throws Exception{
		Optional<TagEntity> tagExistenteOpt = tagRepository.findById(idTag);
		
		if (tagExistenteOpt.isPresent()) {
			TagEntity tagExistente = tagExistenteOpt.get();
			tagExistente.setTituloTag(novaTagEntity.getTituloTag());
			return tagRepository.save(tagExistente);
		}
		else {
			throw new Exception("Tag " + idTag + "não encontrada!");
		}
	}
}