package com.example.courses.controllers;

import java.util.List;

import com.example.courses.exception.ResourseNotFoundException;
import com.example.courses.models.Course;
import com.example.courses.repositories.CourseRepository;
import com.example.courses.repositories.InstructorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CourseController {
    
    @Autowired 
    CourseRepository courseRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @GetMapping("/instructor/{instructorId}/course")
    public List<Course> getInstructorCourses(@PathVariable Long instructorId) {
        return this.courseRepository.findByInstructorId(instructorId);
    }
    
    @PostMapping("/instructor/{instructorId}/course")
    public Course addCourseToInstructor(@PathVariable Long instructorId, @RequestBody Course course) throws ResourseNotFoundException {
        return this.instructorRepository.findById(instructorId).map(instructor -> {
            course.setInstructor(instructor);
            return courseRepository.save(course);
        }).orElseThrow(() -> new ResourseNotFoundException("Instructor not found"));
    }
    
    @PutMapping("/instructor/{instructorId}/course/{courseId}")
    public Course updateInstructorCourse(@PathVariable Long instructorId, @PathVariable Long courseId, @RequestBody Course course) throws ResourseNotFoundException {
        if(this.instructorRepository.existsById(instructorId)){
            throw new ResourseNotFoundException("Instructor not found");
        }  
        
        return this.courseRepository.findById(courseId).map(courseBd ->{
            courseBd.setTitle(course.getTitle());
            return this.courseRepository.save(courseBd);
        }).orElseThrow(() -> new ResourseNotFoundException("Course not found"));
    }

    @DeleteMapping("/instructor/{instructorId}/course/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long instructorId, @PathVariable Long courseId) throws ResourseNotFoundException{
        return this.courseRepository.findByIdAndInstructorId(courseId, instructorId).map(course -> {
            this.courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourseNotFoundException("Course linked to instructor not found"));
    }
}
