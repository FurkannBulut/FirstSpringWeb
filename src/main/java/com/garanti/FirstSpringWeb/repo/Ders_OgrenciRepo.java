package com.garanti.FirstSpringWeb.repo;

import com.garanti.FirstSpringWeb.Constants;
import com.garanti.FirstSpringWeb.model.Ders_Ogrenci;
import com.garanti.FirstSpringWeb.model.Ogrenci;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class Ders_OgrenciRepo {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Ders_Ogrenci> getAll()
    {
        return jdbcTemplate.query("select * from BILGE.DERS_OGRENCI", BeanPropertyRowMapper.newInstance(Ders_Ogrenci.class));
    }

    public Ders_Ogrenci getById(int id)
    {
        Ders_Ogrenci dersOgrenci = null;
        String sql = "select * from BILGE.DERS_OGRENCI where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        dersOgrenci = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Ders_Ogrenci.class));

        return dersOgrenci;
    }

    public boolean deleteById(int id)
    {
        String sql = "delete from BILGE.DERS_OGRENCI where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean save(Ders_Ogrenci ders_ogrenci)
    {
        String sql = "Insert into BILGE.DERS_OGRENCI (DERS_ID,OGRENCI_ID, DEVAMSIZLIK, NOTE) values (:DERS_ID, :OGRENCI_ID, :DEVAMSIZLIK, :NOTE)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("DERS_ID", ders_ogrenci.getDERS_ID());
        paramMap.put("OGRENCI_ID", ders_ogrenci.getOGRENCI_ID());
        paramMap.put("DEVAMSIZLIK", ders_ogrenci.getDEVAMSIZLIK());
        paramMap.put("NOTE", ders_ogrenci.getNOTE());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
    public List<Ders_Ogrenci> getAllLike(String name)
    {
        String sql = "select * from BILGE.DERS_OGRENCI where NAME LIKE :NAME";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("NAME", "%" + name + "%");
        return namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(Ders_Ogrenci.class));
    }

}