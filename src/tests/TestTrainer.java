package tests;

import Database.CreateDb;
import Database.DatabaseConnection;
import Entities.Trainer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestTrainer {

    @Test
    public void deveriaCadastrarUmTreinador(){
        DatabaseConnection.makeJDBCConnection();
        CreateDb.CreateDb();
        Trainer trainer = new Trainer("Rogério",18);
        trainer.create();
        boolean cadastrou = false;
        ArrayList<Trainer> trainers = trainer.read();
        for(Trainer rogerio : trainers){
            if(rogerio.getName().equals("Rogério"))
                cadastrou = true;
        }
        assertTrue(cadastrou);
    }
    @Test
    public void deveriaExcluirUmTreinador() {
        DatabaseConnection.makeJDBCConnection();
        CreateDb.CreateDb();

        Trainer trainer = new Trainer("Ricardo",18);
        trainer.create();
        boolean excluiu;
        int id = 0;
        ArrayList<Trainer> trainers = trainer.read();
        for(Trainer rogerio : trainers){
            if(rogerio.getName().equals("Ricardo"))
                id = rogerio.getId();
        }
        System.out.println(id);
        trainer.delete(Integer.toString(id));
        excluiu = true;
        trainers = trainer.read();
        for(Trainer rogerio : trainers){
            if(rogerio.getName().equals("Ricardo"))
                excluiu = false;
        }
        assertTrue(excluiu);
    }
}
