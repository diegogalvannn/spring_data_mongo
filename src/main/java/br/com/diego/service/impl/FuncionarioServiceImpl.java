package br.com.diego.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.model.Funcionario;
import br.com.diego.repository.FuncionarioRepository;
import br.com.diego.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public List<Funcionario> obterTodos() {
		return this.funcionarioRepository.findAll();
	}

	@Override
	public Funcionario obterPorCodigo(String codigo) {
		return this.funcionarioRepository
				.findById(codigo)
				.orElseThrow(()->new IllegalArgumentException("Funcionario não existe"));
	}

	@Override
	public Funcionario criar(Funcionario funcionario) {
		Funcionario chefe = this.funcionarioRepository
				.findById(funcionario.getChefe().getCodigo())
				.orElseThrow(()->new IllegalArgumentException("Chefe não existe"));
		
		funcionario.setChefe(chefe);
		
		return this.funcionarioRepository.save(funcionario);
	}

	@Override
	public List<Funcionario> obterFuncionariosPorRangeDeIdade(Integer de, Integer ate) {
		return this.funcionarioRepository.obterFuncionariosPorRangeDeIdade(de, ate);
	}

	@Override
	public List<Funcionario> obterFuncionariosPorNome(String nome) {
		return this.funcionarioRepository.findByNome(nome);
	}

}
