package com.rest.workshop.services.implementation.symptom.answer;

import com.rest.workshop.domain.entitties.symptom.Answer;
import com.rest.workshop.services.AnswerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public Answer findById(Long answerId) {
        return answerRepository.findById(answerId).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }
}
