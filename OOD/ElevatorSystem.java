package Test;

import java.util.*;

public class ElevatorSystem {
    enum Direction {
        UP, DOWN
    }

    enum Status {
        UP, DOWN, IDLE
    }

    static class ExternalRequest {

        private Direction direction;
        private int level;

        public ExternalRequest(int l, Direction d) {
            this.level = l;
            this.direction = d;
        }

        public int getLevel()
        {
            return level;
        }

        public Direction getDirection()
        {
            return direction;
        }
    }

    static class InternalRequest {
        private int level;

        public InternalRequest(int l) {
            this.level = l;
        }

        public int getLevel()
        {
            return level;
        }
    }

    class ElevatorButton {
        private int level;
        private Elevator elevator;

        public ElevatorButton(int level, Elevator e) {
            this.level = level;
            this.elevator = e;
        }

        public void pressButton() {
            InternalRequest request = new InternalRequest(level);
            elevator.handleInternalRequest(request);
        }
    }


    public static class Elevator {
        private int currLevel;
        private Status status;


        private List<Boolean> upStops;
        private List<Boolean> downStops;

        private List<ElevatorButton> buttons;

        public Elevator(int n) {
            currLevel = 0;
            buttons = new ArrayList<ElevatorButton>();
            upStops = new ArrayList<Boolean>();
            downStops = new ArrayList<Boolean>();
            status = Status.IDLE;

            for(int i = 0; i < n; i++) {
                upStops.add(false);
                downStops.add(false);
            }
        }

        public void handleExternalRequest(ExternalRequest r) {
            if(r.getDirection() == Direction.UP) {
                upStops.set(r.getLevel() - 1, true);
                if(noRequests(downStops)) {
                    status = Status.UP;
                }
            } else {
                downStops.set(r.getLevel() - 1, true);
                if(noRequests(upStops)) {
                    status = Status.DOWN;
                }
            }
        }

        public void handleInternalRequest(InternalRequest r) {
            // if r.getLevel() == curr level, we press the button at the same floor
            // if the elevator is currently going up, we need the check whether the
            // the level pressed is on the same direction of up, if YES, add : if NO, disregard
            if(status == Status.UP) {
                if(r.getLevel() >= currLevel + 1) {
                    upStops.set(r.getLevel() - 1, true);
                }
            } else if(status == Status.DOWN) {
                if(r.getLevel() <= currLevel + 1) {
                    downStops.set(r.getLevel() - 1, true);
                }
            }
        }

        public void openGate() {
            // if the elevator is currently going up
            if(status == Status.UP) {
                for(int i = 0; i < upStops.size(); i++) {
                    int checkLevel = (currLevel + i) % upStops.size();
                    if(upStops.get(checkLevel)) {
                        currLevel = checkLevel;
                        upStops.set(checkLevel, false);
                        break;
                    }
                }
            // if the elevator is currently going down
            } else if(status == Status.DOWN) {
                for(int i = 0; i < downStops.size(); i++) {
                    int checkLevel = (currLevel + downStops.size() - i) % downStops.size();
                    if(downStops.get(checkLevel)) {
                        currLevel = checkLevel;
                        downStops.set(checkLevel, false);
                        break;
                    }
                }
            }
        }

        public void closeGate() {
            // if the elevator is IDLE
            if(status == Status.IDLE) {

                if(noRequests(downStops)) {
                    status = Status.UP;
                    return;
                }

                if(noRequests(upStops)) {
                    status = Status.DOWN;
                    return;
                }
            // if the elevator is currently going up
            } else if(status == Status.UP) {

                if(noRequests(upStops)) {
                    if (noRequests(downStops)) {
                        status = Status.IDLE;
                    } else {
                        status = Status.DOWN;
                    }
                }
            // if the elevator is currently going down
            } else {

                if(noRequests(downStops)) {

                    if(noRequests(upStops)) {
                        status = Status.IDLE;
                    }
                    else {
                        status = Status.UP;
                    }
                }

            }
        }

        private boolean noRequests(List<Boolean> stops) {
            for(int i = 0; i < stops.size(); i++)
            {
                if(stops.get(i))
                {
                    return false;
                }
            }
            return true;
        }

        public void insertButton(ElevatorButton eb) {
            buttons.add(eb);
        }

        public String elevatorStatusDescription()
        {
            String description = "Currently elevator status is : " + status
                    + ".\nCurrent level is at: " + (currLevel + 1)
                    + ".\nup stop list looks like: " + upStops
                    + ".\ndown stop list looks like:  " + downStops
                    + ".\n*****************************************\n";
            return description;
        }
    }

    public static void main(String[] args) {
        ElevatorSystem.Elevator elevator = new ElevatorSystem.Elevator(5);
        ExternalRequest r1 = new ExternalRequest(2, Direction.DOWN);
        ExternalRequest r2 = new ExternalRequest(3, Direction.DOWN);

        elevator.handleExternalRequest(r1);
        System.out.println(elevator.elevatorStatusDescription());
        elevator.handleExternalRequest(r2);

        System.out.println(elevator.elevatorStatusDescription());
        elevator.openGate();
        System.out.println(elevator.elevatorStatusDescription());

        InternalRequest ir1 = new InternalRequest(1);
        elevator.handleInternalRequest(ir1);
        System.out.println(elevator.elevatorStatusDescription());

        elevator.closeGate();
        System.out.println(elevator.elevatorStatusDescription());

        elevator.openGate();
        System.out.println(elevator.elevatorStatusDescription());
        elevator.closeGate();
        System.out.println(elevator.elevatorStatusDescription());

        elevator.openGate();
        System.out.println(elevator.elevatorStatusDescription());
        elevator.closeGate();
        System.out.println(elevator.elevatorStatusDescription());

    }
}

/**
 * Currently elevator status is : DOWN.
 * Current level is at: 1.
 * up stop list looks like: [false, false, false, false, false].
 * down stop list looks like:  [false, true, false, false, false].
 * *****************************************
 *
 * Currently elevator status is : DOWN.
 * Current level is at: 1.
 * up stop list looks like: [false, false, false, false, false].
 * down stop list looks like:  [false, true, true, false, false].
 * *****************************************
 *
 * Currently elevator status is : DOWN.
 * Current level is at: 3.
 * up stop list looks like: [false, false, false, false, false].
 * down stop list looks like:  [false, true, false, false, false].
 * *****************************************
 *
 * Currently elevator status is : DOWN.
 * Current level is at: 3.
 * up stop list looks like: [false, false, false, false, false].
 * down stop list looks like:  [true, true, false, false, false].
 * *****************************************
 *
 * Currently elevator status is : DOWN.
 * Current level is at: 3.
 * up stop list looks like: [false, false, false, false, false].
 * down stop list looks like:  [true, true, false, false, false].
 * *****************************************
 *
 * Currently elevator status is : DOWN.
 * Current level is at: 2.
 * up stop list looks like: [false, false, false, false, false].
 * down stop list looks like:  [true, false, false, false, false].
 * *****************************************
 *
 * Currently elevator status is : DOWN.
 * Current level is at: 2.
 * up stop list looks like: [false, false, false, false, false].
 * down stop list looks like:  [true, false, false, false, false].
 * *****************************************
 *
 * Currently elevator status is : DOWN.
 * Current level is at: 1.
 * up stop list looks like: [false, false, false, false, false].
 * down stop list looks like:  [false, false, false, false, false].
 * *****************************************
 *
 * Currently elevator status is : IDLE.
 * Current level is at: 1.
 * up stop list looks like: [false, false, false, false, false].
 * down stop list looks like:  [false, false, false, false, false].
 * *****************************************
 */

