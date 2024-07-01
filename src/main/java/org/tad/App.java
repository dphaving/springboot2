package org.tad;

import ch.qos.logback.core.db.DBHelper;
import com.sun.glass.ui.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.tad.bean.Car;
import org.tad.bean.Person;
import org.tad.bean.Pet;
import org.tad.bean.User;
import org.tad.config.MyConfig;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        System.out.println("================================***============================");
        File folder = new File("C:\\");
        File[] files = folder.listFiles();

        if (files != null) {
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File file1, File file2) {
                    long size1 = file1.isDirectory() ? getFolderSize(file1) : 0;
                    long size2 = file2.isDirectory() ? getFolderSize(file2) : 0;
                    return Long.compare(size2, size1);
                }
            });

            int count = 0;
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(file.getAbsolutePath());
                    count++;
                }
                if (count == 10) {
                    break;
                }
            }
        }


        System.out.println("================================***============================");
        User jack = context.getBean("jack", User.class);
        User copyJack = context.getBean("jack", User.class);
        System.out.println("从容器中多次获取jack: " + (jack == copyJack));

        /**
         * 获取配置类:
         *  1.proxyBeanMethods = true: EnhancerBySpringCGLIB（代理类）
         *  2.proxyBeanMethods = false: MyConfig（普通类）
         */
        MyConfig myConfig = context.getBean(MyConfig.class);
        System.out.println(myConfig);

        /**
         * 代理类的好处：解决组件依赖
         * 非代理类好处：无需判断容器中有无bean，启动快
         */
        User jack1 = myConfig.jack();
        User jack2 = myConfig.jack();
        System.out.println(jack1 == jack2);

        Pet pet = context.getBean("miXian", Pet.class);
        System.out.println("解决组件依赖：" + (jack1.getPet() == pet));

        /**
         * 使用Import往容器中注册组件
         */
        String[] users = context.getBeanNamesForType(User.class);
        for (String user : users) {
            System.out.println(user);
        }
        DBHelper dbHelper = context.getBean(DBHelper.class);
        System.out.println(dbHelper);

        /**
         * 通过@ConfigurationProperties注解给容器注册组件，并赋值
         */
        Car car = context.getBean(Car.class);
        System.out.println(car);

        Person person = context.getBean(Person.class);
        System.out.println(person);


    }

    private static long getFolderSize(File folder) {
        long size = 0;
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += getFolderSize(file);
                }
            }
        }
        return size;
    }
}
