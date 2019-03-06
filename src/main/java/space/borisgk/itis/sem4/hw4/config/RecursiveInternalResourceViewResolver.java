package space.borisgk.itis.sem4.hw4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Queue;

public class RecursiveInternalResourceViewResolver extends InternalResourceViewResolver {
    @Autowired
    ServletContext context;

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        if (isNumber(viewName)) {
            var num = (Integer) Integer.parseInt(viewName);
            return new RedirectView(){{
                setStatusCode(HttpStatus.valueOf(num));
                setUrl("/");
            }};
        }
        return super.buildView(searchPath(viewName));
    }

    protected String searchPath(String viewName) {
        Path root = Paths.get(context.getRealPath("/"));
        if (getPrefix().charAt(0) == '/') {
            root = root.resolve(getPrefix().substring(1));
        }
        else {
            root = root.resolve(getPrefix());
        }
        var queue = new ArrayDeque<File>();
        if (root.toFile().exists() == false) {
            return viewName;
        }
        if (root.toFile().isDirectory() == false) {
            return viewName;
        }
        queue.add(root.toFile());
        while (!queue.isEmpty()) {
            var p = queue.getFirst().getAbsolutePath();
            if (p.endsWith(viewName + getSuffix())) {
                var s = p.substring(root.toString().length());
                return s.substring(1, s.length() - getSuffix().length()).replace('\\', '/');
            }
            for (var f : queue.getFirst().listFiles()) {
                queue.addLast(f);
            }
            queue.pollFirst();
        }
        return viewName;
    }

    protected boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
