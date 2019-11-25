import org.w3c.dom.ls.LSOutput;

import javax.ejb.Stateless;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Stateless(name = "MyBean")
public class MyEJBean {

    int a;
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
        return SL;
    }
}