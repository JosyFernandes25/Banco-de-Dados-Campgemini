
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {
    
    public void save (Project project){
        String sql = "INSERT INTO project ( name,"
                + " description," 
                + "createdAt," 
                + "updatedAt) VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date (project.getCreatedAt().getTime()));
            statement.setDate(4, new Date (project.getUpdatedAt().getTime()));
            statement.execute();
   
        } catch ( SQLException ex) {
            throw new RuntimeException ("Erro ao salvar o projeto ", ex);
            
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);        
        }
      }
        public void update(Project project) {
            String sql = "UPDATE projects SET"
                    + "name = ?,"
                    + "description = ?,"
                    + "createdAt = ?,"
                    + "updatedAt = ?,"
                    + "WHERE id = ?";
            
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date (project.getCreatedAt().getTime()));
            statement.setDate(4, new Date (project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();
   
        } catch ( SQLException ex) {
            throw new RuntimeException ("Erro ao atualizar o projeto ", ex);
            
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);        
        }
     }
        
        
        public List<Project> getAll() throws SQLException{
            String sql = "SELECT * FROM projects";
            List <Project> projects = new ArrayList<> ();
            
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                
                while (resultSet.next()){
                    Project project = new Project();
                    
                    project.setId(resultSet.getInt("id"));
                    project.setName(resultSet.getString("name"));
                    project.setDescription(resultSet.getString("description"));
                    project.setCreatedAt(resultSet.getDate("createdAt"));
                    project.setUpdatedAt(resultSet.getDate("updatedAt"));
                    projects.add(project);

                }
            } catch (SQLException ex){
                throw new RuntimeException ("Erro ao buscar os projeto ", ex);
            
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement); 
            if (resultSet != null){
                resultSet.close();
        }
            return projects;
                
            }
            
        }
        public void removeById(int idProject){ /*remove por id*/
        
        String sql = "DELETE FROM projects WHERE id = ? "; /*monte query mysql*/
        
        Connection connection = null;
        PreparedStatement statement= null; /*prepara o comando sql para ser usado na conexao*/
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql); /*prepara a conex�o com a query sql*/
            statement.setInt(1, idProject); /*coloca n�mero no ? ( que � o primeiro parametro) segundo o Id informado */
            statement.execute(); /*executa*/
        } catch ( Exception ex){
            throw new RuntimeException("Erro ao deletar o projeto" ,ex);
        } finally{ /*Finaliza conex�o*/
            ConnectionFactory.closeConnection(connection, statement);     
        }
                           
      }
            
   }
        
                         

    

