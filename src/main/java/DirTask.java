import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class DirTask extends RecursiveTask<List<String>> {

    interface i {

    }
    private String dir;

    public DirTask(String dir) {
        this.dir = dir;
    }

    @Override
    protected List<String> compute() {
        try {
            Path path = Paths.get(dir);
            if (Files.isDirectory(path)) {
                List<Path> innerFiles = Files.list(path).collect(Collectors.toList());
                List<DirTask> tasks = new ArrayList<>();
                for (Path innerFile : innerFiles) {
                    DirTask task = new DirTask(innerFile.toString());
                    tasks.add(task);
                }

                List<List<String>> collect = new ArrayList<>();
                Collection<DirTask> dirTasks = ForkJoinTask.invokeAll(tasks);
                for (DirTask t : dirTasks) {
                    List<String> join = t.join();
                    if (join == null) {
                        continue;
                    }
                    collect.add(join);
                }

                List<String> result = new ArrayList<>();
                for (List<String> strings : collect) {
                    result.addAll(strings);
                }
                return result;
            } else {
                return Collections.singletonList(dir);
            }
        } catch (IOException e) {
            return null;
        }
    }
}