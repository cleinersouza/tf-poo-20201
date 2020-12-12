package GraphicInterface;

import Entities.Pokemon;

import javax.swing.*;

public class PokemonDetailsWindow extends JFrame {
    private JFrame window;
    private Pokemon updateThis;
    public PokemonDetailsWindow(Pokemon pokemon, JFrame window) {
        this.window = window;
        JFrame popUp = new JFrame();
        popUp.setTitle("Visualizar Pokemon");
        popUp.setBounds(200, 200, 400, 200);
        popUp.getContentPane().setLayout(null);
        int x=6,y=2,width=300, height=50;
        JLabel name = createJLabel("Nome: "+ pokemon.getName(), x,y);
        y+=22;
        JLabel habitat = createJLabel("Habitat: "+ pokemon.getHabitat(), x,y);
        y+=22;
        JLabel weight = createJLabel("Peso: "+ pokemon.getWeight(), x,y);
        y+=22;
        JLabel isAggressive = createJLabel("Agressivo: "+ pokemon.isAggressive(), x,y);
        popUp.add(name);
        popUp.add(habitat);
        popUp.add(weight);
        popUp.add(isAggressive);

        JButton update = createUpdateButton(pokemon.getName());
        JButton delete = createDeleteButton(pokemon.getName());
        popUp.add(update);
        popUp.add(delete);

        popUp.setVisible(true);
    }
    public JLabel createJLabel(String nome, int x, int y) {
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, 300,50);
        return label;
    }
    public JButton createUpdateButton(String name){
        JButton newButton = new JButton("Atualizar");
        newButton.setBounds(180, 22,100,30);
        Pokemon pokemon = new Pokemon();
        this.updateThis = pokemon.selectOne(name);
        newButton.addActionListener((actionEvent) -> {
            windowUpdate(this.updateThis);
                });
        return newButton;
    }
    public void windowUpdate(Pokemon pokemon){
        JFrame popUp = new JFrame();
        popUp.setTitle("Atualizar Pokemon");
        popUp.setBounds(200, 200, 600, 350);
        popUp.getContentPane().setLayout(null);


        JLabel weight = createLabel("Peso: ",6, 2);
        JTextField weightInput = createInput(60,12);
        popUp.getContentPane().add(weight);
        popUp.getContentPane().add(weightInput);

        JLabel habitat= createLabel("Habitat: ",6, 39);
        JTextField habitatInput = createInput(60,48);
        popUp.add(habitat);
        popUp.add(habitatInput);

        String [] op = {"Sim", "Não"};
        JLabel aggressive = new JLabel("Agressivo: ");
        aggressive.setBounds(242, 2, 100,50);

        JComboBox<String> aggressiveInput = new JComboBox<String>(op);
        aggressiveInput.setBounds(310,20,80,20);
        popUp.add(aggressive);
        popUp.add(aggressiveInput);

        JButton submit = submitButton(pokemon, weightInput, habitatInput, aggressiveInput);
        popUp.add(submit);
        popUp.setVisible(true);
    }
    public JButton submitButton(Pokemon pokemon, JTextField weightInput, JTextField habitatInput, JComboBox<String> aggressiveInput){
        JButton submit = new JButton("Criar");
        submit.setBounds(316, 120, 160,30);

        submit.addActionListener(actionEvent -> {

            float weightPokemon = Float.parseFloat(weightInput.getText());
            String habitatPokemon = habitatInput.getText();

            boolean aggressivePokemon = aggressiveInput.getSelectedItem().toString().equals("Sim") ? true: false;
            Pokemon pokemonUpdate = new Pokemon();
            pokemonUpdate.updatePokemon(pokemon.getName(), weightPokemon, habitatPokemon, aggressivePokemon);
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
    public JButton createDeleteButton(String name){
        JButton newButton = new JButton("Deletar");
        newButton.setBounds(180, 62,100,30);
        newButton.addActionListener(ActionListener->{
            Pokemon pokemon = new Pokemon();
            pokemon.delete(name);
        });

        return newButton;
    }
}
