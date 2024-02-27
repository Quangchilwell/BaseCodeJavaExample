package com.example.demo.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class SqlUtils {

    @Autowired
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ApplicationContext context;

    public SqlUtils(NamedParameterJdbcTemplate jdbcTemplate, ApplicationContext context) {
        this.jdbcTemplate = jdbcTemplate;
        this.context = context;
    }

    public static class SqlQueryModel {
        private final NamedParameterJdbcTemplate jdbcTemplate;
        private final ApplicationContext context;

        SqlQueryModel(NamedParameterJdbcTemplate jdbcTemplate, ApplicationContext context) {
            this.jdbcTemplate = jdbcTemplate;
            this.context = context;
        }

        public <T> List<T> queryForList(String sql, Map<String, Object> params, Class<T> clazz) {
            var parameters = new MapSqlParameterSource(params);
            return queryForList(sql, parameters, clazz);
        }

        public <T> T queryForObject(String sql, Map<String, Object> params, Class<T> clazz) {
            var parameters = new MapSqlParameterSource(params);
            return queryForObject(sql, parameters, clazz);
        }


        private <T> List<T> queryForListPrimitive(String sql, MapSqlParameterSource parameters, Class<T> clazz) {
            return jdbcTemplate.queryForList(sql, parameters, clazz);
        }

        private <T> List<T> queryForListNotPrimitive(String sql, MapSqlParameterSource parameters, Class<T> clazz) {
            return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<>(clazz));
        }

        private  <T> List<T> queryForList(String sql, MapSqlParameterSource parameters, Class<T> clazz) {
            if (BeanUtils.isSimpleValueType(clazz)) {
                return queryForListPrimitive(sql, parameters, clazz);
            }
            return queryForListNotPrimitive(sql, parameters, clazz);
        }

        private <T> T queryForObject(String sql, MapSqlParameterSource parameters, Class<T> clazz) {
            var result = queryForListNotPrimitive(sql, parameters, clazz);
            if (result.isEmpty()) {
                return null;
            }
            return result.get(0);

        }
    }

    public SqlQueryModel sqlQueryModel() {
        return new SqlQueryModel(this.jdbcTemplate, context);
    }
}
