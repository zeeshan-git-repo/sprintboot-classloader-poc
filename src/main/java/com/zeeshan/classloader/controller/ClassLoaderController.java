package com.zeeshan.classloader.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api")
public class ClassLoaderController {

    @GetMapping("/process")
    public String processData(@RequestParam String filePath, @RequestParam String className) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<String> result = executor.submit(() -> {
            try (URLClassLoader loader = new URLClassLoader(
                    new URL[]{new File(filePath).toURI().toURL()},
                    ClassLoaderController.class.getClassLoader())) {

                Class<?> externalClass = loader.loadClass(className);
                Object instance = externalClass.getDeclaredConstructor().newInstance();

                Method method = externalClass.getMethod("fetchData");
                String data = (String) method.invoke(instance);

                return "Data fetched: " + data;
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        });

        try {
            return result.get(); // Wait for thread to finish
        } catch (Exception e) {
            return "Execution failed: " + e.getMessage();
        } finally {
            executor.shutdown();
        }
    }
}
