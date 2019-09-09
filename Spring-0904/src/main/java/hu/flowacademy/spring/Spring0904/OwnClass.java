package hu.flowacademy.spring.Spring0904;

import java.util.concurrent.atomic.AtomicInteger;

public class OwnClass {
    private Integer id;
    private String name;
    private int value;
    //private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public OwnClass(Integer id, String name, int value) {
    //    this.id = atomicInteger.incrementAndGet();
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
