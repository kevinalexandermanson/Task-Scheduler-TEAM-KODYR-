package scheduling;

import graph.TaskGraph;
import javafx.concurrent.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olive on 14/08/2018.
 */
public class ParallelSchedule extends Schedule implements Serializable {

    private ParallelThread thread;
    private int upperBound;
    private boolean finishedDFS = false;

    /**
     * Constructor initliases graph, and thread.
     * This constructor is to be called
     * @param processorNumber
     * @param graph
     */
    public ParallelSchedule(int processorNumber, TaskGraph graph, int upperBound) {
        super(processorNumber, graph);
        this.upperBound = upperBound;
        setThread();
    }

//    public ParallelSchedule(int processorNumber, TaskGraph graph, ParallelThread thread, int upperBound) {
//        super(processorNumber, graph);
//        this.thread = thread;
//        ParallelDFS dfs = new ParallelDFS(this, upperBound);
//        thread.addScheduler(dfs);
//    }
//
//    public void setThread(ParallelSchedule schedule) {
//        this.thread = schedule.thread;
//        ParallelDFS dfs = new ParallelDFS(this, upperBound);
//        thread.addScheduler(dfs, this);
//    }

    public void setThread() {
        thread = new ParallelThread();
        ParallelDFS dfs = new ParallelDFS(this, upperBound);
        thread.addScheduler(dfs, this);
    }


    public void run() {
        if (!thread.isAlive()) {
            thread.start();
        }
    }

    public ParallelThread getThread() {
        return thread;
    }

    void setFinishedDFS() {
        finishedDFS = true;
    }

    public boolean getFinished() {
        return finishedDFS;
    }
}