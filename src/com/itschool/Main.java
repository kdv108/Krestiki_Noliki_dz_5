package com.itschool;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Random rnd = new Random();
    private static Scanner in = new Scanner(System.in);
    private static final byte RAZMER = 3;
    private static char[][] playDisplay = new char[RAZMER][RAZMER];
    private static byte players;
    private static byte playMove;
    private static boolean win;
    private static byte[][] records = new byte[2][2];

    public static void main(String[] args) {
        boolean start = true;
        byte choice;
        for (int i = 0; i < records.length; i++){
            for (int j = 0; j < records[i].length; j++){
                records[i][j] = 0;
            }
        }
        while(start) {
            System.out.println("\n\n\n>>>>>>>>>  М Е Н Ю :  <<<<<<<<<\n\n  1 - Старт игры Крестики - Нолики\n  8 - Статистика\n  0 - Выход\n\n  Введите номер пункта меню: ");
            choice = in.nextByte();
            if (choice == 1) {
                for (int i = 0; i < playDisplay.length; i++) {
                    for (int j = 0; j < playDisplay[i].length; j++) {
                        playDisplay[i][j] = 0;
                    }
                }
                playMove = 0;
                win = false;
                gameXO();
            } else if (choice == 8){
                System.out.println("\n\n ______________Статистика_____________\n Вид игры:          |   Победа   |     Поражение");
                for (byte[] a:records ){
                    choice++;
                    if (choice == 9)System.out.print("Игра с соперником  ");
                    else System.out.print("\nИгра с компьютером ");
                    for (byte b:a){
                        System.out.print(" |     "+b+"     ");
                    }
                }
            } else if (choice == 0){
                start = false;
            }
        }
    }
    private static void xDisplay(){
        int ramka = 0;
        for (char[] row:playDisplay){
            for (char cloumn:row){
                if ((ramka % RAZMER) != 0) System.out.print("  |  "+(cloumn == 0 ? " " : cloumn));
                else System.out.print("  "+(cloumn == 0 ? " " : cloumn));
                ramka++;
            }
            if (ramka != (RAZMER * RAZMER)) System.out.println("\n-----------------");
        }
    }
    private static void move(){
        int xMove;
        boolean kontr = false;
        if ((playMove % 2) == 0) {
            System.out.println("\nВведите номер свободной ячейки от 1 до 9, Ход Игрока №1:");
            xMove = in.nextInt();
            playDisplay[(((xMove - 1) - (xMove - 1) % RAZMER) / RAZMER)][((xMove - 1) % RAZMER)] = 'X';
        } else {
            if (players == 2){
                System.out.println("\nВведите номер свободной ячейки от 1 до 9, Ход Игрока №2:");
                xMove = in.nextInt();
                playDisplay[(((xMove - 1) - (xMove - 1) % RAZMER) / RAZMER)][((xMove - 1) % RAZMER)] = 'O';
            } else {
                while(!kontr) {
                    xMove = rnd.nextInt(RAZMER * RAZMER - 1) + 1;
                    if (playDisplay[(((xMove - 1) - (xMove - 1) % RAZMER) / RAZMER)][((xMove - 1) % RAZMER)] == 0) {
                        System.out.println("\nКомпьютер делает ход...");
                        playDisplay[(((xMove - 1) - (xMove - 1) % RAZMER) / RAZMER)][((xMove - 1) % RAZMER)] = 'O';
                        kontr = true;
                    }
                }
            }
        }
    }
    private static void winner(){
        for (int i = 0; i < RAZMER; i++){
            if ((playDisplay[i][RAZMER - 3] == playDisplay[i][RAZMER - 2]) && (playDisplay[i][RAZMER - 2] == playDisplay[i][RAZMER - 1]) && (playDisplay[i][RAZMER - 1] != 0)){
                win = true;
                break;
            } else if ((playDisplay[RAZMER - 3][i] == playDisplay[RAZMER - 2][i]) && (playDisplay[RAZMER - 2][i] == playDisplay[RAZMER - 1][i]) && (playDisplay[RAZMER - 1][i] != 0)){
                win = true;
                break;
            } else if (i == 0){
                if ((playDisplay[i][i] == playDisplay[i + 1][i + 1]) && (playDisplay[i + 1][i + 1] == playDisplay[i + 2][i + 2]) && (playDisplay[i + 2][i + 2] != 0)){
                    win = true;
                    break;
                } else if ((playDisplay[i][i + 2] == playDisplay[i + 1][i + 1]) && (playDisplay[i + 2][i] == playDisplay[i + 1][i + 1]) && (playDisplay[i + 1][i + 1] != 0)){
                    win = true;
                    break;
                }
            }
        }
    }
    private static void gameXO(){
        System.out.println("--------------------------<<<<<<  КРЕСТИКИ - НОЛИКИ  >>>>>>----------------------------\n\n\n Введите колличество игроков ( 1 или 2 ): ");
        players = in.nextByte();
        for (int i = 0; i < RAZMER*RAZMER; i++) {
            xDisplay();
            move();
            if (i > RAZMER) {
                winner();
                if (win) break;
            }
            playMove++;
        }
        xDisplay();
        if (win){
            if ((playMove % 2) == 0){
                System.out.println("\n --------------- Поздравляем! Игрок №1 Победил! -------------");
                if (players == 2) records[0][0]++;
                else records[1][0]++;
            } else if (players == 2){
                System.out.println("\n --------------- Поздравляем! Игрок №2 Победил! -------------");
                records[0][1]++;
            } else {
                System.out.println("\n -------------- Компьютер в очередной раз обыграл человека! Вы проиграли... -------------");
                records[1][1]++;
            }
        } else System.out.println("\n -------------- На этот раз Победила Дружба, Ничья! -------------");
    }
}
