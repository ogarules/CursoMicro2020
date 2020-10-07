package com.example.courses.repositories;

import java.util.List;
import java.util.Optional;

import com.example.courses.models.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    List<Course> findByInstructorId(Long instructorId);
    Optional<Course> findByIdAndInstructorId(Long id, Long instructorId);
}
