import java.util.*;

class TaskManager {

    // Custom Task class to hold task details
    class Task {
        int userId;
        int taskId;
        int priority;

        Task(int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }
    }

    // Priority queue to store tasks, ordered by priority (desc) then taskId (desc)
    private PriorityQueue<Task> pq;
    // HashMap for O(1) lookups of tasks by taskId
    private HashMap<Integer, Task> taskMap;
    // Set to track removed or edited tasks for lazy deletion
    private HashSet<Integer> removedTasks;

    public TaskManager(List<List<Integer>> tasks) {
        pq = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) {
                return Integer.compare(b.priority, a.priority);
            }
            return Integer.compare(b.taskId, a.taskId);
        });
        taskMap = new HashMap<>();
        removedTasks = new HashSet<>();

        for (List<Integer> taskData : tasks) {
            int userId = taskData.get(0);
            int taskId = taskData.get(1);
            int priority = taskData.get(2);
            Task task = new Task(userId, taskId, priority);
            pq.add(task);
            taskMap.put(taskId, task);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        pq.add(task);
        taskMap.put(taskId, task);
    }

    public void edit(int taskId, int newPriority) {
        Task oldTask = taskMap.get(taskId);
        if (oldTask != null) {
            removedTasks.add(oldTask.taskId);
        }

        Task newTask = new Task(oldTask.userId, oldTask.taskId, newPriority);
        pq.add(newTask);
        taskMap.put(taskId, newTask);
    }

    public void rmv(int taskId) {
        if (taskMap.containsKey(taskId)) {
            removedTasks.add(taskId);
            taskMap.remove(taskId);
        }
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            Task topTask = pq.peek();
            if (taskMap.get(topTask.taskId) == topTask) {
                pq.poll();
                taskMap.remove(topTask.taskId);
                return topTask.userId;
            } else {
                pq.poll();
            }
        }
        return -1;
    }
}