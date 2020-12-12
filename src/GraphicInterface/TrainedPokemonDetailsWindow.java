package GraphicInterface;

import Entities.TrainedPokemon;

import javax.swing.*;

public class TrainedPokemonDetailsWindow extends JFrame {
    private JFrame window;
    private TrainedPokemon updateThis;

    public TrainedPokemonDetailsWindow(TrainedPokemon trainedPokemon, JFrame window) {
        this.window = window;
        JFrame popUp = new JFrame();
        popUp.setTitle("Visualizar Pokemons treinados");
        popUp.setBounds(430, 200, 400, 210);
        popUp.getContentPane().setLayout(null);
        int x=6,y=1,width=300, height=50;
        JLabel id = createJLabel("Id: "+ trainedPokemon.getId(), x,y);
        y+=22;
        JLabel name = createJLabel("Nome: "+ trainedPokemon.getName(), x,y);
        y+=22;
        JLabel nickname = createJLabel("Apelido: "+ trainedPokemon.getNickname(), x,y);
        y+=22;
        JLabel habitat = createJLabel("Habitat: "+ trainedPokemon.getHabitat(), x,y);
        y+=22;
        JLabel level = createJLabel("Nível: "+ trainedPokemon.getLevel(), x,y);
        y+=22;
        JLabel weight = createJLabel("Peso: "+ trainedPokemon.getWeight(), x,y);
        y+=22;
        JLabel isArchived = createJLabel("Arquivado: "+ trainedPokemon.isArchived(), x,y);

        popUp.add(id);
        popUp.add(name);
        popUp.add(nickname);
        popUp.add(habitat);
        popUp.add(level);
        popUp.add(weight);
        popUp.add(isArchived);


        JButton update = createUpdateButton(trainedPokemon.getId());
        JButton delete = createDeleteButton(trainedPokemon.getId());
        popUp.add(update);
        popUp.add(delete);

        popUp.setVisible(true);
    }
    public JLabel createJLabel(String nome, int x, int y) {
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, 300,50);
        return label;
    }
    public JButton createUpdateButton(int id){
        JButton newButton = new JButton("Atualizar");
        newButton.setBounds(180, 22,100,30);
        TrainedPokemon pokemon = new TrainedPokemon();
        this.updateThis = pokemon.selectOne(id);
        newButton.addActionListener((actionEvent) -> {
            windowUpdate(this.updateThis);
        });

        return newButton;
    }
    public void windowUpdate(TrainedPokemon pokemon){
        JFrame popUp = new JFrame();
        popUp.setTitle("Atualizar Pokemon");
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

        JButton submit = submitButton(pokemon, nameInput, weightInput, habitatInput, aggressiveInput, nicknameInput,
                levelInput, archivedInput);
        popUp.add(submit);
        popUp.setVisible(true);
    }
    public JButton submitButton(TrainedPokemon pokemon,JTextField nameInput, JTextField weightInput, JTextField habitatInput, JComboBox<String> aggressiveInput,
                                JTextField nicknameInput, JTextField levelInput, JComboBox<String> archivedInput){
        JButton submit = new JButton("Criar");
        submit.setBounds(316, 120, 160,30);

        submit.addActionListener(actionEvent -> {

            float weightPokemon = Float.parseFloat(weightInput.getText());
            String habitatPokemon = habitatInput.getText();
            String namePokemon = nameInput.getText();
            boolean aggressivePokemon = aggressiveInput.getSelectedItem().toString().equals("Sim") ? true: false;
            String nicknamePokemon = nicknameInput.getText();
            int levelPokemon = Integer.parseInt(levelInput.getText());
            boolean archivedPokemon = archivedInput.getSelectedItem().toString().equals("Sim") ? true: false;

            TrainedPokemon pokemonUpdate = new TrainedPokemon();
            pokemonUpdate.updatePokemon(pokemon.getId(), namePokemon, weightPokemon, habitatPokemon, aggressivePokemon,
                    nicknamePokemon, levelPokemon, archivedPokemon);
            System.out.println(pokemon.toString());
        });
        return submit;
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
    public JButton createDeleteButton(int id){
        JButton newButton = new JButton("Deletar");
        newButton.setBounds(180, 62,100,30);
        newButton.addActionListener(ActionListener->{
            TrainedPokemon pokemon = new TrainedPokemon();
            pokemon.delete(Integer.toString(id));
        });

        return newButton;
    }
}
