package com.mensal3.mensal3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.repositories.TextoRepository;

@Service
public class TextoService {

    @Autowired
    private TextoRepository textoRepository;

    public TextoEntity registrarTexto(TextoEntity textoEntity) {
		return textoRepository.save(textoEntity);
	}
	
	public List<TextoEntity> listAllTexto() {
		return textoRepository.findAll();
	}
	
	public List<TextoEntity> buscarTextoTag(String tagName) {
		return textoRepository.findByTag_TituloTag(tagName);
	}
	
	public List<TextoEntity> buscarTextoCategoria(String categoriaName) {
		return textoRepository.findByCategoria_TituloCategoria(categoriaName);
	}
	
	public void deleteTexto(Long idTexto) {
		textoRepository.deleteById(idTexto);
	}
	
	public TextoEntity alterarTitulo(Long idTexto, TextoEntity novaTextoEntity) throws Exception{
		Optional<TextoEntity> textoExistenteOpt = textoRepository.findById(idTexto);
		
		if (textoExistenteOpt.isPresent()) {
			TextoEntity textoExistente = textoExistenteOpt.get();
			
			textoExistente.setTituloTexto(novaTextoEntity.getTituloTexto());
			
			return textoRepository.save(textoExistente);
		}
		else {
			throw new Exception("Texto " + idTexto + "não encontrada!");
		}
	}
	
	public TextoEntity alterarConteudo(Long idTexto, TextoEntity novaTextoEntity) throws Exception{
		Optional<TextoEntity> textoExistenteOpt = textoRepository.findById(idTexto);
		
		if (textoExistenteOpt.isPresent()) {
			TextoEntity textoExistente = textoExistenteOpt.get();
			
			textoExistente.setConteudoTexto(novaTextoEntity.getConteudoTexto());
			
			return textoRepository.save(textoExistente);
		}
		else {
			throw new Exception("Texto " + idTexto + "não encontrada!");
		}
	}
}