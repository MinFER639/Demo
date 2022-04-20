package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DemoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Demo> getAll() {
        return jdbcTemplate.query("SELECT id, movie, rating FROM demo",
                BeanPropertyRowMapper.newInstance(Demo.class));
    }

    public Demo getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, movie, rating FROM demo WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Demo.class), id);
    }

    public int save(List<Demo> demos) {
        demos.forEach(demo -> jdbcTemplate.
                update("INSERT INTO demo(movie, rating) VALUES(?, ?)",
                        demo.getMovie(), demo.getRating()));
        return 123;
    }

    public int update(Demo demo) {
        jdbcTemplate.update("UPDATE demo SET movie=?, rating=? WHERE id=?",
                demo.getMovie(), demo.getRating(), demo.getId());
        return 123;
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM demo WHERE id=?", id);
    }
}
