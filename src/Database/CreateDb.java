package Database;

public class CreateDb {
    private static String pokemon ="CREATE TABLE IF NOT EXISTS pokemon (" +
            "  name varchar(45) NOT NULL PRIMARY KEY," +
            "  habitat varchar(45) NOT NULL, " +
            "  weight float NOT NULL, " +
            "  isAggressive bool NOT NULL" +
            ");";
    private static String trainedpokemon ="CREATE TABLE IF NOT EXISTS trainedpokemon (" +
            "  id SERIAL PRIMARY KEY,"+
            "  name varchar(45) NOT NULL," +
            "  habitat varchar(45) NOT NULL, " +
            "  weight float NOT NULL, " +
            "  isAggressive bool NOT NULL," +
            "  nickname varchar(450),"+
            "  level int NOT NULL," +
            "  isArchived bool NOT NULL"+
            ");";
    private static String trainer ="CREATE TABLE IF NOT EXISTS trainer(" +
            "  id SERIAL PRIMARY KEY,"+
            "  name varchar(45) NOT NULL," +
            "  age int NOT NULL" +
            ");";
    private static String trainer_pokemon ="CREATE TABLE IF NOT EXISTS trainer_pokemon(" +
            "  id SERIAL PRIMARY KEY,"+
            "  trainer_id int NOT NULL REFERENCES trainer(id)," +
            "  pokemon_id int NOT NULL REFERENCES trainedpokemon(id)" +
            ");";
    public static void CreateDb(){
        DatabaseConnection.executeQuery(pokemon);
        DatabaseConnection.executeQuery(trainedpokemon);
        DatabaseConnection.executeQuery(trainer);
        DatabaseConnection.executeQuery(trainer_pokemon);
    }
}
