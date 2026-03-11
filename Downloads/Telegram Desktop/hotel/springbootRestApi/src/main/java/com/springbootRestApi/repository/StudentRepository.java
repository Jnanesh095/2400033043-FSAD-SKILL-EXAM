package com.springbootRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootRestApi.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
