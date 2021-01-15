package com.rest.workshop.services.implementation.symptom.respondent_answer;

import com.rest.workshop.domain.entitties.symptom.RespondentAnswer;
import com.rest.workshop.services.RespondentAnswerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
@Transactional
public class RespondentAnswerServiceImpl implements RespondentAnswerService {

    private final RespondentAnswerRepository respondentAnswerRepository;

    public RespondentAnswerServiceImpl(RespondentAnswerRepository respondentAnswerRepository) {
        this.respondentAnswerRepository = respondentAnswerRepository;
    }

    @Override
    public void save(RespondentAnswer respondentAnswer) {
        respondentAnswerRepository.save(respondentAnswer);
    }

    @Override
    public RespondentAnswer findById(Long id) {
        return respondentAnswerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(RespondentAnswer respondentAnswer) {
        respondentAnswerRepository.delete(respondentAnswer);
    }

    @Override
    public List<RespondentAnswer> findAll() {
        return respondentAnswerRepository.findAll();
    }
}