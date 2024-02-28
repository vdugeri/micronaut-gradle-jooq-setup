package com.infelsoft.student.repository;

import com.infelsoft.student.Tables;
import com.infelsoft.student.tables.pojos.Students;
import com.infelsoft.student.tables.records.StudentsRecord;
import jakarta.inject.Singleton;
import org.jooq.DSLContext;
import reactor.core.publisher.Mono;

import static com.infelsoft.student.Tables.STUDENTS;

@Singleton
public class StudentRepository {

  private final DSLContext dslContext;

  public StudentRepository(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  public Mono<Students> createStudent(Students record) {
    return Mono.from(dslContext.insertInto(STUDENTS)
                               .columns(STUDENTS.ID,
                                        STUDENTS.FIRST_NAME,
                                        STUDENTS.LAST_NAME,
                                        STUDENTS.OTHER_NAMES,
                                        STUDENTS.REG_NUMBER,
                                        STUDENTS.USER_ID,
                                        STUDENTS.CREATED_AT)
                               .values(record.getId(),
                                       record.getFirstName(),
                                       record.getLastName(),
                                       record.getOtherNames(),
                                       record.getRegNumber(),
                                       record.getUserId(),
                                       record.getCreatedAt())
                               .returningResult(STUDENTS))
               .map(result -> result.into(Students.class));
  }
}
