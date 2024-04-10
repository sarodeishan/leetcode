package src;

import java.util.Arrays;
import java.util.function.Consumer;

public class RobotBoundedInACircle {

    public static void main(String[] args) {
        RobotBoundedInACircle test = new RobotBoundedInACircle();
        System.out.println("Answer:" + test.isRobotBounded("GGLLGG"));
        System.out.println();
        System.out.println("Answer:" + test.isRobotBounded("GG"));
        System.out.println();
        System.out.println("Answer:" + test.isRobotBounded("GL"));
        System.out.println();
    }

    enum Instruction {
        GO('G', (robot) -> robot.direction.move.accept(robot)),
        LEFT('L', (robot) -> robot.direction = robot.direction.rotate(-1)),
        RIGHT('R', (robot) -> robot.direction = robot.direction.rotate(1));

        private Character c;
        private Consumer<Robot> perform;

        Instruction(Character c, Consumer<Robot> perform) {
            this.c = c;
            this.perform = perform;
        }

        static Instruction getInstruction(Character val) {
            return Arrays.stream(Instruction.values()).filter(instruction -> instruction.c == val).findAny().get();
        }
    }

    enum Direction {
        N(0, robot -> robot.y++),
        E(1, robot -> robot.x++),
        S(2, robot -> robot.y--),
        W(3, robot -> robot.x--);

        int value;
        Consumer<Robot> move;

        Direction(int value, Consumer<Robot> move) {
            this.value = value;
            this.move = move;
        }

        Direction rotate(int val) {
            int newValue = this.value + val;
            newValue %= Direction.values().length;
            if (newValue < 0) {
                newValue = Direction.values().length + newValue;
            }
            return fromValue(newValue);
        }

        private Direction fromValue(int value) {
            return Arrays.stream(Direction.values()).filter(direction -> direction.value == value).findAny().orElse(null);
        }
    }

    static class Robot {
        int x = 0;
        int y = 0;
        Direction direction = Direction.N;

        void perform(Instruction instruction) {
            instruction.perform.accept(this);
        }

    }

    public boolean isRobotBounded(String instructions) {
        Robot robot = new Robot();
        boolean result = false;
        for (int i = 0; i < 4; i++) {
            performInstructions(instructions, robot);
            if (robot.x == 0 && robot.y == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    private void performInstructions(String instructions, Robot robot) {
        for (Character instr : instructions.toCharArray()) {
            Instruction instruction = Instruction.getInstruction(instr);
            robot.perform(instruction);
        }
    }

}
