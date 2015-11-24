package org.lemaframework.web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lemaframework.web.infra.ConnectionFactory;
import org.lemaframework.web.model.Membro;

public class MembroDao {
	
	public void inserir(Membro membro) { 
		String sql = "insert into Membro (nome, email) values (?, ?)";
		try(Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, membro.getNome());
			ps.setString(2, membro.getEmail());
			
			ps.execute();
			
		} catch(SQLException e) { 
			throw new RuntimeException(e);
		}
	}
	
	public void remover(Membro membro) {
		String sql = "delete from Membro where id = ?";
		try(Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, membro.getId());
			
			ps.execute();
			
		} catch(SQLException e) { 
			throw new RuntimeException(e);
		}
		
	}

	public void atualiza(Membro membro) { 
		String sql = "update Membro set nome = ?, email = ? where id = ?";
		try(Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, membro.getNome());
			ps.setString(2, membro.getEmail());
			ps.setLong(3, membro.getId());
			
			ps.execute();
			
		} catch(SQLException e) { 
			throw new RuntimeException(e);
		}
	}
	
	public List<Membro> list() { 
		String sql = "select * from Membro";
		List<Membro> membros = new ArrayList<Membro>();
		
		try(Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) { 
				Membro membro = new Membro();
				
				membro.setNome(resultSet.getString("nome"));
				membro.setEmail(resultSet.getString("email"));
				membro.setId(resultSet.getLong("id"));
				
				membros.add(membro);
			}
			resultSet.close();
			return membros;
		
		} catch(SQLException e) { 
			throw new RuntimeException(e);
		}
	}

	public Membro buscarPor(int id) { 
		String sql = "select * from Membro where id = ?";
		
		try (Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) { 
				Membro membro = new Membro();
				
				membro.setNome(resultSet.getString("nome"));
				membro.setEmail(resultSet.getString("email"));
				membro.setId(resultSet.getLong("id"));
				
				return membro;
				
			}
			resultSet.close();
			return null;
		
		} catch(SQLException e) { 
			throw new RuntimeException(e);
		}
	}
}
