package org.example.userapi.domain.repository;

import lombok.RequiredArgsConstructor;
import org.example.userapi.domain.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getLong("id"),
            rs.getString("user_name"),
            rs.getInt("age"),
            rs.getTimestamp("created_at").toLocalDateTime()
    );

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper);
    }

    public User save(User user) {
        jdbcTemplate.update("INSERT INTO users(user_name, age) VALUES (?, ?)",
                user.getUsername(),
                user.getAge());
        return user;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    public Optional<User> findById(Long id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id = ?", userRowMapper, id);
        return users.stream().findFirst();
    }

    public User update(User user) {
        jdbcTemplate.update("UPDATE users SET user_name = ?, age = ? WHERE id = ?",
                user.getUsername(),
                user.getAge(),
                user.getId());
        return user;
    }

    public Integer count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM users");
    }
}
