package br.com.devtree.repository;

import br.com.devtree.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {
    List<Student> findByNameIgnoreCaseContaining(String name);
}
