package dao;


import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String GET_ALL_STUDENTS = "SELECT student.id, student.firstname, student.lastname, student.email FROM student ORDER BY student.id DESC";
    private static final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?";
    private static final String ADD_STUDENT = "INSERT INTO student (firstname, lastname, email) VALUES(?,?,?)";
    private static final String GET_COUNT="SELECT COUNT(student.id) FROM student";
    private static final String GET_RANGE =" LIMIT ? OFFSET ?";
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public void deleteStudent(int studentId){
        jdbcTemplate.update(DELETE_STUDENT, studentId);
    }
    @Override
    public void addStudent(final Student student) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement prepStat;
                    prepStat = con.prepareStatement(ADD_STUDENT);
                prepStat.setString(1, student.getFirstName());
                prepStat.setString(2, student.getLastName());
                prepStat.setString(3, student.getEmail());
                return prepStat;
            }
        });
    }

    @Override
    public List<Student> getAllStudent(int offset, int numberPerPage) {
    return jdbcTemplate.query(GET_ALL_STUDENTS+GET_RANGE, new Object[]{numberPerPage, offset}, rowMapper);
    }

    @Override
    public int getCountPages() {
        return  jdbcTemplate.queryForObject(GET_COUNT, Integer.class);
    }

    private static final RowMapper<Student> rowMapper = new RowMapper<Student>() {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("firstname"));
            student.setLastName(rs.getString("lastname"));
            student.setEmail(rs.getString("email"));
            return student;
        }
    };

}
