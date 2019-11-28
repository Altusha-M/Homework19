package myApp;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Stateless(name = "MyBean")
public class MyEJBean {

    /**
     *
     * @param dir specific directory which files and dirs you want to get
     * @return all files and dirs, inside requested directory
     */
    ArrayList<String> getDir(String dir) {
        System.out.println();
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        StringBuffer sb = new StringBuffer();
        DirTask t = new DirTask(dir);
        List<String> invoke = forkJoinPool.invoke(t);
        ArrayList<String> SL = new ArrayList<>();
        for (String s : invoke) {
            if (s == null) {
                continue;
            }
            SL.add(s);
        }
        String s = "java";
        return SL;
    }
}