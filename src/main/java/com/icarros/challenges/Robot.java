package com.icarros.challenges;

public class Robot {

    static class Rover {
        int x, y, degree;
        char direction;

        public Rover(int x, int y, char direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.degree = directionToDegree(direction);
        }

        public void executeCommand(char command) {
            if (command == 'M') {
                this.move();
            } else if (command == 'L') {
                this.rotate('L');
            } else if (command == 'R') {
                this.rotate('R');
            }
        }

        public void move() {

            switch (this.direction) {
                case 'N':
                    this.y++;
                    break;
                case 'S':
                    this.y--;
                    break;
                case 'W':
                    this.x--;
                    break;
                case 'E':
                    this.x++;
                    break;
                default:
                    break;
            }
        }

        public void rotate(char rotation) {

            if (rotation == 'L') {
                this.degree -= 90;

            } else if (rotation == 'R') {
                this.degree += 90;
            }
            this.direction = this.degreeToDirection(this.degree);

        }

        public char degreeToDirection(int degree) {
            degree = Math.floorMod(degree, 360);

            switch (degree) {
                case 0:
                    return 'N';
                case 90:
                    return 'E';
                case 180:
                    return 'S';
                case 270:
                    return 'W';
                default:
                    return ' ';
            }
        }

        public char directionToDegree(char direction) {
            switch (direction) {
                case'N':
                    return 0;
                case'E':
                    return 90;
                case 'S':
                    return 180;
                case 'W':
                    return 270;
                default:
                    return ' ';
            }
        }

        @Override
        public String toString() {
            return String.format("%d %d %s", x, y, direction);
        }
    }

    public static String controlRover(Rover rover, String commands) {

        for (char command : commands.toCharArray()) {
            rover.executeCommand(command);
        }

        return rover.toString();
    }


    public static void main(String[] args) {
        Rover rover0 = new Rover(1, 2, 'N');
        Rover rover1 = new Rover(3, 3, 'E');

        String commands0 = "LMLMLMLMM";
        String commands1 = "MMRMMRMRRM";

        controlRover(rover0, commands0);
        controlRover(rover1, commands1);

        System.out.println(rover0);
        System.out.println(rover1);
    }
}
