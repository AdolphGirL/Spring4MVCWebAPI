package com.kangdainfo.webapi.model.jdbcbo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.kangdainfo.webapi.model.bo.Exp0001Db;

public class Exp0001DbRowMapper implements RowMapper<Exp0001Db> {

	@Override
	public Exp0001Db mapRow(ResultSet rs, int rowNum) throws SQLException {
		Exp0001Db exp0001Db = new Exp0001Db();
		
//		exp0001Db.setId(rs.getBigDecimal("ID"));
//		exp0001Db.setGeneralRate(rs.getDouble("GENERAL_RATE"));
//		exp0001Db.setEnhanceRate(rs.getDouble("ENHANCE_RATE"));
//		exp0001Db.setEachcaseRate(rs.getDouble("EACHCASE_RATE"));
//		exp0001Db.setNbatch(rs.getDouble("NBATCH"));
//		exp0001Db.setNdouble(rs.getDouble("NDOUBLE"));
//		exp0001Db.setNbatch2(rs.getDouble("NBATCH2"));
//		exp0001Db.setNdouble(rs.getDouble("NDOUBLE2"));
		
		return exp0001Db;
	}

}
