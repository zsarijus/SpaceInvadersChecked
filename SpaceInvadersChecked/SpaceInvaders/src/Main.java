import java.util.Scanner;
   /* String referenceScreen = "_____________________________________________________________________________________\n" +
            "                     (=HOH=)                                                                \n" +
            "            T     T     T     T     T     T     T     T     T     T     T            \n" +
            "            T     T     T     T     T     T     T     T     T     T     T            \n" +
            "           [Y]   [Y]   [Y]   [Y]   [Y]   [Y]   [Y]   [Y]   [Y]   [Y]   [Y]           \n" +
            "           [Y]   [Y]   [Y]   [Y]   [Y]   [Y]   [Y]   [Y]   [Y]   [Y]   [Y]           \n" +
            "          (=H=) (=H=) (=H=) (=H=) (=H=) (=H=) (=H=) (=H=) (=H=) (=H=) (=H=)          \n" +
            "          (=H=) (=H=) (=H=) (=H=) (=H=) (=H=) (=H=) (=H=) (=H=) (=H=) (=H=)          \n" +
            "                                                                                     \n" +
            "                                                                                     \n" +
            "                                                                                     \n" +
            "                                                                                     \n" +
            "                                                                                     \n" +
            "          ##########        ##########         ##########        ##########          \n" +
            "          ##########        ##########         ##########        ##########          \n" +
            "          ###    ###        ###    ###         ###    ###        ###    ###          \n" +
            "                                                                                     \n" +
            "                                         <I>                                         \n" +
            "                                                                                     \n" +
            "_____________________________________________________________________________________\n";*/
public class Main {






    public static void main(String[] args){
        Boolean gameEnded = false;
        Boolean roundEnded;
        Scanner scanner = new Scanner(System.in);
        while(gameEnded == false){
            roundEnded = false;
            GameEngine gameEngine = new GameEngine();
            gameEngine.UpdateBattleField();
            gameEngine.RenderGame();
            while(roundEnded == false){
                String input = scanner.nextLine();
                gameEngine.UpdatePlayer(input);
                gameEngine.UpdateBattleField();
                gameEngine.RenderGame();
                gameEnded = gameEngine.CheckIfGameEnded();
                if(gameEnded == true){
                    break;
                }
                roundEnded = gameEngine.CheckIfRoundEnded();
            }
        }
        scanner.close();






    }

}

