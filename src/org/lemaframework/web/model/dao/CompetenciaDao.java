package org.lemaframework.web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lemaframework.web.infra.ConnectionFactory;
import org.lemaframework.web.model.Competencia;

public class CompetenciaDao {
	

	public void inserir(Competencia competencia) { 
		String sql = "insert into Competencia (nome) values (?)";
		try {
			Connection connection = new ConnectionFactory().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, competencia.getNome());
			
			ps.execute();
			
			connection.close();
		
		} catch(SQLException e) { 
			throw new RuntimeException(e);
		}
	}
	
	public void remover(Competencia competencia) {
		String sql = "delete from Competencia where id = ?";
		try {
			Connection connection = new ConnectionFactory().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, competencia.getId());
			
			ps.execute();
			
			connection.close();
		
		} catch(SQLException e) { 
			throw new RuntimeException(e);
		}
		
	}

	public void atualiza(Competencia competencia) { 
		String sql = "update Competencia set nome = ? where id = ?";
		try {
			Connection connection = new ConnectionFactory().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, competencia.getNome());
			ps.setLong(2, competencia.getId());
			
			ps.execute();
			
			connection.close();
		
		} catch(SQLException e) { 
			throw new RuntimeException(e);
		}
	}
	
	public List<Competencia> list() { 
		String sql = "select * from Competencia";
		List<Competencia> competencias = new ArrayList<Competencia>();
		
		try {
			Connection connection = new ConnectionFactory().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) { 
				Competencia competencia = new Competencia();
				
				competencia.setNome(resultSet.getString("nome"));
				competencia.setId(resultSet.getLong("id"));
				
				competencias.add(competencia);
			}
			
			connection.close();
			
			return competencias;
		
		} catch(SQLException e) { 
			throw new RuntimeException(e);
		}
	}

	public Competencia buscarPor(int id) { 
		String sql = "select * from Competencia where id = ?";
		
		try {
			Connection connection = new ConnectionFactory().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) { 
				Competencia competencia = new Competencia();
				
				competencia.setNome(resultSet.getString("nome"));
				competencia.setId(resultSet.getLong("id"));
				
				return competencia;
			}
			
			connection.close();
			
			return null;
			
			
		} catch(SQLException e) { 
			throw new RuntimeException(e);
		}
	}

}
