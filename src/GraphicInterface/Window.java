package GraphicInterface;

import Entities.Pokemon;
import Entities.TrainedPokemon;
import Entities.Trainer;
import Exceptions.PokemonNameException;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JScrollPane pokemonList;
    private JScrollPane trainerList;
    private JScrollPane trainedPokemonList;

    public Window(String title, int x, int y, int width, int height) {
        setTitle(title);
        setBounds(x, y, width, height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        getContentPane().setBackground(Color.darkGray);

        JLabel pokemons = new JLabel("Pokemons");
        pokemons.setBounds(80, 20, 400,50);
        pokemons.setForeground(Color.white);
        this.getContentPane().add(pokemons);

        JLabel treinadores = new JLabel("Treinadores");
        treinadores.setBounds(410, 20, 400,50);
        treinadores.setForeground(Color.white);
        this.getContentPane().add(treinadores);

        JLabel pokemonsTreinados = new JLabel("Pokemons treinados");
        pokemonsTreinados .setBounds(720, 20, 400,50);
        pokemonsTreinados .setForeground(Color.white);
        this.getContentPane().add(pokemonsTreinados );

        this.pokemonList = new PokemonListJPanel(this).createScrollPanel();
        this.getContentPane().add(pokemonList);

        JButton addPokemonButton = PokemonCreateButton();
        this.getContentPane().add(addPokemonButton);

        this.trainerList = new TrainerListJPanel(this).createScrollPanel();
        this.getContentPane().add(this.trainerList);

        JButton addTrainerButton = TrainerCreateButton();
        this.getContentPane().add(addTrainerButton);

        this.trainedPokemonList = new TrainedPokemonListJPanel(this).createScrollPanel();
        this.getContentPane().add(this.trainedPokemonList);
        JButton addTrainedPokemonButton = TrainedPokemonCreateButton();
        this.getContentPane().add(addTrainedPokemonButton);
        this.getContentPane().add(refresh());
        this.setVisible(true);
    }
    public JButton PokemonCreateButton() {
        JButton newButton = new JButton("Adicionar novo");
        newButton.setBounds(215, 70,130,30);

        newButton.addActionListener(ActionEvent ->{
            addPokemonWindow();
        });

        return newButton;
    }
    public JButton refresh() {
        JButton newButton = new JButton("Atualizar");
        newButton.setBounds(1000, 400,130,30);

        newButton.addActionListener(ActionEvent ->{
            this.getContentPane().remove(this.pokemonList);
            this.getContentPane().remove(this.trainedPokemonList);
            this.getContentPane().remove(this.trainerList);
            this.pokemonList = new PokemonListJPanel(this).createScrollPanel();
            this.trainedPokemonList = new TrainedPokemonListJPanel(this).createScrollPanel();
            this.trainerList = new TrainerListJPanel(this).createScrollPanel();
            this.getContentPane().add(pokemonList);
            this.getContentPane().add(trainedPokemonList);
            this.getContentPane().add(trainerList);
            this.invalidate();
            this.validate();
        });

        return newButton;
    }
    public JButton TrainerCreateButton() {
        JButton newButton = new JButton("Adicionar novo");
        newButton.setBounds(525, 70,130,30);

        newButton.addActionListener(ActionEvent ->{
            addTrainerWindow();
        });

        return newButton;
    }
    public JButton TrainedPokemonCreateButton() {
        JButton newButton = new JButton("Adicionar novo");
        newButton.setBounds(855, 70,130,30);

        newButton.addActionListener(ActionEvent ->{
            addTrainedPokemonWindow();
        });

        return newButton;
    }
    public void addPokemonWindow(){
        JFrame popUp = new JFrame();
        popUp.setTitle("Adicionar Pokemon");
        popUp.setBounds(200, 200, 600, 350);
        popUp.getContentPane().setLayout(null);

        JLabel name = createLabel("Nome: ",6, 2);
        JTextField nameInput = createInput(60,12);
        popUp.getContentPane().add(name);
        popUp.getContentPane().add(nameInput);

        JLabel weight = createLabel("Peso: ",6, 39);
        JTextField weightInput = createInput(60,48);
        popUp.getContentPane().add(weight);
        popUp.getContentPane().add(weightInput);

        JLabel habitat = createLabel("Habitat: ",6, 71);
        JTextField habitatInput = createInput(60,84);
        popUp.add(habitat);
        popUp.add(habitatInput);

        String [] op = {"Sim", "Não"};
        JLabel aggressive = new JLabel("Agressivo: ");
        aggressive.setBounds(242, 2, 100,50);

        JComboBox<String> aggressiveInput = new JComboBox<String>(op);
        aggressiveInput.setBounds(310,20,80,20);
        popUp.add(aggressive);
        popUp.add(aggressiveInput);

        JButton submit = submitButton(nameInput, weightInput, habitatInput, aggressiveInput);
        popUp.add(submit);
        popUp.setVisible(true);
    }
    public JLabel createLabel(String title, int x, int y) {
        JLabel label = new JLabel(title);
        label.setBounds(x, y, 100,50);
        return label;
    }
    public JTextField createInput(int x, int y) {
        JTextField input = new JTextField();
        input.setBounds(x,y,126,26);
        return input;
    }
    public JButton submitButton(JTextField nameInput, JTextField weightInput, JTextField habitatInput, JComboBox<String> aggressiveInput){
        JButton submit = new JButton("Criar");
        submit.setBounds(316, 120, 160,30);

        submit.addActionListener(actionEvent -> {
            String namePokemon = nameInput.getText();
            float weightPokemon = Float.parseFloat(weightInput.getText());
            String habitatPokemon = habitatInput.getText();

            boolean aggressivePokemon = aggressiveInput.getSelectedItem().toString().equals("Sim") ? true: false;
            Pokemon pokemon = null;
            try {
                pokemon = new Pokemon(namePokemon,habitatPokemon ,weightPokemon,aggressivePokemon);
            } catch (PokemonNameException e) {
                e.printStackTrace();
            }
            pokemon.create();
            this.getContentPane().remove(this.pokemonList);
            this.pokemonList = new PokemonListJPanel(this).createScrollPanel();
            this.getContentPane().add(pokemonList);
            this.invalidate();
            this.validate();
        });
        return submit;
    }
    public void addTrainerWindow(){
        JFrame popUp = new JFrame();
        popUp.setTitle("Adicionar Treinador");
        popUp.setBounds(300, 200, 600, 350);
        popUp.getContentPane().setLayout(null);

        JLabel name = createLabel("Nome: ",6, 2);
        JTextField nameInput = createInput(60,12);
        popUp.getContentPane().add(name);
        popUp.getContentPane().add(nameInput);

        JLabel weight = createLabel("Idade: ",6, 39);
        JTextField weightInput = createInput(60,48);
        popUp.getContentPane().add(weight);
        popUp.getContentPane().add(weightInput);


        JButton submit = submitTrainerButton(nameInput, weightInput);
        popUp.add(submit);
        popUp.setVisible(true);
    }
    private JButton submitTrainerButton(JTextField nameInput, JTextField ageInput){
        JButton submit = new JButton("Criar");
        submit.setBounds(316, 120, 160,30);

        submit.addActionListener(actionEvent -> {
            String name = nameInput.getText();
            int age = Integer.parseInt(ageInput.getText());

            Trainer trainer = new Trainer(name, age);
            trainer.create();
            this.getContentPane().remove(this.trainerList);
            this.trainerList = new TrainerListJPanel(this).createScrollPanel();
            this.getContentPane().add(this.trainerList);
            this.invalidate();
            this.validate();
        });
        return submit;
    }
    public void addTrainedPokemonWindow(){
        JFrame popUp = new JFrame();
        popUp.setTitle("Adicionar Pokemon Treinado");
        popUp.setBounds(200, 200, 600, 350);
        popUp.getContentPane().setLayout(null);

        JLabel name = createLabel("Nome: ",6, 2);
        JTextField nameInput = createInput(60,12);
        popUp.getContentPane().add(name);
        popUp.getContentPane().add(nameInput);

        JLabel weight = createLabel("Peso: ",6, 39);
        JTextField weightInput = createInput(60,48);
        popUp.getContentPane().add(weight);
        popUp.getContentPane().add(weightInput);

        JLabel habitat= createLabel("Habitat: ",6, 71);
        JTextField habitatInput = createInput(60,84);
        popUp.add(habitat);
        popUp.add(habitatInput);

        String [] op = {"Sim", "Não"};
        JLabel aggressive = new JLabel("Agressivo: ");
        aggressive.setBounds(242, 2, 100,50);

        JComboBox<String> aggressiveInput = new JComboBox<String>(op);
        aggressiveInput.setBounds(310,20,80,20);
        popUp.add(aggressive);
        popUp.add(aggressiveInput);

        JLabel nickname = createLabel("Apelido: ",242, 39);
        JTextField nicknameInput = createInput(310,48);
        popUp.add(nickname);
        popUp.add(nicknameInput);

        JLabel level = createLabel("Nível: ",242, 71);
        JTextField levelInput = createInput(310,84);
        popUp.add(level);
        popUp.add(levelInput);

        JLabel archived = new JLabel("Arquivado: ");
        archived.setBounds(6, 101, 100,50);

        JComboBox<String> archivedInput = new JComboBox<String>(op);
        archivedInput.setBounds(75,115,80,20);
        popUp.add(archived);
        popUp.add(archivedInput);

        JButton submit = submitTrainedPokemonButton(nameInput, weightInput, habitatInput, aggressiveInput,nicknameInput, levelInput, archivedInput);
        popUp.add(submit);
        popUp.setVisible(true);
    }
    public JButton submitTrainedPokemonButton(JTextField nameInput, JTextField weightInput, JTextField habitatInput, JComboBox<String> aggressiveInput,
                                              JTextField nicknameInput, JTextField levelInput, JComboBox<String> archivedInput){
        JButton submit = new JButton("Criar");
        submit.setBounds(316, 120, 160,30);

        submit.addActionListener(actionEvent -> {
            String namePokemon = nameInput.getText();
            float weightPokemon = Float.parseFloat(weightInput.getText());
            String habitatPokemon = habitatInput.getText();
            boolean aggressivePokemon = aggressiveInput.getSelectedItem().toString().equals("Sim") ? true: false;
            String nicknamePokemon = nicknameInput.getText();
            int levelPokemon = Integer.parseInt(levelInput.getText());
            boolean archivedPokemon = archivedInput.getSelectedItem().toString().equals("Sim") ? true: false;

            TrainedPokemon pokemon = null;
            try {
                pokemon = new TrainedPokemon(namePokemon, habitatPokemon, weightPokemon, aggressivePokemon,
                        nicknamePokemon, levelPokemon, archivedPokemon);
            } catch (PokemonNameException e) {
                e.printStackTrace();
            }
            pokemon.create();
            this.getContentPane().remove(this.trainedPokemonList);
            this.trainedPokemonList = new TrainedPokemonListJPanel(this).createScrollPanel();
            this.getContentPane().add(trainedPokemonList);
            this.invalidate();
            this.validate();
        });
        return submit;
    }
}
