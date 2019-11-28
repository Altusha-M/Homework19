package myApp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.*;

public class DirTaskTest {

    @Test
    public void compute() {
        ArrayList<String> path1 = getPaths("./src/main/test");
        Set<String> files1 = new HashSet<String>(){{
            add("./src/main/test/myApp/DirTaskTest.java");
        }};
        ArrayList<String> path = getPaths("./1");
        Set<String> files = new HashSet<String>(){{
            add("./1/docker-compose.yml");
            add("./1/Dockerfile");
            add("./1/Homework19.war");
        }};
        assertTrue(path.containsAll(files));
        assertTrue(path1.containsAll(files1));
    }

    /**
     * Collect all files in directory
     * @param dir requested directory
     * @return files and
     */
    ArrayList<String> getPaths(String dir) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        DirTask t1 = new DirTask(dir);
        List<String> invoke1 = forkJoinPool.invoke(t1);
        ArrayList<String> SL1 = new ArrayList<>();
        for (String s : invoke1) {
            if (s == null) {
                continue;
            }
            String[] s1 = s.split("\\\\");
            StringBuilder stringBuilder = new StringBuilder();
            for (String ss : s1){
                stringBuilder.append(ss + "/");
            }
            SL1.add(stringBuilder.toString().substring(0, stringBuilder.length()-1));
        }
        return SL1;
    }
}