import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SJN {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 0, 6));
        processes.add(new Process(2, 1, 8));
        processes.add(new Process(3, 2, 7));
        processes.add(new Process(4, 3, 3));

        System.out.println("Best Fit SJN:");
        bestFitSJN(new ArrayList<>(processes));

        System.out.println("\nFirst Fit SJN:");
        firstFitSJN(new ArrayList<>(processes));
    }

    public static void bestFitSJN(List<Process> processes) {
        Collections.sort(processes, (p1, p2) -> Integer.compare(p1.burstTime, p2.burstTime));

        int currentTime = 0;
        double totalTurnaroundTime = 0;
        double totalWaitingTime = 0;

        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");

        while (!processes.isEmpty()) {
            Process bestFitProcess = null;

            for (Process process : processes) {
                if (process.arrivalTime <= currentTime
                        && (bestFitProcess == null || process.burstTime < bestFitProcess.burstTime)) {
                    bestFitProcess = process;
                }
            }

            if (bestFitProcess == null) {
                currentTime++;
                continue;
            }

            int completionTime = currentTime + bestFitProcess.burstTime;
            int turnaroundTime = completionTime - bestFitProcess.arrivalTime;
            int waitingTime = turnaroundTime - bestFitProcess.burstTime;

            totalTurnaroundTime += turnaroundTime;
            totalWaitingTime += waitingTime;

            System.out.println(bestFitProcess.id + "\t\t\t" + bestFitProcess.arrivalTime + "\t\t\t\t" + bestFitProcess.burstTime + "\t\t\t\t" +
                    completionTime + "\t\t\t\t" + turnaroundTime + "\t\t\t" + waitingTime);

            processes.remove(bestFitProcess);
            currentTime = completionTime;
        }

        double averageTurnaroundTime = totalTurnaroundTime / processes.size();
        double averageWaitingTime = totalWaitingTime / processes.size();

        System.out.println("\nAverage Turnaround Time: " + averageTurnaroundTime);
        System.out.println("Average Waiting Time: " + averageWaitingTime);
    }

    public static void firstFitSJN(List<Process> processes) {
        Collections.sort(processes, (p1, p2) -> Integer.compare(p1.burstTime, p2.burstTime));

        int currentTime = 0;
        double totalTurnaroundTime = 0;
        double totalWaitingTime = 0;

        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");

        for (Process process : processes) {
            int completionTime = currentTime + process.burstTime;
            int turnaroundTime = completionTime - process.arrivalTime;
            int waitingTime = turnaroundTime - process.burstTime;

            totalTurnaroundTime += turnaroundTime;
            totalWaitingTime += waitingTime;

            System.out.println(process.id + "\t\t\t" + process.arrivalTime + "\t\t\t\t" + process.burstTime + "\t\t\t\t" +
                    completionTime + "\t\t\t\t" + turnaroundTime + "\t\t\t" + waitingTime);

            currentTime = completionTime;
        }

        double averageTurnaroundTime = totalTurnaroundTime / processes.size();
        double averageWaitingTime = totalWaitingTime / processes.size();

        System.out.println("\nAverage Turnaround Time: " + averageTurnaroundTime);
        System.out.println("Average Waiting Time: " + averageWaitingTime);
    }
}
