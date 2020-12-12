package GraphicInterface;

import Entities.Trainer;

import javax.swing.*;

public class TrainerDetailsWindow extends JFrame {
    private JFrame window;
    private Trainer updateThis;

    public TrainerDetailsWindow(Trainer trainer, JFrame window) {
        this.window = window;
        JFrame popUp = new JFrame();
        popUp.setTitle("Visualizar Treinador");
        popUp.setBounds(320, 200, 400, 200);
        popUp.getContentPane().setLayout(null);
        int x=6,y=2,width=300, height=50;
        JLabel id = createJLabel("Id: "+ trainer.getId(), x,y);
        y+=22;
        JLabel name = createJLabel("Nome: "+ trainer.getName(), x,y);
        y+=22;
        JLabel age = createJLabel("Idade: "+ trainer.getAge(), x,y);

        popUp.add(id);
        popUp.add(name);
        popUp.add(age);


        JButton update = createUpdateButton(trainer.getId());
        JButton delete = createDeleteButton(trainer.getId());
        JButton pokemonsTreinador = createPokemonsButton(trainer);
        popUp.add(update);
        popUp.add(delete);
        popUp.add(pokemonsTreinador);
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
        Trainer trainer = new Trainer();
       this.updateThis = trainer.selectOne(id);
        newButton.addActionListener((actionEvent) -> {
            windowUpdate(this.updateThis);
        });

        return newButton;
    }
    public void windowUpdate(Trainer trainer){
        JFrame popUp = new JFrame();
        popUp.setTitle("Atualizar Treinador");
        popUp.setBounds(300, 200, 600, 350);
        popUp.getContentPane().setLayout(null);


        JLabel name = createLabel("Nome: ",6, 2);
        JTextField nameInput = createInput(60,12);
        popUp.getContentPane().add(name);
        popUp.getContentPane().add(nameInput);

        JLabel age = createLabel("Idade: ",6, 39);
        JTextField ageInput = createInput(60,48);
        popUp.add(age);
        popUp.add(ageInput);

        JButton submit = submitButton(trainer, nameInput, ageInput);
        popUp.add(submit);
        popUp.setVisible(true);
    }
    public JButton submitButton(Trainer trainer, JTextField nameInput, JTextField ageInput){
        JButton submit = new JButton("Atualizar");
        submit.setBounds(316, 120, 160,30);

        submit.addActionListener(actionEvent -> {

            String name = nameInput.getText();
            int age = Integer.parseInt(ageInput.getText());

            Trainer trainerUpdate = new Trainer();
            trainerUpdate.updateTrainer(trainer.getId(), name, age);

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
            Trainer trainer = new Trainer();
            trainer.delete(Integer.toString(id));
        });

        return newButton;
    }
    public JButton createPokemonsButton(Trainer trainer){
        JButton newButton = new JButton("Pokemons");
        newButton.setBounds(180, 102,100,30);
        newButton.addActionListener(ActionListener->{
            PokemonTrainerReference trainer_pokemons = new PokemonTrainerReference(trainer);
        });

        return newButton;
    }

}
