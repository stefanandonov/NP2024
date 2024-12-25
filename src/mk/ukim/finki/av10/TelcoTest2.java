package mk.ukim.finki.av10;

//package mk.ukim.finki.midterm;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class DurationConverter {
    public static String convert(long duration) {
        long minutes = duration / 60;
        duration %= 60;
        return String.format("%02d:%02d", minutes, duration);
    }
}


abstract class State {
    Call call;

    public State(Call call) {
        this.call = call;
    }

    abstract void answer(long timestamp) throws Exception;

    abstract void end(long timestamp) throws Exception;

    abstract void hold(long timestamp) throws Exception;

    abstract void resume(long timestamp) throws Exception;
}

class RingingState extends State {

    public RingingState(Call call) {
        super(call);
    }

    @Override
    void answer(long timestamp) {
        this.call.start = timestamp;
        this.call.state = new InProgressState(this.call);

    }

    @Override
    void end(long timestamp) {
//        this.call.start = 0L;
        this.call.end = timestamp;
        this.call.state = new IdleState(this.call);
    }

    @Override
    void hold(long timestamp) throws Exception {
        throw new Exception("Not allowed action");
    }

    @Override
    void resume(long timestamp) throws Exception {
        throw new Exception("Not allowed action");
    }

    @Override
    public String toString() {
        return "RingingState{}";
    }
}

class InProgressState extends State {

    public InProgressState(Call call) {
        super(call);
    }

    @Override
    void answer(long timestamp) throws Exception {
        throw new Exception("Not allowed action");
    }

    @Override
    void end(long timestamp) {
        this.call.end = timestamp;
        this.call.state = new IdleState(this.call);
    }

    @Override
    void hold(long timestamp) {
        this.call.holdStarted = timestamp;
        this.call.state = new PausedState(this.call);
    }

    @Override
    void resume(long timestamp) throws Exception {
        throw new Exception("Not allowed action");
    }

    @Override
    public String toString() {
        return "InProgressState{}";
    }
}

class PausedState extends State {

    public PausedState(Call call) {
        super(call);
    }

    @Override
    void answer(long timestamp) throws Exception {
        throw new Exception("Not allowed action");
    }

    @Override
    void end(long timestamp) {
        this.call.end = timestamp;
        this.call.totalTimeInPaused += (timestamp - this.call.holdStarted);
        this.call.holdStarted = null;

        this.call.state = new IdleState(this.call);
    }

    @Override
    void hold(long timestamp) throws Exception {
        throw new Exception("Not allowed action");
    }

    @Override
    void resume(long timestamp) {
        this.call.totalTimeInPaused += (timestamp - this.call.holdStarted);
        this.call.holdStarted = null;
        this.call.state = new InProgressState(this.call);
    }

    @Override
    public String toString() {
        return "PausedState{}";
    }
}

class IdleState extends State {

    public IdleState(Call call) {
        super(call);
    }

    @Override
    void answer(long timestamp) throws Exception {
        throw new Exception("Not allowed action");
    }

    @Override
    void end(long timestamp) throws Exception {
        throw new Exception("Not allowed action");
    }

    @Override
    void hold(long timestamp) throws Exception {
        throw new Exception("Not allowed action");
    }

    @Override
    void resume(long timestamp) throws Exception {
        throw new Exception("Not allowed action");
    }

    @Override
    public String toString() {
        return "IdleState{}";
    }
}

class Call {
    String uuid;
    String dialer;
    String receiver;

    Long initialized;

    Long start;

    Long end;

    Long holdStarted;

    long totalTimeInPaused = 0;

    State state;

    public Call(String uuid, String dialer, String receiver, Long initialized) {
        this.uuid = uuid;
        this.dialer = dialer;
        this.receiver = receiver;
        this.initialized = initialized;
        state = new RingingState(this);
    }

    public Long getInitialized() {
        return initialized;
    }

    public Long getStart() {
        return start == null ? initialized : start;
    }

    public Long getEnd() {
        return end;
    }

    public Long getHoldStarted() {
        return holdStarted;
    }

    public long getTotalTimeInPaused() {
        return totalTimeInPaused;
    }

    public void update(String action, long timestamp) throws Exception {
        if (action.equals("ANSWER")) {
            state.answer(timestamp);
        } else if (action.equals("HOLD")) {
            state.hold(timestamp);
        } else if (action.equals("END")) {
            state.end(timestamp);
        } else {
            state.resume(timestamp);
        }
    }

