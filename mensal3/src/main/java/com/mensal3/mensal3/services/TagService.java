// package com.mensal3.mensal3.services;

// import org.springframework.stereotype.Service;

// @Service
// public class TagService {

    // @Autowired
    // private TagRepository tagRepository;

	// public TagEntity registrarTag(TagEntity tagEntity) {
	// 	return tagRepository.save(tagEntity);
	// }
	
	// public List<TagEntity> listAllTag() {
	// 	return tagRepository.findAll();	
	// }
	
	// public void deleteTag(Long idTag) {
	// 	tagRepository.deleteById(idTag);
	// }
	
	// public TagEntity alterarTag(Long idTag, TagEntity novaTagEntity) throws Exception{
	// 	Optional<TagEntity> tagExistenteOpt = tagRepository.findById(idTag);
		
	// 	if (tagExistenteOpt.isPresent()) {
	// 		TagEntity tagExistente = tagExistenteOpt.get();
			
	// 		tagExistente.setTituloTag(novaTagEntity.getTituloTag());
			
	// 		return tagRepository.save(tagExistente);
	// 	}
	// 	else {
	// 		throw new Exception("Tag " + idTag + "não encontrada!");
	// 	}
	// }
// }