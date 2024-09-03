// package com.mensal3.mensal3.services;

// import org.springframework.stereotype.Service;

// @Service
// public class TextoService {

    // @Autowired
    // private TextoRepository textoRepository;

    // public TextoEntity registrarTexto(TextoEntity textoEntity) {
	// 	return textoRepository.save(textoEntity);
	// }
	
	// public List<TextoEntity> listAllTexto() {
	// 	return textoRepository.findAll();
	// }
	
	// public List<TextoEntity> buscarTextoTag(String tagName) {
	// 	return textoRepository.findByTags(tagName);
	// }
	
	// public List<TextoEntity> buscarTextoCategoria(String categoriaName) {
	// 	return textoRepository.findByCategoria(categoriaName);
	// }
	
	// public void deleteTexto(Long idTexto) {
	// 	textoRepository.deleteById(idTexto);
	// }
	
	// public TextoEntity alterarTitulo(Long idTexto, TextoEntity novaTextoEntity) throws Exception{
	// 	Optional<TextoEntity> textoExistenteOpt = textoRepository.findById(idTexto);
		
	// 	if (textoExistenteOpt.isPresent()) {
	// 		TextoEntity textoExistente = textoExistenteOpt.get();
			
	// 		textoExistente.setTituloTexto(novaTextoEntity.getTituloTexto());
			
	// 		return textoRepository.save(textoExistente);
	// 	}
	// 	else {
	// 		throw new Exception("Texto " + idTexto + "não encontrada!");
	// 	}
	// }
	
	// public TextoEntity alterarConteudo(Long idTexto, TextoEntity novaTextoEntity) throws Exception{
	// 	Optional<TextoEntity> textoExistenteOpt = textoRepository.findById(idTexto);
		
	// 	if (textoExistenteOpt.isPresent()) {
	// 		TextoEntity textoExistente = textoExistenteOpt.get();
			
	// 		textoExistente.setConteudoTexto(novaTextoEntity.getConteudoTexto());
			
	// 		return textoRepository.save(textoExistente);
	// 	}
	// 	else {
	// 		throw new Exception("Texto " + idTexto + "não encontrada!");
	// 	}
	// }
// }