    @Override
    public String toString() {
        return "Call{" +
                "uuid='" + uuid + '\'' +
                ", dialer='" + dialer + '\'' +
                ", receiver='" + receiver + '\'' +
                ", initialized=" + initialized +
                ", start=" + start +
                ", end=" + end +
                ", holdStarted=" + holdStarted +
                ", totalTimeInPaused=" + totalTimeInPaused +
                ", state=" + state +
                '}';
    }

    public long totalDuration() {
        return start == null ? 0 : end - start - totalTimeInPaused;
    }

    public String reportForPhoneNumber(String phoneNumber) {
        StringBuilder sb = new StringBuilder();
        if (dialer.equals(phoneNumber)) {
            sb.append("D").append(" ").append(receiver);
        } else {
            sb.append("R").append(" ").append(dialer);
        }

        sb.append(" ").append(start == null ? end : start).append(" ").append(start == null ? "MISSED CALL" : end).append(" ").append(DurationConverter.convert(start == null ? 0 : (end - start - totalTimeInPaused)));

        return sb.toString();

    }
}

class TelcoApp {

    Map<String, Call> callsByUUID = new HashMap<>();

    Map<String, List<Call>> callsByPhoneNumbers = new HashMap<>();

    public void addCall(String uuid, String dialer, String receiver, long timestamp) {
        Call call = new Call(uuid, dialer, receiver, timestamp);

        callsByUUID.put(uuid, call);

        callsByPhoneNumbers.putIfAbsent(dialer, new ArrayList<>());
        callsByPhoneNumbers.get(dialer).add(call);

        callsByPhoneNumbers.putIfAbsent(receiver, new ArrayList<>());
        callsByPhoneNumbers.get(receiver).add(call);

    }

    public void updateCall(String uuid, long timestamp, String action) throws Exception {
        callsByUUID.get(uuid).update(action, timestamp);
    }

    public void printChronologicalReport(String phoneNumber) {
        Comparator<Call> byStartComparator = Comparator.comparing(Call::getStart);

        callsByPhoneNumbers.get(phoneNumber).stream().sorted(byStartComparator).forEach(
                call -> System.out.println(call.reportForPhoneNumber(phoneNumber))
        );
    }

    public void printReportByDuration(String phoneNumber) {
        Comparator<Call> byDurationComparator = Comparator.comparing(Call::totalDuration).reversed();
        callsByPhoneNumbers.get(phoneNumber).stream().sorted(byDurationComparator).forEach(
                call -> System.out.println(call.reportForPhoneNumber(phoneNumber))
        );
    }

    public void printCallsDuration() {
        Map<String, Long> result = callsByUUID.values().stream().collect(Collectors.groupingBy(
                call -> String.format("%s <-> %s", call.dialer, call.receiver),
                Collectors.summingLong(Call::totalDuration)
        ));

        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.printf("%s : %s%n", entry.getKey(), DurationConverter.convert(entry.getValue())));
    }
}


public class TelcoTest2 {
    public static void main(String[] args) throws Exception {

//        Call call = new Call("1", "223", "305", 1000L);
//
////        System.out.println(c);
//
//
//        System.out.println(call.reportForPhoneNumber("223"));
//
//        call.update("ANSWER", 1100L);
//
////        System.out.println(call.reportForPhoneNumber("223"));
//
//        call.update("HOLD", 1220L);
//
////        System.out.println(call.reportForPhoneNumber("223"));
//
//        call.update("RESUME", 1250L);
//
////        System.out.println(call.reportForPhoneNumber("223"));
//
//        call.update("HOLD", 2020L);
//
////        System.out.println(call.reportForPhoneNumber("223"));
//
//
//        call.update("END", 2100L);
//
//        System.out.println(call.reportForPhoneNumber("223"));
        TelcoApp app = new TelcoApp();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            String command = parts[0];

            if (command.equals("addCall")) {
                String uuid = parts[1];
                String dialer = parts[2];
                String receiver = parts[3];
                long timestamp = Long.parseLong(parts[4]);
                app.addCall(uuid, dialer, receiver, timestamp);
            } else if (command.equals("updateCall")) {
                String uuid = parts[1];
                long timestamp = Long.parseLong(parts[2]);
                String action = parts[3];
                try {
                    app.updateCall(uuid, timestamp, action);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("printChronologicalReport")) {
                String phoneNumber = parts[1];
                app.printChronologicalReport(phoneNumber);
            } else if (command.equals("printReportByDuration")) {
                String phoneNumber = parts[1];
                app.printReportByDuration(phoneNumber);
            } else {
                app.printCallsDuration();
            }
        }

    }
}
