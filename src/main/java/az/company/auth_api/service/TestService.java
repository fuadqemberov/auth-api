package az.company.auth_api.service;

import az.company.auth_api.entity.Test;
import az.company.auth_api.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test save(Test test) {
        return testRepository.save(test);
    }

    public Optional<Test> findById(Long id) {
        return testRepository.findById(id);
    }

    public Iterable<Test> findAll() {
        return testRepository.findAll();
    }

    public void deleteById(Long id) {
        testRepository.deleteById(id);
    }
}