package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws IOException {
        char anotherGame;
        Scanner scanner = new Scanner(System.in);

        do {
            Hangman.mainGame();
            System.out.println("Would you like to play again? (y/n");
            anotherGame = scanner.nextLine().charAt(0);
        } while (anotherGame == 'y');
    }

    public static void mainGame() throws IOException {
        // Dictionary of words
        Scanner wordFile =
                new Scanner(new URL("http://web.stanford.edu/class/archive/cs/cs106l/cs106l.1102/assignments/dictionary.txt").openStream());
        // Create an array list of words
        ArrayList<String> dict = new ArrayList<>();
        while (wordFile.hasNext()) {
            dict.add(wordFile.nextLine());
        }

        // Computer to choose a random word from the array list dict
        Random rand = new Random();
        String randWord = dict.get(rand.nextInt(dict.size())); //get random element in dict
//        System.out.println(randWord);


        ArrayList<Character> letterBank = new ArrayList<>();
        ArrayList<Character> wrongLetterBank = new ArrayList<>();

        System.out.println("***************************************************************");
        System.out.println("LET'S PLAY HANGMAN");
        System.out.println("+---+");
        System.out.println("    |");
        System.out.println("    |");
        System.out.println("    |");
        System.out.println("   ===");
        System.out.println();
        System.out.println("***************************************************************");
        System.out.println("Guess the word. It has " + randWord.length() + " letters in it.");


        wordGuess(randWord, letterBank);

        // Guess the letter
        while (true) {
            if (wrongLetterBank.isEmpty()) {
                System.out.println("Missed letters: ______");
            } else {
                System.out.println("Missed letters: " + wrongLetterBank.toString().replace("[", "").replace("]", ""));
            }

            if (wrongLetterBank.size() == 1) {
                System.out.println("+---+");
                System.out.println(" O  |");
                System.out.println("    |");
                System.out.println("    |");
                System.out.println("   ===");
                System.out.println();
            }
            if (wrongLetterBank.size() == 2) {
                System.out.println("+---+");
                System.out.println(" O  |");
                System.out.println(" |  |");
                System.out.println("    |");
                System.out.println("   ===");
                System.out.println();
            }
            if (wrongLetterBank.size() == 3) {
                System.out.println("+---+");
                System.out.println(" O  |");
                System.out.println("\\|  |");
                System.out.println("    |");
                System.out.println("   ===");
                System.out.println();
            }
            if (wrongLetterBank.size() == 4) {
                System.out.println("+---+");
                System.out.println(" O  |");
                System.out.println("\\|/ |");
                System.out.println("    |");
                System.out.println("   ===");
                System.out.println();
            }
            if (wrongLetterBank.size() == 5) {
                System.out.println("+---+");
                System.out.println(" O  |");
                System.out.println("\\|/ |");
                System.out.println("/   |");
                System.out.println("   ===");
                System.out.println();
            }
            if (wrongLetterBank.size() == 6) {
                System.out.println("+---+");
                System.out.println(" O  |");
                System.out.println("\\|/ |");
                System.out.println("/ \\ |");
                System.out.println("   ===");
                System.out.println("Game over! You lose.");
                break;
            }

            System.out.println("Guess a letter.");
            Scanner sc = new Scanner(System.in);
            String guess = sc.nextLine();
            if (guess.length() > 1) {
                System.out.println("Please enter only one letter.");
            } else if (guess.isEmpty()) {
                System.out.println("Please enter a letter.");
            } else if (letterBank.contains(guess.charAt(0))) {
                System.out.println("You have already guessed that letter. Choose again.");
            } else if (!randWord.contains(guess)) {
                wrongLetterBank.add(guess.charAt(0));
            } else {
                letterBank.add(guess.charAt(0));
            }

            if (wordGuess(randWord, letterBank)) {
                System.out.println("Yes! The secret word is " + randWord + "! You have won!");
                break;
            }
        }


    }

    private static boolean wordGuess(String randWord, List<Character> letterBank) {
        int counter = 0;
        for (int i = 0; i < randWord.length(); i++){
            if (letterBank.contains(randWord.charAt(i))){
                System.out.print(randWord.charAt(i));
                counter++;
            } else {
                System.out.print("_");
            }
        }

        System.out.println();
        return (randWord.length() == counter);
    }
}