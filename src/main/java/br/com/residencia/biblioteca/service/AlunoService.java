package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.AlunoResumoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.AlunoRepository;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class AlunoService {

	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	EmprestimoRepository emprestimoRepository;

	public List<Aluno> getAllAlunos() {
		return alunoRepository.findAll();
	}

	public Aluno getAlunoById(Integer id) {

		return alunoRepository.findById(id).orElse(null);
	}

	public Aluno saveAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	public Aluno updateAluno(Aluno aluno, Integer id) {
		Aluno alunoExistenteNoBanco = alunoRepository.findById(id).get();

		alunoExistenteNoBanco.setBairro(aluno.getBairro());
		alunoExistenteNoBanco.setCidade(aluno.getCidade());
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento());
		alunoExistenteNoBanco.setCpf(aluno.getCpf());
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento());
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());

		return alunoRepository.save(alunoExistenteNoBanco);
	}

	public Aluno deleteAluno(Integer id) {
		alunoRepository.deleteById(id);
		return getAlunoById(id);
	}

	// DTO
	
	public AlunoDTO getAlunoDTOById(Integer id) {
		AlunoDTO alunoDTO = new AlunoDTO();
		Aluno aluno = alunoRepository.findById(id).orElse(null);
		
		alunoDTO = toDTO(aluno);
				
		return alunoDTO;
	}
	
	public List<AlunoDTO> getAllAlunosDTO() {
		List<Aluno> listaAluno = alunoRepository.findAll();
		List<AlunoDTO> listaAlunoDTO = new ArrayList<>();

		for (Aluno aluno : listaAluno) {

			AlunoDTO alunoDTO = toDTO(aluno);

			listaAlunoDTO.add(alunoDTO);
		}
		return listaAlunoDTO;

	}

	public AlunoDTO saveAlunoDTO(AlunoDTO AlunoDTO) {
		Aluno aluno = toEntidade(AlunoDTO);
		Aluno novoAluno = alunoRepository.save(aluno);

		AlunoDTO alunoAtualizadoDTO = toDTO(novoAluno);

		return alunoAtualizadoDTO;
	}

	public AlunoDTO updateAlunoDTO(AlunoDTO alunoDTO, Integer id) {

		Aluno alunoExistenteNoBanco = getAlunoById(id);
		AlunoDTO alunoAtualizadoDTO = new AlunoDTO();
		if (alunoExistenteNoBanco != null) {

			alunoExistenteNoBanco = toEntidade(alunoDTO);
			Aluno alunoAtualizado = alunoRepository.save(alunoExistenteNoBanco);

			alunoAtualizadoDTO = toDTO(alunoAtualizado);

		}
		return alunoAtualizadoDTO;
	}

	private Aluno toEntidade(AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno();

		aluno.setBairro(alunoDTO.getBairro());
		aluno.setCidade(alunoDTO.getCidade());
		aluno.setComplemento(alunoDTO.getComplemento());
		aluno.setCpf(alunoDTO.getCpf());
		aluno.setDataNascimento(alunoDTO.getDataNascimento());
		aluno.setLogradouro(alunoDTO.getLogradouro());
		aluno.setNome(alunoDTO.getNome());
		aluno.setNumeroLogradouro(alunoDTO.getNumeroLogradouro());

		return aluno;
	}

	private AlunoDTO toDTO(Aluno aluno) {

		AlunoDTO alunoDTO = new AlunoDTO();

		alunoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		alunoDTO.setBairro(aluno.getBairro());
		alunoDTO.setCidade(aluno.getCidade());
		alunoDTO.setComplemento(aluno.getComplemento());
		alunoDTO.setCpf(aluno.getCpf());
		alunoDTO.setDataNascimento(aluno.getDataNascimento());
		alunoDTO.setLogradouro(aluno.getLogradouro());
		alunoDTO.setNome(aluno.getNome());
		alunoDTO.setNumeroLogradouro(aluno.getNumeroLogradouro());

		return alunoDTO;
	}
	
	///AlunoResumoDTO
	
	public AlunoDTO getAlunoDTOById(Integer id) {
		AlunoDTO alunoDTO = new AlunoDTO();
		Aluno aluno = alunoRepository.findById(id).orElse(null);
		
		alunoDTO = toDTO(aluno);
		
		List<Emprestimo> listaEmprestimos = emprestimoRepository.findbyAlunoDTO(aluno);
				
		return alunoDTO;
	}
	
	public List<AlunoResumoDTO> getAllAlunoResumoDTO() {
		List<AlunoResumoDTO> listaAluno = alunoRepository.findAll();
		List<AlunoResumoDTO> listaAlunoDTO = new ArrayList<>();

		for (Aluno aluno : listaAluno) {

			AlunoDTO alunoDTO = toDTO(aluno);

			listaAlunoDTO.add(alunoDTO);
		}
		return listaAlunoDTO;

	}
}
