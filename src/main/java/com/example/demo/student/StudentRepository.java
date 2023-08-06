package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository                                 //      <typeOfObjectforTable,PK type>
public interface StudentRepository extends JpaRepository<Student,Long> {

    //Here WRITE  CUSTOM FUNCTIONS

    // This HIBENRATE will convert to Query: SELECT & FROM student WHERE EMAIL = ? (What we pass here will take for param)
//    @Query("SELECT s FROM Student s WHERE s.email = ?1") //this is JPQL
    Optional<Student> findStudentByEmail(String email);
}
