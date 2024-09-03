// package com.mensal3.mensal3.services;

// import org.springframework.stereotype.Service;

// @Service
// public class CategoriaService {

    // @Autowired
    // private CategoriaRepository categoriaRepository;

    // public CategoriaEntity registrarCategoria(CategoriaEntity categoriaEntity) {
	// 	return categoriaRepository.save(categoriaEntity);
	// }
	
	// public List<CategoriaEntity> listAllCategoria() {
	// 	return categoriaRepository.findAll();
	// }
	
	// public void deleteCategoria(Long idCategoria) {
	// 	categoriaRepository.deleteById(idCategoria);
	// }
	
	// public CategoriaEntity alterarCategoria(Long idCategoria, CategoriaEntity novaCategoriaEntity) throws Exception{
	// 	Optional<CategoriaEntity> categoriaExistenteOpt = categoriaRepository.findById(idCategoria);
		
	// 	if (categoriaExistenteOpt.isPresent()) {
	// 		CategoriaEntity categoriaExistente = categoriaExistenteOpt.get();
			
	// 		categoriaExistente.setTituloCategoria(novaCategoriaEntity.getTituloCategoria());
			
	// 		return categoriaRepository.save(categoriaExistente);
	// 	}
	// 	else {
	// 		throw new Exception("Categoria " + idCategoria + "não encontrada!");
	// 	}
	// }
// }