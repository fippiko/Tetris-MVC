package game.model;

import game.enums.GameState;
import game.model.form.Form;

import java.util.ArrayList;

public class Game {
   private Form            activeForm  = null;
   private ArrayList<Form> activeForms = new ArrayList<Form>();

   private GameState       state;
   
   public static final int COLCOUNT = 18;
   public static final int ROWCOUNT = 18;

   public Game() {
      this.state = GameState.NEXTFORM;
   }

   public void addForm(Form newForm) {
      this.activeForm = newForm;
      this.activeForms.add(newForm);

      this.state = GameState.FORMACTIVE;
   }

   public ArrayList<Form> getActiveForms() {
      return this.activeForms;
   }

   public GameState getState() {
      return this.state;
   }

   public Form getActiveForm() {
      return this.activeForm;
   }

   public void setState(GameState state) {
      this.state = state;
   }
}
