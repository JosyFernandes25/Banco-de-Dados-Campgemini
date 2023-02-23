
package TesteBanco;

import controller.ProjectController;
import controller.TaskController;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;

public class Main {
    public static void main (String[] args) throws SQLException{
        
        ProjectController projectController = new ProjectController();
        
        /*cria projeto no banco*/
        Project project = new Project();
        project.setName ("Projeto Teste");
        project.setDescription ("description");
        projectController.save(project);
        
        /*pesquisa projeto*/
        project.setName("Novo nome do Projeto");
        projectController.update(project);
        
        List <Project> projects = projectController.getAll();
        System.out.println("Total de projetos = "+ projects.size());
        
        
        /*Para adicionar tarefas*/
        TaskController taskController = new TaskController();
        Task task = new Task ();
        task.setIdProject(2);
        task.setName("criar as telas da aplicação");
        task.setDescription("Devem ser criadas telas para os cadastros");
        task.setNotes("sem notas");
        task.setIsCompleted(false);
        task.setDeadline(new Date());
        
        taskController.save(task);
        
        /*Atualizar tarefas*/
        
        task.setName("Alterar telas de aplicação");
        taskController.update(task);
        
        
        /*Pesquisa em tarefas*/
        
        List <Task> tasks = taskController.getAll(12);
        System.out.println("Total de tarefas= "+ tasks.size());
        
                  
    }
    
}
