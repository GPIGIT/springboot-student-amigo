package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                 //      <typeOfObjectforTable,PK type>
public interface StudentRepository extends JpaRepository<Student,Long> {
}
