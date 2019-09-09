package hu.flowacademy.spring.Spring0904;

import java.util.ArrayList;
import java.util.List;

public class MyMemoService {
    private List<String> memoList = new ArrayList<>();

    public void store(String memo) {
        memoList.add(memo);
    }

    public List list() {
        return this.memoList;
    }
}
