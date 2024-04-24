package com.accolite.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.accolite.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{
	
	@Modifying
	@Transactional
	@Query(value="update Student s set s.email_id=?2 where s.first_name=?1",nativeQuery=true)
	void updateStudentByFirstName(String string, String string2);

	List<Student> findByFirstName(String name);

}
